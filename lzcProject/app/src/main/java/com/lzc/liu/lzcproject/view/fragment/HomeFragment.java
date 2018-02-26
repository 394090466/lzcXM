package com.lzc.liu.lzcproject.view.fragment;

import android.graphics.Color;

import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.adapter.HomeFragmentAdapter;
import com.lzc.liu.lzcproject.base.BaseViewLazyFragment;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.SpringBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.shizhefei.view.viewpager.SViewPager;

import butterknife.BindView;

/**
 * Created by liu on 2018/2/26.
 */

public class HomeFragment extends BaseViewLazyFragment {


    @BindView(R.id.home_fiv)
    ScrollIndicatorView homeFiv;
    @BindView(R.id.sp_home)
    SViewPager spHome;

    private IndicatorViewPager indicatorViewPager;

    @Override
    protected int getViewID() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        homeFiv.setOnTransitionListener(new OnTransitionTextListener().setColorId(getBaseActivity(), R.color.sytb1, R.color.white));
        homeFiv.setScrollBar(new SpringBar(getApplicationContext(), Color.WHITE));
        indicatorViewPager = new IndicatorViewPager(homeFiv, spHome);
        indicatorViewPager.setAdapter(new HomeFragmentAdapter(getChildFragmentManager(), getBaseActivity()));
        homeFiv.setSplitAuto(false);
        spHome.setCanScroll(true);
        spHome.setOffscreenPageLimit(3);
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
