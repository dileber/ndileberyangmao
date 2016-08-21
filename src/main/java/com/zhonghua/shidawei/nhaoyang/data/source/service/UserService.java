package com.zhonghua.shidawei.nhaoyang.data.source.service;

import android.content.Intent;

import com.zhonghua.shidawei.nhaoyang.data.model.UserWrapper;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by shidawei on 16/8/7.
 */
public interface UserService {
    /**
     * 登录
     */
    public static final String ADD_LOGIN = "api/mizi/login";


    @GET(ADD_LOGIN)
    Call<UserWrapper> login(@Query("loginname")  String loginname, @Query("userpass")  String userpass, @Query("apptype")   Integer apptype);

}
