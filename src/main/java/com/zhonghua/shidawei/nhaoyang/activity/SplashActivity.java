package com.zhonghua.shidawei.nhaoyang.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.drcosu.ndileber.app.ThreadExecutor;
import com.drcosu.ndileber.mvp.acivity.BaseActivity;
import com.drcosu.ndileber.tools.DialogLinstener;
import com.drcosu.ndileber.tools.HNetwork;
import com.drcosu.ndileber.tools.UUi;
import com.drcosu.ndileber.tools.annotation.CloseStatusBar;
import com.drcosu.ndileber.tools.annotation.CloseTitle;
import com.zhonghua.shidawei.nhaoyang.R;
import com.zhonghua.shidawei.nhaoyang.contract.SplashContract;

@CloseTitle
@CloseStatusBar
public class SplashActivity extends BaseActivity implements SplashContract.View{

    Splashhandler splashhandler;
    Handler x;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        showSplash();
        if(!HNetwork.getInstance().checkNetwork()){
            toast("网络未连接", Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void showSplash() {

        x = new Handler();
        splashhandler = new Splashhandler();

    }

    class Splashhandler implements Runnable{

        public void run() {
            Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(x!=null&&splashhandler!=null){
            x.removeCallbacks(splashhandler);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(x!=null&&splashhandler!=null){
            x.postDelayed(splashhandler, 3000);
        }
    }


    @Override
    public void setPresenter(Object presenter) {

    }

    @Override
    public void toast(String msg, int duration) {
        UUi.toast(this,msg,duration);
    }

    @Override
    public void showAlert(Integer type, String message) {

    }

    @Override
    public void loading() {

    }

    @Override
    public void loadDialogDismiss() {

    }

    @Override
    public void dialogOk(String content, DialogLinstener dialogLinstener) {

    }
}
