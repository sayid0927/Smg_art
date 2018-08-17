package com.smg.art.presenter.contract.activity;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.CollectionBean;
import com.smg.art.bean.DeleteCollectionBean;

/**
 * Created by Mervin on 2018/7/30 0030.
 */

public interface MyCollectionContract {

    interface View extends BaseContract.BaseView {
        /**
         * 获取我的收藏
         */
        void FetchMyCollectionSuccess(CollectionBean collectionBean);

        /**
         * 删除我的收藏
         */
        void FetchDeleteCollectionSuccess(DeleteCollectionBean deleteCollectionBean);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        void FetchMyCollection(String... s);

        void FetchDeleteCollection(String... s);
    }
}
