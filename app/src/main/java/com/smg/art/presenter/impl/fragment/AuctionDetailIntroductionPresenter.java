
package com.smg.art.presenter.impl.fragment;

import com.smg.art.api.Api;
import com.smg.art.base.AuctionDetailBean;
import com.smg.art.base.BasePresenter;
import com.smg.art.base.FindCustomerServiceBean;
import com.smg.art.bean.AuctionCenterBean;
import com.smg.art.bean.SaveCollectsBean;
import com.smg.art.presenter.contract.fragment.AuctionContract;
import com.smg.art.presenter.contract.fragment.AuctionDeatilIntroductionContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class AuctionDetailIntroductionPresenter extends BasePresenter<AuctionDeatilIntroductionContract.View> implements AuctionDeatilIntroductionContract.Presenter<AuctionDeatilIntroductionContract.View> {

    private Api api;

    @Inject
    public AuctionDetailIntroductionPresenter(Api api) {
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
