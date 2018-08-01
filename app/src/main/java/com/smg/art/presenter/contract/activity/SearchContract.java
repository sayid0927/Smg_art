
package com.smg.art.presenter.contract.activity;

import com.smg.art.base.BaseContract;

public interface SearchContract {

    interface View extends BaseContract.BaseView {

        void  FetchHotWordsListSuccess();


    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        void  FetchHotWordsList();
    }
}
