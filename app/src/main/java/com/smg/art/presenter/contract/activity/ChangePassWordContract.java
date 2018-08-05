package com.smg.art.presenter.contract.activity;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.ChangePassWordBean;

/**
 * Created by Mervin on 2018/8/4 0004.
 */

public interface ChangePassWordContract {

    interface View extends BaseContract.BaseView {

        /**
         * 修改密码
         */
        void FetchChangePassWordSuccess(ChangePassWordBean changePassWordBean);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        /**
         * 修改密码
         */
        void FetchChangePassWord(String... s);

    }
}
