package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.AuctionBuyerDepositBean;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.AddBankCardBean;
import com.smg.art.bean.PhoneVerifyCodeBean;
import com.smg.art.presenter.contract.activity.AuctionBuyerDepositContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mervin on 2018/8/2 0002.
 */

public class AuctionBuyerDepositPresenter extends BasePresenter<AuctionBuyerDepositContract.View> implements AuctionBuyerDepositContract.Presenter<AuctionBuyerDepositContract.View> {
    private Api api;

    @Inject
    public AuctionBuyerDepositPresenter(Api api) {
        this.api = api;
    }


    @Override
    public void FetchAuctionBuyerDeposit(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchAuctionBuyerDeposit(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AuctionBuyerDepositBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(AuctionBuyerDepositBean data) {
                        hideWaitingDialog();
                        if (mView != null && data != null && data.getStatus() == 1) {
                            mView.FetchAuctionBuyerDepositSuccess(data);
                        } else {
                            if (data != null && data.getMsg() != null)
                                mView.showError(data.getMsg());
                        }
                    }
                }));
    }
}
