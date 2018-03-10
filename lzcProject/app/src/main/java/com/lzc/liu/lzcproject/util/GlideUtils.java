package com.lzc.liu.lzcproject.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.lzc.liu.lzcproject.R;

/**
 * Created by liu on 2018/3/3.
 */

public class GlideUtils {

    private static RequestOptions options = null;

    private static RequestBuilder<Drawable> requestBuilder = null;

    @SuppressLint("CheckResult")
    public static RequestBuilder<Drawable> loadImageView(Context context, String url, ImageView imageView) {
        if (options == null) {
            options = new RequestOptions();
            options
                    .fitCenter()
                    .placeholder(R.drawable.icon_loading)
                    .error(R.drawable.icon_loading)
                    .fallback(R.drawable.icon_loading);
        }
        requestBuilder = Glide.with(context)
                .asDrawable().apply(options);
        requestBuilder
                .clone()
                .load(url)
                .into(imageView);
        return requestBuilder;
    }

}
