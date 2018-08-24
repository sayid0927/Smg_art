
package com.smg.art.presenter.contract.fragment;


import com.smg.art.base.BaseContract;
import com.smg.art.bean.PersonalCenterBean;
import com.smg.art.bean.ServiceBean;

public interface MyFragmentContract {

    interface View extends BaseContract.BaseView {

        /**
         * 个人中心
         */
        void FetchPersonalCenterSuccess(PersonalCenterBean announcementAuctionListBean);

        /**
         * 查看客服信息
         */
        void FetchServiceSuccess(ServiceBean serviceBean);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 个人中心
         */
        void FetchPersonalCenter(String... s);

        /**
         * 查看客服信息
         */
        void FetchService(String... s);
    }
}
