package com.lzc.liu.lzcproject.model;

import android.util.Log;

import com.lzc.liu.lzcproject.bean.NewListBean;
import com.lzc.liu.lzcproject.bean.SubscrebedBean;
import com.lzc.liu.lzcproject.interfaces.model.HomeNewMode;
import com.lzc.liu.lzcproject.netapi.Retrofit2Service;
import com.lzc.liu.lzcproject.netapi.RxService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeNewModeImpl implements HomeNewMode{

    @Override
    public void GetSubscribed(final onHomeNewListener listener) {
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

    @Override
    public void GetNewDataRefresh(final String category , final int refer, int count,long refreshtime,long min_behot_time, final onHomeNewListener listener) {
        RxService.createApi(Retrofit2Service.class).getNewDataRefresh(category,refer,count,refreshtime,min_behot_time)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<NewListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewListBean subscrebedBean) {
                        listener.onSuccess(subscrebedBean,category);
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

    @Override
    public void GetNewDataLoadMore(final String category, int refer, int count, long refreshtime, long max_behot_time, final onHomeNewListener listener) {
        RxService.createApi(Retrofit2Service.class).getNewDataLoadMore(category,refer,count,refreshtime,max_behot_time)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<NewListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewListBean subscrebedBean) {
                        listener.onSuccess(subscrebedBean,category);
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

        void onSuccess(SubscrebedBean subscrebedBean);

        void onSuccess(NewListBean newListBean,String category);

        void onError();
    }

}
