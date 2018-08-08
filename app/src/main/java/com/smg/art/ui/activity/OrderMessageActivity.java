package com.smg.art.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.SystemMessageBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.OrderMessageActivityContract;
import com.smg.art.presenter.contract.activity.SystemMessageActivityContract;
import com.smg.art.presenter.impl.activity.OrderMeesagePresenter;
import com.smg.art.presenter.impl.activity.SystemMeesagePresenter;
import com.smg.art.ui.adapter.SystemMessageApadter;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.view.MyLoadMoreView;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderMessageActivity extends BaseActivity implements OrderMessageActivityContract.View, OnLoadmoreListener, OnRefreshListener {

    @Inject
    OrderMeesagePresenter mPresenter;

    @BindView(R.id.rl_back)
    AutoRelativeLayout rlBack;
    @BindView(R.id.actionbar_title)
    TextView actionbarTitle;
    @BindView(R.id.rl_system_message)
    RecyclerView rlSystemMessage;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;

    private SystemMessageApadter mAdapter;
    private List<SystemMessageBean.DataBean.RowsBean> systemMessageBeans;
    private  int page = 1;
    private  int rows =10;


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_system_message;
    }

    @Override
    public void attachView() {
        mPresenter.attachView(this, this);
    }

    @Override
    public void detachView() {
        mPresenter.detachView();
    }

    @Override
    public void initView() {
        setSwipeBackEnable(true);
        actionbarTitle.setText(R.string.order_message);

        mAdapter = new SystemMessageApadter(systemMessageBeans, this);
        mAdapter.setLoadMoreView(new MyLoadMoreView());
        rlSystemMessage.setLayoutManager(new LinearLayoutManager(this));
        rlSystemMessage.setAdapter(mAdapter);
        srl.setOnLoadmoreListener(this);
        srl.setOnRefreshListener(this);

          mPresenter.FetchOrderLidtFront("page",String.valueOf(page),"rows",String.valueOf(rows),
                  "memberId",String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()));

    }


    @OnClick({R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
        }
    }


    /**
     * 获取系统消息列表
     */
    @Override
    public void FetchOrderLidtFrontSuccess(SystemMessageBean systemMessageBean) {
        if(srl.isLoading()){
            mAdapter.addData(systemMessageBean.getData().getRows());
            srl.finishLoadmore();
        }else {
            if(srl.isRefreshing()) srl.finishRefresh();
            if(systemMessageBeans.size()!=0)systemMessageBeans.clear();
            this.systemMessageBeans = systemMessageBean.getData().getRows();
            mAdapter.setNewData(systemMessageBeans);
        }
    }

    @Override
    public void showError(String message) {
        ToastUtils.showLongToast(message);
    }


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        // 加载更多
        page++;
        mPresenter.FetchOrderLidtFront("page",String.valueOf(page),"rows",String.valueOf(rows),
                "memberId",String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()));
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        //下拉刷新
        page = 1;
        mPresenter.FetchOrderLidtFront("page",String.valueOf(page),"rows",String.valueOf(rows),
                "memberId",String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()));
    }

}
