package com.lzc.liu.lzcproject.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.view.activity.MainActivity;
import com.shizhefei.view.indicator.IndicatorViewPager;

import java.util.ArrayList;

/**
 * Created by liu on 2018/2/24.
 */

public class MainFragmentAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

    private LayoutInflater inflater;

    private ArrayList<Fragment> fragmentlist = new ArrayList<>();

    private String[] tabename = {"首页","直播","天气","我"};

    private int[] tabIcons = {R.drawable.selector_home_icon,  R.drawable.selector_live_icon,R.drawable.selector_weather_icon,
            R.drawable.selector_me_icon};

    private Context context;

    public MainFragmentAdapter(FragmentManager supportFragmentManager, MainActivity mainActivity, ArrayList<Fragment> fragmentlist) {
        super(supportFragmentManager);
        inflater = LayoutInflater.from(mainActivity);
        this.fragmentlist = fragmentlist;
        this.context = mainActivity;
    }

    @Override
    public int getCount() {
        return fragmentlist.size();
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_main_tabeicon, container, false);
        }
        TextView textView = (TextView) convertView;
        textView.setText(tabename[position]);
        Drawable drawable= this.context.getResources().getDrawable(tabIcons[position]);
        drawable.setBounds(0, ConvertUtils.px2dp(-3), ConvertUtils.px2dp(100), ConvertUtils.px2dp(100));
        textView.setCompoundDrawables(null,drawable,null,null);
        return textView;
    }

    @Override
    public Fragment getFragmentForPage(int position) {
        return fragmentlist.get(position);
    }
}
