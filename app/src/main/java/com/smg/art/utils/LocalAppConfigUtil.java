package com.smg.art.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.smg.art.base.BaseApplication;
import com.smg.art.base.Constant;
import com.smg.art.ui.login.LoginActivity;


/**
 * @author ：Mervin
 * @ClassName ：  Constants
 * @Description:
 * @date ：  2018-5-4
 */

public class LocalAppConfigUtil {
    private static SharedPreferences sp;
    private static LocalAppConfigUtil instance = null;
    private SharedPreferences.Editor editor;

    /* 私有构造方法，防止被实例化 */
    private LocalAppConfigUtil() {
    }

    public static LocalAppConfigUtil getInstance() {
        if (instance == null) {
            instance = new LocalAppConfigUtil();
        }
        sp = BaseApplication.getContext().getSharedPreferences(Constant.LOCAL_APP_CONFIG_FILE_NAME, Context.MODE_MULTI_PROCESS);
        return instance;
    }

    /**
     * 获得当前登录用户userid
     *
     * @return
     */
    public int getCurrentUserId() {
        return sp.getInt("user_id", 0);
    }

    /**
     * 设置当前登录用户userid
     *
     * @param userId
     */
    public void setCurrentUserId(int userId) {
        editor = sp.edit();
        editor.putInt("user_id", userId);
        editor.commit();
    }

    /**
     * 获取用户username
     *
     * @return
     */
    public String getCurrentUserName() {
        return sp.getString("user_name", "");
    }

    /**
     * 设置当前登录用户username
     *
     * @param username
     */
    public void setCurrentUserName(String username) {
        editor = sp.edit();
        editor.putString("user_name", username);
        editor.commit();
    }

    /**
     * 获取用户头像
     *
     * @return
     */
    public String getCurrentAvatar() {
        return sp.getString("avatar", "");
    }

    /**
     * 设置当前登录用户头像
     *
     * @param avatar
     */
    public void setCurrentAvatar(String avatar) {
        editor = sp.edit();
        editor.putString("avatar", avatar);
        editor.commit();
    }

    /**
     * 获取客服电话
     *
     * @return
     */
    public String getServiceTel() {
        return sp.getString("serviceTel", "");
    }

    /**
     * 设置客服电话
     *
     * @param serviceTel
     */
    public void setServiceTel(String serviceTel) {
        editor = sp.edit();
        editor.putString("serviceTel", serviceTel);
        editor.commit();
    }

    /**
     * 获取信息数
     *
     * @return
     */
    public int getMessCount() {
        return sp.getInt("messCount", 0);
    }

    /**
     * 获取用户username
     *
     * @return
     */
    public String getAccessToken() {
        return sp.getString("access_token", "");
    }

    /**
     * 设置当前登录用户access_token
     *
     * @param accessToken
     */
    public void setAccessToken(String accessToken) {
        editor = sp.edit();
        editor.putString("access_token", accessToken);
        editor.commit();
    }

    public String getPassword() {
        return sp.getString("password", "");
    }

    // 用户的密码
    public void setPassword(String password) {
        editor = sp.edit();
        editor.putString("password", password);
        editor.commit();
    }

    /**
     * 获取用户username
     *
     * @return
     */
    public String getUserTelephone() {
        return sp.getString("telephone", "");
    }

    /**
     * 设置当前登录用户手机号码
     *
     * @param telephone
     */
    public void setUserTelephone(String telephone) {
        editor = sp.edit();
        editor.putString("telephone", telephone);
        editor.commit();
    }

    /**
     * 判断是否登录
     *
     * @return true：已登录 ,false：未登录
     */
    public boolean isLogin() {
        if (!TextUtils.isEmpty(getUserTelephone())) {
            return true;
        }
        return false;
    }

    /**
     * 判断是不是登录：重载方法，调用些方法时如果没有登录则会跳转到登录界面
     *
     * @return
     */
    public boolean isLogin(Activity activity) {
        if (!TextUtils.isEmpty(getUserTelephone())) {
            return true;
        }
        activity.startActivity(new Intent(activity, LoginActivity.class));
        return false;
    }


}

