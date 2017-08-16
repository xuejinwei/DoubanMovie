package com.xuejinwei.doubanmovie.ui.helper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by xuejinwei on 2017/3/20.
 * Email:xuejinwei@outlook.com
 * ViewPager + TabLayout 通用适配器
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private List<String>             mTitles;
    private List<? extends Fragment> mFragment;

    public TabPagerAdapter(FragmentManager fm, List<String> titles, List<Fragment> fragment) {
        super(fm);
        mTitles = titles;
        mFragment = fragment;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position % mTitles.size());
    }
}
