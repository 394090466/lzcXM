package com.lzc.liu.lzcproject.view.activity;

import android.content.res.Configuration;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.Transition;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.base.BaseActivity;
import com.lzc.liu.lzcproject.entity.SwitchVideoModel;
import com.lzc.liu.lzcproject.entity.douyu.RoomInfoEntity;
import com.lzc.liu.lzcproject.interfaces.presenter.LivePresenter;
import com.lzc.liu.lzcproject.interfaces.view.LiveAcView;
import com.lzc.liu.lzcproject.presenter.LivePresenterImpl;
import com.lzc.liu.lzcproject.widgets.LiveLayoutVideo;
import com.lzc.liu.lzcproject.widgets.SwitchVideoTypeDialog;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class LiveActivity extends BaseActivity implements LiveAcView {

    public static final String ROOM_ID = "room_id";
    public static final String ROOM_TITLE = "room_title";

    public final static String IMG_TRANSITION = "IMG_TRANSITION";
    public final static String TRANSITION = "TRANSITION";

    @BindView(R.id.llv_live_livevideo)
    LiveLayoutVideo videoPlayer;

    OrientationUtils orientationUtils;

    private boolean isTransition;

    private Transition transition;

    private boolean isPause;

    private boolean isRelease;

    private MediaMetadataRetriever mCoverMedia;

    private boolean isPlay;

    private String roomId;
    private String roomTitle;

    private LivePresenter livePresenter;

    private RoomInfoEntity.DataBean.CdnsWithNameBean mCDN;
    private RoomInfoEntity.DataBean.MultiratesBean mRate;



    @Override
    protected int getView() {
        return R.layout.activity_live;
    }

    @Override
    protected void onNetworkConnected(int type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    public void initView() {
        livePresenter = new LivePresenterImpl(this,this);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        roomId = bundle.getString(ROOM_ID, "");
        roomTitle = bundle.getString(ROOM_TITLE, "");

        mCDN = new RoomInfoEntity.DataBean.CdnsWithNameBean();
        mRate = new RoomInfoEntity.DataBean.MultiratesBean();
        mCDN.setCdn("ws");
        mCDN.setName("主线路");
        mRate.setName("超清");
        mRate.setType(0);


        isTransition = getIntent().getBooleanExtra(TRANSITION, false);





        //增加封面
        //        ImageView imageView = new ImageView(this);
        //        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //        imageView.setImageResource(R.mipmap.xxx1);
        //        videoPlayer.setThumbImageView(imageView);

        //增加title
        videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);

        //关闭自动旋转
        videoPlayer.setRotateViewAuto(false);
        //一全屏不锁屏横屏
        videoPlayer.setLockLand(false);
        //全屏动画
        videoPlayer.setShowFullAnimation(false);
        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, videoPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);
        //关闭自动旋转
        videoPlayer.setRotateViewAuto(false);
        videoPlayer.setLockLand(false);

        //        //过渡动画
        //        initTransition();
    }

    @Override
    public void initData() {
        livePresenter.getCDNandRateInfo(roomId);
    }

    @Override
    public void initListener() {
        //设置返回按键功能
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //屏蔽，实现竖屏全屏
                orientationUtils.resolveByClick();

                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                videoPlayer.startWindowFullscreen(LiveActivity.this, true, true);
            }
        });

        videoPlayer.setVideoAllCallBack(new GSYSampleCallBack() {
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                //开始播放了才能旋转和全屏
                //orientationUtils.setEnable(true);
                orientationUtils.setEnable(false);
                isPlay = true;
            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
                super.onAutoComplete(url, objects);
            }

            @Override
            public void onClickStartError(String url, Object... objects) {
                super.onClickStartError(url, objects);
            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);
                //屏蔽，实现竖屏全屏
                if (orientationUtils != null) {
                    orientationUtils.backToProtVideo();
                }
            }
        });

        videoPlayer.setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                //屏蔽，实现竖屏全屏
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils.setEnable(!lock);
                }
            }
        });

        videoPlayer.setSwitchOnListItem(new SwitchVideoTypeDialog.OnListItemClickListener() {
            @Override
            public void onItemClick(int position,List<SwitchVideoModel> data) {
                livePresenter.onRateChange(data.get(position).getMultiratesBeans());
                videoPlayer.setSwitch(data.get(position).getName());
            }
        });
    }


    @Override
    public void onBackPressed() {

        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }

        if (GSYVideoManager.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }


    @Override
    protected void onPause() {
        getCurPlay().onVideoPause();
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        getCurPlay().onVideoResume();
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRelease = true;
        if (isPlay) {
            getCurPlay().release();
        }
        //GSYPreViewManager.instance().releaseMediaPlayer();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
        if (mCoverMedia != null) {
            mCoverMedia.release();
            mCoverMedia = null;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            videoPlayer.onConfigurationChanged(this, newConfig, orientationUtils, true, true);
        }
        //竖屏全屏
        orientationUtils.setEnable(false);
    }


    private GSYVideoPlayer getCurPlay() {
        if (videoPlayer.getFullWindowPlayer() != null) {
            return videoPlayer.getFullWindowPlayer();
        }
        return videoPlayer;
    }

    @Override
    public void updateCDNandRateInfo(List<RoomInfoEntity.DataBean.CdnsWithNameBean> list, List<RoomInfoEntity.DataBean.MultiratesBean> list2) {

        List<SwitchVideoModel> switchVideoModelList = new ArrayList<>();

        for (RoomInfoEntity.DataBean.MultiratesBean rate : list2) {
            String source = rate.getName();
            String name = rate.getName();
            Log.v("lzc",rate.getType()+"------------"+rate.getName());
            SwitchVideoModel switchVideoModel = new SwitchVideoModel(name, source,rate);
            switchVideoModelList.add(switchVideoModel);
        }

        videoPlayer.setSwitchVideo(switchVideoModelList);

    }

    /**
     * 准备播放
     */
    @Override
    public void preparePlay() {
        if (!TextUtils.isEmpty(roomId) && mCDN != null && mRate != null)
        {
            livePresenter.getHLSUrl(roomId, mCDN.getCdn(), mRate.getType() + "");
        }
    }

    @Override
    public void upDateCDN(RoomInfoEntity.DataBean.CdnsWithNameBean cdnsWithNameBean) {
        this.mCDN = cdnsWithNameBean;
//        cdn.setText(cdnsWithNameBean.getName());
        preparePlay();
    }

    @Override
    public void upDateRate(RoomInfoEntity.DataBean.MultiratesBean multiratesBean) {
        this.mRate = multiratesBean;
        videoPlayer.setSwitch(multiratesBean.getName());
//        rate.setText(multiratesBean.getName());
        preparePlay();
    }

    @Override
    public void setMediaCodec(boolean b) {

    }

    /**
     * 获取视频地址，进行播放
     *
     * @param url
     */
    @Override
    public void updateHLSUrl(String url) {
        LogUtils.i(url);
        videoPlayer.setPlayUrl(url);
        videoPlayer.setUp(url,false, roomTitle);
        videoPlayer.startPlayLogic();
    }
}
