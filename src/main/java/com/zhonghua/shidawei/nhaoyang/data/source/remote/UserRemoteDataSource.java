package com.zhonghua.shidawei.nhaoyang.data.source.remote;

import android.support.annotation.NonNull;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.data.source.remote.BaseRemoteDataSource;
import com.drcosu.ndileber.tools.HRetrofit;
import com.drcosu.ndileber.tools.net.RetCallback;
import com.orhanobut.logger.Logger;
import com.zhonghua.shidawei.nhaoyang.app.Configer;
import com.zhonghua.shidawei.nhaoyang.data.model.UserModel;
import com.zhonghua.shidawei.nhaoyang.data.model.UserWrapper;
import com.zhonghua.shidawei.nhaoyang.data.source.UserDataSource;
import com.zhonghua.shidawei.nhaoyang.data.source.service.TaskService;
import com.zhonghua.shidawei.nhaoyang.data.source.service.UserService;
import com.zhonghua.shidawei.nhaoyang.data.source.service.wrappersss;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shidawei on 16/8/6.
 */
public class UserRemoteDataSource extends BaseRemoteDataSource implements UserDataSource{

    private static volatile UserRemoteDataSource instance;

    HRetrofit hRetrofit;
    UserService userService;

    private UserRemoteDataSource(){
        hRetrofit = HRetrofit.getInstence(Configer.HTTP_URL);
        userService = hRetrofit.retrofit.create(UserService.class);
    }


    public static UserRemoteDataSource getInstance(){
        if (instance==null){
            synchronized (UserRemoteDataSource.class){
                if(instance==null){
                    instance = new UserRemoteDataSource();
                }
            }
        }
        return instance;
    }

    @Override
    public void checkUser(@NonNull UserModel userModel, final BaseCallback<UserWrapper> callback) {


        Call<UserWrapper> call = userService.login(userModel.getLoginname(),userModel.getUserpass(),Configer.app_type);

        call.enqueue(new RetCallback<UserWrapper>() {
            @Override
            protected void onSuccess(Call<UserWrapper> call, Response<UserWrapper> response) {
                UserWrapper userWrapper = response.body();
                if(userWrapper!=null){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new DataSourceException("数据返回为空"));
                }
            }

            @Override
            protected void failure(Call<UserWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException(throwable.getMessage()));
            }
        });
    }

    @Override
    public void saveUser(@NonNull UserModel userModel) {

    }

    @Override
    public void getUser(BaseCallback<UserModel> callback) {

    }

    @Override
    public void clearUser() {

    }
}
