package com.smg.art.presenter.impl.activity;

import android.graphics.BitmapFactory;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.AddBankCardBean;
import com.smg.art.bean.PhoneVerifyCodeBean;
import com.smg.art.presenter.contract.activity.AddBankCardContract;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mervin on 2018/8/2 0002.
 */

public class AddBankCardPresenter extends BasePresenter<AddBankCardContract.View> implements AddBankCardContract.Presenter<AddBankCardContract.View> {
    private Api api;

    @Inject
    public AddBankCardPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void FetchAddBankCard(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchAddBankCard(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddBankCardBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(AddBankCardBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchAddBankCardSuccess(data);
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

    @Override
    public void FetchPictureCode(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchPictureCode(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
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
                    public void onNext(ResponseBody data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.iv().setImageBitmap(BitmapFactory.decodeStream(data.byteStream()));
                        }
                    }
                }));
    }
}
