package com.smg.art.presenter.contract.activity;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.PersonalCenterBean;
import com.smg.art.bean.UpLoadBean;

/**
 * Created by Mervin on 2018/8/1 0001.
 */

public interface SettingContract {
    interface View extends BaseContract.BaseView {
        /**
         * 上传头像
         */
        void FetchUploadPicSuccess(UpLoadBean upLoadBean);

        /**
         * 个人中心
         */
        void FetchPersonalCenterSuccess(PersonalCenterBean announcementAuctionListBean);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        void FetchUploadPic(String... s);

        /**
         * 个人中心
         */
        void FetchPersonalCenter(String... s);
    }

}
