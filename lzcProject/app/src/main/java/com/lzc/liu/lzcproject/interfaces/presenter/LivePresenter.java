package com.lzc.liu.lzcproject.interfaces.presenter;

import com.lzc.liu.lzcproject.entity.douyu.RoomInfoEntity;

/**
 * Created by liu on 2018/3/13.
 */

public interface LivePresenter {

    void getChannels();

    void getCDNandRateInfo(String roomId);

    void getHLSUrl(String roomid, String cdn, String rate);

    void onRateChange(RoomInfoEntity.DataBean.MultiratesBean rate);
}
