
package com.smg.art.presenter.impl.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.api.Api;
import com.smg.art.base.BaseApplication;
import com.smg.art.base.BasePresenter;
import com.smg.art.presenter.contract.activity.MainContract;
import com.smg.art.ui.login.LoginActivity;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.utils.RongIMCStateful;
import com.smg.art.utils.RongIMCUtils;
import com.smg.art.utils.UIUtils;

import javax.inject.Inject;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;


public class MainActivityPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter<MainContract.View>, RongIMCStateful {

    private Api api;

    @Inject
    public MainActivityPresenter(Api api) {
        this.api = api;

    }

    @Override
    public void connect(String token) {
        if (UIUtils.getContext().getApplicationInfo().packageName.equals(BaseApplication.getCurProcessName(UIUtils.getContext()))) {

            /**
             * IMKit SDK调用第二步,建立与服务器的连接
             */
            RongIM.connect(token, getConnectCallback());
        }
    }

    public RongIMClient.ConnectCallback getConnectCallback() {
        RongIMClient.ConnectCallback connectCallback = new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                ToastUtils.showLongToast("onTokenIncorrect");
            }

            @Override
            public void onSuccess(String userid) {
                LocalAppConfigUtil.getInstance().setRongUserId(userid);
                Uri RongHeadImg = Uri.parse(LocalAppConfigUtil.getInstance().getRongUserHeadImg());
                String RongUserName = LocalAppConfigUtil.getInstance().getRongUserName();
                /**
                 * 设置当前用户信息，
                 * @param userInfo 当前用户信息
                 */
                RongIM.getInstance().setCurrentUserInfo(new UserInfo(userid, RongUserName,RongHeadImg));
                RongIM.getInstance().setMessageAttachedUserInfo(true);
                setRongIMCState(RongIMCUtils.Connect_Success);
            }

            @Override
            public void onError(final RongIMClient.ErrorCode errorCode) {
                ToastUtils.showLongToast("--onError" + errorCode);
            }
        };
        return connectCallback;
    }

    @Override
    public void setRongIMCState(int state) {
        RongIMCUtils.state = state;
    }

}
