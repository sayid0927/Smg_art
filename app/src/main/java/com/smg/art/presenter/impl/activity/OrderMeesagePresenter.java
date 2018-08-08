package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.SystemMessageBean;
import com.smg.art.presenter.contract.activity.OrderMessageActivityContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mervin on 2018/8/2 0002.
 */

public class OrderMeesagePresenter extends BasePresenter<OrderMessageActivityContract.View> implements OrderMessageActivityContract.Presenter<OrderMessageActivityContract.View> {
    private Api api;

    @Inject
    public OrderMeesagePresenter(Api api) {
        this.api = api;
    }

    @Override
    public void FetchOrderLidtFront(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchOrderLidtFront(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SystemMessageBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(SystemMessageBean data) {
                        if (mView != null && data != null && data.getStatus() == 1) {
                            hideWaitingDialog();
                            mView.FetchOrderLidtFrontSuccess(data);
                        }
                    }
                }));
    }
}
