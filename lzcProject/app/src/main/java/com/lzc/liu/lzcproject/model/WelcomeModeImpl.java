package com.lzc.liu.lzcproject.model;

import android.util.Log;

import com.lzc.liu.lzcproject.bean.NewDataBean;
import com.lzc.liu.lzcproject.interfaces.model.WelcomeModel;
import com.lzc.liu.lzcproject.netapi.Retrofit2Service;
import com.lzc.liu.lzcproject.netapi.RxService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WelcomeModeImpl implements WelcomeModel {



    @Override
    public void GetDataInit(final onWelcomeListener listener) {
        RxService.createApi(Retrofit2Service.class).getDataInit("adc=322230")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<NewDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewDataBean newDataBean) {
                        listener.onSuccess(newDataBean);
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

    public interface onWelcomeListener{

        void onSuccess(NewDataBean newDataBean);

        void onError();
    }
}
