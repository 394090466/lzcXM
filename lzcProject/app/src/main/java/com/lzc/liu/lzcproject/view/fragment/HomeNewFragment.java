package com.lzc.liu.lzcproject.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.adapter.NewDataItemTypeAdapter;
import com.lzc.liu.lzcproject.base.BaseViewLazyFragment;
import com.lzc.liu.lzcproject.bean.NewListBean;
import com.lzc.liu.lzcproject.constant.NewData;
import com.lzc.liu.lzcproject.interfaces.presenter.HomeNewPresenter;
import com.lzc.liu.lzcproject.interfaces.view.HomeNewView;
import com.lzc.liu.lzcproject.presenter.HomeNewPresenterImpl;
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

    @Override
    protected int getViewID() {
        return R.layout.fragment_new_home;
    }

    @Override
    public void initView() {
        homeNewPresenter = new HomeNewPresenterImpl(getBaseActivity(), this);
        datalist = new ArrayList<>();
        Bundle bundle = getArguments();
        int index = bundle.getInt(INTENT_INT_INDEX);
        for (int i = 0; i < NewData.getDataBeanList().size(); i++) {
            if (NewData.getDataBeanList().get(i).getName().equals(NewData.getNewTabList().get(index))){
                category = NewData.getDataBeanList().get(i).getCategory();
            }
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        mAdapter = new NewDataItemTypeAdapter(getBaseActivity(), datalist);
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

    @Override
    protected void onNetworkConnected(int type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    public void initData() {
        refresh();
    }

    private void refresh(){
        homeNewPresenter.GetNewData(category, Page, REQUEST_COUNT);
    }

    @Override
    public void onRefure(ArrayList<NewListBean.DataBean> dataBean) {
        if (Page == 1){
            datalist.clear();
        }
        datalist.addAll(dataBean);
        mLRecyclerViewAdapter.notifyDataSetChanged();
        recyclerView.refreshComplete(Page);
    }

    @Override
    public void initListener() {
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
//                if (datalist.size() < TOTAL_COUNTER) {
//                    Page++;
//                    presenter.HighlightReplaysData(Page);
//                } else {
//                    recyclerView.setNoMore(true);
//                }
            }
        });
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                HighlightReplaysBean.ListBean listBean = datalist.get(position);
//                String[] strings = {listBean.getTime(),listBean.getName(),listBean.getIntroduce(),listBean.getUrl()};
//                HighlightsActivity.createIntent(WonderfulReviewActivity.this,strings);
            }
        });
    }


}
