package com.lzc.liu.lzcproject.adapter;

import android.content.Context;

import com.lzc.liu.lzcproject.adapter.newitem.EssayJokeItem;
import com.lzc.liu.lzcproject.adapter.newitem.ImageFunnyItem;
import com.lzc.liu.lzcproject.adapter.newitem.NewDataItemImg1;
import com.lzc.liu.lzcproject.adapter.newitem.NewDataItemImg2;
import com.lzc.liu.lzcproject.adapter.newitem.NewDataItemImg3;
import com.lzc.liu.lzcproject.adapter.newitem.NewDataItemText;
import com.lzc.liu.lzcproject.bean.NewListBean;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

/**
 * Created by liu on 2018/3/2.
 */

public class NewDataItemTypeAdapter extends MultiItemTypeAdapter<NewListBean.DataBean> {

    public NewDataItemTypeAdapter(Context context, List<NewListBean.DataBean> datas, String category) {
        super(context, datas);
        addItemViewDelegate(new NewDataItemImg1(context, category));
        addItemViewDelegate(new NewDataItemImg3(context, category));
        addItemViewDelegate(new NewDataItemImg2(context, category));
        addItemViewDelegate(new NewDataItemText(context, category));
        addItemViewDelegate(new EssayJokeItem(context, category));
        addItemViewDelegate(new ImageFunnyItem(context, category));
    }
}
