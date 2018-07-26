
package com.smg.art.presenter.contract.activity;

import android.widget.TextView;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.Apk_UpdateBean;

public interface MainContract {

    interface View extends BaseContract.BaseView {
        /**
         * 建立与融云服务器的连接成功
         */
        void connectSuccess();

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        /**
         * 建立与融云服务器的连接
         */
        void connect(String token);

    }
}
