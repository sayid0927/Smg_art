
package com.smg.art.presenter.contract.fragment;



import com.smg.art.base.AnnouncementAuctionListBean;
import com.smg.art.base.BaseContract;
import com.smg.art.bean.Apk_UpdateBean;

public interface ClassifyChildContract {

    interface View extends BaseContract.BaseView {

        /**
         * 首页搜索框查询
         */
        void  FetchAuctionListByNameSuccess(AnnouncementAuctionListBean announcementAuctionListBean);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 首页搜索框查询
         */
        void  FetchAuctionListByName(String ...s);
    }
}
