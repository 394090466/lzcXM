package com.lzc.liu.lzcproject.adapter.newitem;

import android.content.Context;
import android.widget.ImageView;

import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.bean.NewContentBean;
import com.lzc.liu.lzcproject.bean.NewListBean;
import com.lzc.liu.lzcproject.constant.NewData;
import com.lzc.liu.lzcproject.util.GlideUtils;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by liu on 2018/3/6.
 */

public class ImageFunnyItem implements ItemViewDelegate<NewListBean.DataBean> {

    private Context context;

    private String category;

    public ImageFunnyItem(Context context, String category) {
        this.context = context;
        this.category = category;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.layout_imagefunny;
    }

    @Override
    public boolean isForViewType(NewListBean.DataBean item, int position) {
        if (StringUtils.equals(category, NewData.getImage_funny())){
            return true;
        }

        return false;
    }

    @Override
    public void convert(ViewHolder holder, NewListBean.DataBean dataBean, int position) {
        String stringjsonData = dataBean.getContent();
        NewContentBean newContentBean = new Gson().fromJson(stringjsonData, NewContentBean.class);
        holder.setText(R.id.tv_essay_context,newContentBean.getContent());
        holder.setText(R.id.tv_essay_dz,newContentBean.getBury_count()+"");
        holder.setText(R.id.tv_essay_bxh,newContentBean.getRepin_count()+"");
        holder.setText(R.id.tv_essay_msg,newContentBean.getComment_count()+"");

        String url = newContentBean.getLarge_image().getUrlX();
        ImageView imageView2 = holder.getView(R.id.iv_imagefunny);
        GlideUtils.loadImageView(context, url, imageView2);
    }
}
