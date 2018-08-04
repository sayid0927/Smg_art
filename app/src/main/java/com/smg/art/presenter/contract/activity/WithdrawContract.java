package com.smg.art.presenter.contract.activity;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.CheckBankCardBean;
import com.smg.art.bean.CurrencyExchangeRateBean;
import com.smg.art.bean.WithDrawBean;

/**
 * Created by Mervin on 2018/8/2 0002.
 */

public interface WithdrawContract {
    interface View extends BaseContract.BaseView {

        /**
         * 查询银行卡
         */
        void FetchCheckBankCardSuccess(CheckBankCardBean checkBankCardBean);

        /**
         * 提现接口
         */
        void FetchWithdrawSuccess(WithDrawBean withDrawBean);

        /**
         * 获取拍卖汇率
         */
        void FetchCurrencyExchangeRateSuccess(CurrencyExchangeRateBean currencyExchangeRateBean);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 查询银行卡
         */
        void FetchCheckBankCard(String... s);

        /**
         * 提现接口
         */
        void FetchWithdrawCode(String... s);

        /**
         * 获取拍卖汇率
         */
        void FetchCurrencyExchangeRate(String... s);

    }
}
