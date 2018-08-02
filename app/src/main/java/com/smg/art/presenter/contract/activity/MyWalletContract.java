package com.smg.art.presenter.contract.activity;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.WalletBalanceBean;

/**
 * Created by Mervin on 2018/8/2 0002.
 */

public interface MyWalletContract {
    interface View extends BaseContract.BaseView {
        /**
         * 我的钱包余额
         */
        void FetchMyWalletBalanceSuccess(WalletBalanceBean walletBalanceBean);


    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        void FetchMyWalletBalance(String... s);
    }
}
