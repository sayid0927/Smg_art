package com.smg.art.presenter.contract.activity;

import android.widget.Button;

import com.smg.art.base.AuctionBuyerDepositBean;
import com.smg.art.base.BaseContract;
import com.smg.art.bean.AddBankCardBean;
import com.smg.art.bean.PhoneVerifyCodeBean;

/**
 * Created by Mervin on 2018/8/2 0002.
 */

public interface AuctionBuyerDepositContract {

    interface View extends BaseContract.BaseView {

        /**
         * 保证金支付
         */
        void  FetchAuctionBuyerDepositSuccess(AuctionBuyerDepositBean auctionBuyerDepositBean);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 保证金支付
         */
        void  FetchAuctionBuyerDeposit(String ...s);
    }
}
