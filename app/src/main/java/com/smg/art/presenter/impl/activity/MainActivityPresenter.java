
package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.Apk_UpdateBean;
import com.smg.art.presenter.contract.activity.MainContract;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivityPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter<MainContract.View> {

    private Api api;

    @Inject
    public MainActivityPresenter(Api api) {
        this.api = api;

    }

    @Override
    public void Apk_Update() {
        showWaitingDialog("加载中...");
        addSubscrebe(api.Fetch_Apk_Update().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    if (mView != null && data.getRes().equals("00000")) {
                        hideWaitingDialog();
                        Apk_UpdateBean.DataBean dataBean = data.getData();
                        mView.ApkUpdateS(dataBean);
                        fillView(dataBean);
                    }
                }, this::loadError));
    }

    private void fillView(Apk_UpdateBean.DataBean dataBean) {
//        mView.tv().setText(dataBean.getApk_Update_Path());
    }

    private void loadError(Throwable throwable) {
        hideWaitingDialog();
    }
}
