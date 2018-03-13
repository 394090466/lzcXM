package com.lzc.liu.lzcproject.interfaces.view;

import com.lzc.liu.lzcproject.entity.douyu.ChannelsEntity;

import java.util.List;

/**
 * Created by liu on 2018/3/13.
 */

public interface LiveView {

    void getChannelsSuccess(List<ChannelsEntity.DataBean> channelEntities);
}
