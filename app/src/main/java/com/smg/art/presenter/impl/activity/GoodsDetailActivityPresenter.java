
package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.bean.AuctionBuyerDepositBean;
import com.smg.art.bean.AuctionDetailBean;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.FindCustomerServiceBean;
import com.smg.art.bean.SaveCollectsBean;
import com.smg.art.presenter.contract.activity.GoodsDetailContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class GoodsDetailActivityPresenter extends BasePresenter<GoodsDetailContract.View> implements GoodsDetailContract.Presenter<GoodsDetailContract.View> {

    private Api api;

    @Inject
    public GoodsDetailActivityPresenter(Api api) {
        this.api = api;

    }


    @Override
    public void FetchHomepageGetauctiondetail(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchHomepageGetauctiondetail(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AuctionDetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(AuctionDetailBean data) {
                        hideWaitingDialog();
                        if (mView != null && data != null && data.getStatus() == 1) {
                            mView.FetchHomepageGetauctiondetailSuccess(data);
                        } else {
                            if (data != null && data.getMsg() != null)
                                mView.showError(data.getMsg());
                        }
                    }
                }));
    }

    @Override
    public void FetchMembercollectspageSave(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchMembercollectspageSave(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SaveCollectsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(SaveCollectsBean data) {
                        hideWaitingDialog();
                        if (mView != null && data != null && data.getStatus() == 1) {
                            mView.FetchMembercollectspageSaveSuccess(data);
                        } else {
                            if (data != null && data.getMsg() != null)
                                mView.showError(data.getMsg());
                        }
                    }
                }));
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
    public void FetchFindCustomerService(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchFindCustomerService(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindCustomerServiceBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(FindCustomerServiceBean data) {
                        hideWaitingDialog();
                        if (mView != null && data != null && data.getStatus() == 1) {
                            mView.FetchFindCustomerServiceSuccess(data);
                        } else {
                            if (data != null && data.getMsg() != null)
                                mView.showError(data.getMsg());
                        }
                    }
                }));
    }
}
