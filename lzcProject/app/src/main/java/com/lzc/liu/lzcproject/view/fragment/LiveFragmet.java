package com.lzc.liu.lzcproject.view.fragment;

import android.util.Log;

import com.lzc.liu.lzcproject.base.ReactFragment;
import com.lzc.liu.lzcproject.constant.Constant;
import com.lzc.liu.lzcproject.entity.douyu.ChannelsEntity;
import com.lzc.liu.lzcproject.interfaces.presenter.LivePresenter;
import com.lzc.liu.lzcproject.interfaces.view.LiveView;
import com.lzc.liu.lzcproject.netapi.RxService;
import com.lzc.liu.lzcproject.presenter.LivePresenterImpl;

import java.util.List;

/**
 * Created by liu on 2018/2/26.
 */

public class LiveFragmet extends ReactFragment implements LiveView {


    private LivePresenter presenter;

    @Override
    protected int getViewID() {
        return 0;
//        return R.layout.fragment_live;
    }

    @Override
    public void initView() {
        presenter = new LivePresenterImpl(getActivity(),this);
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
        RxService.setUrls(Constant.DOUYU_BASE_URL);
        presenter.getChannels();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void getChannelsSuccess(List<ChannelsEntity.DataBean> channelEntities) {
        Log.v("lzc:",channelEntities.get(0).getGame_name());
    }

    @Override
    protected String getMainPageName() {
        return "lzcproject";
    }
}
