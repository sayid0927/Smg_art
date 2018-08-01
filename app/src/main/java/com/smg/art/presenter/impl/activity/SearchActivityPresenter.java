
package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.AuctionBuyerDepositBean;
import com.smg.art.base.BasePresenter;
import com.smg.art.presenter.contract.activity.SearchContract;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class SearchActivityPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter<SearchContract.View> {

    private Api api;

    @Inject
    public SearchActivityPresenter(Api api) {
        this.api = api;
    }


    @Override
    public void FetchHotWordsList() {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchHotWordsList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(Response<ResponseBody> data) {
                        hideWaitingDialog();
//                        if (mView != null && data != null && data.getStatus() == 1) {
//                            mView.FetchHotWordsListSuccess();
//                        } else {
//                            if (data != null && data.getMsg() != null)
//                                mView.showError(data.getMsg());
//                        }
                    }
                }));
    }
}
