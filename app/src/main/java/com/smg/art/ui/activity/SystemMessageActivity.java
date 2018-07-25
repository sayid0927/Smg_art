package com.smg.art.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.GoodsBean;
import com.smg.art.bean.SystemMessageBean;
import com.smg.art.component.AppComponent;
import com.smg.art.ui.adapter.GoodsListApadter;
import com.smg.art.ui.adapter.SystemMessageApadter;
import com.smg.art.view.MyLoadMoreView;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SystemMessageActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    AutoRelativeLayout rlBack;
    @BindView(R.id.actionbar_title)
    TextView actionbarTitle;
    @BindView(R.id.rl_system_message)
    RecyclerView rlSystemMessage;



    private SystemMessageApadter mAdapter;
    private SystemMessageBean systemMessageBean;
    private List<SystemMessageBean> systemMessageBeans;


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_system_message;
    }

    @Override
    public void attachView() {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void initView() {

        actionbarTitle.setText(R.string.system_message);

        systemMessageBeans = new ArrayList<>();
        for(int i=0;i<30;i++){
            SystemMessageBean systemMessageBean = new SystemMessageBean();
            systemMessageBeans.add(systemMessageBean);
        }

        mAdapter = new SystemMessageApadter(systemMessageBeans, this);
//        mAdapter.setOnLoadMoreListener(get, rvGoods);
        mAdapter.setLoadMoreView(new MyLoadMoreView());
        rlSystemMessage.setLayoutManager(new LinearLayoutManager(this));
        rlSystemMessage.setAdapter(mAdapter);

    }


    @OnClick({R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
