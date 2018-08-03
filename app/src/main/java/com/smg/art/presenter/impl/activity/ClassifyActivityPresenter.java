
package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.AnnouncementAuctionListBean;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.Apk_UpdateBean;
import com.smg.art.bean.CashDepositiBean;
import com.smg.art.presenter.contract.activity.ClassifyContract;
import com.smg.art.presenter.contract.activity.MainContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ClassifyActivityPresenter extends BasePresenter<ClassifyContract.View> implements ClassifyContract.Presenter<ClassifyContract.View> {

    private Api api;

    @Inject
    public ClassifyActivityPresenter(Api api) {
        this.api = api;


    }

}
