
package com.smg.art.presenter.impl.login;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.PhoneVerifyCodeBean;
import com.smg.art.bean.RegisterBean;
import com.smg.art.presenter.contract.login.RegisterContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RegisterActivityPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter<RegisterContract.View> {

    private Api api;

    @Inject
    public RegisterActivityPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void FetchRegister(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchREGISTER(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(RegisterBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchRegisterSuccess(data);
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
