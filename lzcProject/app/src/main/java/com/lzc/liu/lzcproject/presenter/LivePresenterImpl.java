package com.lzc.liu.lzcproject.presenter;

import android.content.Context;

import com.lzc.liu.lzcproject.application.MainApplication;
import com.lzc.liu.lzcproject.constant.Constant;
import com.lzc.liu.lzcproject.entity.douyu.ChannelsEntity;
import com.lzc.liu.lzcproject.entity.douyu.RoomInfoEntity;
import com.lzc.liu.lzcproject.entity.douyu.RoomInfoEntity2;
import com.lzc.liu.lzcproject.interfaces.model.liveMode;
import com.lzc.liu.lzcproject.interfaces.presenter.LivePresenter;
import com.lzc.liu.lzcproject.interfaces.view.LiveAcView;
import com.lzc.liu.lzcproject.interfaces.view.LiveView;
import com.lzc.liu.lzcproject.model.liveModeImpl;

import java.util.List;

/**
 * Created by liu on 2018/3/13.
 */

public class LivePresenterImpl implements LivePresenter, liveModeImpl.onliveModeListener {

    private LiveView liveView;
    private LiveAcView liveAcView;
    private Context context;
    private liveMode liveMode;

    public LivePresenterImpl(Context context, LiveView liveView) {
        this.context = context;
        this.liveView = liveView;
        liveMode = new liveModeImpl();
    }

    public LivePresenterImpl(Context context, LiveAcView liveAcView) {
        this.context = context;
        this.liveAcView = liveAcView;
        liveMode = new liveModeImpl();
    }


    /**
     * 获取列表数据
     */
    @Override
    public void getChannels() {
        liveMode.getChannels(this);
    }

    /**
     * 获取房间数据
     *
     * @param roomId
     */
    @Override
    public void getCDNandRateInfo(String roomId) {
        liveMode.getCDNandRateInfo(roomId, this);
    }


    /**
     * 获取视频地址
     *
     * @param roomid
     * @param cdn
     * @param rate
     */
    @Override
    public void getHLSUrl(String roomid, String cdn, String rate) {
        liveMode.getHLSUrl(roomid, cdn, rate, this);

    }

    /**
     * 列表数据处理
     *
     * @param channelsEntity
     */
    @Override
    public void onSuccess(ChannelsEntity channelsEntity) {
        liveView.getChannelsSuccess(channelsEntity.getData());
    }

    /**
     * 获取房间数据
     *
     * @param roomInfoEntity
     */
    @Override
    public void onSuccess(RoomInfoEntity roomInfoEntity) {
        RoomInfoEntity.DataBean.CdnsWithNameBean tempCdn = new RoomInfoEntity.DataBean.CdnsWithNameBean();
        tempCdn.setName("临时线路");
        tempCdn.setCdn("temp");
        RoomInfoEntity.DataBean value = roomInfoEntity.getData();
        value.getCdnsWithName().add(tempCdn);
        if (liveAcView != null) {
            liveAcView.updateCDNandRateInfo(value.getCdnsWithName(), value.getMultirates());
        }
        restoreCDN(value.getCdnsWithName());
        restoreRate(value.getMultirates());
    }

    /**
     * 获取视频地址并进行处理
     *
     * @param roomInfoEntity2
     */
    @Override
    public void onSuccess(RoomInfoEntity2 roomInfoEntity2,String rate) {
        RoomInfoEntity2.DataBean value = roomInfoEntity2.getData();
        String url = "";
        String str = rate;
        int obj = -1;
        switch (str.hashCode()) {
            case 48:
                if (str.equals("0")) {
                    obj = 0;
                }
                break;
            case 49:
                if (str.equals("1")) {
                    obj = 1;
                }
                break;
            case 50:
                if (str.equals("2")) {
                    obj = 2;
                }
                break;
            default:

                break;
        }

        switch (obj) {
            case 0:
                url = value.getRtmp_url() + "/" + value.getRtmp_live();
                break;
            case 1:
                url = value.getRtmp_url() + "/" + value.getRtmp_multi_bitrate().getMiddle();
                break;
            case 2:
                url = value.getRtmp_url() + "/" + value.getRtmp_multi_bitrate().getMiddle2();
                break;
            default:
                url = value.getRtmp_url() + "/" + value.getRtmp_live();
                break;
        }
        liveAcView.updateHLSUrl(url);
    }

    /**
     * 保存cdn，线路如主线路、临时线路
     *
     * @param cdnsWithName
     */
    public void restoreCDN(List<RoomInfoEntity.DataBean.CdnsWithNameBean> cdnsWithName) {
        String cdnCode = MainApplication.mPref.get(Constant.CDN_CODE, "ws");
        for (RoomInfoEntity.DataBean.CdnsWithNameBean cdn : cdnsWithName) {
            if (cdn.getCdn().equals(cdnCode)) {
                liveAcView.upDateCDN(cdn);
                return;
            }
        }

        MainApplication.mPref.put(Constant.CDN_CODE, cdnsWithName.get(0).getCdn());
        liveAcView.upDateCDN(cdnsWithName.get(0));
    }

    /**
     * 保存费率，如超清、高清、普清
     *
     * @param multirates
     */
    public void restoreRate(List<RoomInfoEntity.DataBean.MultiratesBean> multirates) {
        int rateCode = Integer.valueOf(MainApplication.mPref.get(Constant.RATE_CODE, "2")).intValue();
        for (RoomInfoEntity.DataBean.MultiratesBean rate : multirates) {
            if (rate.getType() == rateCode) {
                liveAcView.upDateRate(rate);
                return;
            }
        }

        liveAcView.upDateRate(multirates.get(0));
    }

    /**
     * 清晰度改变
     *
     * @param rate
     */
    public void onRateChange(RoomInfoEntity.DataBean.MultiratesBean rate)
    {
        liveAcView.upDateRate(rate);
        //        mView.preparePlay();
    }

    @Override
    public void onError() {

    }
}
