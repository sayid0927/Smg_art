
package com.smg.art.presenter.contract.activity;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.AddressListBean;

import rx.Observable;

public interface AddAddressListContract {

    interface View extends BaseContract.BaseView {

        /**
         * 新增收货地址
         */
        void  FetchCreateAddressSuccess(AddressListBean addressListBean);
        /**
         * 修改收货地址
         */
        void  FetchUpdateAddressSuccess();

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 新增收货地址
         */
        void  FetchCreateAddress(String ...s);
        /**
         * 修改收货地址
         */
        void  FetchUpdateAddress(String ...s);

    }
}
