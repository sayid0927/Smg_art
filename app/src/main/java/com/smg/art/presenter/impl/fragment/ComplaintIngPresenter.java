
package com.smg.art.presenter.impl.fragment;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.AuctionOrderBean;
import com.smg.art.presenter.contract.fragment.ComplaintIngContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ComplaintIngPresenter extends BasePresenter<ComplaintIngContract.View> implements ComplaintIngContract.Presenter<ComplaintIngContract.View> {

    private Api api;

    @Inject
    public ComplaintIngPresenter(Api api) {
        this.api = api;

    }


    @Override
    public void FetchComplainAuctionInfoList(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchComplainAuctionInfoList(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AuctionOrderBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(AuctionOrderBean data) {
                        hideWaitingDialog();
                        if (mView != null && data != null && data.getStatus() == 1) {
                            mView.FetchComplainAuctionInfoListSuccess(data);
                        } else {
                            if (mView != null && data != null)
                                mView.showError(data.getMsg());
                        }
                    }
                }));
    }
}
