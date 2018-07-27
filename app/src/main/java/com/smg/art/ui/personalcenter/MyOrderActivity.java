package com.smg.art.ui.personalcenter;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.base.BaseApplication;
import com.smg.art.base.BasePagerAdapter;
import com.smg.art.component.AppComponent;
import com.smg.art.ui.fragment.ClassifyChildFragment;
import com.smg.art.ui.personalcenter.fragemnt.AuctionOrderFragment;
import com.smg.art.utils.CommonDpUtils;
import com.smg.art.utils.KeyBoardUtils;
import com.smg.art.view.TabPageIndicator;
import com.smg.art.view.ViewPagerSlide;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mervin on 2018/7/27 0027.
 */

public class MyOrderActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    AutoRelativeLayout rlBack;
    @BindView(R.id.left_title)
    TextView leftTitle;
    @BindView(R.id.actionbar_title)
    TextView actionbarTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.actionbar_text_action)
    TextView actionbarTextAction;
    @BindView(R.id.indicator)
    TabPageIndicator indicator;
    @BindView(R.id.viewPager)
    ViewPagerSlide viewPager;
    AuctionOrderFragment auctionFragment1 = new AuctionOrderFragment();
    AuctionOrderFragment auctionFragment2 = new AuctionOrderFragment();
    AuctionOrderFragment auctionFragment3 = new AuctionOrderFragment();
    AuctionOrderFragment auctionFragment4 = new AuctionOrderFragment();
    AuctionOrderFragment auctionFragment5 = new AuctionOrderFragment();
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private String[] titles;
    private int current = 0;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.my_order;
    }

    @Override
    public void attachView() {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void initView() {
        current = getIntent().getIntExtra("index", 0);
        fragments.add(ClassifyChildFragment.getInstance());
        fragments.add(auctionFragment2);
        fragments.add(auctionFragment3);
        fragments.add(auctionFragment4);
        fragments.add(auctionFragment5);
        titles = getResources().getStringArray(R.array.order_them_title);
        BasePagerAdapter adapter = new BasePagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        actionbarTitle.setText(R.string.auction_order);
        indicator.setViewPager(viewPager);
        setTabPagerIndicator();
        viewPager.setCurrentItem(current);

    }

    private void setTabPagerIndicator() {
        indicator.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_WEIGHT_NOEXPAND_SAME);// 设置模式，一定要先设置模式
        //indicator.setDividerColor(Color.parseColor("#00bbcf"));// 设置分割线的颜色
        indicator.setDividerPadding(CommonDpUtils.dip2px(BaseApplication.getContext(), 10));
        indicator.setIndicatorColor(Color.parseColor("#FFFFFF"));// 设置底部导航线的颜色
        indicator.setTextColorSelected(Color.parseColor("#FFFFFF"));// 设置tab标题选中的颜色
        indicator.setTextColor(Color.parseColor("#724949"));// 设置tab标题未被选中的颜色
        indicator.setTextSize(CommonDpUtils.sp2px(BaseApplication.getContext(), 16));// 设置字体大小
    }

    @OnClick({R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                KeyBoardUtils.hiddenKeyboart(this);
                finish();
                break;
        }
    }

}
