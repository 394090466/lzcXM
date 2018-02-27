package com.lzc.liu.lzcproject.netapi;

import com.blankj.utilcode.util.LogUtils;
import com.lzc.liu.lzcproject.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liu on 2018/2/27.
 */

public class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (BuildConfig.DEBUG) {
            LogUtils.i(String.format("发送请求 %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));
        }
        Request.Builder builder = request.newBuilder();
        builder.addHeader("token", "123");
        return chain.proceed(builder.build());
    }

}
