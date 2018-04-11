package com.lzc.liu.lzcproject.view.fragment;

import android.graphics.Color;
import android.util.Log;

import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.adapter.ChannelPageAdapter;
import com.lzc.liu.lzcproject.base.BaseViewLazyFragment;
import com.lzc.liu.lzcproject.entity.douyu.ChannelsEntity;
import com.lzc.liu.lzcproject.interfaces.presenter.LivePresenter;
import com.lzc.liu.lzcproject.interfaces.view.LiveView;
import com.lzc.liu.lzcproject.presenter.LivePresenterImpl;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.shizhefei.view.viewpager.SViewPager;

import java.util.List;

import butterknife.BindView;

/**
 * Created by liu on 2018/2/26.
 */

public class LiveFragmet extends BaseViewLazyFragment implements LiveView {

    @BindView(R.id.home_fiv)
    ScrollIndicatorView homeFiv;
    @BindView(R.id.sp_home)
    SViewPager spHome;

    private IndicatorViewPager indicatorViewPager;
    private ChannelPageAdapter pageAdapter;

    private LivePresenter presenter;

    @Override
    protected int getViewID() {
//        return 0;
        return R.layout.fragment_live;
    }

    @Override
    public void initView() {
        presenter = new LivePresenterImpl(getActivity(),this);
        homeFiv.setOnTransitionListener(new OnTransitionTextListener().setColorId(getBaseActivity(), R.color.black, R.color.white));
        homeFiv.setScrollBar(new ColorBar(getApplicationContext(), Color.WHITE, 60, ScrollBar.Gravity.CENTENT_BACKGROUND));
//        homeFiv.setScrollBar(new SpringBar(getApplicationContext(), Color.WHITE));
        indicatorViewPager = new IndicatorViewPager(homeFiv, spHome);
        pageAdapter = new ChannelPageAdapter(getChildFragmentManager(), getBaseActivity());
        indicatorViewPager.setAdapter(pageAdapter);
        homeFiv.setSplitAuto(false);
        spHome.setCanScroll(true);
        spHome.setOffscreenPageLimit(4);
    }

    @Override
    protected void onNetworkConnected(int type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    public void initData() {
        refers();
    }

    private void refers(){
        presenter.getChannels();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void getChannelsSuccess(List<ChannelsEntity.DataBean> channelEntities) {
        Log.v("lzc:",channelEntities.get(0).getGame_name());
        pageAdapter.updateData(channelEntities);
        indicatorViewPager.getAdapter().notifyDataSetChanged();
    }
//
//    @Override
//    protected String getMainPageName() {
//        return "lzcproject";
//    }
}
