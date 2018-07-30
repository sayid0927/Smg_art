
package com.smg.art.presenter.contract.fragment;



import com.smg.art.base.AuctionDetailBean;
import com.smg.art.base.BaseContract;
import com.smg.art.base.HomePageImgBean;
import com.smg.art.bean.Apk_UpdateBean;

public interface HomeContract {

    interface View extends BaseContract.BaseView {

        void ApkUpdateS(Apk_UpdateBean.DataBean dataBean);

        /**
         * 首页广告图片列表
         */
        void  FetchHomePageImgSuccess(HomePageImgBean  homePageImgBean);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 首页广告图片列表
         */
        void  FetchHomePageImg();


    }
}
