package com.lzc.liu.lzcproject.netapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SkyEyesStion on 2016/2/26.
 */
public class RxService {


    private static String BASETESTURL = "http://dm.toutiao.com/";
    private static OkHttpClient okHttpClient = OkHttp3Utils.getOkHttpSingletonInstance();

    private static Gson gson = new GsonBuilder()
            //配置你的Gson
            .setDateFormat("yyyy-MM-dd hh:mm:ss")
            .create();

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASETESTURL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();


    private RxService() {
        //construct

    }

    private static void init(){
        retrofit = null;
        retrofit = new Retrofit.Builder()
                .baseUrl(BASETESTURL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static void setUrls(String url){
        BASETESTURL = url;
        init();
    }

    public static <T> T createApi(Class<T> clazz) {
        return retrofit.create(clazz);
    }

}
