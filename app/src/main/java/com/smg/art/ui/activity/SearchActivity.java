package com.smg.art.ui.activity;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.GoodsBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.SearchContract;
import com.smg.art.presenter.impl.activity.SearchActivityPresenter;
import com.smg.art.ui.adapter.HistoricalSearchApadter;
import com.smg.art.ui.adapter.HotSearchApadter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements SearchContract.View {

    @Inject
    SearchActivityPresenter mPresenter;

    @BindView(R.id.ll_back)
    RelativeLayout llBack;
    @BindView(R.id.rv_hot_search)
    RecyclerView rvHotSearch;
    @BindView(R.id.rv_historical_search)
    RecyclerView rvHistoricalSearch;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.rv_goods)
    RecyclerView rvGoods;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;

    private HotSearchApadter hotSearchApadter;
    private HistoricalSearchApadter historicalSearchApadter;
    private List<GoodsBean> historicalSearch = new ArrayList<>();
    private List<GoodsBean> hotSearch = new ArrayList<>();

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
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

        for(int i=0;i<10;i++){
            GoodsBean goodsBean = new GoodsBean();
            hotSearch.add(goodsBean);
        }

        for(int i=0;i<50;i++){
            GoodsBean goodsBean = new GoodsBean();
            historicalSearch.add(goodsBean);
        }

        historicalSearchApadter = new HistoricalSearchApadter(historicalSearch,this);
        hotSearchApadter = new HotSearchApadter(hotSearch,this);
        rvHotSearch.setLayoutManager(new GridLayoutManager(this,3));
        rvHistoricalSearch.setLayoutManager(new LinearLayoutManager(this));
        hotSearchApadter.addHeaderView(View.inflate(this,R.layout.hotsearch_header,null));
        historicalSearchApadter.addHeaderView(View.inflate(this,R.layout.historicalsearch_header,null));

        rvHotSearch.setAdapter(hotSearchApadter);
        rvHistoricalSearch.setAdapter(historicalSearchApadter);

    }

    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back: //返回
                finish();
                break;
        }
    }

    @Override
    public void showError(String message) {

    }
}
