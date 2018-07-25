package com.smg.art.presenter.impl.login;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.ForgetPasswordBean;
import com.smg.art.bean.PhoneVerifyCodeBean;
import com.smg.art.presenter.contract.login.ForgetPasswordContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mervin on 2018/7/25 0025.
 */

public class ForgetPasswordPresenter extends BasePresenter<ForgetPasswordContract.View> implements ForgetPasswordContract.Presenter<ForgetPasswordContract.View> {
    private Api api;

    @Inject
    public ForgetPasswordPresenter(Api api) {
        this.api = api;
    }


    @Override
    public void FetchForgetPassword(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchForgetPassword(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ForgetPasswordBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ForgetPasswordBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchForgetPasswordSuccess(data);
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
