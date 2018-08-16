
package com.smg.art.presenter.contract.activity;

import com.smg.art.base.AuctionDetailBean;
import com.smg.art.base.BaseContract;

public interface AuctionDeatilContract {

    interface View extends BaseContract.BaseView {

        /**
         * 拍卖品详情
         */
        void  FetchHomepageGetauctiondetailSuccess(AuctionDetailBean auctionDetailBean);



    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 拍卖品详情
         */
        void  FetchHomepageGetauctiondetail(String ...s);
    }
}
