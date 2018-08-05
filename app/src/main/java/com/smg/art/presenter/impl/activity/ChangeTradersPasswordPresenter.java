package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.ChangeTraderPasswordBean;
import com.smg.art.bean.PhoneVerifyCodeBean;
import com.smg.art.presenter.contract.activity.ChangeTradersPasswordContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mervin on 2018/8/4 0004.
 */

public class ChangeTradersPasswordPresenter extends BasePresenter<ChangeTradersPasswordContract.View> implements ChangeTradersPasswordContract.Presenter<ChangeTradersPasswordContract.View> {
    private Api api;

    @Inject
    public ChangeTradersPasswordPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void FetchChangeTradersPassword(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchChangeTraderPassword(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChangeTraderPasswordBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                        mView.btn().setClickable(true);
                    }

                    @Override
                    public void onNext(ChangeTraderPasswordBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchChangeTradersPasswordSuccess(data);
                        }
                    }
                }));
    }

    @Override
    public void FetchPhoneVerifyCode(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchPhoneVerifyCode(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PhoneVerifyCodeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                        mView.btn().setClickable(true);
                    }

                    @Override
                    public void onNext(PhoneVerifyCodeBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchPhoneVerifyCodeSuccess(data);
                        }
                    }
                }));
    }
}
