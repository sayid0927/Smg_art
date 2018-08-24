package com.smg.art.presenter.contract.activity;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.LogisticInfo;

/**
 * Created by lurs on 2018/8/23 0023.
 */

public interface LogisticInfoContract {


    interface View extends BaseContract.BaseView {
        /**
         * 物流信息
         */
        void logisticInfoSuccess(LogisticInfo logisticInfo);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        /**
         * 物流信息
         */
        void logisticInfo(String... s);

    }
}
