
package com.smg.art.presenter.contract.fragment;



import com.smg.art.base.BaseContract;
import com.smg.art.bean.AuctionOrderBean;

public interface ComplaintApplyContract {

    interface View extends BaseContract.BaseView {

        /**
         * 我的拍卖
         */
        void FetchAuctionOrderSuccess(AuctionOrderBean auctionOrderBean);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        void FetchAuctionOrder(String... s);
    }
}
