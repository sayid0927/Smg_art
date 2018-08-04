package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.CheckBankCardBean;
import com.smg.art.bean.CurrencyExchangeRateBean;
import com.smg.art.bean.WithDrawBean;
import com.smg.art.presenter.contract.activity.WithdrawContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mervin on 2018/8/2 0002.
 */

public class WithDrawPresenter extends BasePresenter<WithdrawContract.View> implements WithdrawContract.Presenter<WithdrawContract.View> {
    private Api api;

    @Inject
    public WithDrawPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void FetchCheckBankCard(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchCheckBankCard(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CheckBankCardBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(CheckBankCardBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchCheckBankCardSuccess(data);
                        }
                    }
                }));
    }

    @Override
    public void FetchWithdrawCode(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchWithDraw(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WithDrawBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(WithDrawBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchWithdrawSuccess(data);
                        }
                    }
                }));
    }

    @Override
    public void FetchCurrencyExchangeRate(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchCurrencyExchangeRate(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CurrencyExchangeRateBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(CurrencyExchangeRateBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchCurrencyExchangeRateSuccess(data);
                        }
                    }
                }));
    }
}
