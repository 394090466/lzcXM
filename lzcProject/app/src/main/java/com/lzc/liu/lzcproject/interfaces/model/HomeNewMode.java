package com.lzc.liu.lzcproject.interfaces.model;

import com.lzc.liu.lzcproject.model.HomeNewModeImpl;

/**
 * Created by liu on 2018/3/2.
 */

public interface HomeNewMode {

    void GetSubscribed(HomeNewModeImpl.onHomeNewListener listener);

    void GetNewDataRefresh(String category ,int refer,int count,long refreshtime,long min_behot_time,HomeNewModeImpl.onHomeNewListener listener);

    void GetNewDataLoadMore(String category ,int refer,int count,long refreshtime,long max_behot_time,HomeNewModeImpl.onHomeNewListener listener);

}
