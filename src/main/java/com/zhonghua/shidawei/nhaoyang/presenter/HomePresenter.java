package com.zhonghua.shidawei.nhaoyang.presenter;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.tools.UDialog;
import com.drcosu.ndileber.utils.Check;
import com.zhonghua.shidawei.nhaoyang.contract.HomeContract;
import com.zhonghua.shidawei.nhaoyang.data.model.ArtModel;
import com.zhonghua.shidawei.nhaoyang.data.model.ArtWrapper;
import com.zhonghua.shidawei.nhaoyang.data.model.PageWrapper;
import com.zhonghua.shidawei.nhaoyang.data.source.ArticleRepository;

import java.util.List;

/**
 * Created by shidawei on 16/8/20.
 */
public class HomePresenter implements HomeContract.Presenter{

    private boolean mFirstLoad = true;

    private final ArticleRepository mArticleRepository;

    private final HomeContract.View mHomeView;

    public HomePresenter(@NonNull ArticleRepository articleRepository, @NonNull HomeContract.View homeView) {
        mArticleRepository = Check.checkNotNull(articleRepository, "tasksRepository cannot be null");
        mHomeView = Check.checkNotNull(homeView, "tasksView cannot be null!");
        mHomeView.setPresenter(this);
    }


    @Override
    public void loadArticle(boolean forceUpdate) {
        loadArticle(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    private void loadArticle(boolean forceUpdate, final boolean showLoadingUI){
        if (showLoadingUI) {
            mHomeView.setLoadingIndicator(true);
        }
        if (forceUpdate) {
            mArticleRepository.refreshArticle();
        }

        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setPage(1);
        pageWrapper.setCount(2);
        mArticleRepository.getHomeList(pageWrapper, new BaseDataSource.BaseCallback<ArtWrapper>() {
            @Override
            public void onSuccess(ArtWrapper artWrapper) {
                if (!mHomeView.isActive()) {
                    return;
                }
                if (showLoadingUI) {
                    mHomeView.setLoadingIndicator(false);
                }
                processArt(artWrapper.getData());
            }

            @Override
            public void onFailure(DataSourceException e) {
                if (!mHomeView.isActive()) {
                    return;
                }
                if (showLoadingUI) {
                    mHomeView.setLoadingIndicator(false);
                }
                mHomeView.showAlert(UDialog.DIALOG_ERROR,e.getMessage());
            }
        });

    }

    private void processArt(List<ArtModel> artModels) {
        if (artModels.isEmpty()) {
            // Show a message indicating there are no tasks for that filter type.
            processEmptyArt();
        } else {
            mHomeView.showArticle(artModels);
        }
    }

    private void processEmptyArt() {
        mHomeView.toast("数据已经没有了", Toast.LENGTH_SHORT);
    }


    @Override
    public void start() {
        loadArticle(false);
    }
}
