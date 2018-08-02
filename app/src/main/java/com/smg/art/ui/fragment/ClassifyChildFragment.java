package com.smg.art.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.smg.art.R;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.bean.GoodsBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.fragment.ClassifyChildContract;
import com.smg.art.presenter.impl.fragment.ClassifyChildPresenter;
import com.smg.art.presenter.impl.fragment.HomePresenter;
import com.smg.art.ui.adapter.GoodsListApadter;
import com.smg.art.view.MyLoadMoreView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Lenovo on 2018/7/24.
 */

public class ClassifyChildFragment extends BaseFragment implements ClassifyChildContract.View {


    @Inject
    ClassifyChildPresenter mPresenter;


    @BindView(R.id.rv_goods)
    RecyclerView rvGoods;


    private GoodsListApadter mAdapter;
    private GoodsBean goodsBean;
    private List<GoodsBean> goodsBeans;


    public static ClassifyChildFragment getInstance() {
        ClassifyChildFragment sf = new ClassifyChildFragment();
        return sf;
    }


    @Override
    protected void initView(Bundle bundle) {
        super.initView(bundle);
        goodsBeans = new ArrayList<>();
        for(int i=0;i<30;i++){
            GoodsBean goodsBean = new GoodsBean();
            goodsBeans.add(goodsBean);
        }

//        mAdapter = new GoodsListApadter(goodsBeans, getSupportActivity());
////        mAdapter.setOnLoadMoreListener(get, rvGoods);
//        mAdapter.setLoadMoreView(new MyLoadMoreView());
//        rvGoods.setLayoutManager(new GridLayoutManager(getSupportActivity(),2));
//        rvGoods.setAdapter(mAdapter);

    }

    @Override
    public void loadData() {
        setState(Constant.STATE_SUCCESS);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_classifychild;
    }

    @Override
    public void attachView() {
        mPresenter.attachView(this,getActivity());
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public void showError(String message) {

    }
}
