package com.smg.art.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smg.art.R;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.bean.AuctionGoodsBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.fragment.AuctionContract;
import com.smg.art.presenter.impl.fragment.AuctionPresenter;
import com.smg.art.ui.activity.AuctionDeatilActivity;
import com.smg.art.ui.activity.MainActivity;
import com.smg.art.ui.activity.SearchActivity;
import com.smg.art.ui.adapter.AuctionGoodsListApadter;
import com.smg.art.utils.TimerItemUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.et_SearchContent)
    EditText etSearchContent;
    Unbinder unbinder;

    private AuctionGoodsListApadter mAdapter;
    private AuctionGoodsBean auctionGoodsBean;
    private List<AuctionGoodsBean> auctionGoodsBeans;

    @Override
    protected void initView(Bundle bundle) {
        super.initView(bundle);


        tvSearch.setVisibility(View.VISIBLE);
        etSearchContent.setVisibility(View.GONE);


        ivToolbarNavigation.setVisibility(View.GONE);

        srl.setPrimaryColorsId(R.color.main_color);
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                srl.finishRefresh();
            }

        });
        srl.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                srl.finishLoadmore();
            }
        });
        mAdapter = new AuctionGoodsListApadter(getSupportActivity(), TimerItemUtil.getTimerItemList());
        rvGoods.setLayoutManager(new LinearLayoutManager(getSupportActivity()));
        rvGoods.setAdapter(mAdapter);

        mAdapter.OnAuctionGoodsItemListener(new AuctionGoodsListApadter.OnAuctionGoodsItemListener() {
            @Override
            public void OnAuctionGoodsItemListener(int item) {
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cancelAllTimers();
        }
    }

    @OnClick({R.id.rl_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_search:
                Intent i = new Intent(getActivity(), SearchActivity.class);
                i.putExtra("status", "4");
                MainActivity.mainActivity.startActivityIn(i, getActivity());
                break;
        }
    }

}
