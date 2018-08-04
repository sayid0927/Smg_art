package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.ChangeMobilePhoneBean;
import com.smg.art.bean.PhoneVerifyCodeBean;
import com.smg.art.presenter.contract.activity.SendMobilePhoneContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mervin on 2018/8/3 0003.
 */

public class ChangeMobilePhonePresenter extends BasePresenter<SendMobilePhoneContract.View> implements SendMobilePhoneContract.Presenter<SendMobilePhoneContract.View> {
    private Api api;

    @Inject
    public ChangeMobilePhonePresenter(Api api) {
        this.api = api;
    }

    @Override
    public void FetchChangeMobilePhone(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchChangeMobilePhone(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChangeMobilePhoneBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ChangeMobilePhoneBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchChangeMobilePhoneSuccess(data);
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
