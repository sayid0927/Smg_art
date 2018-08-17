package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.CollectionBean;
import com.smg.art.bean.DeleteCollectionBean;
import com.smg.art.presenter.contract.activity.MyCollectionContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mervin on 2018/7/30 0030.
 */

public class MyCollectionPresenter extends BasePresenter<MyCollectionContract.View> implements MyCollectionContract.Presenter<MyCollectionContract.View> {
    private Api api;

    @Inject
    public MyCollectionPresenter(Api api) {
        this.api = api;
    }


    @Override
    public void FetchMyCollection(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchCollection(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CollectionBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(CollectionBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchMyCollectionSuccess(data);
                        }
                    }
                }));
    }

    @Override
    public void FetchDeleteCollection(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchDeleteCollectionService(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeleteCollectionBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(DeleteCollectionBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchDeleteCollectionSuccess(data);
                        }
                    }
                }));
    }
}