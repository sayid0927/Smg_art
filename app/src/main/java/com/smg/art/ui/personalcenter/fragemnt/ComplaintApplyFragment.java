package com.smg.art.ui.personalcenter.fragemnt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.EmptyUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smg.art.R;
import com.smg.art.base.AuctionBuyerDepositBean;
import com.smg.art.base.AuctionDetailBean;
import com.smg.art.base.BaseApplication;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.bean.AuctionCenterBean;
import com.smg.art.bean.AuctionOrderBean;
import com.smg.art.bean.ComplaintApplyBean;
import com.smg.art.bean.ComplaintBean;
import com.smg.art.bean.RefundBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.fragment.AuctionCentreContract;
import com.smg.art.presenter.contract.fragment.ComplaintApplyContract;
import com.smg.art.presenter.impl.fragment.AuctionConterPresenter;
import com.smg.art.presenter.impl.fragment.ComplaintApplyPresenter;
import com.smg.art.ui.activity.MainActivity;
import com.smg.art.ui.adapter.AuctionCentreListApadter;
import com.smg.art.ui.fragment.AuctionCentreFragment;
import com.smg.art.ui.personalcenter.StartComplaintActivity;
import com.smg.art.ui.personalcenter.adapter.ComplaintApplyApadter;
import com.smg.art.utils.GlideUtils;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.view.CustomDialog;
import com.smg.art.view.MyBridgeWebView;
import com.smg.art.view.RoundImageView;

import org.greenrobot.eventbus.Subscribe;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Lenovo on 2018/7/26.
 */

public class ComplaintApplyFragment extends BaseFragment implements ComplaintApplyContract.View, OnLoadmoreListener, OnRefreshListener, ComplaintApplyApadter.OnComplaintPostItemListener {

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

        apadter = new ComplaintApplyApadter(dataBeans, getActivity());
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(apadter);
        srl.setOnLoadmoreListener(this);
        srl.setOnRefreshListener(this);
        apadter.OnComplaintPostItemListener(this);

        mPresenter.FetchAuctionOrder("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                "type", String.valueOf(0), "page", String.valueOf(page), "rows", String.valueOf(count));

    }

    @Subscribe
    public void getEventBus(ComplaintBean complaintBean) {
        page = 1;
        mPresenter.FetchAuctionOrder("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                "type", String.valueOf(0), "page", String.valueOf(page), "rows", String.valueOf(count));
    }


    @Override
    public void showError(String message) {

    }

    @Override
    public void FetchAuctionOrderSuccess(AuctionOrderBean auctionOrderBean) {

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
        mPresenter.FetchAuctionOrder("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                "type", String.valueOf(0), "page", String.valueOf(page), "rows", String.valueOf(count));

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        //下拉刷新
        page = 1;
        mPresenter.FetchAuctionOrder("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                "type", String.valueOf(0), "page", String.valueOf(page), "rows", String.valueOf(count));

    }





    /**
     *  申请投诉
     */
    @Override
    public void OnComplaintPostItemListener(AuctionOrderBean.DataBean item) {
        if (item.getOrderStatus() != 3 && item.getOrderStatus() != 2 && item.getOrderStatus() != 1) {
            Intent intent = new Intent(getActivity(), StartComplaintActivity.class);
            intent.putExtra("auctionId", item.getId());
            intent.putExtra("goodsId", item.getGoodsId());
            MainActivity.mainActivity.startActivityIn(intent, getActivity());
        }
    }
}
