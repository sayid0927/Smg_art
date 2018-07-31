package com.smg.art.ui.personalcenter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.component.AppComponent;
import com.smg.art.ui.login.LoginActivity;
import com.smg.art.utils.KeyBoardUtils;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.view.PopDialog;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mervin on 2018/7/25 0025.
 */

public class SettingActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    AutoRelativeLayout actionbarBack;
    @BindView(R.id.left_title)
    TextView leftTitle;
    @BindView(R.id.actionbar_title)
    TextView actionbarTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.actionbar_text_action)
    TextView actionbarTextAction;
    @BindView(R.id.head)
    RoundedImageView head;
    @BindView(R.id.name)
    LinearLayout name;
    @BindView(R.id.phone)
    LinearLayout phone;
    @BindView(R.id.pwd)
    LinearLayout pwd;
    @BindView(R.id.pay_pwd)
    LinearLayout payPwd;
    @BindView(R.id.exit)
    TextView exit;
    PopDialog popDialog;
    Intent intent;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.setting;
    }

    @Override
    public void attachView() {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void initView() {
        actionbarTitle.setText(R.string.setting);
    }

    @OnClick({R.id.rl_back, R.id.head, R.id.name, R.id.phone, R.id.pwd, R.id.pay_pwd, R.id.exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                KeyBoardUtils.hiddenKeyboart(this);
                finish();
                break;
            case R.id.head:
                break;
            case R.id.name:
                intent = new Intent(this, ChangeNickNameActivity.class);
                startActivityIn(intent, this);
                break;
            case R.id.phone:
                intent = new Intent(this, SendMobilePhoneActivity.class);
                startActivityIn(intent, this);
                break;
            case R.id.pwd:
                intent = new Intent(this, ChangePasswordActivity.class);
                startActivityIn(intent, this);
                break;
            case R.id.pay_pwd:
                intent = new Intent(this, ChangeTradersPasswordActivity.class);
                startActivityIn(intent, this);
                break;
            case R.id.exit:
                popDialog = new PopDialog(this, R.layout.exit_item);
                popDialog.show();
                popDialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popDialog.dismiss();
                    }
                });
                popDialog.findViewById(R.id.roll_out_submit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LocalAppConfigUtil.getInstance().setAccessToken("");
                        LocalAppConfigUtil.getInstance().setCurrentMerberId(0);
                        LocalAppConfigUtil.getInstance().setCurrentMerberNo("");
                        LocalAppConfigUtil.getInstance().setJsessionidShiro("");
                        LocalAppConfigUtil.getInstance().setJsessionId("");
                        LocalAppConfigUtil.getInstance().setRCToken("");
                        LocalAppConfigUtil.getInstance().setUserTelephone("");
                        LocalAppConfigUtil.getInstance().setPassword("");
                        intent = new Intent(SettingActivity.this, LoginActivity.class);
                        startActivityIn(intent, SettingActivity.this);
                        finish();
                    }
                });
                break;
        }
    }

}
