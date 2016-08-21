package com.zhonghua.shidawei.nhaoyang.data.source;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.zhonghua.shidawei.nhaoyang.data.model.ArtModel;
import com.zhonghua.shidawei.nhaoyang.data.model.ArtWrapper;
import com.zhonghua.shidawei.nhaoyang.data.model.PageWrapper;

import java.util.List;

/**
 * Created by shidawei on 16/8/14.
 */
public interface ArticleDataSource extends BaseDataSource {

    void getHomeList(PageWrapper pageWrapper,BaseCallback<ArtWrapper> wrappers);

    void refreshArticle() ;

    void saveArticle(ArtModel artModel);

    void saveArticles(List<ArtModel> artModel);

    void deleteAllArticle();
}
