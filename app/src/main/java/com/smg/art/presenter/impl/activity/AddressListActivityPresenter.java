
package com.smg.art.presenter.impl.activity;

import android.view.LayoutInflater;

import com.smg.art.R;
import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.AddBankCardBean;
import com.smg.art.bean.AddressListBean;
import com.smg.art.presenter.contract.activity.AddressListContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class AddressListActivityPresenter extends BasePresenter<AddressListContract.View> implements AddressListContract.Presenter<AddressListContract.View> {

    private Api api;

    @Inject
    public AddressListActivityPresenter(Api api) {
        this.api = api;

    }


    @Override
    public void FetchAddressList(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchAddressList(s).subscribeOn(Schedulers.io())
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
                        hideWaitingDialog();
                        if(mView != null && data != null  && data.getData().size()==0 && data.getStatus()==1){
                            mView.getRecyclerView().setEmptyView(
                                    LayoutInflater.from(mView.getContext()
                                    ).inflate(R.layout.address_empty, null));
                        } else if (mView != null && data != null && data.getStatus()==1) {
                            hideWaitingDialog();
                            mView.FetchAddressListSuccess(data);
                        }
                    }
                }));
    }

    @Override
    public void FetchDeleteAddress(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchDeleteAddress(s).subscribeOn(Schedulers.io())
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
                        if (mView != null && data != null && data.getStatus()==1) {
                            hideWaitingDialog();
                            mView.FetchDeleteAddressSuccess();
                        }
                    }
                }));
    }
}
