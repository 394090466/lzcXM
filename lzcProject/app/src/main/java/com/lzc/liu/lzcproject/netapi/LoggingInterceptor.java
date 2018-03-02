package com.lzc.liu.lzcproject.netapi;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.lzc.liu.lzcproject.BuildConfig;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by liu on 2018/2/27.
 */

public class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (!NetworkUtils.isConnected()) {
            //无网络下强制使用缓存，无论缓存是否过期,此时该请求实际上不会被发送出去。
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }

        okhttp3.Response response = chain.proceed(request);

        if (NetworkUtils.isConnected()) {//有网络情况下，根据请求接口的设置，配置缓存。
            //这样在下次请求时，根据缓存决定是否真正发出请求。
            String cacheControl = request.cacheControl().toString();
            //当然如果你想在有网络的情况下都直接走网络，那么只需要
            //将其超时时间这是为0即可:String cacheControl="Cache-Control:public,max-age=0"
            int maxAge = 60 * 60; // read from cache for 1 minute
            response = response.newBuilder()
                    //                            .header("Cache-Control", cacheControl)
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .removeHeader("Pragma")
                    .build();
        } else {
            //无网络
            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
            response = response.newBuilder()
                    //                            .header("Cache-Control", "public,only-if-cached,max-stale=360000")
                    .header("Cache-Control", "public,only-if-cached,max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }



        request = response.request();
        Request.Builder builder = request.newBuilder();
        builder.addHeader("Accept-Language", "zh-CN,zh");
        RequestBody requestBody = new FormBody.Builder()
                .add("device_id", "39053133130")
                .add("ac", "wifi")
                .add("app_name", "news_article")
                .add("version_code", "623")
                .add("version_name", "6.2.3")
                .add("device_platform", "android")
                .build();
        builder.post(requestBody);
        request = builder.build();
        if (BuildConfig.DEBUG) {
            LogUtils.i(String.format("发送请求 %s%n%s%n%s%n%s",
                    request.url(), chain.connection(), request.headers(),"body:"+bodyToString(request)));
        }

        return chain.proceed(request);
    }


    private static String bodyToString(final Request request){

        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

}
