
package com.smg.art.presenter.contract;

import com.smg.art.base.BaseContract;

public interface MainContract {

    interface View extends BaseContract.BaseView {

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void Apk_Update();
    }
}
