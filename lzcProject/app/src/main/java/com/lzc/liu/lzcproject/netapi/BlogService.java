package com.lzc.liu.lzcproject.netapi;

import com.lzc.liu.lzcproject.bean.NewDataBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by liu on 2018/2/27.
 */

public interface BlogService {
    @GET("blog/{id}")
    Call<NewDataBean> getBlog(@Header("apikey") String apikey, @Path("id") int id, @Path("l") int a);

    @GET("get_domains/v4/")
    Call<ResponseBody> getBlog();

    @GET("get_domains/v4/")
    Observable<NewDataBean> getBlogs();
}
