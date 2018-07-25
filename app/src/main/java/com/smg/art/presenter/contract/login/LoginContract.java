package com.smg.art.presenter.contract.login;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.LoginBean;

/**
 * Created by Mervin on 2018/7/25 0025.
 */

public interface LoginContract {
    interface View extends BaseContract.BaseView {

        /**
         * 登录成功
         */
        void FetchLoginSuccess(LoginBean loginBean);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 登录
         */
        void FetchLogin(String... s);

    }
}
