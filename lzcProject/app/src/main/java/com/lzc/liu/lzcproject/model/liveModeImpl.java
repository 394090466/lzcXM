package com.lzc.liu.lzcproject.model;

import android.util.Log;

import com.lzc.liu.lzcproject.entity.douyu.ChannelsEntity;
import com.lzc.liu.lzcproject.interfaces.model.liveMode;
import com.lzc.liu.lzcproject.netapi.Retrofit2Service;
import com.lzc.liu.lzcproject.netapi.RxService;

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
        RxService.createApi(Retrofit2Service.class).getChannels()
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

    public interface onliveModeListener{

        void onSuccess(ChannelsEntity channelsEntity);

        void onError();
    }
}
