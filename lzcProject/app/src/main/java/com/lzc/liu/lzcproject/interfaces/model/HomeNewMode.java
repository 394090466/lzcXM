package com.lzc.liu.lzcproject.interfaces.model;

import com.lzc.liu.lzcproject.model.HomeNewModeImpl;

/**
 * Created by liu on 2018/3/2.
 */

public interface HomeNewMode {

    void GetNewData(String category ,int refer,int count,HomeNewModeImpl.onHomeNewListener listener);

}
