package com.lzc.liu.lzcproject.adapter.newitem;

import android.content.Context;

import com.google.gson.Gson;
import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.bean.NewContentBean;
import com.lzc.liu.lzcproject.bean.NewListBean;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by liu on 2018/3/2.
 */

public class NewDataItemText implements ItemViewDelegate<NewListBean.DataBean> {

    private Context context;

    public NewDataItemText(Context context){
        this.context = context;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.layout_newdataitemtext;
    }

    @Override
    public boolean isForViewType(NewListBean.DataBean item, int position) {
        String stringjsonData = item.getContent();
        NewContentBean newContentBean = new Gson().fromJson(stringjsonData, NewContentBean.class);
        if (newContentBean.isHas_video() && !newContentBean.isHas_m3u8_video() && newContentBean.getHas_mp4_video() == 0){
            return false;
        }
        return !newContentBean.isHas_image() ;
    }

    @Override
    public void convert(ViewHolder holder, NewListBean.DataBean newListBean, int position) {
        String stringjsonData = newListBean.getContent();
        NewContentBean newContentBean = new Gson().fromJson(stringjsonData,NewContentBean.class);
        holder.setText(R.id.tv_title_newitem,newContentBean.getTitle());
    }
}
