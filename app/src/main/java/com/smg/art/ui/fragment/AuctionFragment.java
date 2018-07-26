package com.smg.art.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smg.art.R;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.bean.AuctionGoodsBean;
import com.smg.art.bean.GoodsBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.fragment.AuctionContract;
import com.smg.art.presenter.impl.fragment.AuctionPresenter;
import com.smg.art.ui.activity.AuctionDeatilActivity;
import com.smg.art.ui.activity.GoodsDetailActivity;
import com.smg.art.ui.activity.MainActivity;
import com.smg.art.ui.adapter.AuctionGoodsListApadter;
import com.smg.art.ui.adapter.GoodsListApadter;
import com.smg.art.view.MyLoadMoreView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Mervin on 2018/7/24 0024.
 */

public class AuctionFragment extends BaseFragment implements AuctionContract.View, BaseQuickAdapter.RequestLoadMoreListener {


    @Inject
    AuctionPresenter mPresenter;

    @BindView(R.id.ivToolbarNavigation)
    ImageView ivToolbarNavigation;
    @BindView(R.id.rv_goods)
    RecyclerView rvGoods;

    private AuctionGoodsListApadter mAdapter;
    private AuctionGoodsBean auctionGoodsBean;
    private List<AuctionGoodsBean> auctionGoodsBeans;

    @Override
    protected void initView(Bundle bundle) {
        super.initView(bundle);
        ivToolbarNavigation.setVisibility(View.GONE);

        auctionGoodsBeans = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            AuctionGoodsBean auctionGoodsBean = new AuctionGoodsBean();
            auctionGoodsBeans.add(auctionGoodsBean);
        }

        mAdapter = new AuctionGoodsListApadter(auctionGoodsBeans, getSupportActivity());
        mAdapter.setOnLoadMoreListener(this, rvGoods);
        mAdapter.setLoadMoreView(new MyLoadMoreView());
        rvGoods.setLayoutManager(new LinearLayoutManager(getSupportActivity()));
        rvGoods.setAdapter(mAdapter);

        mAdapter.OnAuctionGoodsItemListener(new AuctionGoodsListApadter.OnAuctionGoodsItemListener() {
            @Override
            public void OnAuctionGoodsItemListener(AuctionGoodsBean item) {
                Intent i = new Intent(getActivity(), AuctionDeatilActivity.class);
                MainActivity.mainActivity.startActivityIn(i, getActivity());
            }
        });

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void loadData() {
        setState(Constant.STATE_SUCCESS);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.auction_fragment;
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
    public void onLoadMoreRequested() {
        //加载更多
    }

}
