package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.AuctionOrderBean;
import com.smg.art.bean.CardUrlBean;
import com.smg.art.bean.ComplaintBean;
import com.smg.art.presenter.contract.activity.ComplaintActivityContract;

import java.util.List;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mervin on 2018/8/9 0009.
 */

public class ComplaintActivityPresenter extends BasePresenter<ComplaintActivityContract.View> implements ComplaintActivityContract.Presenter<ComplaintActivityContract.View> {
    private Api api;

    @Inject
    public ComplaintActivityPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void FetchComplaint(String... s) {

    }


}
