package com.smg.art.presenter.contract.activity;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.CollectionBean;

/**
 * Created by Mervin on 2018/7/30 0030.
 */

public interface MyCollectionContract {

    interface View extends BaseContract.BaseView {
        /**
         * 获取我的收藏
         */
        void FetchMyCollectionSuccess(CollectionBean collectionBean);


    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        void FetchMyCollection(String... s);
    }
}
