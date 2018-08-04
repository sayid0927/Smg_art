package com.smg.art.presenter.contract.activity;

import android.widget.Button;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.ChangeTraderPasswordBean;
import com.smg.art.bean.PhoneVerifyCodeBean;

/**
 * Created by Mervin on 2018/8/4 0004.
 */

public interface ChangeTradersPasswordContract {
    interface View extends BaseContract.BaseView {

        /**
         * 修改交易密码
         */
        void FetchChangeTradersPasswordSuccess(ChangeTraderPasswordBean changeTraderPasswordBean);

        /**
         * 获取短信验证码成功
         */
        void FetchPhoneVerifyCodeSuccess(PhoneVerifyCodeBean phoneVerifyCodeBean);

        Button btn();
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        /**
         * 修改交易密码
         */
        void FetchChangeTradersPassword(String... s);

        /**
         * 获取短信验证码
         */
        void FetchPhoneVerifyCode(String... s);
    }

}
