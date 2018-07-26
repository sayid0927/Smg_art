
package com.smg.art.presenter.impl.fragment;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.presenter.contract.activity.AuctionDeatilContract;

import javax.inject.Inject;


public class AuctionDeatilPresenter extends BasePresenter<AuctionDeatilContract.View> implements AuctionDeatilContract.Presenter<AuctionDeatilContract.View> {

    private Api api;

    @Inject
    public AuctionDeatilPresenter(Api api) {
        this.api = api;

    }
}
