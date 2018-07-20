package com.smg.art.ui.activity;

import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.MainContract;
import com.smg.art.presenter.impl.MainActivityPresenter;

import javax.inject.Inject;


public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    MainActivityPresenter mPresenter;


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void attachView() {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void initView() {
        mPresenter.Apk_Update();
    }

    @Override
    public void showError(String message) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
