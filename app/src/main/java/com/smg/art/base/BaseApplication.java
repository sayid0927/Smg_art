package com.smg.art.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import com.blankj.utilcode.utils.Utils;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerAppComponent;
import com.smg.art.module.ApiModule;
import com.smg.art.module.AppModule;

import com.smg.art.utils.CrashHandler;
import com.smg.art.utils.PreferUtil;



public class BaseApplication extends Application {

    public  static BaseApplication baseApplication;
    private static AppComponent appComponent;


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

    }

    private void initCompoent() {
        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .build();
    }



    /**
     * 获取BaseApplication实例
     * @return
     */

    public static BaseApplication getBaseApplication(){
        return baseApplication;
    }

    public static AppComponent getAppComponent(){
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    private void initLogger() {
        Logger.init("ART").methodCount(2).methodOffset(0).logLevel(LogLevel.FULL).hideThreadInfo();
    }
}
