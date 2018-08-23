
package com.smg.art.presenter.contract.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.AddressListBean;
import com.smg.art.ui.adapter.AddressListApadter;

public interface AddressListContract {

    interface View extends BaseContract.BaseView {


        /**
         * 获取收货地址
         */
        void  FetchAddressListSuccess(AddressListBean addressListBean);
        /**
         * 收货地址删除
         */
        void  FetchDeleteAddressSuccess();

        AddressListApadter getRecyclerView();

        Activity getContext();

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        /**
         * 获取收货地址
         */
        void  FetchAddressList(String ...s);
        /**
         * 收货地址删除
         */
        void  FetchDeleteAddress(String ...s);
    }
}
