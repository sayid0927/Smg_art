
package com.smg.art.presenter.impl.fragment;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.presenter.contract.fragment.AuctionContract;

import javax.inject.Inject;


public class AuctionPresenter extends BasePresenter<AuctionContract.View> implements AuctionContract.Presenter<AuctionContract.View> {

    private Api api;

    @Inject
    public AuctionPresenter(Api api) {
        this.api = api;

    }
}
