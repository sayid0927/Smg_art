
package com.smg.art.presenter.contract.fragment;


import android.widget.EditText;

import com.smg.art.base.AuctionDetailBean;
import com.smg.art.base.BaseContract;
import com.smg.art.base.Constant;
import com.smg.art.bean.AuctionCenterBean;
import com.smg.art.bean.RefundBean;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

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
