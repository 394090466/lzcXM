package com.lzc.liu.lzcproject.netapi;

import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzc.liu.lzcproject.constant.Constant;

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
    public static int TOUTIAO_TYPE = 1;
    public static int DOYU_TYPE = 2;
    private static int type = 1;

    private static Gson gson = new GsonBuilder()
            //配置你的Gson
            .setDateFormat("yyyy-MM-dd hh:mm:ss")
            .create();

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constant.TOUTIAO_BASE_URL)
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

    public static <T> T createToutiaoApi(Class<T> clazz) {
        if (!StringUtils.isEmpty(Constant.JINRITOUTIAO)){
            if (!StringUtils.equals(BASETESTURL,Constant.JINRITOUTIAO)){
                BASETESTURL = Constant.JINRITOUTIAO;
                init();
                type = TOUTIAO_TYPE;
            }
        }else {
            if (!StringUtils.equals(BASETESTURL,Constant.TOUTIAO_BASE_URL)){
                BASETESTURL = Constant.TOUTIAO_BASE_URL;
                init();
                type = TOUTIAO_TYPE;
            }
        }

        return retrofit.create(clazz);
    }

    public static <T> T createDouyuApi(Class<T> clazz) {
        if (!StringUtils.equals(BASETESTURL,Constant.DOUYU_BASE_URL)){
            BASETESTURL = Constant.DOUYU_BASE_URL;
            init();
            type = DOYU_TYPE;
        }
        return retrofit.create(clazz);
    }

    public static int getType() {
        return type;
    }
}
