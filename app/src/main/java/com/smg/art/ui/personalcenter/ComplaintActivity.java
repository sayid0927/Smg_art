package com.smg.art.ui.personalcenter;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.base.BaseFragmentPageAdapter;
import com.smg.art.bean.ComplaintBean;
import com.smg.art.bean.TabEntity;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.ComplaintActivityContract;
import com.smg.art.presenter.impl.activity.ComplaintActivityPresenter;
import com.smg.art.ui.personalcenter.fragemnt.ComplaintApplyFragment;
import com.smg.art.ui.personalcenter.fragemnt.ComplaintListFragment;
import com.smg.art.ui.personalcenter.fragemnt.ComplaintRecordFragment;
import com.smg.art.view.NoScrollViewPager;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ComplaintActivity extends BaseActivity implements ComplaintActivityContract.View, OnTabSelectListener {

    @Inject
    ComplaintActivityPresenter mPresenter;


    @BindView(R.id.actionbar_title)
    TextView actionbarTitle;
    @BindView(R.id.rl_back)
    AutoRelativeLayout rlBack;
    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;
    @BindView(R.id.vp)
    NoScrollViewPager vp;


    private ArrayList<String> mTitleList = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_complaint;
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
        actionbarTitle.setText("售后投诉");
        mTitleList.add("投诉申请");
        mTitleList.add("处理中");
        mTitleList.add("申请记录");

        mFragments.add(new ComplaintApplyFragment());
        mFragments.add(new ComplaintListFragment());
        mFragments.add(new ComplaintRecordFragment());

        BaseFragmentPageAdapter myAdapter = new BaseFragmentPageAdapter(getSupportFragmentManager(), mFragments, mTitleList);
        vp.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        for (int i = 0; i < mTitleList.size(); i++) {
            mTabEntities.add(new TabEntity(mTitleList.get(i), 0,0));
        }
        tabLayout.setTabData(mTabEntities);
        tabLayout.setOnTabSelectListener(this);
        vp.setNoScroll(true);

    }

    @Override
    public void FetchComplaintSuccess(ComplaintBean complaintBean) {

    }

    @Override
    public void showError(String message) {

    }

    @OnClick({R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:  // 返回
                finish();
                break;
        }
    }

    @Override
    public void onTabSelect(int position) {
        vp.setCurrentItem(position);
    }

    @Override
    public void onTabReselect(int position) {

    }
}
