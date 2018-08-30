
package com.smg.art.presenter.contract.fragment;


import android.widget.EditText;

import com.smg.art.bean.AuctionDetailBean;
import com.smg.art.base.BaseContract;
import com.smg.art.bean.AuctionCenterBean;
import com.smg.art.bean.RefundBean;

public interface AuctionCentreContract {

    interface View extends BaseContract.BaseView {

        /**
         * 竞价列表以及最高价
         */
        void FetchAuctionCenterListSuccess(AuctionCenterBean auctionCenterBean);
        /**
         * 竞价
         */
        void FetchCreatBiddingSuccess(AuctionCenterBean auctionCenterBean);

        /**
         * 拍卖品详情
         */
        void  FetchHomepageGetauctiondetailSuccess(AuctionDetailBean auctionDetailBean);


        /**
         * 验证交易密码
         */
        void  FetchvalidteTradePwdSuccess(RefundBean refundBean);


        EditText getetCreatBidding();

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {


        /**
         * 竞价列表以及最高价
         */
        void FetchAuctionCenterList(String ...s);
        /**
         * 竞价
         */
        void FetchCreatBidding(String ...s);

        /**
         * 拍卖品详情
         */
        void  FetchHomepageGetauctiondetail(String ...s);
        /**
         * 验证交易密码
         */
        void  FetchvalidteTradePwd(String ...s);


    }
}
