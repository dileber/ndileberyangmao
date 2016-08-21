package com.zhonghua.shidawei.nhaoyang.data.source.local;

import android.support.annotation.NonNull;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.data.source.local.BaseLocalDataSource;
import com.drcosu.ndileber.tools.HJson;
import com.zhonghua.shidawei.nhaoyang.app.Configer;
import com.zhonghua.shidawei.nhaoyang.data.model.UserModel;
import com.zhonghua.shidawei.nhaoyang.data.model.UserWrapper;
import com.zhonghua.shidawei.nhaoyang.data.source.UserDataSource;

/**
 * Created by shidawei on 16/8/6.
 */
public class UserLocalDataSource extends BaseLocalDataSource implements UserDataSource{

    private static volatile UserLocalDataSource instance;

    private UserLocalDataSource(){}

    public static UserLocalDataSource getInstance(){
        if (instance==null){
            synchronized (UserLocalDataSource.class){
                if(instance==null){
                    instance = new UserLocalDataSource();
                }
            }
        }
        return instance;
    }

    @Override
    public void checkUser(@NonNull UserModel userModel, BaseCallback<UserWrapper> callback) {

    }

    @Override
    public void saveUser(@NonNull UserModel userModel) {
        hPref.put(Configer.PERF_APP,Configer.PERF_USER, HJson.toJson(userModel));
    }

    @Override
    public void getUser(BaseCallback<UserModel> callback) {
        String user = hPref.get(Configer.PERF_APP,Configer.PERF_USER,null,String.class);
        if(user!=null){
            callback.onSuccess(HJson.fromJson(user,UserModel.class));
        }else{
            callback.onFailure(new DataSourceException("您尚未登录"));
        }
    }

    @Override
    public void clearUser() {
        hPref.remove(Configer.PERF_APP, Configer.PERF_USER);
    }

}
