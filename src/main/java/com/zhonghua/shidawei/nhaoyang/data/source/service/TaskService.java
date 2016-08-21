package com.zhonghua.shidawei.nhaoyang.data.source.service;

import com.zhonghua.shidawei.nhaoyang.data.model.UserWrapper;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by shidawei on 16/8/5.
 */
public interface TaskService {

    @GET("/api/viewpoint/v1/show_viewpoints")
    Call<wrappersss> wrapper();


    @GET("api/mizi/login")
    Call<UserWrapper> login();

}
