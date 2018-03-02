package com.lzc.liu.lzcproject.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.base.BaseViewLazyFragment;
import com.lzc.liu.lzcproject.bean.NewListBean;
import com.lzc.liu.lzcproject.constant.NewData;
import com.lzc.liu.lzcproject.interfaces.presenter.HomeNewPresenter;
import com.lzc.liu.lzcproject.interfaces.view.HomeNewView;
import com.lzc.liu.lzcproject.presenter.HomeNewPresenterImpl;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.List;

import butterknife.BindView;

public class HomeNewFragment extends BaseViewLazyFragment implements HomeNewView {

    /**
     * 每一页展示多少条数据
     */
    private int REQUEST_COUNT = 20;

    public static final String INTENT_INT_INDEX = "intent_int_index";

    @BindView(R.id.lrv_newhome)
    LRecyclerView recyclerView;

    private HomeNewPresenter homeNewPresenter;

    private int Page = 1;

    private String category;

    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;

    private CommonAdapter<NewListBean> mAdapter;

    private List<NewListBean> datalist;

    @Override
    protected int getViewID() {
        return R.layout.fragment_new_home;
    }

    @Override
    public void initView() {
        homeNewPresenter = new HomeNewPresenterImpl(getBaseActivity(), this);
        Bundle bundle = getArguments();
        int index = bundle.getInt(INTENT_INT_INDEX);
        for (int i = 0; i < NewData.getDataBeanList().size(); i++) {
            if (NewData.getDataBeanList().get(i).getName().equals(NewData.getNewTabList().get(index))){
                category = NewData.getDataBeanList().get(i).getCategory();
            }
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        recyclerView.setHasFixedSize(true);
//        mAdapter = new CommonAdapter<NewListBean>(this,R) {
//            @Override
//            protected void convert(ViewHolder holder, NewListBean newListBean, int position) {
//
//            }
//        }
    }

    @Override
    protected void onNetworkConnected(int type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    public void initData() {
        homeNewPresenter.GetNewData(category, Page, REQUEST_COUNT);
    }

    @Override
    public void initListener() {

    }
}
