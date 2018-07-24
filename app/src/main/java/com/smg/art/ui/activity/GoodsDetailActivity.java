package com.smg.art.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jaeger.library.StatusBarUtil;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.GoodsDetailContract;
import com.smg.art.presenter.impl.activity.GoodsDetailActivityPresenter;
import com.smg.art.presenter.impl.activity.MainActivityPresenter;

import javax.inject.Inject;

public class GoodsDetailActivity extends BaseActivity implements GoodsDetailContract.View {

    @Inject
    GoodsDetailActivityPresenter mPresenter;


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_detail;
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

    }

    @Override
    public void showError(String message) {

    }
}
