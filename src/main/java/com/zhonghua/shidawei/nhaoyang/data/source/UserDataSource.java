package com.zhonghua.shidawei.nhaoyang.data.source;

import android.support.annotation.NonNull;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.zhonghua.shidawei.nhaoyang.data.model.UserModel;
import com.zhonghua.shidawei.nhaoyang.data.model.UserWrapper;

/**
 * Created by shidawei on 16/8/6.
 */
public interface UserDataSource extends BaseDataSource{

    void checkUser(@NonNull UserModel userModel, BaseCallback<UserWrapper> callback);

    void saveUser(@NonNull UserModel userModel);

    void getUser(BaseCallback<UserModel> callback);

    void clearUser();

}
