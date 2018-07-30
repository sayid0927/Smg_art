
package com.smg.art.presenter.impl.fragment;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.base.HomePageImgBean;
import com.smg.art.presenter.contract.fragment.HomeContract;
import com.smg.art.presenter.contract.fragment.MyFragmentContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MyFragmentPresenter extends BasePresenter<MyFragmentContract.View> implements MyFragmentContract.Presenter<MyFragmentContract.View> {

    private Api api;

    @Inject
    public MyFragmentPresenter(Api api) {
        this.api = api;

    }
}
