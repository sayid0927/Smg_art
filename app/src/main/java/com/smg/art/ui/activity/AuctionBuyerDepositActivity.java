package com.smg.art.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.blankj.utilcode.utils.EmptyUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.Gson;
import com.smg.art.R;
import com.smg.art.base.AuctionBuyerDepositBean;
import com.smg.art.base.AuctionDetailBean;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.AuctionGoodsBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.AuctionBuyerDepositContract;
import com.smg.art.presenter.impl.activity.AuctionBuyerDepositPresenter;
import com.smg.art.utils.LocalAppConfigUtil;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuctionBuyerDepositActivity extends BaseActivity implements AuctionBuyerDepositContract.View {
    @Inject
    AuctionBuyerDepositPresenter mPresenter;
    @BindView(R.id.rl_back)
    AutoRelativeLayout rlBack;
    @BindView(R.id.actionbar_title)
    TextView actionbarTitle;
    @BindView(R.id.tv_frontMoneyAmount)
    TextView tvFrontMoneyAmount;
    @BindView(R.id.bt_post)
    Button btPost;

    @BindView(R.id.checkbox)
    CheckBox checkBox;
    private AuctionDetailBean goodsData;
    private int type;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_auction_buyer_deposit;
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

        actionbarTitle.setText("支付");
        String bookJson = getIntent().getStringExtra("data");
        type = getIntent().getIntExtra("type", 1);
        goodsData = new Gson().fromJson(bookJson, AuctionDetailBean.class);
        if( EmptyUtils.isNotEmpty(goodsData) &&  EmptyUtils.isNotEmpty(String.valueOf(goodsData.getData().getFrontMoneyAmount())))
        tvFrontMoneyAmount.setText(String.valueOf(goodsData.getData().getFrontMoneyAmount()));
        Drawable drawable = this.getResources().getDrawable(R.drawable.checkbox_style);
        //设置drawable对象的大小
        drawable.setBounds(0, 0, 40, 40);
         //设置CheckBox对象的位置，对应为左、上、右、下
        checkBox.setCompoundDrawables(drawable, null, null, null);
    }

    /**
     * 保证金支付
     */
    @Override
    public void FetchAuctionBuyerDepositSuccess(AuctionBuyerDepositBean auctionBuyerDepositBean) {
        ToastUtils.showLongToast(auctionBuyerDepositBean.getMsg());
        EventBus.getDefault().post(auctionBuyerDepositBean);
        finish();
    }

    @Override
    public void showError(String message) {
        ToastUtils.showLongToast(message);
    }


    @OnClick({R.id.rl_back, R.id.bt_post})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.bt_post:
                if (checkBox.isChecked()) {
                    if (goodsData != null) {
                        mPresenter.FetchAuctionBuyerDeposit("auctionId", String.valueOf(goodsData.getData().getId()),
                                "goodsId", String.valueOf(goodsData.getData().getGoodsId()),
                                "memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                                "amount", String.valueOf(goodsData.getData().getFrontMoneyAmount()));
                    }
                } else {
                    ToastUtils.showLongToast("请选择支付方式");
                }
                break;
        }
    }
}
