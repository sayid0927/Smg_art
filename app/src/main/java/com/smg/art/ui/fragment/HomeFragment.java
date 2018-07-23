package com.smg.art.ui.fragment;

import android.os.Bundle;

import com.smg.art.R;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.bean.Apk_UpdateBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.fragment.HomeContract;
import com.smg.art.presenter.impl.fragment.HomePresenter;

import javax.inject.Inject;





public class HomeFragment extends BaseFragment implements HomeContract.View {

    @Inject
    HomePresenter mPresenter;

    @Override
    protected void initView(Bundle bundle) {

    }

    @Override
    public void loadData() {
        setState(Constant.STATE_SUCCESS);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
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

    @Override
    public void ApkUpdateS(Apk_UpdateBean.DataBean dataBean) {

    }
}
