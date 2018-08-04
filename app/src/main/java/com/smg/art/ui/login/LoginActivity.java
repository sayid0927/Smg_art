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
import com.jaeger.library.StatusBarUtil;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.LoginBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.login.LoginContract;
import com.smg.art.presenter.impl.login.LoginActivityPresenter;
import com.smg.art.ui.activity.MainActivity;
import com.smg.art.utils.CommonUtil;
import com.smg.art.utils.LocalAppConfigUtil;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Mervin on 2018/7/24 0024.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View, View.OnClickListener {

    @Inject
    LoginActivityPresenter mPresenter;

    @BindView(R.id.login_head)
    ImageView loginHead;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.et_context)
    EditText etContext;
    @BindView(R.id.iv_del)
    ImageView ivDel;
    @BindView(R.id.input_phone)
    LinearLayout inputPhone;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.pwd)
    TextView pwd;
    @BindView(R.id.etPayPwd)
    EditText etPayPwd;
    @BindView(R.id.togglePwd)
    ToggleButton togglePwd;
    @BindView(R.id.pwdImg)
    LinearLayout pwdImg;
    @BindView(R.id.input_pwd)
    LinearLayout inputPwd;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.comfirm)
    Button comfirm;
    @BindView(R.id.login)
    LinearLayout login;
    @BindView(R.id.register_now)
    TextView registerNow;
    @BindView(R.id.foget_passwords)
    TextView fogetPasswords;
    @BindView(R.id.register)
    LinearLayout register;
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {

            if (s == null || s.length() == 0)
                return;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (i != 3 && i != 8 && s.charAt(i) == ' ') {
                    continue;
                } else {
                    sb.append(s.charAt(i));
                    if ((sb.length() == 4 || sb.length() == 9)
                            && sb.charAt(sb.length() - 1) != ' ') {
                        sb.insert(sb.length() - 1, ' ');
                    }
                }
            }
            if (!sb.toString().equals(s.toString())) {
                int index = start + 1;
                if (sb.charAt(start) == ' ') {
                    if (before == 0) {
                        index++;
                    } else {
                        index--;
                    }
                } else {
                    if (before == 1) {
                        index--;
                    }
                }
                etContext.setText(sb.toString());
                etContext.setSelection(index);
            }

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
        return R.layout.login;
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
        StatusBarUtil.setTransparentForImageViewInFragment(LoginActivity.this, null);

        phone.setFocusable(true);
        phone.setFocusableInTouchMode(true);
        phone.requestFocus();
        etContext.addTextChangedListener(textWatcher);
        registerNow.setOnClickListener(this);
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
        fogetPasswords.setOnClickListener(this);
        comfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.foget_passwords:
                startActivityIn(new Intent(this, ForgetPasswordActivity.class),this);
                break;
            case R.id.comfirm:
            /*    startActivityIn(new Intent(this, MainActivity.class),this);
                finish();*/
                if (checkUp()) {
                    mPresenter.FetchLogin("account", etContext.getText().toString().replace(" ", ""), "password", etPayPwd.getText().toString());
                }
                break;
            case R.id.register_now:
                startActivityIn(new Intent(this, RegisterActivity.class),this);
                break;
        }
    }

    @Override
    public void FetchLoginSuccess(LoginBean loginBean) {
        if (loginBean.getStatus() == 1) {
            ToastUtils.showShortToast(getString(R.string.login_success));
            LocalAppConfigUtil.getInstance().setAccessToken(loginBean.getData().getRCToken());
            LocalAppConfigUtil.getInstance().setCurrentMerberId(62);
            LocalAppConfigUtil.getInstance().setCurrentMerberNo(loginBean.getData().getMemberNo());
            LocalAppConfigUtil.getInstance().setJsessionidShiro(loginBean.getData().getJSESSIONID_SHIRO());
            LocalAppConfigUtil.getInstance().setJsessionId(loginBean.getData().getJSESSIONID());
            LocalAppConfigUtil.getInstance().setRCToken(loginBean.getData().getRCToken());
            LocalAppConfigUtil.getInstance().setHeadImg(loginBean.getData().getHeadImg());
            LocalAppConfigUtil.getInstance().setUserTelephone(etContext.getText().toString().replace(" ", ""));
            LocalAppConfigUtil.getInstance().setPassword(etPayPwd.getText().toString());
            startActivityIn(new Intent(this, MainActivity.class),this);
            finish();
        } else {
            ToastUtils.showShortToast(loginBean.getMsg());
        }
    }

    @Override
    public void showError(String message) {

    }

    /**
     * 检查输入是否有错
     *
     * @return
     */
    private boolean checkUp() {
        if (TextUtils.isEmpty(etContext.getText().toString().trim())) {
            ToastUtils.showShortToast("请输入手机号");
            return false;
        }

        if (!CommonUtil.isMobileNO(etContext.getText().toString().replace(" ", ""))) {
            ToastUtils.showShortToast("请输入正确的手机号");
            return false;
        }

        if (TextUtils.isEmpty(etPayPwd.getText().toString()) || etPayPwd.getText().length() < 6) {
            ToastUtils.showShortToast("请输入至少6位密码");
            return false;
        }

        return true;
    }

}
