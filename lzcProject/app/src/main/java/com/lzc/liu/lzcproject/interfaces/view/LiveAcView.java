package com.lzc.liu.lzcproject.interfaces.view;

import com.lzc.liu.lzcproject.entity.douyu.RoomInfoEntity;

import java.util.List;

/**
 * Created by liu on 2018/4/10.
 */

public interface LiveAcView {

    /**
     * 更新线路和清晰度信息
     *
     * @param list
     * @param list2
     */
    void updateCDNandRateInfo(List<RoomInfoEntity.DataBean.CdnsWithNameBean> list,
                              List<RoomInfoEntity.DataBean.MultiratesBean> list2);

    /**
     * 更新房间信息
     * @param dataBean
     */
    void initRoom(RoomInfoEntity.DataBean dataBean);

    /**
     * 准备播放
     */
    void preparePlay();

    /**
     * 更新线路
     *
     * @param cdnsWithNameBean
     */
    void upDateCDN(RoomInfoEntity.DataBean.CdnsWithNameBean cdnsWithNameBean);

    /**
     * 更新清晰度
     *
     * @param multiratesBean
     */
    void upDateRate(RoomInfoEntity.DataBean.MultiratesBean multiratesBean);

    void setMediaCodec(boolean b);

    void updateHLSUrl(String url);


}
