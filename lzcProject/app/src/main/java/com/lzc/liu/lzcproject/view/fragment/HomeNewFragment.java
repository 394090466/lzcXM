package com.lzc.liu.lzcproject.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.StringUtils;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.just.agentweb.AgentWeb;
import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.adapter.NewDataItemTypeAdapter;
import com.lzc.liu.lzcproject.base.BaseViewLazyFragment;
import com.lzc.liu.lzcproject.bean.NewContentBean;
import com.lzc.liu.lzcproject.bean.NewListBean;
import com.lzc.liu.lzcproject.constant.NewData;
import com.lzc.liu.lzcproject.interfaces.presenter.HomeNewPresenter;
import com.lzc.liu.lzcproject.interfaces.view.HomeNewView;
import com.lzc.liu.lzcproject.presenter.HomeNewPresenterImpl;
import com.lzc.liu.lzcproject.view.activity.WebActivity;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;

import butterknife.BindView;

public class HomeNewFragment extends BaseViewLazyFragment implements HomeNewView {

    /**
     * 每一页展示多少条数据
     */
    private int REQUEST_COUNT = 10;

    public static final String INTENT_INT_INDEX = "intent_int_index";


    @BindView(R.id.lrv_newhome)
    LRecyclerView recyclerView;

    private HomeNewPresenter homeNewPresenter;

    private int Page = 1;

    private String category;

    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;

    private LoadMoreWrapper moreWrapper = null;

    private NewDataItemTypeAdapter mAdapter;

    private ArrayList<NewListBean.DataBean> datalist;

    private AgentWeb mAgentWeb;

    @Override
    protected int getViewID() {
        return R.layout.fragment_new_home;
    }

    @Override
    public void initView() {
        Bundle bundle = getArguments();
        int index = bundle.getInt(INTENT_INT_INDEX);
        for (int i = 0; i < NewData.getDataBeanList().size(); i++) {
            if (NewData.getDataBeanList().get(i).getName().equals(NewData.getNewTabList().get(index))) {
                category = NewData.getDataBeanList().get(i).getCategory();
            }
        }
        if (StringUtils.equals(category,NewData.getJinritemai())){
            recyclerView.setVisibility(View.GONE);
            String url = "";
            for (int i = 0; i< NewData.getDataBeanList().size(); i ++){
                if (StringUtils.equals(NewData.getDataBeanList().get(i).getCategory(),category)){
                    url = NewData.getDataBeanList().get(i).getWeb_url();
                }
            }
            mAgentWeb = AgentWeb.with(this)//这里需要把 Activity 、 和 Fragment  同时传入
                    .setAgentWebParent((ViewGroup) getView(), new LinearLayout.LayoutParams(-1, -1))// 设置 AgentWeb 的父控件 ， 这里的view 是 LinearLayout ， 那么需要传入 LinearLayout.LayoutParams
                    .useDefaultIndicator()// 使用默认进度条
                    .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK) //注意这里开启 strict 模式 ， 设备低于 4.2 情况下回把注入的 Js 全部清空掉 ， 这里推荐使用 onJsPrompt 通信
                    .createAgentWeb()//
                    .ready()//
                    .go("http://www.jd.com");

        }else {
            homeNewPresenter = new HomeNewPresenterImpl(getBaseActivity(), this);
            datalist = new ArrayList<>();


            recyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
            mAdapter = new NewDataItemTypeAdapter(getBaseActivity(), datalist,category);
            mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
            moreWrapper = new LoadMoreWrapper(mAdapter);
            DividerDecoration divider = new DividerDecoration.Builder(getBaseActivity())
                    .setHeight(R.dimen.dp_0_5)
                    .setPadding(R.dimen.dp_15)
                    .setColorResource(R.color.gray_cc)
                    .build();
            recyclerView.addItemDecoration(divider);
            recyclerView.setAdapter(mLRecyclerViewAdapter);
        }

    }

    @Override
    protected void onNetworkConnected(int type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    public void initData() {
        if (!StringUtils.equals(category,NewData.getJinritemai())){
            refresh();
        }
    }

    private void refresh() {
        homeNewPresenter.GetNewData(category, Page, REQUEST_COUNT);
    }

    @Override
    public void onRefure(ArrayList<NewListBean.DataBean> dataBean) {
        if (Page == 1) {
            datalist.clear();
        }
        datalist.addAll(dataBean);
        mLRecyclerViewAdapter.notifyDataSetChanged();
        recyclerView.refreshComplete(Page);
    }

    @Override
    public void initListener() {
        if (!StringUtils.equals(category,NewData.getJinritemai())){
            recyclerView.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh() {
                    Page = 1;
                    refresh();
                }
            });

            recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    Page++;
                    refresh();
                }
            });
            mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    //                HighlightReplaysBean.ListBean listBean = datalist.get(position);
                    //                String[] strings = {listBean.getTime(),listBean.getName(),listBean.getIntroduce(),listBean.getUrl()};
                    //                HighlightsActivity.createIntent(WonderfulReviewActivity.this,strings);
                    String stringjsonData = datalist.get(position).getContent();
                    NewContentBean newContentBean = new Gson().fromJson(stringjsonData, NewContentBean.class);
                    WebActivity.createIntent(getBaseActivity(),newContentBean.getArticle_url());
                }
            });
        }
    }


    @Override
    public void onPauseLazy() {
        if (mAgentWeb!=null){
            mAgentWeb.getWebLifeCycle().onPause();
        }
        super.onPauseLazy();
    }


    @Override
    public void onResumeLazy() {
        if (mAgentWeb!=null){
            mAgentWeb.getWebLifeCycle().onResume();
        }
        super.onResumeLazy();
    }

    @Override
    protected void onDestroyViewLazy() {
        if (mAgentWeb!=null){
            mAgentWeb.getWebLifeCycle().onDestroy();
        }
        super.onDestroyViewLazy();
    }
}
