package com.zhonghua.shidawei.nhaoyang.app;

import com.drcosu.ndileber.app.SApplication;
import com.drcosu.ndileber.app.ThreadExecutor;
import com.drcosu.ndileber.tools.annotation.SFontdType;

/**
 * Created by shidawei on 16/8/5.
 */
@SFontdType(value = "icomoon.ttf")
public class MyApplication extends SApplication{

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void quit() {
        super.quit();
        ThreadExecutor.getInstance().stop();
    }
}
