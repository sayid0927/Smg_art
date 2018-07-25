
package com.smg.art.presenter.impl.activity;

import com.orhanobut.logger.Logger;
import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.Apk_UpdateBean;
import com.smg.art.presenter.contract.activity.MainContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivityPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter<MainContract.View> {

    private Api api;

    @Inject
    public MainActivityPresenter(Api api) {
        this.api = api;

    }
}
