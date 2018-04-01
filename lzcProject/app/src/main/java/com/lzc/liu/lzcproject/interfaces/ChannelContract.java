package com.lzc.liu.lzcproject.interfaces;

import android.support.annotation.Keep;

import com.lzc.liu.lzcproject.entity.douyu.RoomsEntity;
import com.lzc.liu.lzcproject.entity.douyu.SlidersEntity;
import com.lzc.liu.lzcproject.model.ChannelModelImpl;

import java.util.List;

/**
 * Created by xiaokun on 2017/8/28.
 */
@Keep
public interface ChannelContract {
    interface Model {

        void getSlider(ChannelModelImpl.onChannelModeListener listener);

        void getRooms(String cateId, int limit, int offset,ChannelModelImpl.onChannelModeListener listener);

    }

    interface View {
        //        void getSliderSuccess(List<SlidersEntity.DataBean> sliderEntities);
        //
        //        void getSliderFailed(Throwable throwable);

        void getRoomsSuccess(List<RoomsEntity.DataBean> roomEntities);

        void getRoomsFailed(Throwable throwable);

        void getMixSuccess(List<SlidersEntity.DataBean> dataBeanList);

        void getMixFailed(Throwable throwable);
    }

    interface Presenter {
        //        public abstract void getSlider();

        void getRooms(String cateId, int limit, int offset);

        void getRoomsWithSliders(String cateId, int limit, int offset);
    }

}
