package com.smg.art.ui.personalcenter;

import android.content.Intent;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.CheckBankCardBean;
import com.smg.art.bean.CurrencyExchangeRateBean;
import com.smg.art.bean.WithDrawBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.WithdrawContract;
import com.smg.art.presenter.impl.activity.WithDrawPresenter;
import com.smg.art.utils.KeyBoardUtils;
import com.smg.art.utils.LocalAppConfigUtil;
import com.zhy.autolayout.AutoRelativeLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mervin on 2018/7/26 0026.
 */

public class WithdrawActivity extends BaseActivity implements WithdrawContract.View {
    @Inject
    WithDrawPresenter mPresenter;

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
    @BindView(R.id.et_context)
    EditText etContext;
    @BindView(R.id.iv_del)
    ImageView ivDel;
    @BindView(R.id.confirm)
    TextView confirm;
    @BindView(R.id.add_bank)
    LinearLayout addBank;
    @BindView(R.id.bank_info)
    LinearLayout bankInfo;
    Intent intent;
    @BindView(R.id.bank_name)
    TextView bankName;
    @BindView(R.id.bank_card)
    TextView bankCard;
    @BindView(R.id.total_money)
    TextView totalMoney;
    @BindView(R.id.exchange_rate)
    TextView exchangeRate;
    @BindView(R.id.total_price)
    TextView totalPrice;
    @BindView(R.id.all_withdraw)
    TextView allWithdraw;
    @BindView(R.id.bank_icon)
    ImageView bankIcon;
    CheckBankCardBean mCheckBankCardBean;
    CurrencyExchangeRateBean mCurrencyExchangeRateBean;
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!etContext.getText().toString().trim().equals("")) {
                setInputStyle(View.VISIBLE);
            } else {
                setInputStyle(View.GONE);
            }
        }
    };

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.withdraw_activity;
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
        actionbarTitle.setText(R.string.withdraw);
        etContext.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        //切换后将EditText光标置于末尾
        CharSequence charSequence = etContext.getText();
        if (charSequence instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText, charSequence.length());
        }

        ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etContext.setText("");
            }
        });

        setInputStyle(View.GONE);
        etContext.addTextChangedListener(textWatcher);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBankData();
    }

    public void getBankData() {
        mPresenter.FetchCheckBankCard("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()), "type", "0");
        mPresenter.FetchCurrencyExchangeRate("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()));
    }

    /**
     * 设置输入时的控件状态
     *
     * @param
     * @param visible 是否显示删除图标
     * @param
     */
    private void setInputStyle(int visible) {
        ivDel.setVisibility(visible);
    }

    @OnClick({R.id.rl_back, R.id.add_bank, R.id.bank_info, R.id.confirm, R.id.all_withdraw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                KeyBoardUtils.hiddenKeyboart(this);
                finish();
                break;
            case R.id.add_bank:
                intent = new Intent(this, AddBankCardActivity.class);
                startActivityIn(intent, this);
                break;
            case R.id.bank_info:
                intent = new Intent(this, AddBankCardActivity.class);
                startActivityIn(intent, this);
                break;
            case R.id.all_withdraw:
                if (!TextUtils.isEmpty(mCurrencyExchangeRateBean.getData().getRmbAmount())) {
                    etContext.setText(mCurrencyExchangeRateBean.getData().getRmbAmount());
                } else {
                    ToastUtils.showShortToast("提现金额不足");
                }
                break;
            case R.id.confirm:
                if (TextUtils.isEmpty(etContext.getText().toString())) {
                    ToastUtils.showShortToast("请输入提现金额");
                } else {
                    mPresenter.FetchWithdrawCode("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()), "cardNo", mCheckBankCardBean.getData().getCardNo(),
                            "amount", etContext.getText().toString());
                }

                break;
        }
    }

    @Override
    public void FetchCheckBankCardSuccess(CheckBankCardBean checkBankCardBean) {
        if (checkBankCardBean.getStatus() == 1) {
            if (checkBankCardBean.getData() != null) {
                mCheckBankCardBean = checkBankCardBean;
                addBank.setVisibility(View.GONE);
                bankInfo.setVisibility(View.VISIBLE);
                bankName.setText(checkBankCardBean.getData().getBank());
                bankCard.setText("(" + checkBankCardBean.getData().getCardNo().substring(checkBankCardBean.getData().getCardNo().length() - 4, checkBankCardBean.getData().getCardNo().length()) + ")");
                switch (checkBankCardBean.getData().getBankType()) {
                    case "ABC":
                        bankIcon.setBackground(getResources().getDrawable(R.mipmap.abc));
                        break;
                    case "BCM":
                        bankIcon.setBackground(getResources().getDrawable(R.mipmap.bcm));
                        break;
                    case "BOC":
                        bankIcon.setBackground(getResources().getDrawable(R.mipmap.boc));
                        break;
                    case "CCB":
                        bankIcon.setBackground(getResources().getDrawable(R.mipmap.ccb));
                        break;
                    case "CEB":
                        bankIcon.setBackground(getResources().getDrawable(R.mipmap.ceb));
                        break;
                    case "CMB":
                        bankIcon.setBackground(getResources().getDrawable(R.mipmap.cmb));
                        break;
                    case "CMBC":
                        bankIcon.setBackground(getResources().getDrawable(R.mipmap.cmbc));
                        break;
                    case "HB":
                        bankIcon.setBackground(getResources().getDrawable(R.mipmap.hb));
                        break;
                    case "ICBC":
                        bankIcon.setBackground(getResources().getDrawable(R.mipmap.icbc));
                        break;
                    case "PSBC":
                        bankIcon.setBackground(getResources().getDrawable(R.mipmap.psbc));
                        break;
                    case "SPDB":
                        bankIcon.setBackground(getResources().getDrawable(R.mipmap.spdb));
                        break;
                }
            } else {
                addBank.setVisibility(View.VISIBLE);
                bankInfo.setVisibility(View.GONE);
            }
        } else {
            ToastUtils.showShortToast(checkBankCardBean.getMsg());
            addBank.setVisibility(View.VISIBLE);
            bankInfo.setVisibility(View.GONE);
        }
    }

    @Override
    public void FetchWithdrawSuccess(WithDrawBean withDrawBean) {
        if (withDrawBean.getStatus() == 1) {
            ToastUtils.showShortToast("提现成功");
            finish();
        } else {
            ToastUtils.showShortToast(withDrawBean.getMsg());
        }
    }

    @Override
    public void FetchCurrencyExchangeRateSuccess(CurrencyExchangeRateBean currencyExchangeRateBean) {
        if (currencyExchangeRateBean.getStatus() == 1) {
            mCurrencyExchangeRateBean = currencyExchangeRateBean;
            totalMoney.setText(String.format("%.2f", Double.valueOf(currencyExchangeRateBean.getData().getAmount())));
            exchangeRate.setText("1币=" + currencyExchangeRateBean.getData().getCurrency() + "元");
            totalPrice.setText("总金额:" + String.format("%.2f", Double.valueOf(currencyExchangeRateBean.getData().getRmbAmount())));
        } else {
            ToastUtils.showShortToast(currencyExchangeRateBean.getMsg());
        }
    }

    @Override
    public void showError(String message) {

    }


}
