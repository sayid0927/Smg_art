package com.smg.art.utils;

import com.smg.art.bean.RongImStateBean;
import com.smg.art.bean.UpudterMessageBean;

import org.greenrobot.eventbus.EventBus;

import io.rong.imlib.RongIMClient;

/**
 * Created by Lenovo on 2018/8/16.
 */

public  class MyConnectionStatusListener implements RongIMClient.ConnectionStatusListener,RongIMCStateful {

    @Override
    public void onChanged(RongIMClient.ConnectionStatusListener.ConnectionStatus connectionStatus) {

        switch (connectionStatus){
            case CONNECTED://连接成功。
                setRongIMCState(RongIMCUtils.CONNECTED);
                break;
            case DISCONNECTED://断开连接。
                setRongIMCState(RongIMCUtils.DISCONNECTED);
                break;
            case CONNECTING://连接中。
                setRongIMCState(RongIMCUtils.CONNECTING);
                break;
            case NETWORK_UNAVAILABLE://网络不可用。
                setRongIMCState(RongIMCUtils.NETWORK_UNAVAILABLE);
                break;
            case KICKED_OFFLINE_BY_OTHER_CLIENT://用户账户在其他设备登录，本机会被踢掉线
                setRongIMCState(RongIMCUtils.KICKED_OFFLINE_BY_OTHER_CLIENT);
                EventBus.getDefault().post(new RongImStateBean("用户账户在其他设备登录，本机会被踢掉线"));
                break;
        }
    }

    @Override
    public void setRongIMCState(int state) {
        RongIMCUtils.state = state;
    }
}