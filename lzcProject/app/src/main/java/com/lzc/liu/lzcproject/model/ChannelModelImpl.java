package com.lzc.liu.lzcproject.model;

import android.util.Log;

import com.lzc.liu.lzcproject.entity.douyu.RoomsEntity;
import com.lzc.liu.lzcproject.entity.douyu.SlidersEntity;
import com.lzc.liu.lzcproject.interfaces.ChannelContract;
import com.lzc.liu.lzcproject.netapi.DouyuApiService;
import com.lzc.liu.lzcproject.netapi.RxService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liu on 2018/4/1.
 */

public class ChannelModelImpl implements ChannelContract.Model {


    @Override
    public void getSlider(final onChannelModeListener listener) {
        RxService.createApi(DouyuApiService.class).getSliders()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<SlidersEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SlidersEntity slidersEntity) {
                        listener.onSuccess(slidersEntity);
                    }


                    @Override
                    public void onError(Throwable e) {
                        listener.getMixFailed(e);
                        Log.i("lzc", "--网络异常--：" + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getRooms(String cateId, int limit, int offset, final onChannelModeListener listener) {
        RxService.createApi(DouyuApiService.class).getRooms(cateId,limit,offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<RoomsEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RoomsEntity roomsEntity) {
                        listener.onSuccess(roomsEntity);
                    }


                    @Override
                    public void onError(Throwable e) {
                        listener.getRoomsFailed(e);
                        Log.i("lzc", "--网络异常--：" + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface onChannelModeListener{

        void onSuccess(SlidersEntity slidersEntity);

        void onSuccess(RoomsEntity roomsEntity);

        void getRoomsFailed(Throwable throwable);

        void getMixFailed(Throwable throwable);
    }
}
