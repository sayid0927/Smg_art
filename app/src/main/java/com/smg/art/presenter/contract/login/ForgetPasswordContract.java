package com.smg.art.presenter.contract.login;

import android.widget.Button;
import android.widget.ImageView;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.ForgetPasswordBean;
import com.smg.art.bean.PhoneVerifyCodeBean;

/**
 * Created by Mervin on 2018/7/25 0025.
 */

public interface ForgetPasswordContract {

    interface View extends BaseContract.BaseView {

        /**
         * 忘记密码修改成功
         */
        void FetchForgetPasswordSuccess(ForgetPasswordBean forgetPasswordBean);

        /**
         * 获取短信验证码成功
         */
        void FetchPhoneVerifyCodeSuccess(PhoneVerifyCodeBean phoneVerifyCodeBean);

        ImageView iv();
        Button btn();
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 忘记密码
         */
        void FetchForgetPassword(String... s);

        /**
         * 获取短信验证码
         */
        void FetchPhoneVerifyCode(String... s);

        /**
         * 图形验证码
         */
        void FetchPictureCode(String... s);


    }
}
