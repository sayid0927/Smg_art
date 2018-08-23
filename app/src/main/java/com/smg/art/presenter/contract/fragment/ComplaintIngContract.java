
package com.smg.art.presenter.contract.fragment;



import com.smg.art.base.BaseContract;
import com.smg.art.bean.AuctionOrderBean;

import rx.Observable;

public interface ComplaintIngContract {

    interface View extends BaseContract.BaseView {

        /**
         * 查询投诉记录
         */
        void FetchComplainAuctionInfoListSuccess(AuctionOrderBean auctionOrderBean);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        /**
         * 查询投诉记录
         */
        void FetchComplainAuctionInfoList(String... s);
    }
}
