package com.lzc.liu.lzcproject.interfaces.model;

import com.lzc.liu.lzcproject.model.liveModeImpl;

/**
 * Created by liu on 2018/3/13.
 */

public interface liveMode {


    void getChannels(liveModeImpl.onliveModeListener listener);

    void getCDNandRateInfo(String roomId,liveModeImpl.onliveModeListener listener);

    void getHLSUrl(String roomid, String cdn, String rate,liveModeImpl.onliveModeListener listener);

}
