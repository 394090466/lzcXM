package com.lzc.liu.lzcproject.view.fragment;

import android.graphics.Color;

import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.adapter.HomeFragmentAdapter;
import com.lzc.liu.lzcproject.base.BaseViewLazyFragment;
import com.lzc.liu.lzcproject.interfaces.presenter.HomeNewPresenter;
import com.lzc.liu.lzcproject.interfaces.view.NewView;
import com.lzc.liu.lzcproject.presenter.HomeNewPresenterImpl;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.SpringBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.shizhefei.view.viewpager.SViewPager;

import butterknife.BindView;

/**
 * Created by liu on 2018/2/26.
 */

public class HomeFragment extends BaseViewLazyFragment implements NewView {


    @BindView(R.id.home_fiv)
    ScrollIndicatorView homeFiv;
    @BindView(R.id.sp_home)
    SViewPager spHome;

    private IndicatorViewPager indicatorViewPager;

    private HomeNewPresenter presenter;

    @Override
    protected int getViewID() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        presenter = new HomeNewPresenterImpl(getBaseActivity(),this);
        homeFiv.setOnTransitionListener(new OnTransitionTextListener().setColorId(getBaseActivity(), R.color.sytb1, R.color.white));
        homeFiv.setScrollBar(new SpringBar(getApplicationContext(), Color.WHITE));
        indicatorViewPager = new IndicatorViewPager(homeFiv, spHome);
        indicatorViewPager.setAdapter(new HomeFragmentAdapter(getChildFragmentManager(), getBaseActivity()));
        homeFiv.setSplitAuto(false);
        spHome.setCanScroll(true);
        spHome.setOffscreenPageLimit(10);
    }


    @Override
    public void onSubscribed() {
        indicatorViewPager.getAdapter().notifyDataSetChanged();
    }

    @Override
    protected void onNetworkConnected(int type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }



    @Override
    public void initData() {
        presenter.GetSubscribed();
    }

    @Override
    public void initListener() {

    }


}
