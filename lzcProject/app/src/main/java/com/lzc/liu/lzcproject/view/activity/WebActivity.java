package com.lzc.liu.lzcproject.view.activity;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.base.BaseActivity;
import com.lzc.liu.lzcproject.util.GlideUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class WebActivity extends BaseActivity {

    @BindView(R.id.ll_webview)
    LinearLayout llWebview;
    @BindView(R.id.web_bank)
    ImageView webBank;
    @BindView(R.id.iv_webicon)
    ImageView ivWebicon;
    @BindView(R.id.tv_title_web)
    TextView tvTitleWeb;


    private AgentWeb mAgentWeb;

    private static String WebUrl = "WEBURL";

    private static String iconUrl = "ICONURL";

    private static String titletext = "TITLETEXT";


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
        String weburls = getIntent().getStringExtra(WebUrl);
        String titletexturls = getIntent().getStringExtra(iconUrl);
        String titletexs = getIntent().getStringExtra(titletext);
        GlideUtils.loadImageView(this,titletexturls,ivWebicon);
        tvTitleWeb.setText(titletexs);
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(llWebview, new LinearLayout.LayoutParams(-1, -1))// 设置 AgentWeb 的父控件 ， 这里的view 是 LinearLayout ， 那么需要传入 LinearLayout.LayoutParams
                .useDefaultIndicator(-1, 3)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)
                .interceptUnkownUrl()
                .createAgentWeb()
                .ready()
                .go(weburls);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick(R.id.web_bank)
    public void onViewClicked() {
        closeActivity();
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
    public static void createIntent(Context context, String weburl,String icon_url,String texttile) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(WebUrl, weburl);
        intent.putExtra(iconUrl, icon_url);
        intent.putExtra(titletext, texttile);
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
