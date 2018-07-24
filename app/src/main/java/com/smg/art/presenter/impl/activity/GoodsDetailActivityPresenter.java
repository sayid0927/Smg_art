
package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.presenter.contract.activity.ClassifyContract;
import com.smg.art.presenter.contract.activity.GoodsDetailContract;

import javax.inject.Inject;


public class GoodsDetailActivityPresenter extends BasePresenter<GoodsDetailContract.View> implements GoodsDetailContract.Presenter<GoodsDetailContract.View> {

    private Api api;

    @Inject
    public GoodsDetailActivityPresenter(Api api) {
        this.api = api;

    }

}
