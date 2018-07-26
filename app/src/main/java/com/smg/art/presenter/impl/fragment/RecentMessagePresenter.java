
package com.smg.art.presenter.impl.fragment;

import com.blankj.utilcode.utils.LogUtils;
import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.presenter.contract.activity.RecentMessageContract;

import java.util.List;

import javax.inject.Inject;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;


public class RecentMessagePresenter extends BasePresenter<RecentMessageContract.View> implements RecentMessageContract.Presenter<RecentMessageContract.View> {

    private Api api;

    @Inject
    public RecentMessagePresenter(Api api) {
        this.api = api;

    }

    @Override
    public void getConversationList() {
        RongIMClient.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
            @Override
            public void onSuccess(List<Conversation> conversations) {
                if (conversations != null && conversations.size() > 0) {
//                    mData.clear();
//                    mData.addAll(conversations);
//                    filterData(mData);
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                LogUtils.e("加载最近会话失败：" + errorCode);
            }
        });
    }
}
