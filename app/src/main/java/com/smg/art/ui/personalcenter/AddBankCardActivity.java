package com.smg.art.ui.personalcenter;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.component.AppComponent;
import com.smg.art.utils.CommonUtil;
import com.smg.art.utils.KeyBoardUtils;
import com.smg.art.utils.TimeCount;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mervin on 2018/7/26 0026.
 */

public class AddBankCardActivity extends BaseActivity implements View.OnClickListener {

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
    TextView addBankCardGetCode;
    @BindView(R.id.addBankCardCode)
    RelativeLayout addBankCardCode;
    @BindView(R.id.tvCommitWithdraw)
    TextView tvCommitWithdraw;
    private TimeCount timeCount;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_bank_card;
    }

    @Override
    public void attachView() {

    }

    @Override
    public void detachView() {
        if (timeCount != null) {
            timeCount.cancel();
        }
    }

    @Override
    public void initView() {
        actionbarTitle.setText(R.string.bank_card);
        addBankCardGetCode.setOnClickListener(this);
        tvCommitWithdraw.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addBankCardGetCode:
                if (TextUtils.isEmpty(etBankCardMobile.getText().toString()) || !CommonUtil.isMobileNO(etBankCardMobile.getText().toString())) {
                    ToastUtils.showShortToast("请输入有效手机号");
                } else {
                    addBankCardGetCode.setClickable(false);
                       /* VolleyManager.volleyPost(UrlEntity.SMS_CODE, VolleyManager.getMap("telephone", etBankCardMobile.getText().toString(), "captcha_id", code, "verify", graphCode.getText().toString()), new VolleyManager.Responses() {
                            @Override
                            public void onResponse(String s, int requestCode) {
                                LoginInfo loginInfo = JsonManager.parseJson(s, LoginInfo.class);
                                if (loginInfo.getError_code() != 0) {
                                    T.show(loginInfo.getError_msg());
                                    addBankCardGetCode.setClickable(true);
                                } else {
                                    T.show(getString(R.string.sms_info));
                                    startTime();
                                }
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                addBankCardGetCode.setClickable(true);
                            }
                        }, 0);*/
                }
                break;
            case R.id.tvCommitWithdraw:
                if (checkUp()) {
                   /* VolleyManager.volleyPost(UrlEntity.ADDBANK, VolleyManager.getMap("name", etBankCardOwner.getText().toString(), "number", etBankCardNO.getText().toString(),
                            "branch", etBankCardBranch.getText().toString(), "telephone", etBankCardMobile.getText().toString(), "telephone_code", etBankCardCode.getText().toString()), new VolleyManager.Responses() {
                        @Override
                        public void onResponse(String s, int requestCode) {
                            if (loading != null) {
                                loading.dismiss();
                            }
                            PublicInfo publicInfo = JsonManager.parseJson(s, PublicInfo.class);
                            if (publicInfo != null) {
                                if (publicInfo.getError_code() == 0) {
                                    T.show("银行卡添加成功");
                                    finish();
                                } else {
                                    T.show(publicInfo.getError_msg());
                                }
                            }
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            if (loading != null) {
                                loading.dismiss();
                            }
                        }
                    }, 0);
                }
                break;*/
                }
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
            timeCount = new TimeCount(60000, 1000, addBankCardGetCode);
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


}
