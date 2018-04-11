package com.lzc.liu.lzcproject.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lzc.liu.lzcproject.R;
import com.lzc.liu.lzcproject.entity.SwitchVideoModel;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liu on 2018/4/10.
 */

public class LiveLayoutVideo extends StandardGSYVideoPlayer {

    private TextView mSwitchSize;

    private List<SwitchVideoModel> mUrlList = new ArrayList<>();

    //数据源
    private int mSourcePosition = 0;

    //记住切换数据源类型
    private int mType = 0;

    private int mTransformSize = 0;

    private String mTypeText = "标准";

    private String playUrl = "";

    private SwitchVideoTypeDialog.OnListItemClickListener switchOnListItem = null;

    public LiveLayoutVideo(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public LiveLayoutVideo(Context context) {
        super(context);
    }

    public LiveLayoutVideo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //这个必须配置最上面的构造才能生效
    @Override
    public int getLayoutId() {
        return R.layout.layout_livelayout_land;
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        setIsTouchWiget(false);
        mSwitchSize = (TextView) findViewById(R.id.switchSize);

        //切换视频清晰度
        mSwitchSize.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showSwitchDialog();
            }
        });
    }

//    @Override
//    protected void updateStartImage() {
//        ImageView imageView = (ImageView) mStartButton;
//        if (mCurrentState == CURRENT_STATE_PLAYING) {
//            imageView.setImageResource(R.drawable.video_click_pause_selector);
//        } else if (mCurrentState == CURRENT_STATE_ERROR) {
//            imageView.setImageResource(R.drawable.video_click_play_selector);
//        } else {
//            imageView.setImageResource(R.drawable.video_click_play_selector);
//        }
//
//    }

//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        int id = v.getId();
//        float x = event.getX();
//        float y = event.getY();
//        if (id == com.shuyu.gsyvideoplayer.R.id.surface_container) {
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//
//                    touchSurfaceDown(x, y);
//
//                    break;
//                case MotionEvent.ACTION_MOVE:
//                    float deltaX = x - mDownX;
//                    float deltaY = y - mDownY;
//                    float absDeltaX = Math.abs(deltaX);
//                    float absDeltaY = Math.abs(deltaY);
//
//                    if ((mIfCurrentIsFullscreen && mIsTouchWigetFull)
//                            || (mIsTouchWiget && !mIfCurrentIsFullscreen)) {
//                        if (!mChangePosition && !mChangeVolume && !mBrightness) {
//                            touchSurfaceMoveFullLogic(absDeltaX, absDeltaY);
//                        }
//                    }
//                    touchSurfaceMove(deltaX, deltaY, y);
//
//                    break;
//                case MotionEvent.ACTION_UP:
//
//                    startDismissControlViewTimer();
//
//                    touchSurfaceUp();
//
//                    startProgressTimer();
//
//                    //不要和隐藏虚拟按键后，滑出虚拟按键冲突
//                    if (mHideKey && mShowVKey) {
//                        return true;
//                    }
//                    break;
//            }
//            gestureDetector.onTouchEvent(event);
//        } else if (id == com.shuyu.gsyvideoplayer.R.id.progress) {
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    cancelDismissControlViewTimer();
//                case MotionEvent.ACTION_MOVE:
//                    cancelProgressTimer();
//                    ViewParent vpdown = getParent();
//                    while (vpdown != null) {
//                        vpdown.requestDisallowInterceptTouchEvent(true);
//                        vpdown = vpdown.getParent();
//                    }
//                    break;
//                case MotionEvent.ACTION_UP:
//                    startDismissControlViewTimer();
//                    startProgressTimer();
//                    ViewParent vpup = getParent();
//                    while (vpup != null) {
//                        vpup.requestDisallowInterceptTouchEvent(false);
//                        vpup = vpup.getParent();
//                    }
//                    mBrightnessData = -1f;
//                    break;
//            }
//        }
//
//        if (mIfCurrentIsFullscreen && mLockCurScreen && mNeedLockFull) {
//            return true;
//        }
//        return false;
//    }

    /**
     * 设置清晰度列表
     * @param url
     */
    public void setSwitchVideo(List<SwitchVideoModel> url){
        mUrlList = url;
    }

//    /**
//     * 设置播放URL
//     *
//     * @param cacheWithPlay 是否边播边缓存
//     * @param title         title
//     * @return
//     */
//    public boolean setUp(String url,boolean cacheWithPlay, String title) {
//        return setUp(url, cacheWithPlay, title);
//    }

    /**
     * 设置播放URL
     *
     * @param url           播放url
     * @param cacheWithPlay 是否边播边缓存
     * @param title         title
     * @return
     */
    public boolean setUp(List<SwitchVideoModel> url, boolean cacheWithPlay, String title) {
        mUrlList = url;
        return setUp(url.get(mSourcePosition).getUrl(), cacheWithPlay, title);
    }

    /**
     * 设置播放URL
     *
     * @param url           播放url
     * @param cacheWithPlay 是否边播边缓存
     * @param cachePath     缓存路径，如果是M3U8或者HLS，请设置为false
     * @param title         title
     * @return
     */
    public boolean setUp(List<SwitchVideoModel> url, boolean cacheWithPlay, File cachePath, String title) {
        mUrlList = url;
        return setUp(url.get(mSourcePosition).getUrl(), cacheWithPlay, cachePath, title);
    }

    /**
     * 全屏时将对应处理参数逻辑赋给全屏播放器
     *
     * @param context
     * @param actionBar
     * @param statusBar
     * @return
     */
    @Override
    public GSYBaseVideoPlayer startWindowFullscreen(Context context, boolean actionBar, boolean statusBar) {
        LiveLayoutVideo LiveLayoutVideo = (LiveLayoutVideo) super.startWindowFullscreen(context, actionBar, statusBar);
        LiveLayoutVideo.mSourcePosition = mSourcePosition;
        LiveLayoutVideo.mType = mType;
        LiveLayoutVideo.mTransformSize = mTransformSize;
        LiveLayoutVideo.mUrlList = mUrlList;
        LiveLayoutVideo.mTypeText = mTypeText;
        LiveLayoutVideo.switchOnListItem = switchOnListItem;
        LiveLayoutVideo.playUrl = playUrl;
        mSwitchSize.setText(mTypeText);
        setUp(playUrl, mCache, mCachePath, mTitle);
        //LiveLayoutVideo.resolveTransform();
//        LiveLayoutVideo.resolveTypeUI();
        //LiveLayoutVideo.resolveRotateUI();
        //这个播放器的demo配置切换到全屏播放器
        //这只是单纯的作为全屏播放显示，如果需要做大小屏幕切换，请记得在这里耶设置上视频全屏的需要的自定义配置
        //比如已旋转角度之类的等等
        //可参考super中的实现
        return LiveLayoutVideo;
    }

    /**
     * 推出全屏时将对应处理参数逻辑返回给非播放器
     *
     * @param oldF
     * @param vp
     * @param gsyVideoPlayer
     */
    @Override
    protected void resolveNormalVideoShow(View oldF, ViewGroup vp, GSYVideoPlayer gsyVideoPlayer) {
        super.resolveNormalVideoShow(oldF, vp, gsyVideoPlayer);
        if (gsyVideoPlayer != null) {
            LiveLayoutVideo LiveLayoutVideo = (LiveLayoutVideo) gsyVideoPlayer;
            mSourcePosition = LiveLayoutVideo.mSourcePosition;
            mType = LiveLayoutVideo.mType;
            mUrlList = LiveLayoutVideo.mUrlList;
            mTransformSize = LiveLayoutVideo.mTransformSize;
            switchOnListItem = LiveLayoutVideo.switchOnListItem;
            mTypeText = LiveLayoutVideo.mTypeText;
            playUrl = LiveLayoutVideo.playUrl;

            mSwitchSize.setText(mTypeText);
//            resolveTypeUI();
        }
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public void setSwitchOnListItem(SwitchVideoTypeDialog.OnListItemClickListener switchOnListItem){
        this.switchOnListItem = switchOnListItem;
    }

    /**
     * 弹出切换清晰度
     */
    private void showSwitchDialog() {
        if (!mHadPlay) {
            return;
        }
        SwitchVideoTypeDialog switchVideoTypeDialog = new SwitchVideoTypeDialog(getContext());
//        switchVideoTypeDialog.initList(mUrlList, new SwitchVideoTypeDialog.OnListItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                final String name = mUrlList.get(position).getName();
//                if (mSourcePosition != position) {
//                    if ((mCurrentState == GSYVideoPlayer.CURRENT_STATE_PLAYING
//                            || mCurrentState == GSYVideoPlayer.CURRENT_STATE_PAUSE)
//                            && getGSYVideoManager().getMediaPlayer() != null) {
//                        final String url = mUrlList.get(position).getUrl();
//                        onVideoPause();
//                        final long currentPosition = mCurrentPosition;
//                        getGSYVideoManager().releaseMediaPlayer();
//                        cancelProgressTimer();
//                        hideAllWidget();
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                setUp(url, mCache, mCachePath, mTitle);
//                                setSeekOnStart(currentPosition);
//                                startPlayLogic();
//                                cancelProgressTimer();
//                                hideAllWidget();
//                            }
//                        }, 500);
//                        mTypeText = name;
//                        mSwitchSize.setText(name);
//                        mSourcePosition = position;
//                    }
//                } else {
//                    Toast.makeText(getContext(), "已经是 " + name, Toast.LENGTH_LONG).show();
//                }
//            }
//        });

        switchVideoTypeDialog.initList(mUrlList,switchOnListItem);
        switchVideoTypeDialog.show();
    }


    public void setSwitch(String name){
        mSwitchSize.setText(name);
        mTypeText = name;
    }
}
