
package com.smg.art.presenter.impl.fragment;

import com.smg.art.api.Api;
import com.smg.art.base.AnnouncementAuctionListBean;
import com.smg.art.base.BasePresenter;
import com.smg.art.presenter.contract.fragment.ClassifyChildContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ClassifyChildPresenter extends BasePresenter<ClassifyChildContract.View> implements ClassifyChildContract.Presenter<ClassifyChildContract.View> {

    private Api api;

    @Inject
    public ClassifyChildPresenter(Api api) {
        this.api = api;

    }

    @Override
    public void FetchAuctionListByName(String... s) {
        addSubscrebe(api.FetchAuctionListByName(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AnnouncementAuctionListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(AnnouncementAuctionListBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchAuctionListByNameSuccess(data);
                        }
                    }
                }));
    }
}
