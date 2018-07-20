
package com.smg.art.presenter.impl;


import com.blankj.utilcode.utils.LogUtils;
import com.smg.art.api.Api;
import com.smg.art.base.RxPresenter;

import com.smg.art.bean.Apk_UpdateBean;
import com.smg.art.presenter.contract.MainContract;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivityPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter<MainContract.View> {


    private Api api;

    @Inject
    public MainActivityPresenter(Api api) {
        this.api = api;
    }


    @Override
    public void Apk_Update() {
        Subscription rxSubscription = api.Fetch_Apk_Update().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Apk_UpdateBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("APK onError>>   "+e.toString());
                    }
                    @Override
                    public void onNext(Apk_UpdateBean data) {
                        if (mView != null && data.getRes().equals("00000")) {
                            Apk_UpdateBean.DataBean dataBean = data.getData();
//                            mView.Apk_Update_Success(dataBean);
                        }
                    }
                });
        addSubscrebe(rxSubscription);

    }


}
