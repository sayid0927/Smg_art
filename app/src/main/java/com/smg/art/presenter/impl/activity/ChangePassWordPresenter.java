package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.ChangePassWordBean;
import com.smg.art.presenter.contract.activity.ChangePassWordContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mervin on 2018/8/4 0004.
 */

public class ChangePassWordPresenter extends BasePresenter<ChangePassWordContract.View> implements ChangePassWordContract.Presenter<ChangePassWordContract.View> {


    private Api api;

    @Inject
    public ChangePassWordPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void FetchChangePassWord(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchChangePassWord(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChangePassWordBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ChangePassWordBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchChangePassWordSuccess(data);
                        }
                    }
                }));

    }
}
