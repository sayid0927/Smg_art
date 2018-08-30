
package com.smg.art.presenter.impl.fragment;

import com.smg.art.api.Api;
import com.smg.art.bean.AuctionDetailBean;
import com.smg.art.base.BasePresenter;
import com.smg.art.presenter.contract.activity.AuctionDeatilContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class AuctionDeatilPresenter extends BasePresenter<AuctionDeatilContract.View> implements AuctionDeatilContract.Presenter<AuctionDeatilContract.View> {

    private Api api;

    @Inject
    public AuctionDeatilPresenter(Api api) {
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
}
