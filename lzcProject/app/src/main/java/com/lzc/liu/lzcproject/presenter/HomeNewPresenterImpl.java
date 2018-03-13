package com.lzc.liu.lzcproject.presenter;

import android.content.Context;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.lzc.liu.lzcproject.entity.NewContentBean;
import com.lzc.liu.lzcproject.entity.NewListBean;
import com.lzc.liu.lzcproject.entity.SubscrebedBean;
import com.lzc.liu.lzcproject.constant.NewData;
import com.lzc.liu.lzcproject.interfaces.model.HomeNewMode;
import com.lzc.liu.lzcproject.interfaces.presenter.HomeNewPresenter;
import com.lzc.liu.lzcproject.interfaces.view.HomeNewView;
import com.lzc.liu.lzcproject.interfaces.view.NewView;
import com.lzc.liu.lzcproject.model.HomeNewModeImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        long datatime = System.currentTimeMillis() / 1000;
        int number1 = new Random().nextInt(7) + 1;
        int number2 = new Random().nextInt(24) + 1;
        int number3 = new Random().nextInt(60) + 1;
        int number4 = new Random().nextInt(60) + 1;
        long chatime = number1 * number2 * number3 * number4;
        long behot_time = datatime - chatime;
        if (refer == 1) {
            homeNewMode.GetNewDataRefresh(category, refer, count, datatime, behot_time, this);
        } else {
            homeNewMode.GetNewDataLoadMore(category, refer, count, datatime, behot_time, this);
        }
    }

    @Override
    public void onSuccess(NewListBean newListBean, String category) {
        ArrayList<NewListBean.DataBean> dataBean = new ArrayList<>();

        if (StringUtils.equals(NewData.getEssay_joke(), category) || StringUtils.equals(NewData.getImage_funny(), category)) {
            dataBean.addAll(newListBean.getData());
            //        } else if () {

        } else {
            for (int i = 0; i < newListBean.getData().size(); i++) {
                NewContentBean newContentBean = new Gson().fromJson(newListBean.getData().get(i).getContent(), NewContentBean.class);

                if (!StringUtils.isEmpty(newContentBean.getAd_label()) && newContentBean.getAd_label().equals("广告")) {
                    continue;
                }

                if (ObjectUtils.isEmpty(newContentBean.getUser_info())) {
                    continue;
                }

                dataBean.add(newListBean.getData().get(i));
            }
        }


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
