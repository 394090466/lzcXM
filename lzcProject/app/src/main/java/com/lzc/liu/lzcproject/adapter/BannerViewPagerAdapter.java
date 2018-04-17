package com.lzc.liu.lzcproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.entity.douyu.SlidersEntity;
import com.lzc.liu.lzcproject.util.GlideUtils;
import com.shizhefei.view.indicator.IndicatorViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liu on 2018/4/1.
 */

public class BannerViewPagerAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {

    private List<SlidersEntity.DataBean> dataBeanList = null;

    private Context context;

    private LayoutInflater inflater;

    public BannerViewPagerAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.dataBeanList = new ArrayList<>();
    }

    public void updateData(List<SlidersEntity.DataBean> dataBeanList) {
        this.dataBeanList.addAll(dataBeanList);
        notifyDataSetChanged();
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = new View(container.getContext());
        }
        return convertView;
    }

    @Override
    public View getViewForPage(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layer_live_banner, container, false);
        }
        ImageView ivbanner = convertView.findViewById(R.id.iv_live_banneriv);
        TextView tv_banner = convertView.findViewById(R.id.tv_live_title);
//        convertView.setBackgroundResource(images[position]);
        tv_banner.setText(this.dataBeanList.get(position).getTitle());
        GlideUtils.loadImageView(context, this.dataBeanList.get(position).getTv_pic_url(), ivbanner);
        return convertView;
    }

//    @Override
//    public int getItemPosition(Object object) {
//        //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
//        // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
//        return PagerAdapter.POSITION_NONE;
//    }

    @Override
    public int getCount() {
        return dataBeanList.size();
    }
}
