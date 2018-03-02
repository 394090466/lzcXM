package com.lzc.liu.lzcproject.presenter;

import android.content.Context;

import com.lzc.liu.lzcproject.bean.SubscrebedBean;
import com.lzc.liu.lzcproject.interfaces.model.HomeNewMode;
import com.lzc.liu.lzcproject.interfaces.presenter.HomeNewPresenter;
import com.lzc.liu.lzcproject.interfaces.view.HomeNewView;
import com.lzc.liu.lzcproject.model.HomeNewModeImpl;

/**
 * Created by liu on 2018/3/2.
 */

public class HomeNewPresenterImpl implements HomeNewPresenter, HomeNewModeImpl.onHomeNewListener {

    private Context mContext;

    private HomeNewView homeNewView;

    private HomeNewMode homeNewMode;

    public HomeNewPresenterImpl(Context context, HomeNewView homeNewView) {
        this.mContext = context;
        this.homeNewView = homeNewView;
        this.homeNewMode = new HomeNewModeImpl();
    }

    @Override
    public void GetNewData(String category, int refer, int count) {
        homeNewMode.GetNewData(category, refer, count, this);
    }

    @Override
    public void onSuccess(SubscrebedBean subscrebedBean) {

    }

    @Override
    public void onError() {

    }
}
