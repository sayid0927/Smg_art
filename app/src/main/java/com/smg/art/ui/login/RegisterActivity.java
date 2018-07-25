package com.smg.art.ui.login;

import android.os.Bundle;
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
import com.smg.art.utils.KeyBoardUtils;

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
    @BindView(R.id.foget_passwords)
    TextView fogetPasswords;

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
    }

    @Override
    public void FetchPhoneVerifyCodeSuccess(PhoneVerifyCodeBean phoneVerifyCodeBean) {

        ToastUtils.showLongToast(phoneVerifyCodeBean.getMsg());
    }

    @Override
    public void showError(String message) {

    }

    @OnClick({R.id.comfirm, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.comfirm:
                mPresenter.FetchPhoneVerifyCode("mobilePhone",etContext.getText().toString().trim());
                break;
            case R.id.register:
//                mPresenter.FetchRegister();
                break;
        }
    }
}
