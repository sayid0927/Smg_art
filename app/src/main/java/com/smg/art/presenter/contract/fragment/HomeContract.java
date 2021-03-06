
package com.smg.art.presenter.contract.fragment;



import com.smg.art.bean.AnnouncementAuctionListBean;
import com.smg.art.base.BaseContract;
import com.smg.art.bean.HomePageImgBean;

public interface HomeContract {

    interface View extends BaseContract.BaseView {


        /**
         * 首页广告图片列表
         */
        void  FetchHomePageImgSuccess(HomePageImgBean  homePageImgBean);

        /**
         * 首页公告期分页查询
         */
        void  FetchAnnouncementAuctionListSuccess(AnnouncementAuctionListBean announcementAuctionListBean);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 首页广告图片列表
         */
        void  FetchHomePageImg();
        /**
         * 首页公告期分页查询
         */
        void  FetchAnnouncementAuctionList(String ...s);


    }
}
