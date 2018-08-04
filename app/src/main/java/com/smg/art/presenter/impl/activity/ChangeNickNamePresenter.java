package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.ChangeNickBean;
import com.smg.art.presenter.contract.activity.ChangeNickContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mervin on 2018/8/3 0003.
 */

public class ChangeNickNamePresenter extends BasePresenter<ChangeNickContract.View> implements ChangeNickContract.Presenter<ChangeNickContract.View> {

    private Api api;

    @Inject
    public ChangeNickNamePresenter(Api api) {
        this.api = api;
    }

    @Override
    public void FetchChangeNick(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchChangeNick(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChangeNickBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ChangeNickBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchChangeNickSuccess(data);
                        }
                    }
                }));
    }
}
