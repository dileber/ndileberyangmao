package com.zhonghua.shidawei.nhaoyang.data.source;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.BaseRepository;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.tools.AndroidCrash;
import com.drcosu.ndileber.tools.HJson;
import com.drcosu.ndileber.utils.Check;
import com.orhanobut.logger.Logger;
import com.zhonghua.shidawei.nhaoyang.data.model.ArtModel;
import com.zhonghua.shidawei.nhaoyang.data.model.ArtWrapper;
import com.zhonghua.shidawei.nhaoyang.data.model.PageWrapper;
import com.zhonghua.shidawei.nhaoyang.data.source.local.ArticleLocalDataSource;
import com.zhonghua.shidawei.nhaoyang.data.source.remote.ArticleRemoteDataSource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import bolts.Task;

/**
 * Created by shidawei on 16/8/14.
 */
public class ArticleRepository extends BaseRepository<ArticleLocalDataSource,ArticleRemoteDataSource> implements ArticleDataSource{

    Map<Integer, ArtModel> mCachedTasks;

    /**
     * 将缓存标记为无效的，以强制更新下一次数据的请求。这个变量有包本地的可见性，所以它可以从测试中访问。
     */
    boolean mCacheIsDirty = false;

    boolean mAdd = false;

    private ArticleRepository(ArticleLocalDataSource localDataSource, ArticleRemoteDataSource remoteDataSource) {
        super(localDataSource, remoteDataSource);
    }

    public static volatile ArticleRepository instance;

    public static ArticleRepository getInstance(){
        if(instance==null){
            synchronized (UserRepository.class){
                if(instance==null){
                    instance = new ArticleRepository(ArticleLocalDataSource.getInstance(),ArticleRemoteDataSource.getInstance());
                }
            }
        }
        return instance;
    }


    @Override
    public void getHomeList(final PageWrapper pageWrapper, final BaseCallback<ArtWrapper> wrappers) {
        Check.checkNotNull(wrappers);
        if (mCachedTasks != null && !mCacheIsDirty) {
            wrappers.onSuccess(getCacheWrapper());
            return;
        }

        if (mCacheIsDirty) {
            getArticleFromRemoteDataSource(pageWrapper,wrappers);
        } else {
            localDataSource.getHomeList(pageWrapper, new BaseCallback<ArtWrapper>() {
                @Override
                public void onSuccess(ArtWrapper artWrapper) {
                    refreshCache(artWrapper.getData());
                    wrappers.onSuccess(artWrapper);
                }

                @Override
                public void onFailure(DataSourceException e) {
                    Logger.d("本地没有数据:");
                    getArticleFromRemoteDataSource(pageWrapper,wrappers);
                }
            });

        }

    }

    private ArtWrapper getCacheWrapper(){
        List<ArtModel> list = new ArrayList<>(mCachedTasks.values());
        Logger.d("获取本地缓存:"+list.size());
        ArtWrapper artWrapper = new ArtWrapper();
        artWrapper.setData(list);
        artWrapper.setState(0);
        return artWrapper;
    }

    private void getArticleFromRemoteDataSource(PageWrapper pageWrapper, final BaseCallback<ArtWrapper> wrappers) {
        remoteDataSource.getHomeList(pageWrapper, new BaseCallback<ArtWrapper>() {
            @Override
            public void onSuccess(ArtWrapper artWrapper) {
                if(artWrapper.getState()==1){
                    List<ArtModel> artModels = artWrapper.getData();
                    Logger.d("网络数据:"+artModels.size());
                    if(mAdd){
                        addCache(artModels);
                        addLocalDataSource(artModels);
                    }else{
                        refreshCache(artModels);
                        refreshLocalDataSource(artModels);
                    }
                    wrappers.onSuccess(getCacheWrapper());
                }else{
                    wrappers.onFailure(new DataSourceException(artWrapper.getMsg()));
                }
            }

            @Override
            public void onFailure(DataSourceException e) {
                wrappers.onFailure(e);
            }
        });
    }

    private void refreshCache(List<ArtModel> artModels) {
        if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }
        mCachedTasks.clear();
        for (ArtModel art : artModels) {
            mCachedTasks.put(art.getId(), art);
        }
        Logger.d("刷新本地缓存:"+mCachedTasks.size());
        mCacheIsDirty = false;
    }

    private void addCache(List<ArtModel> artModels){
        if(mCachedTasks == null){
            mCachedTasks = new LinkedHashMap<>();
        }
        for (ArtModel art : artModels) {
            mCachedTasks.put(art.getId(), art);
        }
        Logger.d("刷新本地缓存:"+mCachedTasks.size());
        mAdd = false;
    }

    private void refreshLocalDataSource(List<ArtModel> artModels) {
        Logger.d("对本地数据库操作:");
        localDataSource.deleteAllArticle();
        localDataSource.saveArticles(artModels);
    }

    private void addLocalDataSource(List<ArtModel> artModels) {
        Logger.d("对本地数据库操作:");
        localDataSource.saveArticles(artModels);
    }

    @Override
    public void refreshArticle() {
        mCacheIsDirty = true;
    }

    public void addArticle(){
        mAdd = true;
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

    public interface ArticleRepositoryObserver {

        void onArticleChanged();

    }

}
