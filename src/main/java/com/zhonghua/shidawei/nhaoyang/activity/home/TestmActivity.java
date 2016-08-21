package com.zhonghua.shidawei.nhaoyang.activity.home;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.drcosu.ndileber.mvp.acivity.BaseActivity;
import com.zhonghua.shidawei.nhaoyang.R;

public class TestmActivity extends BaseActivity {

    SwipeRefreshLayout mSwipeRefreshWidget;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testm);
        mSwipeRefreshWidget = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        mRecyclerView = (RecyclerView) findViewById(android.R.id.list);



    }

}
