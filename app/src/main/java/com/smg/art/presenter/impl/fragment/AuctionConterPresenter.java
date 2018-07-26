
package com.smg.art.presenter.impl.fragment;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.presenter.contract.fragment.AuctionCentreContract;
import com.smg.art.presenter.contract.fragment.AuctionContract;

import javax.inject.Inject;


public class AuctionConterPresenter extends BasePresenter<AuctionCentreContract.View> implements AuctionCentreContract.Presenter<AuctionCentreContract.View> {

    private Api api;

    @Inject
    public AuctionConterPresenter(Api api) {
        this.api = api;

    }
}
