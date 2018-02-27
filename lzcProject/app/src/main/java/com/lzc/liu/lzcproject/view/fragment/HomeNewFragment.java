package com.lzc.liu.lzcproject.view.fragment;

import android.os.Bundle;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.base.BaseViewLazyFragment;

import butterknife.BindView;

/**
 * Created by liu on 2018/2/26.
 */

public class HomeNewFragment extends BaseViewLazyFragment {

    public static final String INTENT_STRING_TABNAME = "intent_String_tabname";
    public static final String INTENT_INT_INDEX = "intent_int_index";
    @BindView(R.id.lrv_newhome)
    LRecyclerView lrvNewhome;


    @Override
    protected int getViewID() {
        return R.layout.fragment_new_home;
    }

    @Override
    public void initView() {
        Bundle bundle = getArguments();
        String tabName = bundle.getString(INTENT_STRING_TABNAME);
        int index = bundle.getInt(INTENT_INT_INDEX);
    }

    @Override
    protected void onNetworkConnected(int type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
