package com.smg.art.utils;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

/**
 * Created by Lenovo on 2018/7/27.
 */

public class RongIMCUtils {


    /***
     * 融云连接状态
     *
     */

    public static int CONNECTED = 0;//连接成功。
    public static int DISCONNECTED = 1; //断开连接。
    public static int CONNECTING = 2; //连接中。
    public static final int NETWORK_UNAVAILABLE = 3;//网络不可用。
    public static final int KICKED_OFFLINE_BY_OTHER_CLIENT = 4;//用户账户在其他设备登录，本机会被踢掉线

    public static int state = CONNECTING;


}
