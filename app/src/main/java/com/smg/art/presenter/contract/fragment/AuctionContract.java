
package com.smg.art.presenter.contract.fragment;


import com.smg.art.base.BaseContract;
import com.smg.art.bean.AuctionGoodsBean;

public interface AuctionContract {

    interface View extends BaseContract.BaseView {

        /**
         * 我的拍卖列表
         */
        void FetchAuctionListByNameSuccess(AuctionGoodsBean auctionGoodsBean);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        void FetchAuctionListByName(String... s);
    }
}
