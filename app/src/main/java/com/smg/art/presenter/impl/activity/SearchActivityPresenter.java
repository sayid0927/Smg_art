
package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.presenter.contract.activity.SearchContract;

import javax.inject.Inject;


public class SearchActivityPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter<SearchContract.View>{

    private Api api;

    @Inject
    public SearchActivityPresenter(Api api) {
        this.api = api;
    }



}
