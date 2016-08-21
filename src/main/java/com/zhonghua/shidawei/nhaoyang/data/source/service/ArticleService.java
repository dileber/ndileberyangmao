package com.zhonghua.shidawei.nhaoyang.data.source.service;

import com.zhonghua.shidawei.nhaoyang.data.model.ArtWrapper;
import com.zhonghua.shidawei.nhaoyang.data.model.UserWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by shidawei on 16/8/14.
 */
public interface ArticleService {

    public static final String FIND_ART = "api/mizi/findArt";

    @GET(FIND_ART)
    Call<ArtWrapper> getHomeList(@Query("page")  Integer page, @Query("count")  Integer count, @Query("like")   String like);



}
