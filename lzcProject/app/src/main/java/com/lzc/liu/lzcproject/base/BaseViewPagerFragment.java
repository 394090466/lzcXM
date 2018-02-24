package com.lzc.liu.lzcproject.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.NetworkUtils;
import com.lzc.liu.lzcproject.broadcastreceiver.NetStateReceiver;
import com.lzc.liu.lzcproject.common.LazyViewPager;


/**
 * Created by liu on 2016/12/6.
 */

public abstract class BaseViewPagerFragment extends LazyViewPager {

    private LayoutInflater inflater;
    private ViewGroup container;
    private View mLayoutView;

    protected NetStateReceiver.NetChangeObserver mNetChangeObserver = null;

    private boolean isNetAvailable;

    private BaseActivity mActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        mLayoutView = inflater.inflate(getViewID(), container, false);
        setContentView(mLayoutView);
        initView();     //初始化布局
        initData();
        initListener();
        netstate();
        broadcast();

    }

    /**
     * 获取视图
     *
     * @return 视图
     */
    protected abstract int getViewID();

    @Override
    protected void onFragmentStartLazy() {
        super.onFragmentStartLazy();
    }

    /**
     * 初始化视图
     */
    public abstract void initView();

    /**
     * 网络连接状态
     *
     * @param type 网络状态
     */
    protected abstract void onNetworkConnected(int type);

    /**
     * 网络断开的时候调用
     */
    protected abstract void onNetworkDisConnected();

    /**
     * data数据方法
     */
    public abstract void initData();

    /**
     * listener事件监听方法
     */
    public abstract void initListener();

    /**
     * 开启广播监听网络
     */
    private void broadcast() {
        // 网络改变的一个回掉类
        mNetChangeObserver = new NetStateReceiver.NetChangeObserver() {
            @Override
            public void onNetConnected(int type) {
                if (!isNetAvailable) {
                    onNetworkConnected(type);
                    isNetAvailable = true;
                }
            }

            @Override
            public void onNetDisConnect() {
                if (isNetAvailable) {
                    onNetworkDisConnected();
                    isNetAvailable = false;
                }
            }
        };

        //开启广播去监听 网络 改变事件
        NetStateReceiver.registerObserver(mNetChangeObserver);
    }

    @Override
    public void onPauseLazy() {
        NetStateReceiver.removeRegisterObserver(mNetChangeObserver);
        super.onPauseLazy();
    }


    @Override
    public void onResumeLazy() {
        NetStateReceiver.registerObserver(mNetChangeObserver);
        super.onResumeLazy();
    }


    /**
     * 判断当前网络状态
     */
    private void netstate() {
        if (NetworkUtils.isConnected()) {
            onNetworkConnected(0);
            isNetAvailable = true;
        } else {
            onNetworkDisConnected();
            isNetAvailable = false;
        }
    }

    /**
     * 获取Activity
     *
     * @return
     */
    public BaseActivity getBaseActivity() {
        if (mActivity == null) {
            mActivity = (BaseActivity) getActivity();
        }
        return mActivity;
    }



}
