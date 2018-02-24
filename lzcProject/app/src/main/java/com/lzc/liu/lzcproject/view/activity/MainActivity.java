package com.lzc.liu.lzcproject.view.activity;

import android.os.Bundle;

import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.adapter.MainFragmentAdapter;
import com.lzc.liu.lzcproject.base.BaseActivity;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.shizhefei.view.viewpager.SViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @BindView(R.id.main_sp)
    SViewPager viewPager;
    @BindView(R.id.main_fiv)
    FixedIndicatorView indicatorView;

    private IndicatorViewPager indicatorViewPager;

    @Override
    protected int getView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onNetworkConnected(int type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    public void initView() {
        indicatorView.setOnTransitionListener(new OnTransitionTextListener().setColorId(this, R.color.colorAccent, R.color.colorPrimary));
        indicatorViewPager = new IndicatorViewPager(indicatorView, viewPager);
        indicatorViewPager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager(), this, fragmentlist));
        viewPager.setCanScroll(false);
        viewPager.setOffscreenPageLimit(4);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
