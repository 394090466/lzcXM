package com.lzc.liu.lzcproject.model;

import com.lzc.liu.lzcproject.interfaces.model.MainMode;

/**
 * Created by liu on 2018/2/27.
 */

public class MainModeImpl implements MainMode{


    public interface onMainListener{

        void onSuccess();

        void onError();
    }
}
