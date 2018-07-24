package com.smg.art.ui.activity;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.base.BaseFragmentPageAdapter;
import com.smg.art.bean.Apk_UpdateBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.MainContract;

import com.smg.art.presenter.impl.activity.MainActivityPresenter;
import com.smg.art.ui.fragment.AuctionFragment;
import com.smg.art.ui.fragment.HomeFragment;
import com.smg.art.ui.fragment.MyFragment;
import com.smg.art.utils.UIUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;


public class MainActivity extends BaseActivity implements MainContract.View, TabLayout.OnTabSelectedListener {

    @Inject
    MainActivityPresenter mPresenter;

    public static MainActivity mainActivity;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.vp)
    ViewPager vp;

    private ArrayList<String> mTitleList = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private TabLayout.Tab tabHome;
    private TabLayout.Tab tabAuction;
    private TabLayout.Tab tabMessage;
    private TabLayout.Tab tabMy;




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
        mPresenter.attachView(this, this);
    }

    @Override
    public void detachView() {
        mPresenter.detachView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initView() {
        setSwipeBackEnable(false);

        mTitleList.add(UIUtils.getString(R.string.tab_item_home));
        mTitleList.add(UIUtils.getString(R.string.tab_item_auction));
        mTitleList.add(UIUtils.getString(R.string.tab_item_message));
        mTitleList.add(UIUtils.getString(R.string.tab_item_my));

        mFragments.add(new HomeFragment());
        mFragments.add(new AuctionFragment());
        mFragments.add(new HomeFragment());
        mFragments.add(new MyFragment());
//        vp = (ViewPager) findViewById(R.id.vp);
        BaseFragmentPageAdapter myAdapter = new BaseFragmentPageAdapter(getSupportFragmentManager(), mFragments, mTitleList);
        vp.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        tabLayout.setupWithViewPager(vp);
        tabLayout.addOnTabSelectedListener(this);


        tabHome = tabLayout.getTabAt(0);
        tabAuction = tabLayout.getTabAt(1);
        tabMessage = tabLayout.getTabAt(2);
        tabMy = tabLayout.getTabAt(3);

        tabHome.setIcon(R.drawable.home_icon_p);
        tabAuction.setIcon(R.drawable.auction_icon_r);
        tabMessage.setIcon(R.drawable.message_icon_r);
        tabMy.setIcon(R.drawable.me_icon_r);

        mPresenter.Apk_Update();
        mainActivity = this;

    }

    @Override
    public void showError(String message) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void ApkUpdateS(Apk_UpdateBean.DataBean dataBean) {

    }

    @Override
    public TextView tv() {
        return null;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        switch (tab.getPosition()) {
            case 0:
                tabHome.setIcon(R.drawable.home_icon_p);
                tabAuction.setIcon(R.drawable.auction_icon_r);
                tabMessage.setIcon(R.drawable.message_icon_r);
                tabMy.setIcon(R.drawable.me_icon_r);
                break;
            case 1:
                tabHome.setIcon(R.drawable.home_icon_r);
                tabAuction.setIcon(R.drawable.auction_icon_p);
                tabMessage.setIcon(R.drawable.message_icon_r);
                tabMy.setIcon(R.drawable.me_icon_r);
                break;
            case 2:
                tabHome.setIcon(R.drawable.home_icon_r);
                tabAuction.setIcon(R.drawable.auction_icon_r);
                tabMessage.setIcon(R.drawable.message_icon_p);
                tabMy.setIcon(R.drawable.me_icon_r);
                break;
            case 3:
                tabHome.setIcon(R.drawable.home_icon_r);
                tabAuction.setIcon(R.drawable.auction_icon_r);
                tabMessage.setIcon(R.drawable.message_icon_r);
                tabMy.setIcon(R.drawable.me_icon_p);
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
