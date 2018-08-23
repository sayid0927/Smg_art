package com.smg.art.presenter.contract.activity;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.CardUrlBean;
import com.smg.art.bean.CheckBankCardBean;
import com.smg.art.bean.ReChargeBean;

import java.util.List;

import okhttp3.MultipartBody;

/**
 * Created by Mervin on 2018/8/3 0003.
 */

public interface ReChargeContract {
    interface View extends BaseContract.BaseView {

        /**
         * 充值
         */
        void FetchReChargeSuccess(ReChargeBean reChargeBean);

        /**
         * 查询银行卡
         */
        void FetchCheckBankCardSuccess(CheckBankCardBean checkBankCardBean);

        /**
         * 上传图片
         */
        void FetchUploadFileSuccess(CardUrlBean cardUrlBean);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 充值
         */
        void FetchReCharge(String... s);

        /**
         * 查询银行卡
         */
        void FetchCheckBankCard(String... s);

        void FetchUploadFile(List<MultipartBody.Part> parts);
    }
}
