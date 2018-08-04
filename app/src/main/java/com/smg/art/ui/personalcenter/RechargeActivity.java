package com.smg.art.ui.personalcenter;

import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.CheckBankCardBean;
import com.smg.art.bean.ReChargeBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.ReChargeContract;
import com.smg.art.presenter.impl.activity.ReChargePresenter;
import com.smg.art.utils.KeyBoardUtils;
import com.smg.art.utils.LocalAppConfigUtil;
import com.zhy.autolayout.AutoRelativeLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mervin on 2018/7/26 0026.
 */

public class RechargeActivity extends BaseActivity implements ReChargeContract.View {
    @Inject
    ReChargePresenter mPresenter;

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
    @BindView(R.id.payee)
    TextView payee;
    @BindView(R.id.bank_icon)
    ImageView bankIcon;
    @BindView(R.id.bank_name)
    TextView bankName;
    @BindView(R.id.bank_card)
    TextView bankCard;
    @BindView(R.id.up_pic)
    ImageView upPic;
    CheckBankCardBean mCheckBankCardBean;
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
        return R.layout.recharge;
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
        actionbarTitle.setText(R.string.recharge);
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
        getBankData();
    }

    public void getBankData() {
        mPresenter.FetchCheckBankCard("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()), "type", "1");
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

    @OnClick({R.id.rl_back, R.id.confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                KeyBoardUtils.hiddenKeyboart(this);
                finish();
                break;
            case R.id.confirm:
                if (TextUtils.isEmpty(etContext.getText().toString())) {
                    ToastUtils.showShortToast("请输入充值金额");
                } else {
                    mPresenter.FetchReCharge("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()), "cardNo", mCheckBankCardBean.getData().getCardNo(),
                            "amount", etContext.getText().toString(), "voucherUrl", "http://baidu.com");
                }
                break;
        }
    }


    @Override
    public void FetchReChargeSuccess(ReChargeBean reChargeBean) {

    }

    @Override
    public void FetchCheckBankCardSuccess(CheckBankCardBean checkBankCardBean) {
        if (checkBankCardBean.getStatus() == 1) {
            mCheckBankCardBean = checkBankCardBean;
            bankName.setText(checkBankCardBean.getData().getBank());
            bankCard.setText(checkBankCardBean.getData().getCardNo());
            payee.setText(checkBankCardBean.getData().getReceiptName());
        } else {
            ToastUtils.showShortToast(checkBankCardBean.getMsg());
        }
    }

    @Override
    public void showError(String message) {

    }
}
