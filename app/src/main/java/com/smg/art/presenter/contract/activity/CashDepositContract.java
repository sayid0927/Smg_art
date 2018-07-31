package com.smg.art.presenter.contract.activity;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.CashDepositiBean;

/**
 * Created by Mervin on 2018/7/30 0030.
 */

public interface CashDepositContract {

    interface View extends BaseContract.BaseView {
        /**
         * 获取保证金
         */
        void FetchCashDepositSuccess(CashDepositiBean cashDepositiBean);


    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        void FetchCashDeposit(String... s);
    }

}
