package com.lzc.liu.lzcproject.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.ArrayList;

/**
 * 应用当前是否前台
 */

public class ForegroundStateReceiver extends BroadcastReceiver{

    public static String CUSTOM_ANDROID_FOREGROUND_ACTION = "ANDROID_FOREGROUND_ACTION";

    public static String ANDROID_FOREGROUND_ACTION = "android_froeground.com.FOREGROUND_ACTION";

    public static String FOREGROUND_STATE_DATA = "FOREGROUND_STATE_DATA";

    private static BroadcastReceiver mBroadcastReceiver;

    private static ArrayList<ForegroundObserver> foregroundObservers = new ArrayList<>();

    private static BroadcastReceiver getReceiver() {
        if (null == mBroadcastReceiver) {
            synchronized (ForegroundStateReceiver.class) {
                if (null == mBroadcastReceiver) {
                    mBroadcastReceiver = new ForegroundStateReceiver();
                }
            }
        }
        return mBroadcastReceiver;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mBroadcastReceiver  = ForegroundStateReceiver.this;
        if (intent.getAction().equalsIgnoreCase(ANDROID_FOREGROUND_ACTION) || intent.getAction().equalsIgnoreCase(CUSTOM_ANDROID_FOREGROUND_ACTION)) {
            notifyObserver();
        }
    }

    /**
     * 注册
     *
     * @param mContext
     */
    public static void registerForegroundStateReceiver(Context mContext) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(CUSTOM_ANDROID_FOREGROUND_ACTION);
        filter.addAction(ANDROID_FOREGROUND_ACTION);
        mContext.getApplicationContext().registerReceiver(getReceiver(), filter);
    }

    /**
     * 清除
     *
     * @param mContext
     */
    public static void checkForegroundState(Context mContext) {
        Intent intent = new Intent();
        intent.setAction(CUSTOM_ANDROID_FOREGROUND_ACTION);
        mContext.sendBroadcast(intent);
    }

    /**
     * 反注册
     *
     * @param mContext
     */
    public static void unRegisterForegroundStateReceiver(Context mContext) {
        if (mBroadcastReceiver != null) {
            try {
                mContext.getApplicationContext().unregisterReceiver(mBroadcastReceiver);
            } catch (Exception e) {

            }
        }

    }

    /**
     * 添加监听
     *
     * @param observer
     */
    public static void registerObserver(ForegroundObserver observer) {
        if (foregroundObservers == null) {
            foregroundObservers = new ArrayList<ForegroundObserver>();
        }

        if (!foregroundObservers.contains(observer)) {
            foregroundObservers.add(observer);
        }
    }

    /**
     * 移除监听
     *
     * @param observer
     */
    public static void removeRegisterObserver(ForegroundObserver observer) {
        if (foregroundObservers != null) {
            if (foregroundObservers.contains(observer)) {
                foregroundObservers.remove(observer);
            }
        }
    }

    private void notifyObserver() {
        if (!foregroundObservers.isEmpty()) {
            int size = foregroundObservers.size();
            for (int i = 0; i < size; i++) {
                ForegroundObserver observer = foregroundObservers.get(i);
                if (observer != null) {
                   observer.onForegroundState();
                }
            }
        }
    }


    /**
     * 前台方法回调
     */
    public interface ForegroundObserver {

        /**
         * 切换到后台，关闭语音
         */
        void onForegroundState();


    }

}
