package com.lzc.liu.lzcproject.presenter;

import android.content.Context;

import com.lzc.liu.lzcproject.bean.NewListBean;
import com.lzc.liu.lzcproject.bean.SubscrebedBean;
import com.lzc.liu.lzcproject.constant.NewData;
import com.lzc.liu.lzcproject.interfaces.model.HomeNewMode;
import com.lzc.liu.lzcproject.interfaces.presenter.HomeNewPresenter;
import com.lzc.liu.lzcproject.interfaces.view.HomeNewView;
import com.lzc.liu.lzcproject.interfaces.view.NewView;
import com.lzc.liu.lzcproject.model.HomeNewModeImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liu on 2018/3/2.
 */

public class HomeNewPresenterImpl implements HomeNewPresenter, HomeNewModeImpl.onHomeNewListener {

    private Context mContext;

    private HomeNewView homeNewView;

    private HomeNewMode homeNewMode;

    private NewView newView;

    public HomeNewPresenterImpl(Context context, HomeNewView homeNewView) {
        this.mContext = context;
        this.homeNewView = homeNewView;
        this.homeNewMode = new HomeNewModeImpl();
    }

    public HomeNewPresenterImpl(Context context, NewView newView) {
        this.mContext = context;
        this.newView = newView;
        this.homeNewMode = new HomeNewModeImpl();
    }

    @Override
    public void GetSubscribed() {
        homeNewMode.GetSubscribed(this);
    }

    @Override
    public void GetNewData(String category, int refer, int count) {
        long datatime = System.currentTimeMillis()/1000;
        long chatime = 4*24*60*60;
        long behot_time = datatime-chatime;
        if(refer == 1){
            homeNewMode.GetNewDataRefresh(category,refer,count,datatime,behot_time,this);
        }else {
            homeNewMode.GetNewDataLoadMore(category,refer,count,datatime,behot_time,this);
        }
    }

    @Override
    public void onSuccess(NewListBean newListBean) {
        ArrayList<NewListBean.DataBean> dataBean = (ArrayList<NewListBean.DataBean>) newListBean.getData();
        homeNewView.onRefure(dataBean);
    }

    @Override
    public void onSuccess(SubscrebedBean subscrebedBean) {
        List<SubscrebedBean.DataBeanX.DataBean> newlist = subscrebedBean.getData().getData();
        NewData.setNewTabList(newlist);
        newView.onSubscribed();
    }


    @Override
    public void onError() {

    }
}
