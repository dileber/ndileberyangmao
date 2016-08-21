package com.zhonghua.shidawei.nhaoyang.data.model;

/**
 * Created by shidawei on 16/8/14.
 */
public class PageWrapper {

    Integer page;
    Integer count;
    String like;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }
}
