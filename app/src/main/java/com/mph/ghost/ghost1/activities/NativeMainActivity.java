package com.mph.ghost.ghost1.activities;

import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.mph.ghost.ghost1.R;
import com.mph.ghost.ghost1.fragments.Frag1;
import com.mph.ghost.ghost1.fragments.Frag2;
import com.mph.ghost.ghost1.fragments.Frag3;
import com.mph.ghost.ghost1.fragments.Frag4;
import com.mph.ghost.ghost1.utils.DoubleClickExitUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NativeMainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.bnb_nativemain_navigator)
    BottomNavigationBar mBnbNativemainNavigator;
    android.support.v4.app.FragmentManager mFragmentManager;
    List<android.support.v4.app.Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        mFragmentManager = getSupportFragmentManager();
        android.support.v4.app.Fragment fragment1 = new Frag1();
        android.support.v4.app.Fragment fragment2 = new Frag2();
        android.support.v4.app.Fragment fragment3 = new Frag3();
        android.support.v4.app.Fragment fragment4 = new Frag4();
        mFragments = new ArrayList<>();
        mFragments.add(fragment1);
        mFragments.add(fragment2);
        mFragments.add(fragment3);
        mFragments.add(fragment4);

        mBnbNativemainNavigator.setTabSelectedListener(this);
        //消息提醒标志
        //        BadgeItem badgeItem = new BadgeItem().setBackgroundColor(Color.RED).setText("99");
        //        mBnbNativemainNavigator.setMode(BottomNavigationBar.MODE_FIXED);
        mBnbNativemainNavigator.setMode(BottomNavigationBar.MODE_SHIFTING);
        mBnbNativemainNavigator.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        mBnbNativemainNavigator.setBarBackgroundColor(R.color.gray);
        mBnbNativemainNavigator.setActiveColor(R.color.white);
        mBnbNativemainNavigator.addItem(new BottomNavigationItem(R.drawable.home, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.home, "音乐"))
                .addItem(new BottomNavigationItem(R.drawable.home, "电影")/*.setBadgeItem(badgeItem)*/)
                .addItem(new BottomNavigationItem(R.drawable.home, "游戏")/*.setActiveColorResource(R.color.white)*/)
                .setFirstSelectedPosition(0)
                .initialise(); //所有的设置需在调用该方法前完成
        mFragmentManager.beginTransaction().replace(R.id.fl_nativemain_content, mFragments.get(0)).commit();


    }

    @Override
    public void onTabSelected(int position) {
        mFragmentManager.beginTransaction().replace(R.id.fl_nativemain_content, mFragments.get(position)).commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onBackPressed() {
        DoubleClickExitUtil.tryExit();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_native_main;
    }

}
