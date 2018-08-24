package com.smg.art.presenter.contract.activity;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.OderDetailBean;
import com.smg.art.bean.RefundBean;
import com.smg.art.bean.ServiceBean;

import rx.Observable;

/**
 * Created by Mervin on 2018/8/8 0008.
 */

public interface OderdetailContract {
    interface View extends BaseContract.BaseView {
        /**
         * 查看单个拍卖
         */
        void FetchQueryAuctionInfoSuccess(OderDetailBean oderDetailBean);

        /**
         * 查看单个投诉记录
         */
        void FetchRefundSuccess(RefundBean refundBean);


        /**
         * 确认收货
         */
        void FetchConfirmBuyerGoodsSuccess(ServiceBean serviceBean);






    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {


        /**
         * 查看单个拍卖
         */
        void FetchQueryAuctionInfoCenter(String... s);

        /**
         * 查看单个投诉记录
         */
        void FetchRefundCenter(String... s);

        /**
         * 确认收货
         */
        void FetchConfirmBuyerGoods(String... s);

    }
}
