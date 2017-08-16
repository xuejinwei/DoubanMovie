package com.xuejinwei.doubanmovie;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.xuejinwei.doubanmovie.base.BaseActivity;
import com.xuejinwei.doubanmovie.ui.fragment.InTheatersFragment;
import com.xuejinwei.doubanmovie.ui.helper.TabPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {


    private Toolbar   mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        List<String> titles = new ArrayList<>();
        titles.add(getString(R.string.in_theaters));
        titles.add(getString(R.string.coming_soon));
        fragments = new ArrayList<>();
        fragments.add(new InTheatersFragment());
        fragments.add(new InTheatersFragment());

        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), titles, fragments);

        mViewPager.setAdapter(adapter);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
    }
}
