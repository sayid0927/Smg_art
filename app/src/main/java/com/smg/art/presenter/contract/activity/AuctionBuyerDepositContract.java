package com.smg.art.presenter.contract.activity;

import com.smg.art.bean.AuctionBuyerDepositBean;
import com.smg.art.base.BaseContract;
import com.smg.art.bean.PlayIntroductionBean;
import com.smg.art.bean.RefundBean;

/**
 * Created by Mervin on 2018/8/2 0002.
 */

public interface AuctionBuyerDepositContract {

    interface View extends BaseContract.BaseView {

        /**
         * 保证金支付
         */
        void FetchAuctionBuyerDepositSuccess(AuctionBuyerDepositBean auctionBuyerDepositBean);

        /**
         * 验证交易密码
         */
        void FetchvalidteTradePwdSuccess(RefundBean refundBean);

        /**
         * 获取竞拍支付说明
         */

        void FetchPayIntroductionSuccess(PlayIntroductionBean playIntroductionBean);


    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 保证金支付
         */
        void FetchAuctionBuyerDeposit(String... s);

        /**
         * 验证交易密码
         */
        void FetchvalidteTradePwd(String... s);

        /**
         * 获取竞拍支付说明
         */
        void FetchPayIntroduction(String... s);

    }
}
