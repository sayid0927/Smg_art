package com.smg.art.presenter.contract.fragment;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.BalanceOfPayBean;

/**
 * Created by Mervin on 2018/8/2 0002.
 */

public interface BalanceContract {
    interface View extends BaseContract.BaseView {

        /**
         * 我的收支
         */
        void FetchBalanceOfPaymentSuccess(BalanceOfPayBean balanceOfPayBean);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        void FetchBalanceOfPayment(String... s);
    }
}
