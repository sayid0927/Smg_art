package com.smg.art.presenter.contract.activity;

import android.widget.Button;
import android.widget.ImageView;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.PhoneVerifyCodeBean;
import com.smg.art.bean.ProofreadCodeBean;

/**
 * Created by Mervin on 2018/8/4 0004.
 */

public interface ProofreadCodeContract {

    interface View extends BaseContract.BaseView {

        /**
         * 校验验证码
         */
        void FetchProoFreadCodeSuccess(ProofreadCodeBean proofreadCodeBean);

        /**
         * 获取短信验证码成功
         */
        void FetchPhoneVerifyCodeSuccess(PhoneVerifyCodeBean phoneVerifyCodeBean);

        ImageView iv();

        Button btn();
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        /**
         * 校验手机
         */
        void FetchProoFreadCode(String... s);

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
