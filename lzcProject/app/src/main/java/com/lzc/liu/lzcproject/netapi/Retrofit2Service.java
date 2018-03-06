package com.lzc.liu.lzcproject.netapi;

import com.lzc.liu.lzcproject.bean.NewDataBean;
import com.lzc.liu.lzcproject.bean.NewListBean;
import com.lzc.liu.lzcproject.bean.SubscrebedBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Retrofit2Service {

    @GET("blog/{id}")
    Call<NewDataBean> getBlog(@Header("apikey") String apikey, @Path("id") int id, @Path("l") int a);

    @POST("get_domains/v4/")
    Observable<NewDataBean> getDataInit(@Query("acd") String id);

    @POST("article/category/get_subscribed/v1/")
    Observable<SubscrebedBean> getSubscribed();

    @POST("api/news/feed/v57/")
    Observable<NewListBean> getNewDataRefresh(@Query("category") String category, @Query("refer") int refer,
                                              @Query("count") int count, @Query("last_refresh_sub_entrance_interval") long refresh,
                                              @Query("min_behot_time") long min_behot_time);

    @POST("api/news/feed/v57/")
    Observable<NewListBean> getNewDataLoadMore(@Query("category") String category, @Query("refer") int refer,
                                              @Query("count") int count, @Query("last_refresh_sub_entrance_interval") long refresh,
                                              @Query("max_behot_time") long max_behot_time);

}
