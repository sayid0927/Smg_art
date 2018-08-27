package com.smg.art.ui.personalcenter.fragemnt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smg.art.R;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.bean.AuctionOrderBean;
import com.smg.art.bean.ComplaintBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.fragment.ComplaintApplyContract;
import com.smg.art.presenter.impl.fragment.ComplaintApplyPresenter;
import com.smg.art.ui.activity.MainActivity;
import com.smg.art.ui.personalcenter.StartComplaintActivity;
import com.smg.art.ui.personalcenter.adapter.ComplaintApplyApadter;
import com.smg.art.utils.LocalAppConfigUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Lenovo on 2018/7/26.
 */

public class ComplaintApplyFragment extends BaseFragment implements ComplaintApplyContract.View,
        OnLoadmoreListener, OnRefreshListener, ComplaintApplyApadter.OnComplaintPostItemListener {

    @Inject
    ComplaintApplyPresenter mPresenter;

    @BindView(R.id.rv)
    RecyclerView rv;

    @BindView(R.id.srl)
    SmartRefreshLayout srl;

    private int page = 1;
    private int count = 10;
    private ComplaintApplyApadter apadter;
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
        EventBus.getDefault().register(this);
        apadter = new ComplaintApplyApadter(dataBeans, getActivity());
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(apadter);
        srl.setOnLoadmoreListener(this);
        srl.setOnRefreshListener(this);
        apadter.OnComplaintPostItemListener(this);

        mPresenter.FetchComplainAuctionInfoList("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                "type", String.valueOf(0), "page", String.valueOf(page), "rows", String.valueOf(count));

    }

    @Subscribe
    public void getEventBus(ComplaintBean complaintBean) {
        page = 1;
        mPresenter.FetchComplainAuctionInfoList("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                "type", String.valueOf(0), "page", String.valueOf(page), "rows", String.valueOf(count));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showError(String message) {

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
     *  申请投诉
     */
    @Override
    public void OnComplaintPostItemListener(AuctionOrderBean.DataBean item) {
        if (!("3").equals(item.getOrderStatus() ) &&!("2").equals(item.getOrderStatus() ) && !("1").equals(item.getOrderStatus() )) {
            Intent intent = new Intent(getActivity(), StartComplaintActivity.class);
            intent.putExtra("auctionId", item.getId());
            intent.putExtra("goodsId", item.getGoodsId());
            MainActivity.mainActivity.startActivityIn(intent, getActivity());
        }
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
            if(apadter.getItemCount()==0){
                apadter.setEmptyView(LayoutInflater.from(getActivity()).inflate(R.layout.complain_state_empty, null));
            }
        }
    }
}
