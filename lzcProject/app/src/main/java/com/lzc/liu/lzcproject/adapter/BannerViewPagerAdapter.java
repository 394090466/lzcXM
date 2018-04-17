package com.lzc.liu.lzcproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.entity.douyu.SlidersEntity;
import com.lzc.liu.lzcproject.util.GlideUtils;
import com.lzc.liu.lzcproject.view.activity.LiveActivity;
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
    public View getViewForPage(final int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layer_live_banner, container, false);
        }
        ImageView ivbanner = convertView.findViewById(R.id.iv_live_banneriv);
        TextView tv_banner = convertView.findViewById(R.id.tv_live_title);
        tv_banner.setText(this.dataBeanList.get(position).getTitle());
        GlideUtils.loadImageView(context, this.dataBeanList.get(position).getTv_pic_url(), ivbanner);
        ivbanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LiveActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(LiveActivity.ROOM_TITLE, dataBeanList.get(position).getRoom().getRoom_name());
                bundle.putString(LiveActivity.ROOM_ID, dataBeanList.get(position).getRoom().getRoom_id());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public int getCount() {
        return dataBeanList.size();
    }
}
