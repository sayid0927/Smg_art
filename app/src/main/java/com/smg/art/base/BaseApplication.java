package com.smg.art.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.utils.ThreadPoolUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.blankj.utilcode.utils.Utils;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerAppComponent;
import com.smg.art.module.ApiModule;
import com.smg.art.module.AppModule;
import com.smg.art.utils.GreenDaoUtil;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.utils.PreferUtil;
import com.smg.art.utils.RongIMCUtils;
import com.smg.art.utils.UIUtils;

import io.rong.eventbus.EventBus;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;

import static io.rong.imlib.RongIMClient.ConnectionStatusListener.ConnectionStatus.*;


public class BaseApplication extends Application implements RongIM.UserInfoProvider {

    public static BaseApplication baseApplication;
    private static AppComponent appComponent;


    public static BaseApplication getContext() {
        return baseApplication;
    }

    /**
     * 获取BaseApplication实例
     *
     * @return
     */
    public static ThreadPoolUtils MAIN_EXECUTOR = new ThreadPoolUtils(ThreadPoolUtils.Type.FixedThread, 5);

    public static BaseApplication getBaseApplication() {
        return baseApplication;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        //将我们自己的MyApplication中的所有逻辑放在这里，例如初始化一些第三方
        initCompoent();
        initLogger();
        Utils.init(this);
//        CrashHandler.getInstance(this).init();
        PreferUtil.getInstance().init(this);
        //初始化融云
        initRongCloud();
        GreenDaoUtil.initDataBase(getApplicationContext());
        //初始化红包
//        initRedPacket();

    }

    private void initCompoent() {
        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .build();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void initLogger() {
        Logger.init("ART").methodCount(2).methodOffset(0).logLevel(LogLevel.FULL).hideThreadInfo();
    }

    private void initRongCloud() {
        /**
         * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIMClient 的进程和 Push 进程执行了 init。
         * io.rong.push 为融云 push 进程名称，不可修改。
         */
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {

            RongIM.init(this);
            RongIM.setUserInfoProvider(this,true);

        }
    }

    @Override
    public UserInfo getUserInfo(String s) {

        Uri RongHeadImg = Uri.parse(LocalAppConfigUtil.getInstance().getRongUserHeadImg());
        String RongUserName = LocalAppConfigUtil.getInstance().getRongUserName();

        UserInfo userInfo = new UserInfo(LocalAppConfigUtil.getInstance().getRongUserId(),RongUserName,RongHeadImg);

        return userInfo;
    }
}
