package com.smg.art.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.base.BaseFragmentPageAdapter;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.AuctionDeatilContract;
import com.smg.art.presenter.impl.fragment.AuctionDeatilPresenter;
import com.smg.art.ui.fragment.AuctionCentreFragment;
import com.smg.art.ui.fragment.AuctionDetailIntroductionFragment;
import com.smg.art.ui.fragment.ContactsFragment;
import com.smg.art.ui.fragment.RecentMessageFragment;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuctionDeatilActivity extends BaseActivity implements AuctionDeatilContract.View {


    @Inject
    AuctionDeatilPresenter mPresenter;

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.vp)
    ViewPager vp;


    private ArrayList<String> mTitleList = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_auction_deatil;
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

        mTitleList.add(getString(R.string.DetailIntroduction));
        mTitleList.add(getString(R.string.AuctionCentre));

        mFragments.add(new AuctionDetailIntroductionFragment());
        mFragments.add(new AuctionCentreFragment());

        BaseFragmentPageAdapter myAdapter = new BaseFragmentPageAdapter(getSupportFragmentManager(), mFragments, mTitleList);
        vp.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        tabLayout.setViewPager(vp);

    }

    @Override
    public void showError(String message) {

    }

    @OnClick({ R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;

        }
    }
}
