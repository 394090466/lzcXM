package com.lzc.liu.lzcproject.adapter.newitem;

import android.content.Context;

import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.bean.NewListBean;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by liu on 2018/3/2.
 */

public class NewDataItemText implements ItemViewDelegate<NewListBean> {

    private Context context;

    public NewDataItemText(Context context){
        this.context = context;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.layout_newdataitemtext;
    }

    @Override
    public boolean isForViewType(NewListBean item, int position) {
        return false;
    }

    @Override
    public void convert(ViewHolder holder, NewListBean newListBean, int position) {

    }
}
