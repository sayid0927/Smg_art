package com.smg.art.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.smg.art.R;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.fragment.RecentMessageContract;
import com.smg.art.presenter.impl.fragment.RecentMessagePresenter;
import com.smg.art.ui.adapter.RecentMessageApadter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.rong.imlib.model.Conversation;

/**
 * Created by Administrator on 2018/8/7.
 */

public class RecentMessageFragment extends BaseFragment implements RecentMessageContract.View {

    @Inject
    RecentMessagePresenter mPresenter;

    @BindView(R.id.rv)
    RecyclerView rv;

    private RecentMessageApadter apadter;
    private List<Conversation> data = new ArrayList<>();

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
        mPresenter.attachView(this, getActivity());
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }


    @Override
    protected void initView(Bundle bundle) {
        super.initView(bundle);

        mPresenter.getConversationList();
        apadter = new RecentMessageApadter(data,getActivity());
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(apadter);

    }

    @Override
    public void getConversationListSuccess(List<Conversation> conversationBean) {
          data = conversationBean;
          apadter.setNewData(data);
    }

    @Override
    public void showError(String message) {

    }
}
