package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.OderDetailBean;
import com.smg.art.bean.RefundBean;
import com.smg.art.bean.ServiceBean;
import com.smg.art.presenter.contract.activity.OderdetailContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mervin on 2018/8/8 0008.
 */

public class OrderDetailPresenter extends BasePresenter<OderdetailContract.View> implements OderdetailContract.Presenter<OderdetailContract.View> {
    private Api api;

    @Inject
    public OrderDetailPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void FetchQueryAuctionInfoCenter(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchQueryAuctionInfoService(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OderDetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(OderDetailBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchQueryAuctionInfoSuccess(data);
                        }
                    }
                }));
    }

    @Override
    public void FetchRefundCenter(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchRefundService(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RefundBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(RefundBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchRefundSuccess(data);
                        }
                    }
                }));
    }

    @Override
    public void FetchConfirmBuyerGoods(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchConfirmBuyerGoods(s).subscribeOn(Schedulers.io())
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
                        if (mView != null && data != null && data.getStatus()==1) {
                            hideWaitingDialog();
                            mView.FetchConfirmBuyerGoodsSuccess(data);
                        }
                    }
                }));
    }
}
