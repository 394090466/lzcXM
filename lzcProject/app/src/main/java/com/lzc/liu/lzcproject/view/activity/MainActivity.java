package com.lzc.liu.lzcproject.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.adapter.MainFragmentAdapter;
import com.lzc.liu.lzcproject.base.BaseActivity;
import com.lzc.liu.lzcproject.interfaces.view.MainView;
import com.lzc.liu.lzcproject.view.fragment.HomeFragment;
import com.lzc.liu.lzcproject.view.fragment.LiveFragmet;
import com.lzc.liu.lzcproject.view.fragment.MeFragment;
import com.lzc.liu.lzcproject.view.fragment.WeatherFragmet;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.shizhefei.view.viewpager.SViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView{


    @BindView(R.id.main_sp)
    SViewPager viewPager;
    @BindView(R.id.main_fiv)
    FixedIndicatorView indicatorView;

    private IndicatorViewPager indicatorViewPager;

    private ArrayList<Fragment> fragmentlist ;

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
        fragmentlist = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        LiveFragmet liveFragmet = new LiveFragmet();
        WeatherFragmet weatherFragmet = new WeatherFragmet();
        MeFragment meFragment = new MeFragment();
        fragmentlist.add(homeFragment);
        fragmentlist.add(liveFragmet);
        fragmentlist.add(weatherFragmet);
        fragmentlist.add(meFragment);
        indicatorView.setOnTransitionListener(new OnTransitionTextListener().setColorId(this, R.color.sytb1, R.color.sytb2));
        indicatorViewPager = new IndicatorViewPager(indicatorView, viewPager);
        indicatorViewPager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager(), this, fragmentlist));
        viewPager.setCanScroll(false);
        viewPager.setOffscreenPageLimit(3);
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
