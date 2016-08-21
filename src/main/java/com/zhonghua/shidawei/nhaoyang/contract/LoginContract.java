package com.zhonghua.shidawei.nhaoyang.contract;

import com.drcosu.ndileber.mvp.acivity.BaseActivity;
import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BaseView;
import com.zhonghua.shidawei.nhaoyang.data.model.UserModel;

/**
 * Created by shidawei on 16/8/6.
 */
public interface LoginContract {
    interface View extends BaseView<Presenter>{

        void toActivity(Class clazz);

        UserModel getUser();

    }
    interface Presenter extends BasePresenter{

        void checkUser();

        void getUser();

    }
}
