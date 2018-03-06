package com.lzc.liu.lzcproject.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.constant.NewData;
import com.lzc.liu.lzcproject.view.fragment.HomeNewFragment;
import com.shizhefei.view.indicator.IndicatorViewPager;

/**
 * Created by liu on 2018/2/26.
 */

public class HomeFragmentAdapter extends  IndicatorViewPager.IndicatorFragmentPagerAdapter{

    private LayoutInflater inflater;

    public  HomeFragmentAdapter(FragmentManager fragmentManager, Activity mactivity) {
        super(fragmentManager);
        inflater = LayoutInflater.from(mactivity);
    }

    @Override
    public int getCount() {
        return NewData.getNewTabList().size();
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_home_tabeicon, container, false);
        }
        TextView textView = (TextView) convertView;
        textView.setText(NewData.getNewTabList().get(position));
        return textView;
    }

    @Override
    public Fragment getFragmentForPage(int position) {
        HomeNewFragment mainFragment = new HomeNewFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(HomeNewFragment.INTENT_INT_INDEX, position);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }
}
