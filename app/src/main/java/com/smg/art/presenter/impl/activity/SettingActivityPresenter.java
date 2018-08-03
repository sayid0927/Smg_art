package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.PersonalCenterBean;
import com.smg.art.bean.UpLoadBean;
import com.smg.art.presenter.contract.activity.SettingContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mervin on 2018/8/1 0001.
 */

public class SettingActivityPresenter extends BasePresenter<SettingContract.View> implements SettingContract.Presenter<SettingContract.View> {
    private Api api;

    @Inject
    public SettingActivityPresenter(Api api) {
        this.api = api;
    }


    @Override
    public void FetchUploadPic(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchUploadHead(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpLoadBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(UpLoadBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchUploadPicSuccess(data);
                        }
                    }
                }));
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
}
