
package com.smg.art.presenter.contract.fragment;


import com.smg.art.base.BaseContract;
import com.smg.art.bean.PersonalCenterBean;

public interface MyFragmentContract {

    interface View extends BaseContract.BaseView {

        /**
         * 个人中心
         */
        void FetchPersonalCenterSuccess(PersonalCenterBean announcementAuctionListBean);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 个人中心
         */
        void FetchPersonalCenter(String... s);
    }
}
