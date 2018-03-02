package com.lzc.liu.lzcproject.adapter;

import android.content.Context;

import com.lzc.liu.lzcproject.adapter.newitem.NewDataItemText;
import com.lzc.liu.lzcproject.bean.NewListBean;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

/**
 * Created by liu on 2018/3/2.
 */

public class NewDataItemTypeAdapter extends MultiItemTypeAdapter<NewListBean> {

    public NewDataItemTypeAdapter(Context context, List<NewListBean> datas) {
        super(context, datas);
        addItemViewDelegate(new NewDataItemText(context));
//        addItemViewDelegate(new NewDataItemImg1(context));
//        addItemViewDelegate(new NewDataItemImg2(context));
    }
}
