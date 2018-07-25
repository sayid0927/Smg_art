package com.smg.art.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.base.BaseFragmentPageAdapter;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.ClassifyContract;
import com.smg.art.presenter.impl.activity.ClassifyActivityPresenter;
import com.smg.art.ui.fragment.ClassifyChildFragment;
import com.smg.art.utils.UIUtils;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClassifyActivity extends BaseActivity implements ClassifyContract.View {


    @Inject
    ClassifyActivityPresenter mPresenter;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.actionbar_title)
    TextView actionbarTitle;
    @BindView(R.id.rl_back)
    AutoRelativeLayout rlBack;

    private ArrayList<String> mTitleList = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_classify;
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
        actionbarTitle.setText(R.string.classify_title);

        setSwipeBackEnable(true);
        int postion = getIntent().getIntExtra("postion", 0);
        mTitleList.add(UIUtils.getString(R.string.bookDraw));
        mTitleList.add(UIUtils.getString(R.string.oilDraw));
        mTitleList.add(UIUtils.getString(R.string.birdDraw));
        mTitleList.add(UIUtils.getString(R.string.hilDraw));

        mTitleList.add(UIUtils.getString(R.string.peopleDraw));
        mTitleList.add(UIUtils.getString(R.string.moneyDraw));
        mTitleList.add(UIUtils.getString(R.string.jadeDraw));
        mTitleList.add(UIUtils.getString(R.string.fineDraw));

        mTitleList.add(UIUtils.getString(R.string.furnitureDraw));
        mTitleList.add(UIUtils.getString(R.string.moreDraw));

        mFragments.add(ClassifyChildFragment.getInstance());
        mFragments.add(ClassifyChildFragment.getInstance());
        mFragments.add(ClassifyChildFragment.getInstance());
        mFragments.add(ClassifyChildFragment.getInstance());
        mFragments.add(ClassifyChildFragment.getInstance());
        mFragments.add(ClassifyChildFragment.getInstance());
        mFragments.add(ClassifyChildFragment.getInstance());
        mFragments.add(ClassifyChildFragment.getInstance());
        mFragments.add(ClassifyChildFragment.getInstance());
        mFragments.add(ClassifyChildFragment.getInstance());

        BaseFragmentPageAdapter myAdapter = new BaseFragmentPageAdapter(getSupportFragmentManager(), mFragments, mTitleList);
        vp.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        tabLayout.setupWithViewPager(vp);
        vp.setCurrentItem(postion);

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
