package com.lzc.liu.lzcproject.presenter;

import android.content.Context;

import com.lzc.liu.lzcproject.entity.douyu.ChannelsEntity;
import com.lzc.liu.lzcproject.interfaces.model.liveMode;
import com.lzc.liu.lzcproject.interfaces.presenter.LivePresenter;
import com.lzc.liu.lzcproject.interfaces.view.LiveView;
import com.lzc.liu.lzcproject.model.liveModeImpl;

/**
 * Created by liu on 2018/3/13.
 */

public class LivePresenterImpl implements LivePresenter,liveModeImpl.onliveModeListener {

    private LiveView liveView;
    private Context context;
    private liveMode liveMode;

    public LivePresenterImpl(Context context, LiveView liveView){
        this.context =context;
        this.liveView = liveView;
        liveMode = new liveModeImpl();
    }



    @Override
    public void getChannels() {
        liveMode.getChannels(this);
    }

    @Override
    public void onSuccess(ChannelsEntity channelsEntity) {
        liveView.getChannelsSuccess(channelsEntity.getData());
    }

    @Override
    public void onError() {

    }
}
