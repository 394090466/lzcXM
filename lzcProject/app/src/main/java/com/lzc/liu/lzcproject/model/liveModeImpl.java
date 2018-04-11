package com.lzc.liu.lzcproject.model;

import android.util.Log;

import com.lzc.liu.lzcproject.entity.douyu.ChannelsEntity;
import com.lzc.liu.lzcproject.entity.douyu.RoomInfoEntity;
import com.lzc.liu.lzcproject.entity.douyu.RoomInfoEntity2;
import com.lzc.liu.lzcproject.interfaces.model.liveMode;
import com.lzc.liu.lzcproject.netapi.DouyuApiService;
import com.lzc.liu.lzcproject.netapi.Retrofit2Service;
import com.lzc.liu.lzcproject.netapi.RxService;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liu on 2018/3/13.
 */

public class liveModeImpl implements liveMode {


    @Override
    public void getChannels(final onliveModeListener listener) {
        RxService.createDouyuApi(Retrofit2Service.class).getChannels()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<ChannelsEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ChannelsEntity channelsEntity) {
                        listener.onSuccess(channelsEntity);
                    }


                    @Override
                    public void onError(Throwable e) {
                        listener.onError();
                        Log.i("lzc", "--网络异常--：" + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getCDNandRateInfo(String roomId, final onliveModeListener listener) {

        String time = String.valueOf((new Date().getTime() / 1000) / 60);
        String signContent = roomId + "bLFlashflowlad92" + time;
        String sign = "";
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(signContent.getBytes("utf-8"));
            sign = new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e)
        {
        }

        RxService.createDouyuApi(DouyuApiService.class).getRoomInfo(roomId, "", "yes", time, sign)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<RoomInfoEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RoomInfoEntity roomInfoEntity) {
                        listener.onSuccess(roomInfoEntity);
                    }


                    @Override
                    public void onError(Throwable e) {
                        listener.onError();
                        Log.i("lzc", "--网络异常--：" + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 获取视频地址
     *
     * @param roomid
     * @param cdn
     * @param rate
     * @param listener
     */
    @Override
    public void getHLSUrl(String roomid, String cdn, final String rate, final onliveModeListener listener) {
        if (cdn.equals("temp"))
        {
            return;
            //            return dyService.getTempLiveInfo(roomid).retry(3);
        }
        String time = String.valueOf(new Date().getTime() / 1000);
        String aid = "wp";
        String client_sys = "wp";
        String authStr = "room/" + roomid + "?aid=" + aid + "&cdn=" + cdn + "&client_sys=" + client_sys + "&time=" + time + "zNzMV1y4EMxOHS6I5WKm";
        String authMD5 = "";
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(authStr.getBytes());
            authMD5 = new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e)
        {
        }


        RxService.createDouyuApi(DouyuApiService.class).getLiveInfo(roomid, aid, cdn, client_sys, time, authMD5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<RoomInfoEntity2>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RoomInfoEntity2 roomInfoEntity2) {
                        listener.onSuccess(roomInfoEntity2,rate);
                    }


                    @Override
                    public void onError(Throwable e) {
                        listener.onError();
                        Log.i("lzc", "--网络异常--：" + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface onliveModeListener{

        void onSuccess(ChannelsEntity channelsEntity);

        void onSuccess(RoomInfoEntity roomInfoEntity);

        void onSuccess(RoomInfoEntity2 roomInfoEntity2,String rate);

        void onError();
    }
}
