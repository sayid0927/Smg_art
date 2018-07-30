
package com.smg.art.presenter.contract.activity;

import com.smg.art.base.AuctionBuyerDepositBean;
import com.smg.art.base.AuctionDetailBean;
import com.smg.art.base.BaseContract;
import com.smg.art.base.Constant;
import com.smg.art.bean.SaveCollectsBean;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface GoodsDetailContract {

    interface View extends BaseContract.BaseView {


        /**
         * 拍卖品详情
         */
        void  FetchHomepageGetauctiondetailSuccess(AuctionDetailBean auctionDetailBean);
        /**
         * 新增收藏商品
         */
        void  FetchMembercollectspageSaveSuccess(SaveCollectsBean saveCollectsBean);

        /**
         * 保证金支付
         */
        void  FetchAuctionBuyerDepositSuccess(AuctionBuyerDepositBean auctionBuyerDepositBean);


    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 拍卖品详情
         */
        void  FetchHomepageGetauctiondetail(String ...s);

        /**
         * 新增收藏商品
         */
        void  FetchMembercollectspageSave(String ...s);

        /**
         * 保证金支付
         */
        void  FetchAuctionBuyerDeposit(String ...s);

    }
}
