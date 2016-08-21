package com.zhonghua.shidawei.nhaoyang.data.source.remote;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.data.source.remote.BaseRemoteDataSource;
import com.drcosu.ndileber.tools.HJson;
import com.drcosu.ndileber.tools.HRetrofit;
import com.drcosu.ndileber.tools.net.RetCallback;
import com.drcosu.ndileber.tools.net.RetLog;
import com.orhanobut.logger.Logger;
import com.zhonghua.shidawei.nhaoyang.app.Configer;
import com.zhonghua.shidawei.nhaoyang.data.model.ArtModel;
import com.zhonghua.shidawei.nhaoyang.data.model.ArtWrapper;
import com.zhonghua.shidawei.nhaoyang.data.model.PageWrapper;
import com.zhonghua.shidawei.nhaoyang.data.model.UserWrapper;
import com.zhonghua.shidawei.nhaoyang.data.source.ArticleDataSource;
import com.zhonghua.shidawei.nhaoyang.data.source.service.ArticleService;
import com.zhonghua.shidawei.nhaoyang.data.source.service.UserService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by shidawei on 16/8/14.
 */
public class ArticleRemoteDataSource extends BaseRemoteDataSource implements ArticleDataSource {

    private static volatile ArticleRemoteDataSource instance;

    HRetrofit hRetrofit;
    ArticleService articleService;

    private ArticleRemoteDataSource(){
        hRetrofit = HRetrofit.getInstence(Configer.HTTP_URL);
        articleService = hRetrofit.retrofit.create(ArticleService.class);
    }


    public static ArticleRemoteDataSource getInstance(){
        if (instance==null){
            synchronized (ArticleRemoteDataSource.class){
                if(instance==null){
                    instance = new ArticleRemoteDataSource();
                }
            }
        }
        return instance;
    }

    @Override
    public void getHomeList(PageWrapper pageWrapper, final BaseCallback<ArtWrapper> wrappers) {
        Call<ArtWrapper> call =  articleService.getHomeList(pageWrapper.getPage(),pageWrapper.getCount(),pageWrapper.getLike());
        call.enqueue(new RetCallback<ArtWrapper>() {
            @Override
            protected void onSuccess(Call<ArtWrapper> call, Response<ArtWrapper> response) {
                Logger.d("测试请求"+ HJson.toJson(response));
                wrappers.onSuccess(response.body());
            }

            @Override
            protected void failure(Call<ArtWrapper> call, Throwable throwable) {
                wrappers.onFailure(new DataSourceException("我还没想出来"));
            }
        });


    }

    @Override
    public void refreshArticle() {

    }

    @Override
    public void saveArticle(ArtModel artModel) {

    }

    @Override
    public void saveArticles(List<ArtModel> artModel) {

    }

    @Override
    public void deleteAllArticle() {

    }
}
