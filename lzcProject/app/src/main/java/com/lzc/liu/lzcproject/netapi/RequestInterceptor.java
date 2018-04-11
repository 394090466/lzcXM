package com.lzc.liu.lzcproject.netapi;

import com.blankj.utilcode.util.LogUtils;
import com.lzc.liu.lzcproject.BuildConfig;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by liu on 2018/2/27.
 */

public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (RxService.getType() == RxService.TOUTIAO_TYPE){
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
        }else {
            Request.Builder builder = request.newBuilder();
            RequestBody requestBody = new FormBody.Builder()
                    .build();
            builder.post(requestBody);
            request = builder.build();
        }

        if (BuildConfig.DEBUG) {
            LogUtils.i(String.format("发送请求 %s%n%s%n%s%n%s",
                    request.url(), chain.connection()+"", request.headers()+"","body:"+bodyToString(request)));
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
