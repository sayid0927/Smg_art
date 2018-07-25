package com.smg.art.presenter.impl.login;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.LoginBean;
import com.smg.art.presenter.contract.login.LoginContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mervin on 2018/7/25 0025.
 */

public class LoginActivityPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter<LoginContract.View> {
    private Api api;

    @Inject
    public LoginActivityPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void FetchLogin(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchLogin(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(LoginBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchLoginSuccess(data);
                        }
                    }
                }));
    }
}
