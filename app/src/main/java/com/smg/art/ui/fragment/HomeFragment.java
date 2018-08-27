package com.smg.art.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smg.art.R;
import com.smg.art.base.AnnouncementAuctionListBean;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.bean.HomePageImgBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.fragment.HomeContract;
import com.smg.art.presenter.impl.fragment.HomePresenter;
import com.smg.art.ui.activity.ClassifyActivity;
import com.smg.art.ui.activity.GoodsDetailActivity;
import com.smg.art.ui.activity.MainActivity;
import com.smg.art.ui.activity.SearchActivity;
import com.smg.art.ui.adapter.GoodsListApadter;
import com.smg.art.ui.adapter.HomeIconApadter;
import com.smg.art.ui.adapter.HomeUnderListApadter;
import com.smg.art.utils.GlideCommonUtils;
import com.smg.art.view.NumberDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;


public class HomeFragment extends BaseFragment implements HomeContract.View, BGABanner.Delegate, OnLoadmoreListener, OnRefreshListener, GoodsListApadter.OnGoodsItemListener, HomeIconApadter.OnHomeIconItemListener, HomeUnderListApadter.OnUnderItemListener {

    @Inject
    HomePresenter mPresenter;
    @BindView(R.id.banner)
    BGABanner numBanner;
    @BindView(R.id.rv_goods)
    RecyclerView rvGoods;

    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.et_SearchContent)
    EditText etSearchContent;
    @BindView(R.id.ivToolbarNavigation)
    ImageView ivToolbarNavigation;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.rv_under)
    RecyclerView rvUnder;
    @BindView(R.id.rv_icon)
    RecyclerView rvIcon;


    private GoodsListApadter mAdapter;
    private HomeUnderListApadter underListApadter;
    private HomeIconApadter homeIconApadter;
    private List<AnnouncementAuctionListBean.DataBean.RowsBean> rowsBeans = new ArrayList<>();
    private List<HomePageImgBean.DataBean.CategoryListBean> categoryListBeans = new ArrayList<>();
    private List<HomePageImgBean.DataBean.UpperListBean> upperListBeans = new ArrayList<>();
    private List<HomePageImgBean.DataBean.UnderListBean> underListBeans = new ArrayList<>();


    private int page = 1;
    private int rows = 10;


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(Bundle bundle) {

        tvSearch.setVisibility(View.VISIBLE);
        etSearchContent.setVisibility(View.GONE);
        ivToolbarNavigation.setVisibility(View.GONE);

        homeIconApadter = new HomeIconApadter(categoryListBeans, getActivity());
        mAdapter = new GoodsListApadter(rowsBeans, getSupportActivity());
        underListApadter = new HomeUnderListApadter(underListBeans, getSupportActivity());
        underListApadter.OnUnderItemListener(this);


        rvGoods.setLayoutManager(new GridLayoutManager(getSupportActivity(), 2));
        rvUnder.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvIcon.setLayoutManager(new GridLayoutManager(getSupportActivity(), 5));

        rvGoods.setAdapter(mAdapter);
        rvUnder.setAdapter(underListApadter);
        rvIcon.setAdapter(homeIconApadter);

        srl.setOnLoadmoreListener(this);
        srl.setOnRefreshListener(this);
        mAdapter.OnGoodsItemListener(this);
        homeIconApadter.OnHomeIconItemListener(this);

    }


    @Override
    public void loadData() {
        setState(Constant.STATE_SUCCESS);
        mPresenter.FetchHomePageImg();
        mPresenter.FetchAnnouncementAuctionList("page", String.valueOf(page), "rows", String.valueOf(rows));
    }


    @Override
    public void attachView() {
        mPresenter.attachView(this, getSupportActivity());
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public void showError(String message) {
        setState(Constant.STATE_SUCCESS);
    }

    /**
     * 首页广告图片列表
     */
    @Override
    public void FetchHomePageImgSuccess(HomePageImgBean homePageImgBean) {
        setState(Constant.STATE_SUCCESS);
        if (srl.isRefreshing()) {
            srl.finishRefresh();
        }
        if (categoryListBeans.size() != 0)categoryListBeans.clear();
        if(upperListBeans.size()!=0) upperListBeans.clear();
        if(underListBeans.size()!=0) underListBeans.clear();
        this.categoryListBeans = homePageImgBean.getData().getCategoryList();
        this.upperListBeans = homePageImgBean.getData().getUpperList();
        this.underListBeans = homePageImgBean.getData().getUnderList();
        initUnder(underListBeans);
        initBanner(upperListBeans);
        fillView(categoryListBeans);

    }

    /**
     * 首页公告期分页查询
     */
    @Override
    public void FetchAnnouncementAuctionListSuccess(AnnouncementAuctionListBean announcementAuctionListBean) {
        if (srl.isLoading()) {
            mAdapter.addData(announcementAuctionListBean.getData().getRows());
            srl.finishLoadmore();
        } else {
            if (rowsBeans.size() != 0) rowsBeans.clear();
            if (srl.isRefreshing()) srl.finishRefresh();
            rowsBeans = announcementAuctionListBean.getData().getRows();
            mAdapter.setNewData(rowsBeans);
        }
    }

    /**
     * 活动广告跳转详情页
     */
    @Override
    public void onBannerItemClick(BGABanner banner, View itemView, Object model, int position) {
//        Intent i = new Intent(getActivity(), GoodsDetailActivity.class);
//        i.putExtra("postion",this.upperListBeans.get(position).getId());
//        MainActivity.mainActivity.startActivityIn(i, getActivity());
    }


    @OnClick({R.id.rl_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_search:
                Intent i = new Intent(getActivity(), SearchActivity.class);
                i.putExtra("status", "3");
                MainActivity.mainActivity.startActivityIn(i, getActivity());
                break;
        }
    }


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        // 加载更多
        page++;
        mPresenter.FetchAnnouncementAuctionList("page", String.valueOf(page), "rows", String.valueOf(rows));
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        //下拉刷新
        mPresenter.FetchHomePageImg();
        page = 1;
        mPresenter.FetchAnnouncementAuctionList("page", String.valueOf(page), "rows", String.valueOf(rows));
    }

    /**
     * 跳转详情页面
     */
    @Override
    public void OnGoodsItemListener(AnnouncementAuctionListBean.DataBean.RowsBean item) {
        Intent i = new Intent(getActivity(), GoodsDetailActivity.class);
        i.putExtra("postion", item.getId());
        MainActivity.mainActivity.startActivityIn(i, getActivity());
    }

    /**
     * 固定广告位
     */
    private void initUnder(List<HomePageImgBean.DataBean.UnderListBean> underListBeans) {
        underListApadter.setNewData(underListBeans);
    }


    /**
     * 滚动 广告图片
     */
    private void initBanner(List<HomePageImgBean.DataBean.UpperListBean> upperListBeans) {
        List<String> imgUrls = new ArrayList<>();
        for (int i = 0; i < upperListBeans.size(); i++) {
            imgUrls.add(Constant.BaseImgUrl + upperListBeans.get(i).getImgPath());
        }
        numBanner.setDelegate(this);
        numBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
//                GlideUtils.loadFitCenter(getActivity(), model, itemView);
                GlideCommonUtils.showTSquarePic(getActivity(), model, itemView);
            }
        });
        numBanner.setData(imgUrls, null);
    }

    /***
     * 填充icon
     */
    private void fillView(List<HomePageImgBean.DataBean.CategoryListBean> categoryListBeans) {
        HomePageImgBean.DataBean.CategoryListBean  categoryListBean = new HomePageImgBean.DataBean.CategoryListBean();
        categoryListBean.setId(categoryListBeans.size()+1);
        categoryListBean.setCategoryName("全部");
        categoryListBeans.add(categoryListBean);
        homeIconApadter.setNewData(categoryListBeans);
    }

    /**
     * 跳转分类
     */
    @Override
    public void OnHomeIconItemListener(HomePageImgBean.DataBean.CategoryListBean item, int postion) {

        if (categoryListBeans.size() > 0) {
            Bundle bundle = new Bundle();
            Intent i = new Intent(getActivity(), ClassifyActivity.class);
            i.putExtra("postion", postion);
            bundle.putSerializable("item", (Serializable) categoryListBeans);
            i.putExtras(bundle);
            MainActivity.mainActivity.startActivityIn(i, getActivity());
        }
    }

    /**
     * 固定广告跳转详情页
     */
    @Override
    public void OnUnderItemListener(HomePageImgBean.DataBean.UnderListBean item) {
//        Intent i = new Intent(getActivity(), GoodsDetailActivity.class);
//        i.putExtra("postion",item.getId());
//        MainActivity.mainActivity.startActivityIn(i, getActivity());

    }
}
