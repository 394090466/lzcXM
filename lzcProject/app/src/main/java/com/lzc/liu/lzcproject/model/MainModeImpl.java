package com.lzc.liu.lzcproject.model;

import android.util.Log;

import com.lzc.liu.lzcproject.bean.SubscrebedBean;
import com.lzc.liu.lzcproject.interfaces.model.MainMode;
import com.lzc.liu.lzcproject.netapi.Retrofit2Service;
import com.lzc.liu.lzcproject.netapi.RxService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liu on 2018/2/27.
 */

public class MainModeImpl implements MainMode{
    @Override
    public void GetSubscribed(final MainModeImpl.onMainListener listener) {
        RxService.createApi(Retrofit2Service.class).getSubscribed()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<SubscrebedBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SubscrebedBean subscrebedBean) {
                        listener.onSuccess(subscrebedBean);
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

    public interface onMainListener{

        void onSuccess(SubscrebedBean subscrebedBean);

        void onError();
    }
}