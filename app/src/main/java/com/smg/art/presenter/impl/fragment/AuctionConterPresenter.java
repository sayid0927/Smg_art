
package com.smg.art.presenter.impl.fragment;

import com.smg.art.api.Api;
import com.smg.art.base.AuctionDetailBean;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.AddressBookFriendsBean;
import com.smg.art.bean.AuctionCenterBean;
import com.smg.art.bean.SystemMessageBean;
import com.smg.art.presenter.contract.fragment.AuctionCentreContract;
import com.smg.art.presenter.contract.fragment.AuctionContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class AuctionConterPresenter extends BasePresenter<AuctionCentreContract.View> implements AuctionCentreContract.Presenter<AuctionCentreContract.View> {

    private Api api;

    @Inject
    public AuctionConterPresenter(Api api) {
        this.api = api;

    }

    @Override
    public void FetchAuctionCenterList(String ...s) {
//        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchAuctionCenterList(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AuctionCenterBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(AuctionCenterBean data) {
                        hideWaitingDialog();
                        if (mView != null && data != null && data.getStatus() == 1 && data.getData().getList()!=null && data.getData().getMaxMoney()!=null ) {
                            mView.FetchAuctionCenterListSuccess(data);
                        } else {
                            if (data != null && data.getMsg() != null)
                                mView.showError(data.getMsg());
                        }
                    }
                }));
    }

    @Override
    public void FetchCreatBidding(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchCreatBidding(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AuctionCenterBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(AuctionCenterBean data) {
                        hideWaitingDialog();
                        if (mView != null && data != null && data.getStatus() == 1) {
                            mView.FetchAuctionCenterListSuccess(data);
                        } else {
                            if (data != null && data.getMsg() != null)
                                mView.showError(data.getMsg());
                        }
                    }
                }));
    }

    @Override
    public void FetchHomepageGetauctiondetail(String... s) {
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
}
