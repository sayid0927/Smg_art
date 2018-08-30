package com.smg.art.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smg.art.R;
import com.smg.art.bean.AnnouncementAuctionListBean;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.fragment.ClassifyChildContract;
import com.smg.art.presenter.impl.fragment.ClassifyChildPresenter;
import com.smg.art.ui.activity.ClassifyActivity;
import com.smg.art.ui.activity.GoodsDetailActivity;
import com.smg.art.ui.adapter.GoodsListApadter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Lenovo on 2018/7/24.
 */

public class ClassifyChildFragment extends BaseFragment implements ClassifyChildContract.View, OnLoadmoreListener, OnRefreshListener, GoodsListApadter.OnGoodsItemListener {


    @Inject
    ClassifyChildPresenter mPresenter;


    @BindView(R.id.rv_goods)
    RecyclerView rvGoods;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;


    private GoodsListApadter mAdapter;
    private List<AnnouncementAuctionListBean.DataBean.RowsBean> rowsBeans = new ArrayList<>();
    private int id;
    private int page = 1;
    private int rows = 10;
    private  String title;

    public static ClassifyChildFragment getInstance(int id , String title) {
        ClassifyChildFragment sf = new ClassifyChildFragment();
        sf.id = id;
        sf.title = title;
        return sf;
    }


    @Override
    protected void initView(Bundle bundle) {
        super.initView(bundle);

        mAdapter = new GoodsListApadter(rowsBeans, getSupportActivity());
        rvGoods.setLayoutManager(new GridLayoutManager(getSupportActivity(), 2));
        rvGoods.setAdapter(mAdapter);
        srl.setOnLoadmoreListener(this);
        srl.setOnRefreshListener(this);
        mAdapter.OnGoodsItemListener(this);

    }

    @Override
    public void loadData() {
        setState(Constant.STATE_LOADING);
        if(title.equals("全部")){
            mPresenter.FetchAnnouncementAuctionList("page", String.valueOf(page), "rows", String.valueOf(rows));
        }else {
            mPresenter.FetchAuctionListByName("categoryId", String.valueOf(id), "status", String.valueOf(3),
                    "page", String.valueOf(page), "rows", String.valueOf(rows));
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_classifychild;
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
    public void showError(String message) {

    }

    /**
     * 首页搜索框查询
     */
    @Override
    public void FetchAuctionListByNameSuccess(AnnouncementAuctionListBean announcementAuctionListBean) {
        if (srl!=null && srl.isLoading()) {
            mAdapter.addData(announcementAuctionListBean.getData().getRows());
            srl.finishLoadmore();
        } else {
            if(announcementAuctionListBean.getData().getRows().size()==0){
                setState(Constant.STATE_EMPTY);
            }else {
                setState(Constant.STATE_SUCCESS);
                if (rowsBeans.size() != 0) rowsBeans.clear();
                if (srl.isRefreshing()) srl.finishRefresh();
                rowsBeans = announcementAuctionListBean.getData().getRows();
                mAdapter.setNewData(rowsBeans);
            }
        }
    }

    @Override
    public void FetchAnnouncementAuctionListSuccess(AnnouncementAuctionListBean announcementAuctionListBean) {
        if (srl!=null && srl.isLoading()) {
            mAdapter.addData(announcementAuctionListBean.getData().getRows());
            srl.finishLoadmore();
        } else {
            if(announcementAuctionListBean.getData().getRows().size()==0){
                setState(Constant.STATE_EMPTY);
            }else {
                setState(Constant.STATE_SUCCESS);
                if (rowsBeans.size() != 0) rowsBeans.clear();
                if (srl.isRefreshing()) srl.finishRefresh();
                rowsBeans = announcementAuctionListBean.getData().getRows();
                mAdapter.setNewData(rowsBeans);
            }
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        // 加载更多
        page++;
//        mPresenter.FetchAuctionListByName("categoryId", String.valueOf(id), "status", String.valueOf(3),
//                "page", String.valueOf(page), "rows", String.valueOf(rows));
        if(title.equals("全部")){
            mPresenter.FetchAnnouncementAuctionList("page", String.valueOf(page), "rows", String.valueOf(rows));
        }else {
            mPresenter.FetchAuctionListByName("categoryId", String.valueOf(id), "status", String.valueOf(3),
                    "page", String.valueOf(page), "rows", String.valueOf(rows));
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        //下拉刷新
        page = 1;
//        mPresenter.FetchAuctionListByName("categoryId", String.valueOf(id), "status", String.valueOf(3),
//                "page", String.valueOf(page), "rows", String.valueOf(rows));
        if(title.equals("全部")){
            mPresenter.FetchAnnouncementAuctionList("page", String.valueOf(page), "rows", String.valueOf(rows));
        }else {
            mPresenter.FetchAuctionListByName("categoryId", String.valueOf(id), "status", String.valueOf(3),
                    "page", String.valueOf(page), "rows", String.valueOf(rows));
        }
    }

    /**
     * 跳转详情页面
     *
     * @param item
     */
    @Override
    public void OnGoodsItemListener(AnnouncementAuctionListBean.DataBean.RowsBean item) {
        Intent i = new Intent(getActivity(), GoodsDetailActivity.class);
        i.putExtra("postion", item.getId());
        ClassifyActivity.classifyActivity.startActivityIn(i, getActivity());
    }
}
