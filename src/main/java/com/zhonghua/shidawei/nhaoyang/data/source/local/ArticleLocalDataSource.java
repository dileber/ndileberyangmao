package com.zhonghua.shidawei.nhaoyang.data.source.local;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.data.source.local.BaseLocalDataSource;
import com.drcosu.ndileber.mvp.data.source.local.DBManager;
import com.orhanobut.logger.Logger;
import com.zhonghua.shidawei.nhaoyang.data.model.ArtModel;
import com.zhonghua.shidawei.nhaoyang.data.model.ArtWrapper;
import com.zhonghua.shidawei.nhaoyang.data.model.PageWrapper;
import com.zhonghua.shidawei.nhaoyang.data.model.UserModel;
import com.zhonghua.shidawei.nhaoyang.data.model.local.ArtLocModel;
import com.zhonghua.shidawei.nhaoyang.data.source.ArticleDataSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shidawei on 16/8/14.
 */
public class ArticleLocalDataSource extends BaseLocalDataSource implements ArticleDataSource {

    String article = "CREATE TABLE article (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,artid INTEGER,title TEXT,content TEXT,userId INTEGER,userName TEXT,userImage TEXT,time TEXT)";


    private static volatile ArticleLocalDataSource instance;

    private ArticleLocalDataSource(){
        List<String> list = new ArrayList<>();
        list.add(article);
        dbManager = dbManager.getDB("article",2,list);
    }

    public static ArticleLocalDataSource getInstance(){
        if (instance==null){
            synchronized (ArticleLocalDataSource.class){
                if(instance==null){
                    instance = new ArticleLocalDataSource();
                }
            }
        }
        return instance;
    }


    @Override
    public void getHomeList(PageWrapper pageWrapper, BaseCallback<ArtWrapper> wrappers) {
        try {
            List<ArtLocModel> artLocModels = dbManager.queryData2T("select * from article",null,ArtLocModel.class);
            if(artLocModels==null||artLocModels.size()==0){
                wrappers.onFailure(new DataSourceException("数据为空"));
                Logger.d("数据为空");
                return;
            }
            wrappers.onSuccess(ArtLocModel.getArtWrapper(artLocModels));
        } catch (Exception e) {
            e.printStackTrace();
            wrappers.onFailure(new DataSourceException("数据库错误"));
            Logger.d("数据库错误");
        }
    }

    @Override
    public void refreshArticle() {

    }

    @Override
    public void saveArticle(ArtModel artModel) {

    }

    @Override
    public void saveArticles(List<ArtModel> artModels) {
        List<Object[]> objects = new ArrayList<>();
        for(ArtModel art:artModels){
            UserModel userModel = art.getUser();
            Object[] ob = {art.getId(),art.getTitle(),art.getContent(),art.getUserid(),userModel.getUsername(),userModel.getUserimage(),String.valueOf(art.getTime().getTime())};
            objects.add(ob);
        }
        int m = dbManager.dataBatch("insert into article(artid,title,content,userId,userName,userImage,time) values(?,?,?,?,?,?,?)",objects, DBManager.Type.INSERT);
        Logger.d(objects.size()+"条数据"+m);
    }

    @Override
    public void deleteAllArticle() {
        try {
            dbManager.deleteDataBySql("delete from article",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
