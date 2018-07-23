
package com.smg.art.presenter.impl.fragment;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.presenter.contract.fragment.HomeContract;

import javax.inject.Inject;


public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter<HomeContract.View> {

    private Api api;

    @Inject
    public HomePresenter(Api api) {
        this.api = api;

    }
}
