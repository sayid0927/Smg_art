
package com.smg.art.presenter.impl.activity;

import android.net.Uri;

import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.api.Api;
import com.smg.art.base.BaseApplication;
import com.smg.art.base.BasePresenter;
import com.smg.art.presenter.contract.activity.MainContract;
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

            RongIMClient.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                 */

                @Override
                public void onTokenIncorrect() {
                    setRongIMCState(RongIMCUtils.Connect_TokenIncorrect);
                    ToastUtils.showLongToast("onTokenIncorrect");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token
                 */

                @Override
                public void onSuccess(String userid) {

                    LocalAppConfigUtil.getInstance().setRongUserId(userid);
                    Uri RongHeadImg = Uri.parse(LocalAppConfigUtil.getInstance().getRongUserHeadImg());
                    String RongUserName = LocalAppConfigUtil.getInstance().getRongUserName();
                    RongIM.getInstance().setCurrentUserInfo(new UserInfo(userid, RongUserName, RongHeadImg));
                    RongIM.getInstance().setMessageAttachedUserInfo(true);
                    setRongIMCState(RongIMCUtils.Connect_Success);

                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    setRongIMCState(RongIMCUtils.Connect_Error);
                    ToastUtils.showLongToast("--onError" + errorCode);

                }
            });
        }
    }

    @Override
    public void setRongIMCState(int state) {
        RongIMCUtils.state = state;
    }

}
