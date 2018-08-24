package com.smg.art.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.utils.EmptyUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.Gson;
import com.smg.art.R;
import com.smg.art.base.AuctionBuyerDepositBean;
import com.smg.art.base.AuctionDetailBean;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.AuctionGoodsBean;
import com.smg.art.bean.PlayIntroductionBean;
import com.smg.art.bean.RefundBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.AuctionBuyerDepositContract;
import com.smg.art.presenter.impl.activity.AuctionBuyerDepositPresenter;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.view.CustomDialog;
import com.smg.art.view.NumberDialog;
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
    @BindView(R.id.tv_play)
    TextView tvPlay;
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
        if (EmptyUtils.isNotEmpty(goodsData) && EmptyUtils.isNotEmpty(String.valueOf(goodsData.getData().getFrontMoneyAmount())))
            tvFrontMoneyAmount.setText(String.valueOf(goodsData.getData().getFrontMoneyAmount()));
        Drawable drawable = this.getResources().getDrawable(R.drawable.checkbox_style);
        drawable.setBounds(0, 0, 40, 40);
        checkBox.setCompoundDrawables(drawable, null, null, null);
        mPresenter.FetchPayIntroduction();

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


    /**
     * 验证交易密码(Gumq)
     */

    @Override
    public void FetchvalidteTradePwdSuccess(RefundBean refundBean) {
        mPresenter.FetchAuctionBuyerDeposit("auctionId", String.valueOf(goodsData.getData().getId()),
                "goodsId", String.valueOf(goodsData.getData().getGoodsId()),
                "memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                "amount", String.valueOf(goodsData.getData().getFrontMoneyAmount()));
    }

    @Override
    public void FetchPayIntroductionSuccess(PlayIntroductionBean playIntroductionBean) {
        String play = playIntroductionBean.getData().getPayIntroduction();
        String[] plays = play.split(";");
        StringBuffer stringBuffer = new StringBuffer();
        for (int s = 0; s < plays.length; s++) {
            stringBuffer.append(plays[s]).append("\n");
        }
        tvPlay.setText(stringBuffer.toString());
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
//                    if (goodsData != null) {
//                        View dialogview = View.inflate(this, R.layout.dialog_validtetradepwd, null);
//                        Button btPost = dialogview.findViewById(R.id.bt_post);
//                        Button btClecn = dialogview.findViewById(R.id.bt_clecn);
//                        final EditText edPwd = dialogview.findViewById(R.id.ed_pwd);
//                        final CustomDialog mDialogWaiting = new CustomDialog(this, dialogview, R.style.MyDialog);
//                        mDialogWaiting.show();
//                        mDialogWaiting.setCancelable(true);
//
//                        btClecn.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                mDialogWaiting.dismiss();
//                            }
//                        });
//
//                        btPost.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                String pwd = edPwd.getText().toString().trim();
//                                if (EmptyUtils.isNotEmpty(pwd)) {
//                                    mPresenter.FetchvalidteTradePwd("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
//                                            "tradePwd", pwd);
//                                    mDialogWaiting.dismiss();
//                                } else {
//                                    ToastUtils.showLongToast("请输入交易密码");
//                                }
//                            }
//                        });


                    final NumberDialog numberDialog = new NumberDialog(this);
                    numberDialog.show();
                    numberDialog.OnbtAuctionClick(new NumberDialog.OnbtAuctionClick() {
                        @Override
                        public void OnbtAuctionClick(String pwd) {
                            if (EmptyUtils.isNotEmpty(pwd)) {
                                mPresenter.FetchvalidteTradePwd("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                                        "tradePwd", pwd);
                                numberDialog.dismiss();
                            }
                        }
                    });
                } else {
                    ToastUtils.showLongToast("请选择支付方式");
                }


                break;
        }
    }
}
