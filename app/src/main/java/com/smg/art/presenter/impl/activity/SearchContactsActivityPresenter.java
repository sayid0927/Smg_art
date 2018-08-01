
package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.bean.AddFriendBean;
import com.smg.art.bean.SearchMemberBean;
import com.smg.art.presenter.contract.activity.SearchContactsContract;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class SearchContactsActivityPresenter extends BasePresenter<SearchContactsContract.View> implements SearchContactsContract.Presenter<SearchContactsContract.View> {

    private Api api;

    @Inject
    public SearchContactsActivityPresenter(Api api) {
        this.api = api;
    }


    @Override
    public void FetchSearchMember(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchSearchMember(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchMemberBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(SearchMemberBean data) {
                        hideWaitingDialog();
                        if (mView != null && data != null && data.getStatus() == 1) {
                            mView.FetchSearchMemberSuccess(data);
                        } else {
                            if (data != null && data.getMsg() != null)
                                mView.showError(data.getMsg());
                        }
                    }
                }));
    }

    @Override
    public void FetchAddFriend(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchAddFriend(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddFriendBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(AddFriendBean data) {
                        hideWaitingDialog();
                        if (mView != null && data != null && data.getStatus() == 1) {
                            mView.FetchAddFriendSuccess(data);
                        } else {
                            if (data != null && data.getMsg() != null)
                                mView.showError(data.getMsg());
                        }
                    }
                }));
    }
}
