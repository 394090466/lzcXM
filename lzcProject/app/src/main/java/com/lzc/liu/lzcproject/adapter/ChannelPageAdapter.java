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
import com.lzc.liu.lzcproject.entity.douyu.ChannelsEntity;
import com.lzc.liu.lzcproject.view.fragment.ChannelFragment;
import com.shizhefei.view.indicator.IndicatorViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaokun on 2017/8/28.
 */

public class ChannelPageAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
    private List<ChannelsEntity.DataBean> channelEntityList =null;

    private LayoutInflater inflater;

    public ChannelPageAdapter(FragmentManager fm, Activity mactivity) {
        super(fm);
        if (channelEntityList == null){
            channelEntityList = new ArrayList<>();
        }
        inflater = LayoutInflater.from(mactivity);
    }

    public void updateData(List<ChannelsEntity.DataBean> channelEntityList) {
        this.channelEntityList = channelEntityList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.channelEntityList.size() + 1;
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        String name = null;
        if (position == 0) {
            name = "首页";
        }else {
            name = channelEntityList.get(position - 1).getGame_name();
        }

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_home_tabeicon, container, false);
        }
        TextView textView = (TextView) convertView;
        textView.setText(name);

        return textView;
    }

    @Override
    public Fragment getFragmentForPage(int position) {
        ChannelFragment channelFragment = new ChannelFragment();
        Bundle bundle = new Bundle();
        if (position == 0) {
            bundle.putString(ChannelFragment.CATE_ID, "0");
        } else {
            bundle.putString(ChannelFragment.CATE_ID, channelEntityList.get(position - 1).getCate_id());
        }
        channelFragment.setArguments(bundle);
        return channelFragment;
    }


}
