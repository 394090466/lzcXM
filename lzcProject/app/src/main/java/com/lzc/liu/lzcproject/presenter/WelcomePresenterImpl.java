package com.lzc.liu.lzcproject.presenter;

import android.content.Context;

import com.blankj.utilcode.util.SPUtils;
import com.lzc.liu.lzcproject.bean.NewDataBean;
import com.lzc.liu.lzcproject.interfaces.model.WelcomeModel;
import com.lzc.liu.lzcproject.interfaces.presenter.WelcomePresenter;
import com.lzc.liu.lzcproject.interfaces.view.WelcomeView;
import com.lzc.liu.lzcproject.model.WelcomeModeImpl;
import com.lzc.liu.lzcproject.netapi.RxService;

import java.util.List;

/**
 * Created by liu on 2018/3/1.
 */

public class WelcomePresenterImpl implements WelcomePresenter, WelcomeModeImpl.onWelcomeListener {

    private Context mContext;

    private WelcomeView welcomeView;

    private WelcomeModel welcomeModel;

    private String INIT_URL = "initurl";

    public WelcomePresenterImpl(Context context, WelcomeView welcomeView) {
        this.mContext = context;
        this.welcomeView = welcomeView;
        this.welcomeModel = new WelcomeModeImpl();
    }

    @Override
    public void AppDataInit() {
        welcomeModel.GetDataInit(this);
    }


    @Override
    public void onSuccess(NewDataBean newDataBean) {
        List<NewDataBean.DataBean.IHostListBean> hostlist = newDataBean.get_$Data114().getI_host_list();
        int https_to_http = newDataBean.get_$Data114().getHttps_to_http();
        hostlist.get(1).getMax_time();
        String url = "http://" + hostlist.get(https_to_http).getHost() + "/";
        RxService.setUrls(url);
        SPUtils.getInstance().put(INIT_URL, url);
        welcomeView.onInitOver();
    }

    @Override
    public void onError() {
        String url = SPUtils.getInstance().getString(INIT_URL, null);
        if (url != null) {
            RxService.setUrls(url);
        }
    }


}
