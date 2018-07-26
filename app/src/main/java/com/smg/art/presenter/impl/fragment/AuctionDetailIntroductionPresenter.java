
package com.smg.art.presenter.impl.fragment;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.presenter.contract.fragment.AuctionContract;
import com.smg.art.presenter.contract.fragment.AuctionDeatilIntroductionContract;

import javax.inject.Inject;


public class AuctionDetailIntroductionPresenter extends BasePresenter<AuctionDeatilIntroductionContract.View> implements AuctionDeatilIntroductionContract.Presenter<AuctionDeatilIntroductionContract.View> {

    private Api api;

    @Inject
    public AuctionDetailIntroductionPresenter(Api api) {
        this.api = api;

    }
}
