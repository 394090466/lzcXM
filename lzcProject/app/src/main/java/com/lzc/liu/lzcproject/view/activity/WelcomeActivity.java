package com.lzc.liu.lzcproject.view.activity;

import android.content.Intent;

import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.base.BaseActivity;
import com.lzc.liu.lzcproject.interfaces.presenter.WelcomePresenter;
import com.lzc.liu.lzcproject.interfaces.view.WelcomeView;
import com.lzc.liu.lzcproject.presenter.WelcomePresenterImpl;

public class WelcomeActivity extends BaseActivity implements WelcomeView{

    private WelcomePresenter presenter;


    @Override
    protected int getView() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void onNetworkConnected(int type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    public void initView() {
        presenter = new WelcomePresenterImpl(this,this);
    }


    @Override
    public void initData() {
        presenter.AppDataInit();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onInitOver() {
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        closeActivity();
    }


}
