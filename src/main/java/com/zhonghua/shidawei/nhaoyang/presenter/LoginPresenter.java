package com.zhonghua.shidawei.nhaoyang.presenter;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.tools.UDialog;
import com.drcosu.ndileber.tools.UText;
import com.zhonghua.shidawei.nhaoyang.activity.Home2Activity;
import com.zhonghua.shidawei.nhaoyang.activity.HomeActivity;
import com.zhonghua.shidawei.nhaoyang.app.AppHper;
import com.zhonghua.shidawei.nhaoyang.contract.LoginContract;
import com.zhonghua.shidawei.nhaoyang.data.model.UserModel;
import com.zhonghua.shidawei.nhaoyang.data.model.UserWrapper;
import com.zhonghua.shidawei.nhaoyang.data.source.UserDataSource;

/**
 * Created by shidawei on 16/8/6.
 */
public class LoginPresenter implements LoginContract.Presenter{

    LoginContract.View mView;
    UserDataSource mUserDataSource;

    public LoginPresenter(@NonNull LoginContract.View view, @NonNull UserDataSource userDataSource){
        mView = view;
        mUserDataSource = userDataSource;
        mView.setPresenter(this);
    }

    @Override
    public void checkUser() {
        final UserModel userModel = mView.getUser();
        if(userModel==null){
            mView.toast("用户为空", Toast.LENGTH_SHORT);
        }
        final String userName = userModel.getLoginname();
        final String pass = userModel.getUserpass();
        if(!UText.checkEditText(userName,6)||!UText.checkEditText(pass,6)){
            mView.toast("账号或密码不能小于6位数", Toast.LENGTH_SHORT);
            return;
        }
        mView.loading();
        mUserDataSource.checkUser(userModel, new BaseDataSource.BaseCallback<UserWrapper>() {
            @Override
            public void onSuccess(UserWrapper userWrapper) {
                if(userWrapper.getState()==1){
                    UserModel userModel1 = userWrapper.getData();
                    if(userModel1==null){
                        mView.showAlert(UDialog.DIALOG_ERROR,"用户异常");
                    }else{
                        mUserDataSource.saveUser(userModel1);
                        toHome(userModel1);
                    }

                }else if(userWrapper.getState()==-1){
                    mView.showAlert(UDialog.DIALOG_ERROR,userWrapper.getMsg());
                }

                mView.loadDialogDismiss();
            }

            @Override
            public void onFailure(DataSourceException e) {
                mView.showAlert(UDialog.DIALOG_ERROR,e.getMessage());
                mView.loadDialogDismiss();
            }
        });

    }

    @Override
    public void getUser() {
        mUserDataSource.getUser(new BaseDataSource.BaseCallback<UserModel>() {
            @Override
            public void onSuccess(UserModel userModel) {
                toHome(userModel);
            }

            @Override
            public void onFailure(DataSourceException e) {

            }
        });
    }

    private void toHome(UserModel userModel){
        mView.toActivity(HomeActivity.class);
        mView.toast("欢迎" + userModel.getUsername(), Toast.LENGTH_SHORT);
    }

    @Override
    public void start() {

    }
}
