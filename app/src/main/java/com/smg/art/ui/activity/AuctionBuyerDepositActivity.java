package com.smg.art.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.blankj.utilcode.utils.EmptyUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.Gson;
import com.smg.art.R;
import com.smg.art.bean.AuctionBuyerDepositBean;
import com.smg.art.bean.AuctionDetailBean;
import com.smg.art.base.BaseActivity;
import com.smg.art.base.Constant;
import com.smg.art.bean.PlayIntroductionBean;
import com.smg.art.bean.RefundBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.AuctionBuyerDepositContract;
import com.smg.art.presenter.impl.activity.AuctionBuyerDepositPresenter;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.view.NumberDialog;
import com.smg.art.view.webview.PublicWebViewActivity;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
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
    @BindView(R.id.tv_agreement)
    TextView tvAgreement;
    @BindView(R.id.tv_rulespage)
    TextView tvRulespage;
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
        if (EmptyUtils.isNotEmpty(goodsData) && EmptyUtils.isNotEmpty(String.valueOf(goodsData.getData().getBuyerEnsureAmount())))
            tvFrontMoneyAmount.setText(String.valueOf(goodsData.getData().getBuyerEnsureAmount()));
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
                "amount", String.valueOf(goodsData.getData().getBuyerEnsureAmount()));
    }

    @Override
    public void FetchPayIntroductionSuccess(PlayIntroductionBean playIntroductionBean) {
        String play = playIntroductionBean.getData().getPayIntroduction();
        String[] plays = play.split(";");
        StringBuffer stringBuffer = new StringBuffer();
        for (String play1 : plays) {
            stringBuffer.append(play1).append("\n");
        }
        tvPlay.setText(stringBuffer.toString());
    }

    @Override
    public void showError(String message) {
        ToastUtils.showLongToast(message);
    }


    @OnClick({R.id.rl_back, R.id.bt_post, R.id.tv_rulespage, R.id.tv_agreement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;

            case R.id.tv_agreement:  // 用户竞拍服务协议
                Intent intent = new Intent(this, PublicWebViewActivity.class);
                intent.putExtra("url", Constant.API_BASE_URL + Constant.MEMBER_TOAUCTIONAGREEMENTPAGE);
                intent.putExtra("title", "竞拍服务协议");
                startActivityIn(intent, this);
                break;

            case R.id.tv_rulespage: // 保证金规则
                Intent intent1 = new Intent(this, PublicWebViewActivity.class);
                intent1.putExtra("url", Constant.API_BASE_URL + Constant.MEMBER_TOMARGINRULESPAGE);
                intent1.putExtra("title", "保证金规则");
                startActivityIn(intent1, this);
                break;

            case R.id.bt_post:
                if (checkBox.isChecked()) {
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
