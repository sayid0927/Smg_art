package com.smg.art.presenter.contract.activity;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.SystemMessageBean;

import rx.Observable;

/**
 * Created by Mervin on 2018/8/2 0002.
 */

public interface OrderMessageActivityContract {

    interface View extends BaseContract.BaseView {

        /**
         * 获取系统消息列表
         */
        void FetchOrderLidtFrontSuccess(SystemMessageBean systemMessageBean);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 获取系统消息列表
         */
        void FetchOrderLidtFront(String... s);
    }
}
