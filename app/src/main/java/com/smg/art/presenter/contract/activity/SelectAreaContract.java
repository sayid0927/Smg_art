
package com.smg.art.presenter.contract.activity;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.SearchAreaBean;

public interface SelectAreaContract {

    interface View extends BaseContract.BaseView {

        /**
         * 获取省市区信息
         */
        void  FetchRegionInfoSuccess(SearchAreaBean  searchAreaBean);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 获取省市区信息
         */
        void  FetchRegionInfo(String ...s);
    }
}
