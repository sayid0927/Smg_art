
package com.smg.art.presenter.impl.fragment;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.base.HomePageImgBean;
import com.smg.art.bean.AddFriendBean;
import com.smg.art.bean.AddressBookFriendsBean;
import com.smg.art.presenter.contract.fragment.ContactsFragmentContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ContactsFragmentPresenter extends BasePresenter<ContactsFragmentContract.View> implements ContactsFragmentContract.Presenter<ContactsFragmentContract.View> {

    private Api api;

    @Inject
    public ContactsFragmentPresenter(Api api) {
        this.api = api;


    }

    @Override
    public void FetchAddressBookFriends(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchAddressBookFriends(s).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddressBookFriendsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitingDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(AddressBookFriendsBean data) {
                        hideWaitingDialog();
                        if (mView != null && data != null && data.getStatus() == 1) {
                            mView.FetchAddressBookFriendsSuccess(data);
                        } else {
                            if (data != null && data.getMsg() != null)
                                mView.showError(data.getMsg());
                        }
                    }
                }));
    }

    @Override
    public void FetchUpdateFriendRelation(String... s) {
        showWaitingDialog("加载中...");
        addSubscrebe(api.FetchUpdateFriendRelation(s).subscribeOn(Schedulers.io())
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
                            mView.FetchUpdateFriendRelationSuccess(data);
                        } else {
                            if (data != null && data.getMsg() != null)
                                mView.showError(data.getMsg());
                        }
                    }
                }));
    }
}
