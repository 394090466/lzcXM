package com.lzc.liu.lzcproject.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.lzc.liu.lzcproject.application.MainApplication;
import com.lzc.liu.lzcproject.broadcastreceiver.NetStateReceiver;
import com.lzc.liu.lzcproject.util.AppManagerUtils;

import butterknife.ButterKnife;

/**
 * Created by liu on 2018/2/24.
 */

public abstract class BaseActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {

    protected NetStateReceiver.NetChangeObserver mNetChangeObserver = null;

    private boolean isNetAvailable;

    private ReactInstanceManager mReactInstanceManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        initView();
        initData();
        initListener();
        netstate();
        broadcast();
        AppManagerUtils.getAppManager().addActivity(this);
        /**
         * Get the reference to the ReactInstanceManager
         */

        mReactInstanceManager =
                ((MainApplication) getApplication()).getReactNativeHost().getReactInstanceManager();

    }

    /**
     * 处理视图
     */
    private void setView() {
//        BarUtils.setNavBarImmersive(this);
        BarUtils.setStatusBarAlpha(this,100);
        setContentView(getView());
        ButterKnife.bind(this);
    }

    /**
     * 获取视图
     *
     * @return 视图
     */
    protected abstract int getView();

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
     * UI显示方法
     */
    public abstract void initView();

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

    public void closeActivity() {
        AppManagerUtils.getAppManager().finishActivity(this);
    }

    @Override
    protected void onPause() {
        NetStateReceiver.removeRegisterObserver(mNetChangeObserver);
        super.onPause();
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostPause();
        }
    }

    @Override
    protected void onResume() {
        NetStateReceiver.registerObserver(mNetChangeObserver);
        super.onResume();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostResume(this, this);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        AppManagerUtils.getAppManager().finishActivity(this);
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

}
