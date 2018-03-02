package com.lzc.liu.lzcproject.interfaces.model;

import com.lzc.liu.lzcproject.model.MainModeImpl;

/**
 * Created by liu on 2018/2/27.
 */

public interface MainMode {

    void GetSubscribed(MainModeImpl.onMainListener listener);

}
