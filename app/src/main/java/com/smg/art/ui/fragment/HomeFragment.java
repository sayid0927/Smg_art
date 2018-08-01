package com.smg.art.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smg.art.R;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.base.HomePageImgBean;
import com.smg.art.bean.Apk_UpdateBean;
import com.smg.art.bean.GoodsBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.fragment.HomeContract;
import com.smg.art.presenter.impl.fragment.HomePresenter;
import com.smg.art.ui.activity.ClassifyActivity;
import com.smg.art.ui.activity.GoodsDetailActivity;
import com.smg.art.ui.activity.MainActivity;
import com.smg.art.ui.activity.SearchActivity;
import com.smg.art.ui.adapter.GoodsListApadter;
import com.smg.art.ui.adapter.HomeUnderListApadter;
import com.smg.art.utils.GlideUtils;
import com.smg.art.view.MyLoadMoreView;
import com.smg.art.view.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.bgabanner.BGABanner;


public class HomeFragment extends BaseFragment implements HomeContract.View, BGABanner.Delegate, OnLoadmoreListener, OnRefreshListener, GoodsListApadter.OnGoodsItemListener {

    @Inject
    HomePresenter mPresenter;
    @BindView(R.id.banner)
    BGABanner numBanner;
    @BindView(R.id.rv_goods)
    RecyclerView rvGoods;

    @BindView(R.id.ll_book_draw)
    LinearLayout llBookDraw;
    @BindView(R.id.ll_oil_draw)
    LinearLayout llOilDraw;
    @BindView(R.id.ll_bird_draw)
    LinearLayout llBirdDraw;
    @BindView(R.id.ll_hill_draw)
    LinearLayout llHillDraw;
    @BindView(R.id.ll_people_draw)
    LinearLayout llPeopleDraw;
    @BindView(R.id.ll_money_draw)
    LinearLayout llMoneyDraw;
    @BindView(R.id.ll_jade_draw)
    LinearLayout llJadeDraw;
    @BindView(R.id.ll_fine_draw)
    LinearLayout llFineDraw;
    @BindView(R.id.ll_furniture_draw)
    LinearLayout llFurnitureDraw;
    @BindView(R.id.ll_more_draw)
    LinearLayout llMoreDraw;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.et_SearchContent)
    EditText etSearchContent;
    @BindView(R.id.ivToolbarNavigation)
    ImageView ivToolbarNavigation;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.iv_book_draw)
    RoundImageView ivBookDraw;
    @BindView(R.id.tv_book_draw)
    TextView tvBookDraw;
    @BindView(R.id.iv_oil_draw)
    RoundImageView ivOilDraw;
    @BindView(R.id.tv_oil_draw)
    TextView tvOilDraw;
    @BindView(R.id.iv_bird_draw)
    RoundImageView ivBirdDraw;
    @BindView(R.id.tv_bird_draw)
    TextView tvBirdDraw;
    @BindView(R.id.iv_hill_draw)
    RoundImageView ivHillDraw;
    @BindView(R.id.tv_hill_draw)
    TextView tvHillDraw;
    @BindView(R.id.iv_people_draw)
    RoundImageView ivPeopleDraw;
    @BindView(R.id.tv_people_draw)
    TextView tvPeopleDraw;
    @BindView(R.id.iv_money_draw)
    RoundImageView ivMoneyDraw;
    @BindView(R.id.tv_money_draw)
    TextView tvMoneyDraw;
    @BindView(R.id.iv_jade_draw)
    RoundImageView ivJadeDraw;
    @BindView(R.id.tv_jade_draw)
    TextView tvJadeDraw;
    @BindView(R.id.iv_fine_draw)
    RoundImageView ivFineDraw;
    @BindView(R.id.tv_fine_draw)
    TextView tvFineDraw;
    @BindView(R.id.iv_furniture_draw)
    RoundImageView ivFurnitureDraw;
    @BindView(R.id.tv_furniture_draw)
    TextView tvFurnitureDraw;
    @BindView(R.id.iv_more_draw)
    RoundImageView ivMoreDraw;
    @BindView(R.id.tv_more_draw)
    TextView tvMoreDraw;
    @BindView(R.id.rv_under)
    RecyclerView rvUnder;


    private GoodsListApadter mAdapter;
    private HomeUnderListApadter underListApadter;
    private GoodsBean goodsBean;
    private List<GoodsBean> goodsBeans;
    private Intent i;
    private List<HomePageImgBean.DataBean.CategoryListBean> categoryListBeans = new ArrayList<>();
    private List<HomePageImgBean.DataBean.UpperListBean> upperListBeans = new ArrayList<>();
    private List<HomePageImgBean.DataBean.UnderListBean> underListBeans = new ArrayList<>();
    private ArrayList<String> mTitleList = new ArrayList<>();

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(Bundle bundle) {

        goodsBeans = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            GoodsBean goodsBean = new GoodsBean();
            goodsBeans.add(goodsBean);
        }

        tvSearch.setVisibility(View.VISIBLE);
        etSearchContent.setVisibility(View.GONE);
        ivToolbarNavigation.setVisibility(View.GONE);

        mAdapter = new GoodsListApadter(goodsBeans, getSupportActivity());
        underListApadter = new HomeUnderListApadter(underListBeans,getSupportActivity());
        rvGoods.setLayoutManager(new GridLayoutManager(getSupportActivity(), 2));
        rvUnder.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvGoods.setAdapter(mAdapter);
        rvUnder.setAdapter(underListApadter);

        srl.setOnLoadmoreListener(this);
        srl.setOnRefreshListener(this);
        mAdapter.OnGoodsItemListener(this);

        mPresenter.FetchHomePageImg();

    }


    @Override
    public void loadData() {
        setState(Constant.STATE_SUCCESS);
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

    }

    @Override
    public void ApkUpdateS(Apk_UpdateBean.DataBean dataBean) {

    }

    /**
     * 首页广告图片列表
     *
     * @param homePageImgBean
     */
    @Override
    public void FetchHomePageImgSuccess(HomePageImgBean homePageImgBean) {

        if(srl.isRefreshing()){
            srl.finishRefresh();
        }
        this.categoryListBeans = homePageImgBean.getData().getCategoryList();
        this.upperListBeans = homePageImgBean.getData().getUpperList();
        this.underListBeans = homePageImgBean.getData().getUnderList();
        initUnder(underListBeans);
        initBanner(upperListBeans);
        fillView(categoryListBeans);
    }



    @Override
    public void onBannerItemClick(BGABanner banner, View itemView, Object model, int position) {
        Toast.makeText(banner.getContext(), "点击了第" + (position + 1) + "页", Toast.LENGTH_SHORT).show();
    }


    @OnClick({R.id.rl_search, R.id.ll_book_draw, R.id.ll_oil_draw, R.id.ll_bird_draw, R.id.ll_hill_draw, R.id.ll_people_draw, R.id.ll_money_draw, R.id.ll_jade_draw, R.id.ll_fine_draw, R.id.ll_furniture_draw, R.id.ll_more_draw})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_search:
                MainActivity.mainActivity.startActivityIn(new Intent(getActivity(), SearchActivity.class), getActivity());
                break;
            case R.id.ll_book_draw:
                if (mTitleList.size() >= 0) {
                    i = new Intent(getActivity(), ClassifyActivity.class);
                    i.putStringArrayListExtra("TitleList", mTitleList);
                    i.putExtra("postion", 0);
                    MainActivity.mainActivity.startActivityIn(i, getActivity());
                }
                break;
            case R.id.ll_oil_draw:
                if (mTitleList.size() >= 1) {
                    i = new Intent(getActivity(), ClassifyActivity.class);
                    i.putStringArrayListExtra("TitleList", mTitleList);
                    i.putExtra("postion", 1);
                    MainActivity.mainActivity.startActivityIn(i, getActivity());
                }
                break;
            case R.id.ll_bird_draw:
                if (mTitleList.size() >= 2) {
                    i = new Intent(getActivity(), ClassifyActivity.class);
                    i.putStringArrayListExtra("TitleList", mTitleList);
                    i.putExtra("postion", 2);
                    MainActivity.mainActivity.startActivityIn(i, getActivity());
                }
                break;
            case R.id.ll_hill_draw:
                if (mTitleList.size() >= 3) {
                    i = new Intent(getActivity(), ClassifyActivity.class);
                    i.putStringArrayListExtra("TitleList", mTitleList);
                    i.putExtra("postion", 3);
                    MainActivity.mainActivity.startActivityIn(i, getActivity());
                }
                break;
            case R.id.ll_people_draw:
                if (mTitleList.size() >= 4) {
                    i = new Intent(getActivity(), ClassifyActivity.class);
                    i.putStringArrayListExtra("TitleList", mTitleList);
                    i.putExtra("postion", 4);
                    MainActivity.mainActivity.startActivityIn(i, getActivity());
                }
                break;
            case R.id.ll_money_draw:
                if (mTitleList.size() >= 5) {
                    i = new Intent(getActivity(), ClassifyActivity.class);
                    i.putStringArrayListExtra("TitleList", mTitleList);
                    i.putExtra("postion", 5);
                    MainActivity.mainActivity.startActivityIn(i, getActivity());
                }
                break;
            case R.id.ll_jade_draw:
                if (mTitleList.size() >= 6) {
                    i = new Intent(getActivity(), ClassifyActivity.class);
                    i.putStringArrayListExtra("TitleList", mTitleList);
                    i.putExtra("postion", 6);
                    MainActivity.mainActivity.startActivityIn(i, getActivity());
                }
                break;
            case R.id.ll_fine_draw:
                if (mTitleList.size() >= 7) {
                    i = new Intent(getActivity(), ClassifyActivity.class);
                    i.putStringArrayListExtra("TitleList", mTitleList);
                    i.putExtra("postion", 7);
                    MainActivity.mainActivity.startActivityIn(i, getActivity());
                }
                break;
            case R.id.ll_furniture_draw:
                if (mTitleList.size() >= 8) {
                    i = new Intent(getActivity(), ClassifyActivity.class);
                    i.putStringArrayListExtra("TitleList", mTitleList);
                    i.putExtra("postion", 8);
                    MainActivity.mainActivity.startActivityIn(i, getActivity());
                }
                break;
            case R.id.ll_more_draw:
                if (mTitleList.size() >= 9) {
                    i = new Intent(getActivity(), ClassifyActivity.class);
                    i.putStringArrayListExtra("TitleList", mTitleList);
                    i.putExtra("postion", 9);
                    MainActivity.mainActivity.startActivityIn(i, getActivity());
                }
                break;
        }
    }


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        // 加载更多
        Logger.t("TAG").e("CCCCCCC");
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        //下拉刷新
        mPresenter.FetchHomePageImg();
    }

    /**
     * 跳转详情页面
     *
     * @param item
     * @param postion
     */
    @Override
    public void OnGoodsItemListener(GoodsBean item, int postion) {
        Intent i = new Intent(getActivity(), GoodsDetailActivity.class);
        i.putExtra("postion", postion);
        MainActivity.mainActivity.startActivityIn(i, getActivity());
    }

    /**
     * 固定广告位
     * @param underListBeans
     */
    private void initUnder(List<HomePageImgBean.DataBean.UnderListBean> underListBeans) {
        underListApadter.setNewData(underListBeans);
    }


    /**
     *滚动 广告图片
     *
     * @param upperListBeans
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
                GlideUtils.loadFitCenter(getActivity(), model, itemView);
            }
        });
        numBanner.setData(imgUrls, null);
    }


    /***
     * 填充icon
     * @param categoryListBeans
     */
    private void fillView(List<HomePageImgBean.DataBean.CategoryListBean> categoryListBeans) {

        if (categoryListBeans.size() >= 1 && categoryListBeans.get(0) != null) {
            HomePageImgBean.DataBean.CategoryListBean categoryListBean = categoryListBeans.get(0);
            GlideUtils.load(getActivity(), Constant.BaseImgUrl + categoryListBean.getIco(), ivBookDraw,R.drawable.draw_def);
            tvBookDraw.setText(categoryListBean.getCategoryName());
            mTitleList.add(categoryListBean.getCategoryName());
        } else {
            llBookDraw.setVisibility(View.INVISIBLE);
        }

        if (categoryListBeans.size() >= 2 && categoryListBeans.get(1) != null) {
            HomePageImgBean.DataBean.CategoryListBean categoryListBean = categoryListBeans.get(1);
            GlideUtils.load(getActivity(), Constant.BaseImgUrl + categoryListBean.getIco(), ivOilDraw,R.drawable.draw_def);
            tvOilDraw.setText(categoryListBean.getCategoryName());
            mTitleList.add(categoryListBean.getCategoryName());
        } else {
            llOilDraw.setVisibility(View.INVISIBLE);
        }

        if (categoryListBeans.size() >= 3 && categoryListBeans.get(3) != null) {
            HomePageImgBean.DataBean.CategoryListBean categoryListBean = categoryListBeans.get(2);
            GlideUtils.load(getActivity(), Constant.BaseImgUrl + categoryListBean.getIco(), ivBirdDraw,R.drawable.draw_def);
            tvBirdDraw.setText(categoryListBean.getCategoryName());
            mTitleList.add(categoryListBean.getCategoryName());
        } else {
            llBirdDraw.setVisibility(View.INVISIBLE);
        }

        if (categoryListBeans.size() >= 4 && categoryListBeans.get(3) != null) {
            HomePageImgBean.DataBean.CategoryListBean categoryListBean = categoryListBeans.get(3);
            GlideUtils.load(getActivity(), Constant.BaseImgUrl + categoryListBean.getIco(), ivHillDraw,R.drawable.draw_def);
            tvHillDraw.setText(categoryListBean.getCategoryName());
            mTitleList.add(categoryListBean.getCategoryName());
        } else {
            llHillDraw.setVisibility(View.INVISIBLE);
        }

        if (categoryListBeans.size() >= 5 && categoryListBeans.get(4) != null) {
            HomePageImgBean.DataBean.CategoryListBean categoryListBean = categoryListBeans.get(4);
            GlideUtils.load(getActivity(), Constant.BaseImgUrl + categoryListBean.getIco(), ivPeopleDraw,R.drawable.draw_def);
            tvPeopleDraw.setText(categoryListBean.getCategoryName());
            mTitleList.add(categoryListBean.getCategoryName());
        } else {
            llPeopleDraw.setVisibility(View.INVISIBLE);
        }

        if (categoryListBeans.size() >= 6 && categoryListBeans.get(5) != null) {
            HomePageImgBean.DataBean.CategoryListBean categoryListBean = categoryListBeans.get(5);
            GlideUtils.load(getActivity(), Constant.BaseImgUrl + categoryListBean.getIco(), ivMoneyDraw,R.drawable.draw_def);
            tvMoneyDraw.setText(categoryListBean.getCategoryName());
            mTitleList.add(categoryListBean.getCategoryName());
        } else {
            llMoneyDraw.setVisibility(View.INVISIBLE);
        }

        if (categoryListBeans.size() >= 7 && categoryListBeans.get(6) != null) {
            HomePageImgBean.DataBean.CategoryListBean categoryListBean = categoryListBeans.get(6);
            GlideUtils.load(getActivity(), Constant.BaseImgUrl + categoryListBean.getIco(), ivJadeDraw,R.drawable.draw_def);
            tvJadeDraw.setText(categoryListBean.getCategoryName());
            mTitleList.add(categoryListBean.getCategoryName());
        } else {
            llJadeDraw.setVisibility(View.INVISIBLE);
        }

        if (categoryListBeans.size() >= 8 && categoryListBeans.get(7) != null) {
            HomePageImgBean.DataBean.CategoryListBean categoryListBean = categoryListBeans.get(7);
            GlideUtils.load(getActivity(), Constant.BaseImgUrl + categoryListBean.getIco(), ivFineDraw,R.drawable.draw_def);
            tvFineDraw.setText(categoryListBean.getCategoryName());
            mTitleList.add(categoryListBean.getCategoryName());
        } else {
            llFineDraw.setVisibility(View.INVISIBLE);
        }

        if (categoryListBeans.size() >= 9 && categoryListBeans.get(8) != null) {
            HomePageImgBean.DataBean.CategoryListBean categoryListBean = categoryListBeans.get(8);
            GlideUtils.load(getActivity(), Constant.BaseImgUrl + categoryListBean.getIco(), ivFurnitureDraw,R.drawable.draw_def);
            tvFurnitureDraw.setText(categoryListBean.getCategoryName());
            mTitleList.add(categoryListBean.getCategoryName());
        } else {
            llFurnitureDraw.setVisibility(View.INVISIBLE);
        }

        if (categoryListBeans.size() >= 10 && categoryListBeans.get(9) != null) {
            HomePageImgBean.DataBean.CategoryListBean categoryListBean = categoryListBeans.get(9);
            GlideUtils.load(getActivity(), Constant.BaseImgUrl + categoryListBean.getIco(), ivMoreDraw,R.drawable.draw_def);
            tvMoreDraw.setText(categoryListBean.getCategoryName());
            mTitleList.add(categoryListBean.getCategoryName());
        } else {
            llMoreDraw.setVisibility(View.INVISIBLE);
        }
    }
}
