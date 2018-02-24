package com.lzc.liu.lzcproject.view.activity;

import android.os.Bundle;

import com.blankj.utilcode.util.BarUtils;
import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.base.BaseActivity;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BarUtils.setNavBarImmersive(this);
        BarUtils.setNavBarVisibility(this,false);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    protected void onNetworkConnected(int type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
