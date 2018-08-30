
package com.smg.art.presenter.contract.fragment;



import com.smg.art.bean.AnnouncementAuctionListBean;
import com.smg.art.base.BaseContract;

public interface ClassifyChildContract {

    interface View extends BaseContract.BaseView {

        /**
         * 首页搜索框查询
         */
        void  FetchAuctionListByNameSuccess(AnnouncementAuctionListBean announcementAuctionListBean);

        /**
         * 首页公告期分页查询
         */
        void  FetchAnnouncementAuctionListSuccess(AnnouncementAuctionListBean announcementAuctionListBean);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 首页搜索框查询
         */
        void  FetchAuctionListByName(String ...s);

        /**
         * 首页公告期分页查询
         */
        void  FetchAnnouncementAuctionList(String ...s);


    }
}
