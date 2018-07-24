package com.smg.art.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.component.AppComponent;
import com.smg.art.utils.KeyBoardUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mervin on 2018/7/24 0024.
 */

public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.register)
    Button register;
    @BindView(R.id.register_btn)
    LinearLayout registerBtn;
    @BindView(R.id.foget_passwords)
    TextView fogetPasswords;
    @BindView(R.id.login_text)
    LinearLayout loginText;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.forget_password;
    }

    @Override
    public void attachView() {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void initView() {
        actionbarTitle.setText(R.string.forget_password);
        actionbarBack.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
}
