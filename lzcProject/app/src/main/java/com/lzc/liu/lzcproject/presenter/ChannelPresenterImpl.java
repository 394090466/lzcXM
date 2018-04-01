package com.lzc.liu.lzcproject.presenter;

import android.content.Context;

import com.lzc.liu.lzcproject.entity.douyu.RoomsEntity;
import com.lzc.liu.lzcproject.entity.douyu.SlidersEntity;
import com.lzc.liu.lzcproject.interfaces.ChannelContract;
import com.lzc.liu.lzcproject.model.ChannelModelImpl;
import com.lzc.liu.lzcproject.view.fragment.ChannelFragment;

/**
 * Created by liu on 2018/4/1.
 */

public class ChannelPresenterImpl implements ChannelContract.Presenter , ChannelModelImpl.onChannelModeListener {

    private Context context;
    private ChannelContract.View view;
    private ChannelContract.Model model;

    public ChannelPresenterImpl(Context context, ChannelFragment channelFragment){
        this.context = context;
        this.view = channelFragment;
        this.model = new ChannelModelImpl();

    }

    @Override
    public void getRooms(String cateId, int limit, int offset) {
        model.getRooms(cateId,limit,offset,this);
    }

    @Override
    public void getRoomsWithSliders(String cateId, int limit, int offset) {
        model.getSlider(this);
    }

    @Override
    public void onSuccess(SlidersEntity slidersEntity) {
        view.getMixSuccess(slidersEntity.getData());
    }

    @Override
    public void onSuccess(RoomsEntity roomsEntity) {
        view.getRoomsSuccess(roomsEntity.getData());
    }

    @Override
    public void getRoomsFailed(Throwable throwable) {
        view.getRoomsFailed(throwable);
    }

    @Override
    public void getMixFailed(Throwable throwable) {
        view.getMixFailed(throwable);
    }

}
