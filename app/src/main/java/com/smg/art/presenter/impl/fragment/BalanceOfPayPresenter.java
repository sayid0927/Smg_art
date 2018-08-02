package com.smg.art.presenter.impl.fragment;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.BalanceOfPayBean;
import com.smg.art.presenter.contract.fragment.BalanceContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mervin on 2018/8/2 0002.
 */

public class BalanceOfPayPresenter extends BasePresenter<BalanceContract.View> implements BalanceContract.Presenter<BalanceContract.View> {

    private Api api;

    @Inject
    public BalanceOfPayPresenter(Api api) {
        this.api = api;

    }

    @Override
    public void FetchBalanceOfPayment(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchBalanceOfPay(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BalanceOfPayBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(BalanceOfPayBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchBalanceOfPaymentSuccess(data);
                        }
                    }
                }));
    }
}
