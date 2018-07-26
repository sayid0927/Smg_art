package com.smg.art.ui.fragment;

import com.smg.art.R;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.fragment.AuctionDeatilIntroductionContract;
import com.smg.art.presenter.impl.fragment.AuctionDetailIntroductionPresenter;

import javax.inject.Inject;

/**
 * Created by Lenovo on 2018/7/26.
 */

public class AuctionDetailIntroductionFragment extends BaseFragment implements AuctionDeatilIntroductionContract.View {


    @Inject
    AuctionDetailIntroductionPresenter mPresenter;

    @Override
    public void loadData() {
        setState(Constant.STATE_SUCCESS);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_auction_detail_introduction;
    }

    @Override
    public void attachView() {
        mPresenter.attachView(this, getActivity());
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public void showError(String message) {

    }
}
