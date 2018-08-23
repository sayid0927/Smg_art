
package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.SearchAreaBean;
import com.smg.art.presenter.contract.activity.SelectAreaContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class SelectAreaPresenter extends BasePresenter<SelectAreaContract.View> implements SelectAreaContract.Presenter<SelectAreaContract.View> {

    private Api api;

    @Inject
    public SelectAreaPresenter(Api api) {
        this.api = api;
    }


    @Override
    public void FetchRegionInfo(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchRegionInfo(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchAreaBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(SearchAreaBean data) {
                        hideWaitingDialog();
                        if (mView != null && data != null  ) {
                            mView.FetchRegionInfoSuccess(data);
                        }
                    }
                }));
    }
}
