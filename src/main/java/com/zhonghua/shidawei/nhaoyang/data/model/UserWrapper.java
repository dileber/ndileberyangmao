package com.zhonghua.shidawei.nhaoyang.data.model;

import com.drcosu.ndileber.mvp.data.model.SWrapper;


/**
 * Created by shidawei on 16/3/15.
 */
public class UserWrapper extends SWrapper {

    UserModel data;

    public UserModel getData() {
        return data;
    }

    public void setData(UserModel data) {
        this.data = data;
    }
}
