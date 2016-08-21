package com.zhonghua.shidawei.nhaoyang.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.drcosu.ndileber.mvp.acivity.BaseActivity;
import com.drcosu.ndileber.mvp.fragment.CarouselFragment;
import com.drcosu.ndileber.utils.ActivityUtils;
import com.drcosu.ndileber.view.BottomBar;
import com.drcosu.ndileber.view.BottomBarTab;
import com.zhonghua.shidawei.nhaoyang.R;
import com.zhonghua.shidawei.nhaoyang.activity.consult.ConsultFragment;
import com.zhonghua.shidawei.nhaoyang.activity.home.HomeItemFragment;
import com.zhonghua.shidawei.nhaoyang.activity.home.TestmActivity;
import com.zhonghua.shidawei.nhaoyang.activity.home.dummy.ArticleContent;
import com.zhonghua.shidawei.nhaoyang.activity.message.MessageFragment;
import com.zhonghua.shidawei.nhaoyang.activity.setting.SettingFragment;
import com.zhonghua.shidawei.nhaoyang.data.model.ArtModel;
import com.zhonghua.shidawei.nhaoyang.data.source.ArticleRepository;
import com.zhonghua.shidawei.nhaoyang.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements HomeItemFragment.OnListFragmentInteractionListener,MessageFragment.OnFragmentInteractionListener,SettingFragment.OnFragmentInteractionListener,ConsultFragment.OnFragmentInteractionListener {



    private List<Fragment> list = new ArrayList<>();




    BottomBar mBar;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }



        mBar= getView(R.id.bar);
        mBar.addItem(new BottomBarTab(this,R.string.home2,BottomBarTab.TYPE_FONT)).
                addItem(new BottomBarTab(this,R.string.bubbles,BottomBarTab.TYPE_FONT)).
                addItem(new BottomBarTab(this,R.string.newspaper,BottomBarTab.TYPE_FONT)).
                addItem(new BottomBarTab(this,R.string.cogs,BottomBarTab.TYPE_FONT));

        mBar.setOnClickItemMenu(new BottomBar.OnClickItemMenu() {
            @Override
            public void onClickItem(int nowPosition,int position) {
                FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();
                if(position==0){
                    transaction.show(carouselFragment).commit();
                }else{
                    transaction.hide(carouselFragment).commit();
                }
                switchContent(list.get(nowPosition),list.get(position));
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        addMenu(savedInstanceState);

    }

    private final static String SELECT = "select";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECT,mBar.getmCurrentPosition());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    CarouselFragment carouselFragment = null;
    private HomePresenter mHomePresenter;

    /**
     *  官方的方法是使用replace()来替换Fragment，
     *  但是replace()的调用会导致Fragment的onCreteView()被调用，
     *  所以切换界面时会无法保存当前的状态。因此一般采用add()、hide()与show()配合，来达到保存Fragment的状态。以下为代码片段：
     * 添加菜单
     */
    private void addMenu(Bundle savedInstanceState){
        HomeItemFragment homeItemFragment = ActivityUtils.getFragment(getSupportFragmentManager(),R.id.homeFrame,HomeItemFragment.newInstance());
        MessageFragment messageFragment = ActivityUtils.getFragment(getSupportFragmentManager(),R.id.messageFrame,MessageFragment.newInstance("1","2"));
        ConsultFragment consultFragment = ActivityUtils.getFragment(getSupportFragmentManager(),R.id.consultFrame,ConsultFragment.newInstance("2","3"));
        SettingFragment settingFragment = ActivityUtils.getFragment(getSupportFragmentManager(),R.id.settingFrame,SettingFragment.newInstance("4","5"));

        mHomePresenter = new HomePresenter(ArticleRepository.getInstance(),homeItemFragment);

        list.add(homeItemFragment);
        list.add(messageFragment);
        list.add(consultFragment);
        list.add(settingFragment);
        ArrayList<String> url = new ArrayList<String>();
        url.add("http://7xj92l.com1.z0.glb.clouddn.com/image_th.jpg");
        url.add("http://7xj92l.com1.z0.glb.clouddn.com/image_th1.jpg");
        url.add("http://7xj92l.com1.z0.glb.clouddn.com/image_th2.jpg");
        url.add("http://7xj92l.com1.z0.glb.clouddn.com/image_th3.jpg");
        url.add("http://7xj92l.com1.z0.glb.clouddn.com/image_th4.jpg");

        carouselFragment = ActivityUtils.getFragment(getSupportFragmentManager(),R.id.home_carousel,CarouselFragment.newInstance(url));

        FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();
        transaction.hide(messageFragment).hide(consultFragment).hide(settingFragment).commit();

        if(savedInstanceState!=null){
            mBar.setCurrentItem(savedInstanceState.getInt(SELECT));
        }

    }

    public void switchContent(Fragment from, Fragment to) {
            FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();
            transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
    }


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    public void onListFragmentInteraction(ArtModel item) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        Intent it = new Intent();
                        it.setClass(HomeActivity.this, TestmActivity.class);
                        startActivity(it);
                        return true;
                    }
                });
    }

}
