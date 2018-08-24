
package com.smg.art.presenter.impl.fragment;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.PersonalCenterBean;
import com.smg.art.bean.ServiceBean;
import com.smg.art.presenter.contract.fragment.MyFragmentContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MyFragmentPresenter extends BasePresenter<MyFragmentContract.View> implements MyFragmentContract.Presenter<MyFragmentContract.View> {

    private Api api;

    @Inject
    public MyFragmentPresenter(Api api) {
        this.api = api;

    }

    @Override
    public void FetchPersonalCenter(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchPersonalCenter(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PersonalCenterBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(PersonalCenterBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchPersonalCenterSuccess(data);
                        }
                    }
                }));
    }

    @Override
    public void FetchService(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchServiceService(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ServiceBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ServiceBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchServiceSuccess(data);
                        }
                    }
                }));
    }
}
