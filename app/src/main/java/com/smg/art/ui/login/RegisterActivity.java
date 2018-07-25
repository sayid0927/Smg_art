package com.smg.art.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.blankj.utilcode.utils.ToastUtils;
import com.orhanobut.logger.Logger;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.PhoneVerifyCodeBean;
import com.smg.art.bean.RegisterBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.login.RegisterContract;
import com.smg.art.presenter.impl.login.RegisterActivityPresenter;
import com.smg.art.utils.CommonUtil;
import com.smg.art.utils.KeyBoardUtils;
import com.smg.art.utils.TimeCount;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mervin on 2018/7/24 0024.
 */

public class RegisterActivity extends BaseActivity implements RegisterContract.View, View.OnClickListener {


    @Inject
    RegisterActivityPresenter mPresenter;

    @BindView(R.id.actionbar_back)
    ImageView actionbarBack;
    @BindView(R.id.actionbar_title)
    TextView actionbarTitle;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.et_context)
    EditText etContext;
    @BindView(R.id.iv_del)
    ImageView ivDel;
    @BindView(R.id.input_phone)
    LinearLayout inputPhone;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.yzm_del)
    ImageView yzmDel;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.comfirm)
    Button comfirm;
    @BindView(R.id.etPayPwd)
    EditText etPayPwd;
    @BindView(R.id.togglePwd)
    ToggleButton togglePwd;
    @BindView(R.id.pwdImg)
    LinearLayout pwdImg;
    @BindView(R.id.input_pwd)
    LinearLayout inputPwd;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.ethelp)
    EditText ethelp;
    @BindView(R.id.view4)
    View view4;
    @BindView(R.id.register)
    Button register;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.yzm_code)
    EditText yzmCode;
    private TimeCount timeCount;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.register;
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
        actionbarTitle.setText(R.string.register_title);
        actionbarBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.actionbar_back:
                KeyBoardUtils.hiddenKeyboart(this);
                finish();
                break;
        }
    }

    @Override
    public void FetchRegisterSuccess(RegisterBean registerBean) {
        Logger.t("TAG").e(registerBean.toString());
        if (registerBean.getStatus() == 1) {
            ToastUtils.showShortToast(getString(R.string.register_success));
            finish();
        } else {
            ToastUtils.showShortToast(registerBean.getMsg());
        }
    }

    @Override
    public void FetchPhoneVerifyCodeSuccess(PhoneVerifyCodeBean phoneVerifyCodeBean) {
        ToastUtils.showLongToast(phoneVerifyCodeBean.getMsg());
        if (phoneVerifyCodeBean.getStatus() != 1) {
            comfirm.setClickable(true);
        } else {
            ToastUtils.showShortToast(getString(R.string.sms_success));
            startTime();
        }
    }

    @Override
    public Button btn() {
        return comfirm;
    }

    @Override
    public void showError(String message) {

    }

    @OnClick({R.id.comfirm, R.id.register, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.comfirm:
                if (TextUtils.isEmpty(etContext.getText().toString()) || !CommonUtil.isMobileNO(etContext.getText().toString())) {
                    ToastUtils.showShortToast(R.string.input_correct_phone);
                } else {
                    comfirm.setClickable(false);
                    mPresenter.FetchPhoneVerifyCode("mobilePhone", etContext.getText().toString().trim());
                }
                break;
            case R.id.register:
                if (checkUp()) {
                    mPresenter.FetchRegister("account", etContext.getText().toString().trim(), "password", etPayPwd.getText().toString().trim(),
                            "tradingPassword", ethelp.getText().toString().trim(), "verifyCode", yzmCode.getText().toString());
                }
                break;
            case R.id.login:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }


    /**
     * 开启倒计时
     */
    public void startTime() {
        if (timeCount == null) {
            timeCount = new TimeCount(60000, 1000, comfirm);
        }
        timeCount.start(); //倒计时后重新获取
    }


    /**
     * 检查输入是否有错
     *
     * @return
     */
    private boolean checkUp() {
        if (TextUtils.isEmpty(etContext.getText().toString())) {
            ToastUtils.showShortToast("请输入手机号");
            return false;
        }

        if (!CommonUtil.isMobileNO(etContext.getText().toString())) {
            ToastUtils.showShortToast("请输入正确的手机号");
            return false;
        }

        if (TextUtils.isEmpty(yzmCode.getText().toString())) {
            ToastUtils.showShortToast("请输入验证码");
            return false;
        }

        if (TextUtils.isEmpty(etPayPwd.getText().toString()) || etPayPwd.getText().length() < 6) {
            ToastUtils.showShortToast("请输入至少6位密码");
            return false;
        }

        if (!ethelp.getText().toString().equals(ethelp.getText().toString())) {
            ToastUtils.showShortToast("请输入至少6位交易密码");
            return false;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
