package com.smg.art.presenter.contract.activity;

import android.widget.Button;
import android.widget.ImageView;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.ChangeMobilePhoneBean;
import com.smg.art.bean.PhoneVerifyCodeBean;

/**
 * Created by Mervin on 2018/8/3 0003.
 */

public interface SendMobilePhoneContract {
    interface View extends BaseContract.BaseView {

        /**
         * 修改手机
         */
        void FetchChangeMobilePhoneSuccess(ChangeMobilePhoneBean changeMobilePhoneBean);

        /**
         * 获取短信验证码成功
         */
        void FetchPhoneVerifyCodeSuccess(PhoneVerifyCodeBean phoneVerifyCodeBean);

        Button btn();

        ImageView iv();
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        /**
         * 修改手机
         */
        void FetchChangeMobilePhone(String... s);

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
