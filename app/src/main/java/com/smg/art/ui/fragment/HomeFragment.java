package com.smg.art.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.smg.art.R;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.bean.Apk_UpdateBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.fragment.HomeContract;
import com.smg.art.presenter.impl.fragment.HomePresenter;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.bgabanner.BGABanner;


public class HomeFragment extends BaseFragment implements HomeContract.View, BGABanner.Delegate {

    @Inject
    HomePresenter mPresenter;
    @BindView(R.id.banner)
    BGABanner numBanner;

    @Override
    protected void initView(Bundle bundle) {


        numBanner.setDelegate(this);
        numBanner.setAdapter((banner, itemView, model, position) -> Glide.with(getActivity())
                .load(model)
//                        .placeholder(R.mipmap.holder)
                .error(R.mipmap.ic_launcher)
                .dontAnimate()
                .centerCrop()
                .into((ImageView) itemView));

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
    public int getLayoutResId() {
        return R.layout.fragment_home;
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

}
