package com.smg.art.presenter.contract.activity;

import android.widget.Button;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.AddBankCardBean;
import com.smg.art.bean.AuctionGoodsBean;
import com.smg.art.bean.PhoneVerifyCodeBean;
import com.smg.art.bean.SystemMessageBean;

import rx.Observable;

/**
 * Created by Mervin on 2018/8/2 0002.
 */

public interface SystemMessageActivityContract {

    interface View extends BaseContract.BaseView {

        /**
         * 获取系统消息列表
         */
        void FetchGetListFrontSuccess(SystemMessageBean systemMessageBean);




    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 获取系统消息列表
         */
        void FetchGetListFront(String... s);
    }
}
