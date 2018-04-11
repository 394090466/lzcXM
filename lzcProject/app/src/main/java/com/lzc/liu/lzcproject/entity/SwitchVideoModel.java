package com.lzc.liu.lzcproject.entity;

import com.lzc.liu.lzcproject.entity.douyu.RoomInfoEntity;

/**
 * Created by shuyu on 2016/12/7.
 */

public class SwitchVideoModel {
    private RoomInfoEntity.DataBean.MultiratesBean multiratesBeans;
    private String url;
    private String name;

    public SwitchVideoModel(String name, String url,RoomInfoEntity.DataBean.MultiratesBean multiratesBeans) {
        this.name = name;
        this.url = url;
        this.multiratesBeans = multiratesBeans;
    }

    public RoomInfoEntity.DataBean.MultiratesBean getMultiratesBeans() {
        return multiratesBeans;
    }

    public void setMultiratesBeans(RoomInfoEntity.DataBean.MultiratesBean multiratesBeans) {
        this.multiratesBeans = multiratesBeans;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}