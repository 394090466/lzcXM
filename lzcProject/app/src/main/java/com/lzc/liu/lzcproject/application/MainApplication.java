package com.lzc.liu.lzcproject.application;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.lzc.liu.lzcproject.BuildConfig;
import com.lzc.liu.lzcproject.broadcastreceiver.ForegroundStateReceiver;
import com.lzc.liu.lzcproject.broadcastreceiver.NetStateReceiver;

import java.util.Arrays;
import java.util.List;


//
//                                  _oo8oo_
//                                 o8888888o
//                                 88" . "88
//                                 (| -_- |)
//                                 0\  =  /0
//                               ___/'==='\___
//                             .' \\|     |// '.
//                            / \\|||  :  |||// \
//                           / _||||| -:- |||||_ \
//                          |   | \\\  -  /// |   |
//                          | \_|  ''\---/''  |_/ |
//                          \  .-\__  '-'  __/-.  /
//                        ___'. .'  /--.--\  '. .'___
//                     ."" '<  '.___\_<|>_/___.'  >' "".
//                    | | :  `- `.:`\ _ /`:.`/ -`  : | |
//                    \  \ `-.   \_ __\ /__ _/   .-` /  /
//                =====`-.____`.___ \_____/ ___.`____.-`=====
//                                  `=---=`
//
//
//               ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
//                    佛祖保佑       永无BUG     永不修改
//

public class MainApplication extends Application implements ReactApplication {

    private ReactContext mReactContext;

    public ReactContext getReactContext() {
        return mReactContext;
    }

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            //            return BuildConfig.DEBUG;
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {

            return Arrays.<ReactPackage>asList(
                    new MainReactPackage()
            );
        }

//        @Nullable
//        @Override
//        protected String getJSBundleFile() {
//            String bundlepath = getApplicationContext().getFilesDir().getPath() + "/bundle/index.android.bundle";
//            return bundlepath;
//        }

        @Override
        protected String getJSMainModuleName() {
            return "index";
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    private void registerReactInstanceEventListener() {
        mReactNativeHost.getReactInstanceManager().addReactInstanceEventListener(mReactInstanceEventListener);
    }

    private void unRegisterReactInstanceEventListener() {
        mReactNativeHost.getReactInstanceManager().removeReactInstanceEventListener(mReactInstanceEventListener);
    }

    private final ReactInstanceManager.ReactInstanceEventListener mReactInstanceEventListener = new ReactInstanceManager.ReactInstanceEventListener() {
        @Override
        public void onReactContextInitialized(ReactContext context) {
            mReactContext = context;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        SoLoader.init(this, /* native exopackage */ false);
        broadcast();
        registerReactInstanceEventListener();
    }

    private void broadcast() {
        NetStateReceiver.registerNetworkStateReceiver(this);
        ForegroundStateReceiver.registerForegroundStateReceiver(this);
    }
}
