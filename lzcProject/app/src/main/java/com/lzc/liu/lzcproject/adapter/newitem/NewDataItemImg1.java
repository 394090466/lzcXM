package com.lzc.liu.lzcproject.adapter.newitem;

import android.content.Context;
import android.widget.ImageView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.google.gson.Gson;
import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.bean.NewContentBean;
import com.lzc.liu.lzcproject.bean.NewListBean;
import com.lzc.liu.lzcproject.util.GlideUtils;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by liu on 2018/3/3.
 */
public class NewDataItemImg1 implements ItemViewDelegate<NewListBean.DataBean> {

    private Context context;

    public NewDataItemImg1(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.layout_newdataitemimg1;
    }

    @Override
    public boolean isForViewType(NewListBean.DataBean item, int position) {
        String stringjsonData = item.getContent();
        NewContentBean newContentBean = new Gson().fromJson(stringjsonData, NewContentBean.class);
        if (!StringUtils.isEmpty(newContentBean.getAd_label()) && newContentBean.getAd_label().equals("广告")) {
            return false;
        }

        if (newContentBean.isHas_image() && ObjectUtils.isEmpty(newContentBean.getImage_list())) {
            return true;
        }

        if (newContentBean.isHas_video() && !newContentBean.isHas_m3u8_video() && newContentBean.getHas_mp4_video() == 0){
            return true;
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
        holder.setText(R.id.tv_pj_newitem, newContentBean.getComment_count() + "评语");

        String titleurl = newContentBean.getUser_info().getAvatar_url();
        String url = newContentBean.getMiddle_image().getUrl();

        ImageView imageView1 = holder.getView(R.id.iv_item1);
        GlideUtils.loadImageView(context, url, imageView1);

        ImageView imageView2 = holder.getView(R.id.iv_iconfrom);
        GlideUtils.loadImageView(context, titleurl, imageView2);
    }

}
