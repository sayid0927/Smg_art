package com.smg.art.ui.personalcenter;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.base.BaseApplication;
import com.smg.art.base.BasePagerAdapter;
import com.smg.art.component.AppComponent;
import com.smg.art.ui.fragment.ClassifyChildFragment;
import com.smg.art.ui.personalcenter.fragemnt.AllFragment;
import com.smg.art.utils.CommonDpUtils;
import com.smg.art.utils.KeyBoardUtils;
import com.smg.art.view.TabPageIndicator;
import com.smg.art.view.ViewPagerSlide;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mervin on 2018/7/26 0026.
 */

public class MyWalletActivity extends BaseActivity {

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
    @BindView(R.id.l_price)
    LinearLayout lPrice;
    @BindView(R.id.indicator)
    TabPageIndicator indicator;
    @BindView(R.id.viewPager)
    ViewPagerSlide viewPager;
    @BindView(R.id.withdraw)
    TextView withdraw;
    @BindView(R.id.rcharge)
    TextView rcharge;
    AllFragment allFragment = new AllFragment();
    Intent intent;
    private String[] titles;
    /*    AllFragment allFragment = new AllFragment();
        Expenditurefragment expenditurefragment = new Expenditurefragment();//支出
        IncomeFragment incomeFragment = new IncomeFragment();//收入*/
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        // DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.my_wallet;
    }

    @Override
    public void attachView() {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void initView() {
        actionbarTitle.setText(R.string.mywallet);
        fragments.add(allFragment);
        fragments.add(ClassifyChildFragment.getInstance());
        fragments.add(ClassifyChildFragment.getInstance());
  /*      Bundle bundle = new Bundle();
        bundle.putString("key", "balance");*/
        // allFragment.setArguments();
      /*  allFragment.setArguments(bundle);
        incomeFragment.setArguments(bundle);
        expenditurefragment.setArguments(bundle);*/
        titles = getResources().getStringArray(R.array.balance_title);
        BasePagerAdapter adapter = new BasePagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        setTabPagerIndicator();
    }

    private void setTabPagerIndicator() {
        indicator.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_WEIGHT_NOEXPAND_NOSAME);// 设置模式，一定要先设置模式
        //indicator.setDividerColor(Color.parseColor("#00bbcf"));// 设置分割线的颜色
        indicator.setDividerPadding(CommonDpUtils.dip2px(BaseApplication.getContext(), 10));
        indicator.setIndicatorColor(Color.parseColor("#E70113"));// 设置底部导航线的颜色
        indicator.setTextColorSelected(Color.parseColor("#E70113"));// 设置tab标题选中的颜色
        indicator.setTextColor(Color.parseColor("#212121"));// 设置tab标题未被选中的颜色
        indicator.setTextSize(CommonDpUtils.sp2px(BaseApplication.getContext(), 16));// 设置字体大小
    }

    @OnClick({R.id.rl_back, R.id.withdraw, R.id.rcharge})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                KeyBoardUtils.hiddenKeyboart(this);
                finish();
                break;
            case R.id.rcharge:
                intent = new Intent(this, RechargeActivity.class);
                startActivityIn(intent, this);
                break;
            case R.id.withdraw:
                intent = new Intent(this, WithdrawActivity.class);
                startActivityIn(intent, this);
                break;
        }
    }
}
