package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.WalletBalanceBean;
import com.smg.art.presenter.contract.activity.MyWalletContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mervin on 2018/8/2 0002.
 */

public class MyWalletPresenter extends BasePresenter<MyWalletContract.View> implements MyWalletContract.Presenter<MyWalletContract.View> {
    private Api api;

    @Inject
    public MyWalletPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void FetchMyWalletBalance(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchMyWalletBalance(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WalletBalanceBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(WalletBalanceBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchMyWalletBalanceSuccess(data);
                        }
                    }
                }));
    }
}
