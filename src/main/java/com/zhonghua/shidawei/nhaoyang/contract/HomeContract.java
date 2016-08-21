package com.zhonghua.shidawei.nhaoyang.contract;

import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BaseView;
import com.zhonghua.shidawei.nhaoyang.data.model.ArtModel;

import java.util.List;

/**
 * Created by shidawei on 16/8/20.
 */
public interface HomeContract {

    interface View extends BaseView<Presenter>{
        void setLoadingIndicator(boolean active);

        void showArticle(List<ArtModel> artModelList);

        boolean isActive();
    }

    interface Presenter extends BasePresenter{
        void loadArticle(boolean forceUpdate);
    }

}
