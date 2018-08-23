package com.smg.art.ui.personalcenter.fragemnt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smg.art.R;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.bean.AuctionOrderBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.fragment.ComplaintIngContract;
import com.smg.art.presenter.contract.fragment.ComplaintRecordContract;
import com.smg.art.presenter.impl.activity.ComplaintPresenter;
import com.smg.art.presenter.impl.fragment.ComplaintIngPresenter;
import com.smg.art.presenter.impl.fragment.ComplaintRecordPresenter;
import com.smg.art.ui.activity.MainActivity;
import com.smg.art.ui.personalcenter.ComplaintDeatilActivity;
import com.smg.art.ui.personalcenter.StartComplaintActivity;
import com.smg.art.ui.personalcenter.adapter.ComplaintIngApadter;
import com.smg.art.ui.personalcenter.adapter.ComplaintRecordApadter;
import com.smg.art.utils.LocalAppConfigUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Lenovo on 2018/7/26.
 */

public class ComplaintRecordFragment extends BaseFragment implements ComplaintRecordContract.View,
        OnLoadmoreListener, OnRefreshListener, ComplaintRecordApadter.OnComplaintPostItemListener{


    @Inject
    ComplaintRecordPresenter mPresenter;


    @BindView(R.id.rv)
    RecyclerView rv;

    @BindView(R.id.srl)
    SmartRefreshLayout srl;

    private int page = 1;
    private int count = 10;
    private ComplaintRecordApadter apadter;
    private List<AuctionOrderBean.DataBean> dataBeans = new ArrayList<>();


    @Override
    public void loadData() {
        setState(Constant.STATE_SUCCESS);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_complaint;
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

        apadter = new ComplaintRecordApadter(dataBeans, getActivity());
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(apadter);
        srl.setOnLoadmoreListener(this);
        srl.setOnRefreshListener(this);
        apadter.OnComplaintPostItemListener(this);

        mPresenter.FetchComplainAuctionInfoList("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                "type", String.valueOf(0), "page", String.valueOf(page), "rows", String.valueOf(count));

    }



    @Override
    public void FetchComplainAuctionInfoListSuccess(AuctionOrderBean auctionOrderBean) {
        if (srl.isLoading()) {
            if (auctionOrderBean.getData() != null)
                apadter.addData(auctionOrderBean.getData());
            srl.finishLoadmore();
        } else {

            if (dataBeans != null && dataBeans.size() != 0) dataBeans.clear();
            if (srl.isRefreshing()) srl.finishRefresh();
            dataBeans = auctionOrderBean.getData();
            apadter.setNewData(dataBeans);

        }
    }


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        // 加载更多
        page++;
        mPresenter.FetchComplainAuctionInfoList("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                "type", String.valueOf(0), "page", String.valueOf(page), "rows", String.valueOf(count));

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        //下拉刷新
        page = 1;
        mPresenter.FetchComplainAuctionInfoList("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                "type", String.valueOf(0), "page", String.valueOf(page), "rows", String.valueOf(count));

    }


    /**
     *  查看详情
     */
    @Override
    public void OnComplaintPostItemListener(AuctionOrderBean.DataBean item) {

        Intent intent = new Intent(getActivity(), ComplaintDeatilActivity.class);
        intent.putExtra("data",new Gson().toJson(item));
        MainActivity.mainActivity.startActivityIn(intent, getActivity());

    }


    @Override
    public void showError(String message) {

    }
}
