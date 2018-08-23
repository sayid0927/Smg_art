package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.CardUrlBean;
import com.smg.art.bean.ComplaintBean;
import com.smg.art.presenter.contract.activity.ComplaintContract;

import java.util.List;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mervin on 2018/8/9 0009.
 */

public class ComplaintPresenter extends BasePresenter<ComplaintContract.View> implements ComplaintContract.Presenter<ComplaintContract.View> {
    private Api api;

    @Inject
    public ComplaintPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void FetchComplaint(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchComplaintService(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ComplaintBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ComplaintBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchComplaintSuccess(data);
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
