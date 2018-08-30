package com.smg.art.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.base.BaseFragmentPageAdapter;
import com.smg.art.bean.HomePageImgBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.ClassifyContract;
import com.smg.art.presenter.impl.activity.ClassifyActivityPresenter;
import com.smg.art.ui.fragment.ClassifyChildFragment;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
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
    private List<HomePageImgBean.DataBean.CategoryListBean> categoryListBeans = new ArrayList<>();
    public  static  ClassifyActivity classifyActivity;

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

        categoryListBeans = (List<HomePageImgBean.DataBean.CategoryListBean>) getIntent().getSerializableExtra("item");
         for(int n =0;n<categoryListBeans.size();n++){
             mTitleList.add(categoryListBeans.get(n).getCategoryName());
             mFragments.add(ClassifyChildFragment.getInstance(categoryListBeans.get(n).getId(),categoryListBeans.get(n).getCategoryName()));
         }

        BaseFragmentPageAdapter myAdapter = new BaseFragmentPageAdapter(getSupportFragmentManager(), mFragments, mTitleList);
        vp.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        tabLayout.setupWithViewPager(vp);
        vp.setCurrentItem(postion);
        classifyActivity= this;
    }

    @Override
    public void showError(String message) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        classifyActivity= null;
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
