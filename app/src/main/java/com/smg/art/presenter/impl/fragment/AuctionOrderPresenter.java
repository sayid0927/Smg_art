package com.smg.art.presenter.impl.fragment;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.AuctionOrderBean;
import com.smg.art.presenter.contract.fragment.AuctionOrderConstrat;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mervin on 2018/8/1 0001.
 */

public class AuctionOrderPresenter extends BasePresenter<AuctionOrderConstrat.View> implements AuctionOrderConstrat.Presenter<AuctionOrderConstrat.View> {

    private Api api;

    @Inject
    public AuctionOrderPresenter(Api api) {
        this.api = api;

    }

    @Override
    public void FetchAuctionOrder(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchAuctionOrder().subscribeOn(Schedulers.io())
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
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchAuctionOrderSuccess(data);
                        }
                    }
                }));
    }
}
