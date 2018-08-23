
package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.AddressListBean;
import com.smg.art.presenter.contract.activity.AddAddressListContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class AddAddressListActivityPresenter extends BasePresenter<AddAddressListContract.View> implements AddAddressListContract.Presenter<AddAddressListContract.View> {

    private Api api;

    @Inject
    public AddAddressListActivityPresenter(Api api) {
        this.api = api;

    }


    @Override
    public void FetchCreateAddress(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchCreateAddress(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddressListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(AddressListBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchCreateAddressSuccess(data);
                        }
                    }
                }));
    }

    @Override
    public void FetchUpdateAddress(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchUpdateAddress(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddressListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(AddressListBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchUpdateAddressSuccess();
                        }
                    }
                }));
    }
}
