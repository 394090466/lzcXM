package com.lzc.liu.lzcproject.presenter;

import android.content.Context;

import com.lzc.liu.lzcproject.interfaces.model.MainMode;
import com.lzc.liu.lzcproject.interfaces.presenter.MainPresenter;
import com.lzc.liu.lzcproject.interfaces.view.MainView;
import com.lzc.liu.lzcproject.model.MainModeImpl;

public class MainPresenterImpl implements MainPresenter, MainModeImpl.onMainListener {

    private Context mContext;

    private MainView mainView;

    private MainMode mainMode;

    public MainPresenterImpl(Context context, MainView mainView) {
        this.mContext = context;
        this.mainView = mainView;
        this.mainMode = new MainModeImpl();
    }



    @Override
    public void onSuccess() {
    }

    @Override
    public void onError() {

    }
}
