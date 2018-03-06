package com.lzc.liu.lzcproject.adapter.newitem;

import android.content.Context;
import android.widget.ImageView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.google.gson.Gson;
import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.bean.NewContentBean;
import com.lzc.liu.lzcproject.bean.NewListBean;
import com.lzc.liu.lzcproject.util.GlideUtils;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by liu on 2018/3/3.
 */

public class NewDataItemImg2 implements ItemViewDelegate<NewListBean.DataBean> {

    private Context context;

    public NewDataItemImg2(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.layout_newdataitemimg2;
    }

    @Override
    public boolean isForViewType(NewListBean.DataBean item, int position) {
        String stringjsonData = item.getContent();
        NewContentBean newContentBean = new Gson().fromJson(stringjsonData, NewContentBean.class);
        if (newContentBean.isHas_image()) {
            if (ObjectUtils.isNotEmpty(newContentBean.getImage_list())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void convert(ViewHolder holder, NewListBean.DataBean newListBean, int position) {
        String stringjsonData = newListBean.getContent();
        NewContentBean newContentBean = new Gson().fromJson(stringjsonData, NewContentBean.class);
        holder.setText(R.id.tv_title_newitem, newContentBean.getTitle());
        long time = Long.parseLong(newContentBean.getPublish_time() + "000");
        holder.setText(R.id.tv_time_newitem, TimeUtils.getFriendlyTimeSpanByNow(time));
        holder.setText(R.id.tv_ly_newitem, newContentBean.getUser_info().getName());
        String titleurl = newContentBean.getUser_info().getAvatar_url();
        ImageView imageView2 = holder.getView(R.id.iv_iconfrom);
        GlideUtils.loadImageView(context, titleurl, imageView2);
        List<NewContentBean.ImageListBean> imageListBeanList = newContentBean.getImage_list();
        for (int i = 0; i < imageListBeanList.size(); i++) {
            if (i < 3){
                ImageView imageView = holder.getView(context.getResources().getIdentifier("iv_newdata_itme2_d"+(i+1),"id",context.getApplicationContext().getPackageName()));
                GlideUtils.loadImageView(context, imageListBeanList.get(i).getUrl(), imageView);
            }
        }

    }
}