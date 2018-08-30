package com.smg.art.presenter.impl.activity;

import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.api.Api;
import com.smg.art.bean.AuctionBuyerDepositBean;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.PlayIntroductionBean;
import com.smg.art.bean.RefundBean;
import com.smg.art.presenter.contract.activity.AuctionBuyerDepositContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mervin on 2018/8/2 0002.
 */

public class AuctionBuyerDepositPresenter extends BasePresenter<AuctionBuyerDepositContract.View> implements AuctionBuyerDepositContract.Presenter<AuctionBuyerDepositContract.View> {
    private Api api;

    @Inject
    public AuctionBuyerDepositPresenter(Api api) {
        this.api = api;
    }


    @Override
    public void FetchAuctionBuyerDeposit(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchAuctionBuyerDeposit(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AuctionBuyerDepositBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(AuctionBuyerDepositBean data) {
                        hideWaitingDialog();
                        if (mView != null && data != null && data.getStatus() == 1) {
                            mView.FetchAuctionBuyerDepositSuccess(data);
                        } else {
                            if (data != null && data.getMsg() != null)
                                mView.showError(data.getMsg());
                        }
                    }
                }));
    }

    @Override
    public void FetchvalidteTradePwd(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchvalidteTradePwd(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RefundBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        ToastUtils.showLongToast("验证交易密码失败");
                    }

                    @Override
                    public void onNext(RefundBean data) {
                        hideWaitingDialog();
                        if (mView != null && data != null && data.getStatus() == 1) {
                            mView.FetchvalidteTradePwdSuccess(data);
                        } else {
                            if (mView != null && data != null && data.getMsg() != null)
                                ToastUtils.showLongToast(data.getMsg());
                        }
                    }
                }));
    }

    @Override
    public void FetchPayIntroduction(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchPayIntroduction(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PlayIntroductionBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        ToastUtils.showLongToast("");
                    }

                    @Override
                    public void onNext(PlayIntroductionBean data) {
                        hideWaitingDialog();
                        if (mView != null && data != null && data.getStatus() == 1) {
                            mView.FetchPayIntroductionSuccess(data);
                        } else {
                            if (mView != null && data != null && data.getMsg() != null)
                                ToastUtils.showLongToast(data.getMsg());
                        }
                    }
                }));
    }
}
