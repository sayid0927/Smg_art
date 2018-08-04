
package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.AnnouncementAuctionListBean;
import com.smg.art.base.AuctionBuyerDepositBean;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.CreatWordsBean;
import com.smg.art.bean.HotWordsListBean;
import com.smg.art.bean.LoginBean;
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
                .subscribe(new Observer<HotWordsListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(HotWordsListBean data) {
                        hideWaitingDialog();
                        if (mView != null && data != null && data.getStatus() == 1) {
                            mView.FetchHotWordsListSuccess(data);
                        } else {
                            if(mView != null && data != null && data.getStatus() == 10000){
                                 mView.onRestartLoging();
                            }
                            if (data != null && data.getMsg() != null)
                                mView.showError(data.getMsg());
                        }
                    }
                }));
    }

    @Override
    public void FetchLogin(String... s) {
        addSubscrebe(api.FetchLogin(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(LoginBean data) {
                        if (mView != null && data != null && data.getStatus()==1) {
                            hideWaitingDialog();
                            mView.FetchLoginSuccess(data);
                        }
                    }
                }));
    }

    @Override
    public void FetchAuctionListByName(String... s) {
        addSubscrebe(api.FetchAuctionListByName(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AnnouncementAuctionListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(AnnouncementAuctionListBean data) {
                        if (mView != null && data != null && data.getStatus()==1) {
                            hideWaitingDialog();
                            mView.FetchAuctionListByNameSuccess(data);
                        }
                    }
                }));
    }

    @Override
    public void FetchCreatWordsBean(String... s) {
        addSubscrebe(api.FetchCreatWordsBean(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CreatWordsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(CreatWordsBean data) {
                        if (mView != null && data != null && data.getStatus()==1) {
                            hideWaitingDialog();
                            mView.FetchCreatWordsBeanSuccess(data);
                        }
                    }
                }));
    }

    @Override
    public void FetchDeleteWordById(String... s) {
        addSubscrebe(api.FetchDeleteWordById(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CreatWordsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(CreatWordsBean data) {
                        if (mView != null && data != null && data.getStatus()==1) {
                            hideWaitingDialog();
                            mView.FetchDeleteWordByIdSuccess(data);
                        }
                    }
                }));
    }
}
