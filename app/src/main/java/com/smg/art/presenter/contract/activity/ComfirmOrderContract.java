package com.smg.art.presenter.contract.activity;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.ComfirmOrderBean;
import com.smg.art.bean.LogisticInfo;

import rx.Observable;

/**
 * Created by lurs on 2018/8/23 0023.
 */

public interface ComfirmOrderContract {
    interface View extends BaseContract.BaseView {
        /**
         * 确认支付
         */
        void comfirmOrderSuccess(ComfirmOrderBean comfirmOrderBean);

        /**
         * 商品订单支付
         */
        void FetchPayBuyerGoodsSuccess(ComfirmOrderBean comfirmOrderBean);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        /**
         * 确认支付
         */
        void comfirmOrder(String... s);

        /**
         * 商品订单支付
         */
        void FetchPayBuyerGoods(String... s);

    }
}
