package com.lzc.liu.lzcproject.view.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.adapter.BannerViewPagerAdapter;
import com.lzc.liu.lzcproject.adapter.ChannelAdapter;
import com.lzc.liu.lzcproject.base.BaseViewLazyFragment;
import com.lzc.liu.lzcproject.constant.Constant;
import com.lzc.liu.lzcproject.entity.douyu.RoomsEntity;
import com.lzc.liu.lzcproject.entity.douyu.SlidersEntity;
import com.lzc.liu.lzcproject.interfaces.ChannelContract;
import com.lzc.liu.lzcproject.netapi.RxService;
import com.lzc.liu.lzcproject.presenter.ChannelPresenterImpl;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by liu on 2018/4/1.
 */

public class ChannelFragment extends BaseViewLazyFragment implements ChannelContract.View {

    public static final String CATE_ID = "cate_id";

    private String cateId;

    @BindView(R.id.lrv_newhome)
    LRecyclerView recyclerView;

    private int Page = 1;

    private int mLimit = 20;
    private int mOffset = 0;

    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;

    private LoadMoreWrapper moreWrapper = null;

    private ChannelAdapter channelAdapter;

    private List<RoomsEntity.DataBean> mData;

    private ChannelPresenterImpl mPresenter;

    private IndicatorViewPager indicatorViewPager;

    private List<SlidersEntity.DataBean> dataBeanList = null;

    @Override
    protected int getViewID() {
        return R.layout.fragment_douyu;
    }

    @Override
    public void initView() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        cateId = bundle.getString(CATE_ID);
        dataBeanList = new ArrayList<>();
        mPresenter = new ChannelPresenterImpl(getActivity(), this);

        GridLayoutManager manager = new GridLayoutManager(getBaseActivity(), 2);
        recyclerView.setLayoutManager(manager);
        mData = new ArrayList<>();
        channelAdapter = new ChannelAdapter(getBaseActivity(), R.layout.item_room, mData);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(channelAdapter);
        moreWrapper = new LoadMoreWrapper(channelAdapter);

        //                DividerDecoration divider = new DividerDecoration.Builder(getBaseActivity())
        //                        .setHeight(R.dimen.dp_0_5)
        //                        .setPadding(R.dimen.dp_15)
        //                        .setColorResource(R.color.gray_cc)
        //                        .build();
        //                recyclerView.addItemDecoration(divider);

        if (cateId.equals("0")) {
            //add a HeaderView
            View header = LayoutInflater.from(getActivity()).inflate(R.layout.livebanner,(ViewGroup)findViewById(android.R.id.content), false);
            ViewPager viewPager =  header.findViewById(R.id.guide_viewPager);
            Indicator indicator =  header.findViewById(R.id.guide_indicator);
            indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
            BannerViewPagerAdapter bannerViewPagerAdapter = new BannerViewPagerAdapter(getActivity(),dataBeanList);
            indicatorViewPager.setAdapter(bannerViewPagerAdapter);
            mLRecyclerViewAdapter.addHeaderView(header);
        }

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

    private void refresh() {
        RxService.setUrls(Constant.DOUYU_BASE_URL);
        if (cateId.equals("0")) {
            mPresenter.getRoomsWithSliders(cateId, mLimit, mOffset);
        }
        mPresenter.getRooms(cateId, mLimit, mOffset);
    }

    @Override
    public void initListener() {
        recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mOffset = 0;
                refresh();
            }
        });

        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mOffset = mOffset + mLimit;
                refresh();
            }
        });
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }


    /**
     * 普通页面
     *
     * @param roomEntities
     */
    @Override
    public void getRoomsSuccess(List<RoomsEntity.DataBean> roomEntities) {
        if (mOffset == 0) {
            mData.clear();
        }
        mData.addAll(roomEntities);
        mLRecyclerViewAdapter.notifyDataSetChanged();
        recyclerView.refreshComplete(mLimit);
    }


    @Override
    public void getRoomsFailed(Throwable throwable) {

    }

    /**
     * 首页轮播图
     */
    @Override
    public void getMixSuccess(List<SlidersEntity.DataBean> dataBeanList) {
        dataBeanList.addAll(dataBeanList);
        indicatorViewPager.notifyDataSetChanged();
    }

    @Override
    public void getMixFailed(Throwable throwable) {

    }
}
