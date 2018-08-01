package com.smg.art.presenter.contract.fragment;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.AuctionOrderBean;

/**
 *  拍卖订单
 * Created by Mervin on 2018/7/27 0027.
 */

public interface AuctionOrderConstrat {
    interface View extends BaseContract.BaseView {

        /**
         * 获取我的拍卖
         */
        void FetchAuctionOrderSuccess(AuctionOrderBean auctionOrderBean);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        void FetchAuctionOrder(String... s);
    }
}
