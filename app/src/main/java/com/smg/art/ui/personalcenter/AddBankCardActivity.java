package com.smg.art.ui.personalcenter;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.AddBankCardBean;
import com.smg.art.bean.PhoneVerifyCodeBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.AddBankCardContract;
import com.smg.art.presenter.impl.activity.AddBankCardPresenter;
import com.smg.art.utils.CommonUtil;
import com.smg.art.utils.KeyBoardUtils;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.utils.TimeCountRed;
import com.zhy.autolayout.AutoRelativeLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mervin on 2018/7/26 0026.
 */

public class AddBankCardActivity extends BaseActivity implements View.OnClickListener, AddBankCardContract.View {

    @Inject
    AddBankCardPresenter mPresenter;

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
    @BindView(R.id.etBankCardOwner)
    EditText etBankCardOwner;
    @BindView(R.id.addBankCardOwner)
    RelativeLayout addBankCardOwner;
    @BindView(R.id.etBankCardNO)
    EditText etBankCardNO;
    @BindView(R.id.addBankCardNO)
    RelativeLayout addBankCardNO;
    @BindView(R.id.etBankCardBranch)
    EditText etBankCardBranch;
    @BindView(R.id.addBankCardBranch)
    RelativeLayout addBankCardBranch;
    @BindView(R.id.etBankCardMobile)
    EditText etBankCardMobile;
    @BindView(R.id.addBankCardMobile)
    RelativeLayout addBankCardMobile;
    @BindView(R.id.etBankCardCode)
    EditText etBankCardCode;
    @BindView(R.id.addBankCardGetCode)
    Button addBankCardGetCode;
    @BindView(R.id.addBankCardCode)
    RelativeLayout addBankCardCode;
    @BindView(R.id.tvCommitWithdraw)
    TextView tvCommitWithdraw;
    private TimeCountRed timeCount;
    @BindView(R.id.pic_yzm_code)
    EditText picYzmCode;
    @BindView(R.id.pic_yzm_del)
    ImageView picYzmDel;
    @BindView(R.id.pic_view1)
    View picView1;
    @BindView(R.id.get_iamge)
    ImageView getIamge;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_bank_card;
    }

    @Override
    public void attachView() {
        mPresenter.attachView(this, this);
    }

    @Override
    public void detachView() {
        mPresenter.detachView();
        if (timeCount != null) {
            timeCount.cancel();
        }
    }

    @Override
    public void initView() {
        actionbarTitle.setText(R.string.bank_card);
        addBankCardGetCode.setOnClickListener(this);
        tvCommitWithdraw.setOnClickListener(this);
        getIamge.setOnClickListener(this);
        mPresenter.FetchPictureCode("mobilePhone", etBankCardMobile.getText().toString().trim());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addBankCardGetCode:
                if (TextUtils.isEmpty(etBankCardMobile.getText().toString()) || !CommonUtil.isMobileNO(etBankCardMobile.getText().toString())) {
                    ToastUtils.showShortToast(R.string.input_correct_phone);
                } else {
                    if (TextUtils.isEmpty(picYzmCode.getText().toString())) {
                        ToastUtils.showShortToast("请输入图形验证码");
                    } else {
                        addBankCardGetCode.setClickable(false);
                        mPresenter.FetchPhoneVerifyCode("mobilePhone", etBankCardMobile.getText().toString().trim(), "pictureCode", picYzmCode.getText().toString());
                    }
                }
                break;
            case R.id.tvCommitWithdraw:
                if (checkUp()) {
                    mPresenter.FetchAddBankCard("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()), "bankUserName", etBankCardOwner.getText().toString(),
                            "cardNo", etBankCardNO.getText().toString(), "openBankName", etBankCardBranch.getText().toString(), "mobile", etBankCardMobile.getText().toString(),
                            "verifiCode", etBankCardCode.getText().toString());
                }
            case R.id.get_iamge:
                mPresenter.FetchPictureCode("mobilePhone", etBankCardMobile.getText().toString().trim());
                break;
        }
    }


    private boolean checkUp() {
        if (TextUtils.isEmpty(etBankCardOwner.getText().toString())) {
            ToastUtils.showShortToast("姓名不能为空");
            return false;
        }
        if (TextUtils.isEmpty(etBankCardNO.getText().toString())) {
            ToastUtils.showShortToast("银行卡号不能为空");
            return false;
        }
        if (TextUtils.isEmpty(etBankCardBranch.getText().toString())) {
            ToastUtils.showShortToast("支行信息不能为空");
            return false;
        }
        if (TextUtils.isEmpty(etBankCardMobile.getText().toString())) {
            ToastUtils.showShortToast("预留手机不能为空");
            return false;
        }
        if (TextUtils.isEmpty(picYzmCode.getText().toString())) {
            ToastUtils.showShortToast("请输入图形验证码");
            return false;
        }

        if (!CommonUtil.isMobileNO(etBankCardMobile.getText().toString())) {
            ToastUtils.showShortToast("手机号码不正确");
            return false;
        }

        if (TextUtils.isEmpty(etBankCardCode.getText().toString())) {
            ToastUtils.showShortToast("验证码不能为空");
            return false;
        }
        return true;
    }

    /**
     * 开启倒计时
     */
    public void startTime() {
        if (timeCount == null) {
            timeCount = new TimeCountRed(60000, 1000, addBankCardGetCode);
        }
        timeCount.start(); //倒计时后重新获取
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


    @Override
    public void FetchAddBankCardSuccess(AddBankCardBean addBankCardBean) {
        if (addBankCardBean.getStatus() == 1) {
            ToastUtils.showShortToast("银行卡添加成功");
            finish();
        } else {
            ToastUtils.showShortToast(addBankCardBean.getMsg());
        }
    }

    @Override
    public void FetchPhoneVerifyCodeSuccess(PhoneVerifyCodeBean phoneVerifyCodeBean) {
        if (phoneVerifyCodeBean.getStatus() != 1) {
            ToastUtils.showLongToast(phoneVerifyCodeBean.getMsg());
            addBankCardGetCode.setClickable(true);
        } else {
            ToastUtils.showShortToast(getString(R.string.sms_success));
            startTime();
        }
    }


    @Override
    public ImageView iv() {
        return getIamge;
    }

    @Override
    public Button btn() {
        return addBankCardGetCode;
    }

    @Override
    public void showError(String message) {

    }
}
