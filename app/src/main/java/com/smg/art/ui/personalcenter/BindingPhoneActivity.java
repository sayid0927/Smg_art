package com.smg.art.ui.personalcenter;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.ChangeMobilePhoneBean;
import com.smg.art.bean.PhoneVerifyCodeBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.SendMobilePhoneContract;
import com.smg.art.presenter.impl.activity.ChangeMobilePhonePresenter;
import com.smg.art.utils.CommonUtil;
import com.smg.art.utils.KeyBoardUtils;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.utils.TimeCount;
import com.zhy.autolayout.AutoRelativeLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mervin on 2018/7/26 0026.
 */

public class BindingPhoneActivity extends BaseActivity implements SendMobilePhoneContract.View {
    @Inject
    ChangeMobilePhonePresenter mPresenter;
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
    @BindView(R.id.name)
    LinearLayout name;
    @BindView(R.id.graph_code)
    EditText graphCode;
    @BindView(R.id.comfirm)
    Button comfirm;
    @BindView(R.id.binding)
    TextView binding;
    @BindView(R.id.textview6)
    TextView textview6;
    @BindView(R.id.pic_yzm_code)
    EditText picYzmCode;
    @BindView(R.id.pic_yzm_del)
    ImageView picYzmDel;
    @BindView(R.id.pic_view1)
    View picView1;
    @BindView(R.id.get_iamge)
    ImageView getIamge;
    @BindView(R.id.pic_yzm)
    LinearLayout picYzm;
    private TimeCount timeCount;


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.binging_phone;
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
        actionbarTitle.setText(R.string.change_phone);
        mPresenter.FetchPictureCode();
    }

    @OnClick({R.id.rl_back, R.id.binding, R.id.comfirm, R.id.get_iamge})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                KeyBoardUtils.hiddenKeyboart(this);
                finish();
                break;
            case R.id.binding:
                if (checkUp()) {
                    mPresenter.FetchChangeMobilePhone("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()), "mobilePhone", etContext.getText().toString(), "validteCode", graphCode.getText().toString());
                }
                break;
            case R.id.comfirm:
                if (TextUtils.isEmpty(etContext.getText().toString()) || !CommonUtil.isMobileNO(etContext.getText().toString())) {
                    ToastUtils.showShortToast(R.string.input_correct_phone);
                } else {
                    if (TextUtils.isEmpty(picYzmCode.getText().toString())) {
                        ToastUtils.showShortToast("请输入图形验证码");
                    } else {
                        comfirm.setClickable(false);
                        mPresenter.FetchPhoneVerifyCode("mobilePhone", etContext.getText().toString().trim(), "pictureCode", picYzmCode.getText().toString());
                    }
                }
                break;
            case R.id.get_iamge:
                mPresenter.FetchPictureCode();
                break;
        }
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

        if (TextUtils.isEmpty(graphCode.getText().toString())) {
            ToastUtils.showShortToast("请输入验证码");
            return false;
        }

        return true;
    }

    @Override
    public void FetchChangeMobilePhoneSuccess(ChangeMobilePhoneBean changeMobilePhoneBean) {
        if (changeMobilePhoneBean.getStatus() == 1) {
            ToastUtils.showShortToast("手机号修改成功");
            finish();
        } else {
            ToastUtils.showShortToast(changeMobilePhoneBean.getMsg());
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


    /**
     * 开启倒计时
     */
    public void startTime() {
        if (timeCount == null) {
            timeCount = new TimeCount(60000, 1000, comfirm);
        }
        timeCount.start(); //倒计时后重新获取
    }

    @Override
    public Button btn() {
        return null;
    }

    @Override
    public ImageView iv() {
        return getIamge;
    }

    @Override
    public void showError(String message) {

    }


}
