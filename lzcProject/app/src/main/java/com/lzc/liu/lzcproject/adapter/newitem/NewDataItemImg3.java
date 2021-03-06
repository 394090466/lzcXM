package com.lzc.liu.lzcproject.adapter.newitem;

import android.content.Context;
import android.widget.ImageView;

import com.blankj.utilcode.util.TimeUtils;
import com.google.gson.Gson;
import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.entity.NewContentBean;
import com.lzc.liu.lzcproject.entity.NewListBean;
import com.lzc.liu.lzcproject.util.GlideUtils;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by liu on 2018/3/6.
 */

public class NewDataItemImg3 implements ItemViewDelegate<NewListBean.DataBean> {

    private Context context;

    private String category;

    public NewDataItemImg3(Context context, String category) {
        this.context = context;
        this.category = category;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.layout_newdataitemimg3;
    }

    @Override
    public boolean isForViewType(NewListBean.DataBean item, int position) {
//        if (StringUtils.equals(category, NewData.getEssay_joke()) || StringUtils.equals(category, NewData.getImage_ppmm())
//                || StringUtils.equals(category, NewData.getImage_funny()) || StringUtils.equals(category, NewData.getJinritemai())) {
//            return false;
//        }
//        String stringjsonData = item.getContent();
//        NewContentBean newContentBean = new Gson().fromJson(stringjsonData, NewContentBean.class);
//
//        if (newContentBean.isHas_image() && ObjectUtils.isEmpty(newContentBean.getImage_list())) {
//            if ((newContentBean.getMiddle_image().getWidth()-newContentBean.getMiddle_image().getHeight())>=500){
//                return true;
//            }
//        }
//
//        if (newContentBean.isHas_video() && !newContentBean.isHas_m3u8_video() && newContentBean.getHas_mp4_video() == 0) {
//            if ((newContentBean.getMiddle_image().getWidth()-newContentBean.getMiddle_image().getHeight())>=500){
//                return true;
//            }
//        }



        return false;
    }

    @Override
    public void convert(ViewHolder holder, NewListBean.DataBean dataBean, int position) {
        String stringjsonData = dataBean.getContent();
        NewContentBean newContentBean = new Gson().fromJson(stringjsonData, NewContentBean.class);
        long time = Long.parseLong(newContentBean.getPublish_time() + "000");
        holder.setText(R.id.tv_title_newitem, newContentBean.getTitle());
        holder.setText(R.id.tv_time_newitem, TimeUtils.getFriendlyTimeSpanByNow(time));
        holder.setText(R.id.tv_ly_newitem, newContentBean.getUser_info().getName());
        holder.setText(R.id.tv_pj_newitem, newContentBean.getComment_count() + "评语");

        String titleurl = newContentBean.getUser_info().getAvatar_url();
        String url = newContentBean.getMiddle_image().getUrl();

        ImageView imageView1 = holder.getView(R.id.iv_item3);
        GlideUtils.loadImageView(context, url, imageView1);

        ImageView imageView2 = holder.getView(R.id.iv_iconfrom);
        GlideUtils.loadImageView(context, titleurl, imageView2);
    }
}
