package com.smg.art.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smg.art.R;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.bean.ConversationBean;
import com.smg.art.bean.RecentMessageBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.RecentMessageContract;
import com.smg.art.presenter.impl.fragment.RecentMessagePresenter;
import com.smg.art.ui.activity.MainActivity;
import com.smg.art.ui.activity.SystemMessageActivity;
import com.smg.art.ui.adapter.RecentMessageApadter;
import com.smg.art.view.MyLoadMoreView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Lenovo on 2018/7/25.
 */

public class RecentMessageFragment extends BaseFragment implements RecentMessageContract.View,BaseQuickAdapter.RequestLoadMoreListener {

    @Inject
    RecentMessagePresenter mPresenter;

    @BindView(R.id.rl_message)
    RecyclerView rlMessage;

    private RecentMessageApadter mApadter;
    private RecentMessageBean messageBean;
    private List<RecentMessageBean> messageBeans;


    @Override
    protected void initView(Bundle bundle) {
        super.initView(bundle);

        messageBeans = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            RecentMessageBean messageBean = new RecentMessageBean();
            messageBeans.add(messageBean);
        }

        mApadter = new RecentMessageApadter(messageBeans, getSupportActivity());
        mApadter.setOnLoadMoreListener(this, rlMessage);
        mApadter.setLoadMoreView(new MyLoadMoreView());
        rlMessage.setLayoutManager(new LinearLayoutManager(getSupportActivity()));
        mApadter.addHeaderView(View.inflate(getActivity(),R.layout.message_rl_header,null));
        rlMessage.setAdapter(mApadter);

        mPresenter.getConversationList();



        mApadter.OnMessageItemListener(new RecentMessageApadter.OnMessageItemListener() {
            @Override
            public void OnMessageItemListener(RecentMessageBean item) {
                MainActivity.mainActivity.startActivityIn(new Intent(getActivity(), SystemMessageActivity.class),getActivity());
            }
        });
    }

    @Override
    public void loadData() {
        setState(Constant.STATE_SUCCESS);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_recent_message;
    }

    @Override
    public void attachView() {
      mPresenter.attachView(this,getActivity());
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void getConversationListSuccess(ConversationBean conversationBean) {

    }
}
