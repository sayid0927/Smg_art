
package com.smg.art.presenter.impl.fragment;

import com.smg.art.api.Api;
import com.smg.art.base.AuctionDetailBean;
import com.smg.art.base.BasePresenter;
import com.smg.art.base.HomePageImgBean;
import com.smg.art.bean.LoginBean;
import com.smg.art.presenter.contract.fragment.HomeContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter<HomeContract.View> {

    private Api api;

    @Inject
    public HomePresenter(Api api) {
        this.api = api;

    }

    @Override
    public void FetchHomePageImg() {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchHomePageImgBean().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomePageImgBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(HomePageImgBean data) {
                        if (mView != null && data != null && data.getStatus() ==1) {
                            hideWaitingDialog();
                            mView.FetchHomePageImgSuccess(data);
                        }else {
                            if(data!=null && data.getMsg()!=null){
                                mView.showError(data.getMsg());
                            }
                        }
                    }
                }));
    }
}
