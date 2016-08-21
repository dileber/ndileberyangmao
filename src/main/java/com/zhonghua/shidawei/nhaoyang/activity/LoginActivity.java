package com.zhonghua.shidawei.nhaoyang.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.drcosu.ndileber.mvp.acivity.BaseActivity;
import com.drcosu.ndileber.tools.DialogLinstener;
import com.drcosu.ndileber.tools.HRetrofit;
import com.drcosu.ndileber.tools.UDialog;
import com.drcosu.ndileber.tools.UText;
import com.drcosu.ndileber.tools.UUi;
import com.drcosu.ndileber.tools.annotation.CloseTitle;
import com.drcosu.ndileber.tools.net.RetCallback;
import com.orhanobut.logger.Logger;
import com.zhonghua.shidawei.nhaoyang.R;
import com.zhonghua.shidawei.nhaoyang.contract.LoginContract;
import com.zhonghua.shidawei.nhaoyang.data.model.UserModel;
import com.zhonghua.shidawei.nhaoyang.data.source.UserRepository;
import com.zhonghua.shidawei.nhaoyang.data.source.service.TaskService;
import com.zhonghua.shidawei.nhaoyang.data.source.service.wrappersss;
import com.zhonghua.shidawei.nhaoyang.presenter.LoginPresenter;

import retrofit2.Call;
import retrofit2.Response;

@CloseTitle
public class LoginActivity extends BaseActivity implements LoginContract.View,View.OnClickListener{

    LoginContract.Presenter mPresenter;
    EditText login_username,login_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        new LoginPresenter(this, UserRepository.getInstance());
        login_username = getView(R.id.login_username);
        textChange();
        login_pass = getView(R.id.login_pass);
        getView(R.id.login_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.checkUser();
            }
        });
        UUi.setOnClickListener(this,getView(R.id.login_open),getView(R.id.login_regist));

    }

    @Override
    public void toActivity(Class clazz) {
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this,clazz);
        startActivity(intent);
        finish();
    }

    @Override
    public UserModel getUser() {
        UserModel userModel = new UserModel();
        userModel.setLoginname(login_username.getText().toString().trim());
        userModel.setUserpass(login_pass.getText().toString().trim());
        return userModel;
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void toast(String msg, int duration) {
        UUi.toast(this,msg,duration);
    }

    @Override
    public void showAlert(Integer type, String message) {
        UDialog.alert(type,message).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getUser();
    }
    Dialog dialog;


    @Override
    public void loading() {
        if(dialog==null){
            dialog =UDialog.loading();
        }
        dialog.show();
    }

    @Override
    public void loadDialogDismiss() {
        if(dialog!=null){
            dialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(dialog!=null){
            dialog.dismiss();
        }
    }

    @Override
    public void dialogOk(String content, DialogLinstener dialogLinstener) {

    }
    private void textChange(){
        login_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                UText.getWatcher(editable,UText.PATTERN_N_A_Z);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_open:
                toActivity( HomeActivity.class);
                break;
            case R.id.login_regist:
                toActivity( RegisterActivity.class);
                break;
        }

    }

}
