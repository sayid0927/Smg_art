package com.smg.art.ui.activity;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jaeger.library.StatusBarUtil;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.base.BaseFragmentPageAdapter;
import com.smg.art.bean.Apk_UpdateBean;
import com.smg.art.bean.TabEntity;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.MainContract;

import com.smg.art.presenter.impl.activity.MainActivityPresenter;
import com.smg.art.ui.fragment.AuctionFragment;
import com.smg.art.ui.fragment.HomeFragment;
import com.smg.art.ui.fragment.MessageFragment;
import com.smg.art.ui.fragment.MyFragment;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.utils.UIUtils;
import com.smg.art.view.NoScrollViewPager;

import org.w3c.dom.Text;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;


public class MainActivity extends BaseActivity implements MainContract.View, OnTabSelectListener {

    @Inject
    MainActivityPresenter mPresenter;

    public static MainActivity mainActivity;
    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;
    @BindView(R.id.vp)
    NoScrollViewPager vp;

    private ArrayList<String> mTitleList = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private int[] mIconUnselectIds = {
            R.drawable.home_icon_r,R.drawable.auction_icon_r,
            R.drawable.message_icon_r, R.drawable.me_icon_r};
    private int[] mIconSelectIds = {
            R.drawable.home_icon_p,R.drawable.auction_icon_p,
            R.drawable.message_icon_p, R.drawable.me_icon_p};


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
        StatusBarUtil.setTransparentForImageViewInFragment(MainActivity.this, null);
        mTitleList.add(UIUtils.getString(R.string.tab_item_home));
        mTitleList.add(UIUtils.getString(R.string.tab_item_auction));
        mTitleList.add(UIUtils.getString(R.string.tab_item_message));
        mTitleList.add(UIUtils.getString(R.string.tab_item_my));

        mFragments.add(new HomeFragment());
        mFragments.add(new AuctionFragment());
        mFragments.add(new MessageFragment());
        mFragments.add(new MyFragment());

        for (int i = 0; i < mTitleList.size(); i++) {
            mTabEntities.add(new TabEntity(mTitleList.get(i), mIconSelectIds[i], mIconUnselectIds[i]));
        }

        BaseFragmentPageAdapter myAdapter = new BaseFragmentPageAdapter(getSupportFragmentManager(), mFragments, mTitleList);
        vp.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        tabLayout.setTabData(mTabEntities);
        vp.setNoScroll(true);
        tabLayout.setOnTabSelectListener(this);
        mainActivity = this;
        if (!TextUtils.isEmpty(LocalAppConfigUtil.getInstance().getRCToken()))
            mPresenter.connect(LocalAppConfigUtil.getInstance().getRCToken());

        }

        @Override
        public void showError (String message){

        }

        @Override
        protected void onDestroy () {
            super.onDestroy();
        }

        @Override
        public void connectSuccess () {

        }

    /**
     * 再按一次退出程序
     * 判断在一定的时间内连续点击两次才退出程序
     */

    private long waitTime = 2000;
    private long touchTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - touchTime) >= waitTime) {
                ToastUtils.showLongToast("再按一次，退出程序!");
                touchTime = currentTime;
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onTabSelect(int position) {
        vp.setCurrentItem(position);
    }

    @Override
    public void onTabReselect(int position) {

    }
}
