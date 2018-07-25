package com.smg.art.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smg.art.R;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.bean.RecentMessageBean;
import com.smg.art.component.AppComponent;
import com.smg.art.ui.activity.MainActivity;
import com.smg.art.ui.activity.SystemMessageActivity;
import com.smg.art.ui.adapter.RecentMessageApadter;
import com.smg.art.view.MyLoadMoreView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Lenovo on 2018/7/25.
 */

public class RecentMessageFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener {

    
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
        rlMessage.setAdapter(mApadter);

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

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void onLoadMoreRequested() {

    }

}
