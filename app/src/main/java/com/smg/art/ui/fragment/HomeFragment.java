package com.smg.art.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smg.art.R;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
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
import com.smg.art.view.MyLoadMoreView;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.bgabanner.BGABanner;


public class HomeFragment extends BaseFragment implements HomeContract.View, BGABanner.Delegate, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    HomePresenter mPresenter;
    @BindView(R.id.banner)
    BGABanner numBanner;
    @BindView(R.id.rv_goods)
    RecyclerView rvGoods;

    @BindView(R.id.rl_search)
    AutoRelativeLayout rlSearch;
    @BindView(R.id.ll_book_draw)
    AutoLinearLayout llBookDraw;
    @BindView(R.id.ll_oil_draw)
    AutoLinearLayout llOilDraw;
    @BindView(R.id.ll_bird_draw)
    AutoLinearLayout llBirdDraw;
    @BindView(R.id.ll_hill_draw)
    AutoLinearLayout llHillDraw;
    @BindView(R.id.ll_people_draw)
    AutoLinearLayout llPeopleDraw;
    @BindView(R.id.ll_money_draw)
    AutoLinearLayout llMoneyDraw;
    @BindView(R.id.ll_jade_draw)
    AutoLinearLayout llJadeDraw;
    @BindView(R.id.ll_fine_draw)
    AutoLinearLayout llFineDraw;
    @BindView(R.id.ll_furniture_draw)
    AutoLinearLayout llFurnitureDraw;
    @BindView(R.id.ll_more_draw)
    AutoLinearLayout llMoreDraw;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.et_SearchContent)
    EditText etSearchContent;
    @BindView(R.id.ivToolbarNavigation)
    ImageView ivToolbarNavigation;
    @BindView(R.id.srl_android)
    SwipeRefreshLayout srlAndroid;



    private GoodsListApadter mAdapter;
    private GoodsBean goodsBean;
    private List<GoodsBean> goodsBeans;
    private Intent i;


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
        mAdapter.setOnLoadMoreListener(this, rvGoods);
        mAdapter.setLoadMoreView(new MyLoadMoreView());
        rvGoods.setLayoutManager(new GridLayoutManager(getSupportActivity(), 2));
        rvGoods.setAdapter(mAdapter);
        srlAndroid.setOnRefreshListener(this);
        mAdapter.OnGoodsItemListener(new GoodsListApadter.OnGoodsItemListener() {
            @Override
            public void OnGoodsItemListener(GoodsBean item) {

                Intent i = new Intent(getActivity(), GoodsDetailActivity.class);
                MainActivity.mainActivity.startActivityIn(i, getActivity());

            }
        });


        numBanner.setDelegate(this);
        numBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {

                Glide.with(getActivity())
                        .load(model)
//                        .placeholder(R.mipmap.holder)
                        .error(R.mipmap.ic_launcher)
                        .dontAnimate()
                        .centerCrop()
                        .into(itemView);
            }
        });
        numBanner.setData(Arrays.asList(
                "http://a.hiphotos.baidu.com/image/h%3D300/sign=4a51c9cd7e8b4710d12ffbccf3ccc3b2/b64543a98226cffceee78e5eb5014a90f703ea09.jpg",
                "http://a.hiphotos.baidu.com/image/h%3D300/sign=4a51c9cd7e8b4710d12ffbccf3ccc3b2/b64543a98226cffceee78e5eb5014a90f703ea09.jpg",
                "http://a.hiphotos.baidu.com/image/h%3D300/sign=4a51c9cd7e8b4710d12ffbccf3ccc3b2/b64543a98226cffceee78e5eb5014a90f703ea09.jpg"),
                Arrays.asList("", "", ""));
    }


    @Override
    public void loadData() {
        setState(Constant.STATE_SUCCESS);
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

    @Override
    public void ApkUpdateS(Apk_UpdateBean.DataBean dataBean) {

    }

    @Override
    public void onBannerItemClick(BGABanner banner, View itemView, Object model, int position) {
        Toast.makeText(banner.getContext(), "点击了第" + (position + 1) + "页", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMoreRequested() {
        //加载更多
    }


    @Override
    public void onRefresh() {
        // 下拉刷新
    }

    @OnClick({R.id.rl_search, R.id.ll_book_draw, R.id.ll_oil_draw, R.id.ll_bird_draw, R.id.ll_hill_draw, R.id.ll_people_draw, R.id.ll_money_draw, R.id.ll_jade_draw, R.id.ll_fine_draw, R.id.ll_furniture_draw, R.id.ll_more_draw})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_search:
                MainActivity.mainActivity.startActivityIn(new Intent(getActivity(), SearchActivity.class), getActivity());
                break;
            case R.id.ll_book_draw:
                i = new Intent(getActivity(), ClassifyActivity.class);
                i.putExtra("postion", 0);
                MainActivity.mainActivity.startActivityIn(i, getActivity());
                break;
            case R.id.ll_oil_draw:
                i = new Intent(getActivity(), ClassifyActivity.class);
                i.putExtra("postion", 1);
                MainActivity.mainActivity.startActivityIn(i, getActivity());
                break;
            case R.id.ll_bird_draw:
                i = new Intent(getActivity(), ClassifyActivity.class);
                i.putExtra("postion", 2);
                MainActivity.mainActivity.startActivityIn(i, getActivity());
                break;
            case R.id.ll_hill_draw:
                i = new Intent(getActivity(), ClassifyActivity.class);
                i.putExtra("postion", 3);
                MainActivity.mainActivity.startActivityIn(i, getActivity());
                break;
            case R.id.ll_people_draw:
                i = new Intent(getActivity(), ClassifyActivity.class);
                i.putExtra("postion", 4);
                MainActivity.mainActivity.startActivityIn(i, getActivity());
                break;
            case R.id.ll_money_draw:
                i = new Intent(getActivity(), ClassifyActivity.class);
                i.putExtra("postion", 5);
                MainActivity.mainActivity.startActivityIn(i, getActivity());
                break;
            case R.id.ll_jade_draw:
                i = new Intent(getActivity(), ClassifyActivity.class);
                i.putExtra("postion", 6);
                MainActivity.mainActivity.startActivityIn(i, getActivity());
                break;
            case R.id.ll_fine_draw:
                i = new Intent(getActivity(), ClassifyActivity.class);
                i.putExtra("postion", 7);
                MainActivity.mainActivity.startActivityIn(i, getActivity());
                break;
            case R.id.ll_furniture_draw:
                i = new Intent(getActivity(), ClassifyActivity.class);
                i.putExtra("postion", 8);
                MainActivity.mainActivity.startActivityIn(i, getActivity());
                break;
            case R.id.ll_more_draw:
                i = new Intent(getActivity(), ClassifyActivity.class);
                i.putExtra("postion", 9);
                MainActivity.mainActivity.startActivityIn(i, getActivity());
                break;
        }
    }

}
