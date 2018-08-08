package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.base.CardUrlBean;
import com.smg.art.bean.CheckBankCardBean;
import com.smg.art.bean.ReChargeBean;
import com.smg.art.presenter.contract.activity.ReChargeContract;

import java.util.List;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mervin on 2018/8/3 0003.
 */

public class ReChargePresenter extends BasePresenter<ReChargeContract.View> implements ReChargeContract.Presenter<ReChargeContract.View> {
    private Api api;

    @Inject
    public ReChargePresenter(Api api) {
        this.api = api;
    }

    @Override
    public void FetchReCharge(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchReCharge(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReChargeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ReChargeBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchReChargeSuccess(data);
                        }
                    }
                }));
    }

    @Override
    public void FetchCheckBankCard(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchCheckBankCard(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CheckBankCardBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(CheckBankCardBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchCheckBankCardSuccess(data);
                        }
                    }
                }));
    }

    @Override
    public void FetchUploadFile(List<MultipartBody.Part> parts) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchUploadFile(parts).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CardUrlBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(CardUrlBean data) {
                        if (mView != null && data != null && data.getStatus() == 1) {
                            hideWaitingDialog();
                            mView.FetchUploadFileSuccess(data);
                        } else {
                            if (mView != null && data != null)
                                mView.showError(data.getMsg());
                        }
                    }
                }));
    }
}
