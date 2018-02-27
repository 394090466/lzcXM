package com.lzc.liu.lzcproject.view.activity;

import android.util.Log;

import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.base.BaseActivity;
import com.lzc.liu.lzcproject.bean.NewDataBean;
import com.lzc.liu.lzcproject.netapi.BlogService;
import com.lzc.liu.lzcproject.netapi.RxService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WelcomeActivity extends BaseActivity {


    @Override
    protected int getView() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void onNetworkConnected(int type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    public void initView() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://dm.toutiao.com/")
//                .build();
//        BlogService service = retrofit.create(BlogService.class);
//
//        Call<ResponseBody> call = service.getBlog();
//        // 用法和OkHttp的call如出一辙,
//        // 不同的是如果是Android系统回调方法执行在主线程
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    System.out.println(response.body().string());
//                    Log.v("lzc",response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });

        BlogService service  = RxService.createApi(BlogService.class);
        service.getBlogs()
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewDataBean newDataBean) {
                        Log.v("lzc",newDataBean.getMessage());
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.v("lzc","---"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
