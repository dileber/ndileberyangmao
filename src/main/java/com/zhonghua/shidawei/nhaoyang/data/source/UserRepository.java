package com.zhonghua.shidawei.nhaoyang.data.source;

import android.support.annotation.NonNull;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.zhonghua.shidawei.nhaoyang.data.model.UserModel;
import com.zhonghua.shidawei.nhaoyang.data.model.UserWrapper;
import com.zhonghua.shidawei.nhaoyang.data.source.local.UserLocalDataSource;
import com.zhonghua.shidawei.nhaoyang.data.source.remote.UserRemoteDataSource;

/**
 * Created by shidawei on 16/8/7.
 */
public class UserRepository implements UserDataSource{

    public static volatile UserRepository instance;

    private final UserDataSource userLocalDataSource;

    private final UserDataSource userRemoteDataSource;

    private UserRepository(UserDataSource userRemoteDataSource, UserDataSource userLocalDataSource) {
        this.userRemoteDataSource = userRemoteDataSource;
        this.userLocalDataSource = userLocalDataSource;
    }

    public static UserRepository getInstance(){
        if(instance==null){
            synchronized (UserRepository.class){
                if(instance==null){
                    instance = new UserRepository(UserRemoteDataSource.getInstance(),UserLocalDataSource.getInstance());
                }
            }
        }
        return instance;
    }

    @Override
    public void checkUser(@NonNull UserModel userModel, BaseCallback<UserWrapper> callback) {
        userRemoteDataSource.checkUser(userModel,callback);
    }

    @Override
    public void saveUser(@NonNull UserModel userModel) {
        userLocalDataSource.saveUser(userModel);
    }

    @Override
    public void getUser(BaseCallback<UserModel> callback) {
        userLocalDataSource.getUser(callback);
    }

    @Override
    public void clearUser() {
        userLocalDataSource.clearUser();
    }
}
