
package com.smg.art.presenter.impl.fragment;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.AuctionGoodsBean;
import com.smg.art.presenter.contract.fragment.AuctionContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class AuctionPresenter extends BasePresenter<AuctionContract.View> implements AuctionContract.Presenter<AuctionContract.View> {

    private Api api;

    @Inject
    public AuctionPresenter(Api api) {
        this.api = api;

    }

    @Override
    public void FetchAuctionListByName(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchAuctionList(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AuctionGoodsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(AuctionGoodsBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchAuctionListByNameSuccess(data);
                        }
                    }
                }));
    }
}
