package com.smg.art.presenter.impl.activity;

import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.ComfirmOrderBean;
import com.smg.art.bean.LogisticInfo;
import com.smg.art.presenter.contract.activity.ComfirmOrderContract;
import com.smg.art.presenter.contract.activity.LogisticInfoContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lurs on 2018/8/23 0023.
 */

public class ComfirmOrderPresente extends BasePresenter<ComfirmOrderContract.View> implements ComfirmOrderContract.Presenter<ComfirmOrderContract.View> {
    private Api api;

    @Inject
    public ComfirmOrderPresente(Api api) {
        this.api = api;
    }

    @Override
    public void comfirmOrder(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchComfirmOrderService(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ComfirmOrderBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ComfirmOrderBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.comfirmOrderSuccess(data);
                        }
                    }
                }));
    }

    @Override
    public void FetchPayBuyerGoods(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchPayBuyerGoods(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ComfirmOrderBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                       ToastUtils.showLongToast(e.getMessage());
                    }

                    @Override
                    public void onNext(ComfirmOrderBean data) {
                        if (mView != null && data != null) {
                            hideWaitingDialog();
                            mView.FetchPayBuyerGoodsSuccess(data);
                        }
                    }
                }));
    }
}
