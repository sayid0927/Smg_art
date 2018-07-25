package com.smg.art.ui.login;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.ForgetPasswordBean;
import com.smg.art.bean.PhoneVerifyCodeBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.login.ForgetPasswordContract;
import com.smg.art.presenter.impl.login.ForgetPasswordPresenter;
import com.smg.art.utils.CommonUtil;
import com.smg.art.utils.KeyBoardUtils;
import com.smg.art.utils.TimeCount;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mervin on 2018/7/24 0024.
 */

public class ForgetPasswordActivity extends BaseActivity implements ForgetPasswordContract.View, View.OnClickListener {
    @Inject
    ForgetPasswordPresenter mPresenter;
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
    @BindView(R.id.textview2)
    TextView textview2;
    @BindView(R.id.yzm_code)
    EditText yzmCode;
    @BindView(R.id.yzm_del)
    ImageView yzmDel;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.comfirm)
    Button comfirm;
    @BindView(R.id.yzm)
    LinearLayout yzm;
    @BindView(R.id.textview3)
    TextView textview3;
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
    @BindView(R.id.affirm)
    Button affirm;
    @BindView(R.id.register_btn)
    LinearLayout registerBtn;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.login_text)
    LinearLayout loginText;


    private TimeCount timeCount;
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
                ivDel.setVisibility(View.VISIBLE);
            } else {
                ivDel.setVisibility(View.INVISIBLE);
            }

        }
    };

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.forget_password;
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
        actionbarTitle.setText(R.string.forget_password);
        actionbarBack.setOnClickListener(this);
        etContext.addTextChangedListener(textWatcher);
        ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etContext.setText("");
            }
        });
        ivDel.setVisibility(View.INVISIBLE);
        togglePwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (togglePwd.isChecked()) {
                    //如果选中，显示密码
                    etPayPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etPayPwd.setSelection(etPayPwd.getText().toString().length());
                } else {
                    //否则隐藏密码
                    etPayPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etPayPwd.setSelection(etPayPwd.getText().toString().length());
                }
            }
        });
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

    @OnClick({R.id.comfirm, R.id.affirm, R.id.login})
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
            case R.id.affirm:
                if (checkUp()) {
                    mPresenter.FetchForgetPassword("account", etContext.getText().toString().trim(), "validteCode", yzmCode.getText().toString(), "loginPwd", etPayPwd.getText().toString());
                }
                break;
            case R.id.login:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void FetchForgetPasswordSuccess(ForgetPasswordBean forgetPasswordBean) {
        if (forgetPasswordBean.getStatus() == 1) {
            ToastUtils.showShortToast(getString(R.string.modification_success));
            finish();
        } else {
            ToastUtils.showShortToast(forgetPasswordBean.getMsg());
        }
    }

    @Override
    public void FetchPhoneVerifyCodeSuccess(PhoneVerifyCodeBean phoneVerifyCodeBean) {
        if (phoneVerifyCodeBean.getStatus() != 1) {
            ToastUtils.showLongToast(phoneVerifyCodeBean.getMsg());
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

        return true;
    }
}
