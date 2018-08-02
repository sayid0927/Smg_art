package com.smg.art.ui.personalcenter;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.base.BaseApplication;
import com.smg.art.bean.WalletBalanceBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.MyWalletContract;
import com.smg.art.presenter.impl.activity.MyWalletPresenter;
import com.smg.art.ui.adapter.MyWalletViewAdapter;
import com.smg.art.utils.CommonDpUtils;
import com.smg.art.utils.KeyBoardUtils;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.view.TabPageIndicator;
import com.smg.art.view.ViewPagerSlide;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mervin on 2018/7/26 0026.
 */

public class MyWalletActivity extends BaseActivity implements MyWalletContract.View {
    @Inject
    MyWalletPresenter mPresenter;
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
    Intent intent;
    @BindView(R.id.price)
    TextView price;
    private String[] titles;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private int current = 0;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.my_wallet;
    }

    @Override
    public void attachView() {
        mPresenter.attachView(this, this);
    }

    @Override
    public void detachView() {
        mPresenter.detachView();
    }

    public void getData() {
        mPresenter.FetchMyWalletBalance("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()));
    }

    @Override
    public void initView() {
        actionbarTitle.setText(R.string.mywallet);
        titles = getResources().getStringArray(R.array.balance_title);
        MyWalletViewAdapter adapter = new MyWalletViewAdapter(getSupportFragmentManager(), titles);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        setTabPagerIndicator();
        viewPager.setCurrentItem(current);
        getData();
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

    @Override
    public void FetchMyWalletBalanceSuccess(WalletBalanceBean walletBalanceBean) {
        if (walletBalanceBean.getStatus() == 1) {
            if (!TextUtils.isEmpty(walletBalanceBean.getData())) {
                price.setText(String.format("%.2f", Double.valueOf(walletBalanceBean.getData())));
            }
        } else {
            ToastUtils.showShortToast(walletBalanceBean.getMsg());
        }
    }

    @Override
    public void showError(String message) {

    }

}
