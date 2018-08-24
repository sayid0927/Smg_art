package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.LogisticInfo;
import com.smg.art.presenter.contract.activity.LogisticInfoContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lurs on 2018/8/23 0023.
 */

public class LogisticInfoPresenter extends BasePresenter<LogisticInfoContract.View> implements LogisticInfoContract.Presenter<LogisticInfoContract.View> {
    private Api api;

    @Inject
    public LogisticInfoPresenter(Api api) {
        this.api = api;
    }

    public void logisticInfo(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchLogisticInfoService(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LogisticInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(LogisticInfo data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.logisticInfoSuccess(data);
                        }
                    }
                }));
    }


}
