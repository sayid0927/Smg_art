package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.CashDepositiBean;
import com.smg.art.presenter.contract.activity.CashDepositContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mervin on 2018/7/30 0030.
 */

public class CashDepositPresenter extends BasePresenter<CashDepositContract.View> implements CashDepositContract.Presenter<CashDepositContract.View> {
    private Api api;

    @Inject
    public CashDepositPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void FetchCashDeposit(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchCashDeposit(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CashDepositiBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(CashDepositiBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchCashDepositSuccess(data);
                        }
                    }
                }));
    }
}
