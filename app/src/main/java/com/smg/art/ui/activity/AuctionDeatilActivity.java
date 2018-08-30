package com.smg.art.ui.activity;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RelativeLayout;

import com.blankj.utilcode.utils.ToastUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import com.smg.art.R;
import com.smg.art.bean.AuctionDetailBean;
import com.smg.art.base.BaseActivity;
import com.smg.art.base.BaseFragmentPageAdapter;
import com.smg.art.bean.AuctionGoodsBean;
import com.smg.art.bean.TabEntity;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.AuctionDeatilContract;
import com.smg.art.presenter.impl.fragment.AuctionDeatilPresenter;
import com.smg.art.ui.fragment.AuctionCentreFragment;
import com.smg.art.ui.fragment.AuctionDetailIntroductionFragment;
import com.smg.art.view.NoScrollViewPager;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class AuctionDeatilActivity extends BaseActivity implements AuctionDeatilContract.View, OnTabSelectListener {


    @Inject
    AuctionDeatilPresenter mPresenter;

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;
    @BindView(R.id.vp)
    NoScrollViewPager vp;


    private ArrayList<String> mTitleList = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    public static AuctionDeatilActivity auctionDeatilActivity;
    int type;
    int id;
    private int depositStatus;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

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
        type = getIntent().getIntExtra("type", 0);
        id = getIntent().getIntExtra("id", 0);
        if (type == 1) {
            String bookJson = getIntent().getStringExtra("data");
            AuctionGoodsBean.DataBean.RowsBean data = new Gson().fromJson(bookJson, AuctionGoodsBean.DataBean.RowsBean.class);
            mFragments.add(AuctionDetailIntroductionFragment.getInstance(data));
            mFragments.add(AuctionCentreFragment.getInstance(data.getId()));
            mPresenter.FetchHomepageGetauctiondetail("id", String.valueOf(data.getId()));
        } else if (type == 2) {
            mFragments.add(AuctionDetailIntroductionFragment.getInstance(id));
            mFragments.add(AuctionCentreFragment.getInstance(id));
            mPresenter.FetchHomepageGetauctiondetail("id", String.valueOf(id));
        }

        mTitleList.add(getString(R.string.DetailIntroduction));
        mTitleList.add(getString(R.string.AuctionCentre));

        BaseFragmentPageAdapter myAdapter = new BaseFragmentPageAdapter(getSupportFragmentManager(), mFragments, mTitleList);
        vp.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        for (int i = 0; i < mTitleList.size(); i++) {
            mTabEntities.add(new TabEntity(mTitleList.get(i), 0, 0));
        }
        tabLayout.setTabData(mTabEntities);
        vp.setNoScroll(true);
        tabLayout.setOnTabSelectListener(this);

        auctionDeatilActivity = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        auctionDeatilActivity = null;
    }

    public void setCurrentItem(int postion) {
        vp.setCurrentItem(postion);
        tabLayout.setCurrentTab(postion);
    }

    public void setdepositStatus(int postion) {
        depositStatus = 1;
    }

    @Override
    public void showError(String message) {

    }

    @OnClick({R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
        }
    }

    @Override
    public void FetchHomepageGetauctiondetailSuccess(AuctionDetailBean auctionDetailBean) {
        depositStatus = auctionDetailBean.getData().getDepositStatus();
    }

    @Override
    public void onTabSelect(int position) {
        if (position == 1) {
            if (depositStatus == 0) {
                tabLayout.setCurrentTab(0);
                vp.setCurrentItem(0);
                ToastUtils.showLongToast("请先交保证金");
                return;
            } else {
                vp.setCurrentItem(position);
            }
        } else {
            vp.setCurrentItem(position);
        }
    }

    @Override
    public void onTabReselect(int position) {

    }
}
