package com.zhonghua.shidawei.nhaoyang.data.model;

import com.drcosu.ndileber.mvp.data.model.SWrapper;

import java.util.List;

/**
 * Created by shidawei on 16/3/12.
 */
public class ConsultWrapper extends SWrapper {

    List<ConsultModel> data;

    public List<ConsultModel> getData() {
        return data;
    }

    public void setData(List<ConsultModel> data) {
        this.data = data;
    }
}
