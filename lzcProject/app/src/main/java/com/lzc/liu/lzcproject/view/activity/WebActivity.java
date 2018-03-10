package com.lzc.liu.lzcproject.view.activity;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.base.BaseActivity;

import butterknife.BindView;

public class WebActivity extends BaseActivity {

    @BindView(R.id.ll_webview)
    LinearLayout llWebview;

    private AgentWeb mAgentWeb;

    private static String WebUrl = "WEBURL";


    @Override
    protected int getView() {
        return R.layout.activity_web;
    }

    @Override
    protected void onNetworkConnected(int type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    public void initView() {
        String url = getIntent().getStringExtra(WebUrl);
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(llWebview, new LinearLayout.LayoutParams(-1, -1))// 设置 AgentWeb 的父控件 ， 这里的view 是 LinearLayout ， 那么需要传入 LinearLayout.LayoutParams
                .useDefaultIndicator(-1, 3)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)
                .interceptUnkownUrl()
                .createAgentWeb()
                .ready()
                .go(url);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 启动这个Activity的Intent
     *
     * @param context 内容
     */
    public static void createIntent(Context context, String weburl) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(WebUrl, weburl);
        context.startActivity(intent);
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

}
