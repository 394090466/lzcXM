package com.lzc.liu.lzcproject.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;

import com.lzc.liu.lzcproject.view.activity.MainActivity;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;

/**
 * Created by liu on 2018/2/24.
 */

public class MainFragmentAdapter implements IndicatorViewPager.IndicatorPagerAdapter {
    public MainFragmentAdapter(FragmentManager supportFragmentManager, MainActivity mainActivity, Object p2) {
    }

    @Override
    public PagerAdapter getPagerAdapter() {
        return null;
    }

    @Override
    public Indicator.IndicatorAdapter getIndicatorAdapter() {
        return null;
    }

    @Override
    public void notifyDataSetChanged() {

    }
}
