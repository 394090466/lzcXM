package com.lzc.liu.lzcproject.model;

import android.util.Log;

import com.lzc.liu.lzcproject.bean.NewListBean;
import com.lzc.liu.lzcproject.interfaces.model.HomeNewMode;
import com.lzc.liu.lzcproject.netapi.Retrofit2Service;
import com.lzc.liu.lzcproject.netapi.RxService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeNewModeImpl implements HomeNewMode{

    @Override
    public void GetNewData(final String category , final int refer, int count, final onHomeNewListener listener) {
        RxService.createApi(Retrofit2Service.class).getNewData(category,refer,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<NewListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewListBean subscrebedBean) {
                        listener.onSuccess(subscrebedBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("lzc", "--网络异常--：" + e.toString());
                        listener.onError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface onHomeNewListener{

        void onSuccess(NewListBean newListBean);

        void onError();
    }

}
