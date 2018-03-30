package com.lzc.liu.lzcproject.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.lzc.liu.lzcproject.application.MainApplication;

/**
 * Created by liu on 2018/3/15.
 */

public abstract class ReactFragment extends BaseViewLazyFragment{

    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mReactRootView = new ReactRootView(activity);
        mReactInstanceManager = ((MainApplication) getActivity().getApplication()).getReactNativeHost().getReactInstanceManager();
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(mReactRootView);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mReactRootView.startReactApplication(mReactInstanceManager, getMainPageName(), null);
    }

    protected abstract String getMainPageName();

    protected void sendEvent(String eventName,
                             @Nullable WritableMap params) {
        if (((MainApplication) getActivity().getApplication()).getReactContext() != null) {
            ((MainApplication) getActivity().getApplication()).getReactContext()
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit(eventName, params);
        }
    }
}

