package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.AddBankCardBean;
import com.smg.art.bean.AuctionGoodsBean;
import com.smg.art.bean.PhoneVerifyCodeBean;
import com.smg.art.bean.SystemMessageBean;
import com.smg.art.presenter.contract.activity.SystemMessageActivityContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mervin on 2018/8/2 0002.
 */

public class SystemMeesagePresenter extends BasePresenter<SystemMessageActivityContract.View> implements SystemMessageActivityContract.Presenter<SystemMessageActivityContract.View> {
    private Api api;

    @Inject
    public SystemMeesagePresenter(Api api) {
        this.api = api;
    }


    @Override
    public void FetchGetListFront(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchGetListFront(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SystemMessageBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(SystemMessageBean data) {
                        if (mView != null && data != null && data.getStatus() == 1) {
                            hideWaitingDialog();
                            mView.FetchGetListFrontSuccess(data);
                        }
                    }
                }));
    }
}
