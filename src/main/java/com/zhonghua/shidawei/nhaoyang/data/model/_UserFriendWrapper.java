package com.zhonghua.shidawei.nhaoyang.data.model;


import com.drcosu.ndileber.mvp.data.model.SWrapper;

/**
 * Created by shidawei on 16/3/21.
 */
public class _UserFriendWrapper extends SWrapper {

    UserFriendModel data;

    public UserFriendModel getData() {
        return data;
    }

    public void setData(UserFriendModel data) {
        this.data = data;
    }
}
