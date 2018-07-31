package com.smg.art.ui.activity;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
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
import com.smg.art.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
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
    private  List<String> hosts = new ArrayList<>();

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
        setSwipeBackEnable(true);
        for(int i=0;i<20;i++){
//            GoodsBean goodsBean = new GoodsBean();
//            hotSearch.add(goodsBean);
            hosts.add("FFF");
        }

        for(int i=0;i<50;i++){
            GoodsBean goodsBean = new GoodsBean();
            historicalSearch.add(goodsBean);
        }

        historicalSearchApadter = new HistoricalSearchApadter(historicalSearch,this);
        historicalSearchApadter.addHeaderView(View.inflate(this,R.layout.historicalsearch_header,null));
        rvHistoricalSearch.setLayoutManager(new LinearLayoutManager(this));
        rvHistoricalSearch.setAdapter(historicalSearchApadter);

        rvHotSearch.setLayoutManager(new StaggeredGridLayoutManager(7,StaggeredGridLayoutManager.VERTICAL));
        hotSearchApadter = new HotSearchApadter(hosts,this);
        rvHotSearch.setAdapter(hotSearchApadter);
        hotSearchApadter.addHeaderView(View.inflate(this,R.layout.hotsearch_header,null));
        SpacesItemDecoration decoration=new SpacesItemDecoration(6);
        rvHotSearch.addItemDecoration(decoration);
        mPresenter.FetchHotWordsList();

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

    /**
     * 获取搜索列表
     */
    @Override
    public void FetchHotWordsListSuccess() {

    }

    private int dp2px(float value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }
}
