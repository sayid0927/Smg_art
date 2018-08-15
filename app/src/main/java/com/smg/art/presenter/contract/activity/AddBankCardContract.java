package com.smg.art.presenter.contract.activity;

import android.widget.Button;
import android.widget.ImageView;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.AddBankCardBean;
import com.smg.art.bean.PhoneVerifyCodeBean;

/**
 * Created by Mervin on 2018/8/2 0002.
 */

public interface AddBankCardContract {

    interface View extends BaseContract.BaseView {

        /**
         * 添加银行卡
         */
        void FetchAddBankCardSuccess(AddBankCardBean addBankCardBean);

        /**
         * 获取短信验证码成功
         */
        void FetchPhoneVerifyCodeSuccess(PhoneVerifyCodeBean phoneVerifyCodeBean);


        ImageView iv();

        Button btn();
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 会员注册
         */
        void FetchAddBankCard(String... s);

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
