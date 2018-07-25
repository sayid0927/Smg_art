
package com.smg.art.presenter.contract.login;

import android.widget.Button;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.PhoneVerifyCodeBean;
import com.smg.art.bean.RegisterBean;

public interface RegisterContract {

    interface View extends BaseContract.BaseView {

        /**
         * 会员注册成功
         */
        void FetchRegisterSuccess(RegisterBean registerBean);

        /**
         * 获取短信验证码成功
         */
        void FetchPhoneVerifyCodeSuccess(PhoneVerifyCodeBean phoneVerifyCodeBean);

        Button btn();
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 会员注册
         */
        void FetchRegister(String... s);
        /**
         * 获取短信验证码
         */
        void FetchPhoneVerifyCode(String... s);

    }
}
