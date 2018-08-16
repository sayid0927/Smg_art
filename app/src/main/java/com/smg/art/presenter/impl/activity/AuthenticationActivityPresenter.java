package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.base.CardUrlBean;
import com.smg.art.bean.CreatWordsBean;
import com.smg.art.bean.UpLoadBean;
import com.smg.art.presenter.contract.activity.AuthenticationContract;

import java.util.List;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mervin on 2018/8/1 0001.
 */

public class AuthenticationActivityPresenter extends BasePresenter<AuthenticationContract.View> implements AuthenticationContract.Presenter<AuthenticationContract.View> {
    private Api api;

    @Inject
    public AuthenticationActivityPresenter(Api api) {
        this.api = api;
    }


    @Override
    public void FetchUploadFile(List<MultipartBody.Part> parts) {
        addSubscrebe(api.FetchUploadFile(parts).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CardUrlBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(CardUrlBean data) {
                        if (mView != null && data != null&& data.getStatus()==1) {
                            mView.FetchUploadFileSuccess(data);
                        }else {
                            if(mView != null && data != null)
                                mView.showError(data.getMsg());
                        }
                    }
                }));
    }

    @Override
    public void FetchMemberAuthSave(String ...s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchMemberAuthSave(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CreatWordsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(CreatWordsBean data) {
                        hideWaitingDialog();
                        if (mView != null && data != null && data.getStatus()==1) {
                            hideWaitingDialog();
                            mView.FetchMemberAuthSaveSuccess(data);
                        }else {
                            if(mView != null && data != null)
                                mView.showError(data.getMsg());
                        }
                    }
                }));
    }
}
