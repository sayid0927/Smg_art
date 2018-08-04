package com.smg.art.ui.personalcenter;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.ChangePassWordBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.ChangePassWordContract;
import com.smg.art.presenter.impl.activity.ChangePassWordPresenter;
import com.smg.art.utils.KeyBoardUtils;
import com.smg.art.utils.LocalAppConfigUtil;
import com.zhy.autolayout.AutoRelativeLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mervin on 2018/7/26 0026.
 */

public class ChangePasswordActivity extends BaseActivity implements ChangePassWordContract.View {
    @Inject
    ChangePassWordPresenter mPresenter;

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
    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.name)
    LinearLayout name;
    @BindView(R.id.edit_textpwd1)
    EditText editTextpwd1;
    @BindView(R.id.edit_textpwd2)
    EditText editTextpwd2;
    @BindView(R.id.save)
    TextView save;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.change_password;
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
        actionbarTitle.setText(R.string.change_password);
    }

    @OnClick({R.id.rl_back, R.id.save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                KeyBoardUtils.hiddenKeyboart(this);
                finish();
                break;
            case R.id.save:
                if (checkUp()) {
                    mPresenter.FetchChangePassWord("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()), "oldLoginPwd", editText.getText().toString(),
                            "newLoginPwd", editTextpwd1.getText().toString());
                }
                break;
        }
    }

    /**
     * 检查输入是否有错
     *
     * @return
     */
    private boolean checkUp() {

        if (TextUtils.isEmpty(editText.getText().toString()) || editText.getText().length() < 6) {
            ToastUtils.showShortToast("请输入至少6位旧密码");
            return false;
        }

        if (TextUtils.isEmpty(editTextpwd1.getText().toString()) || editTextpwd1.getText().length() < 6) {
            ToastUtils.showShortToast("请输入至少6位新密码");
            return false;
        }

        if (TextUtils.isEmpty(editTextpwd2.getText().toString()) || editTextpwd2.getText().length() < 6) {
            ToastUtils.showShortToast("请输入至少6位确认密码");
            return false;
        }

        if (!(editTextpwd1.getText().toString()).equals(editTextpwd2.getText().toString())) {
            ToastUtils.showShortToast("两次密码不一样");
            return false;
        }


        return true;
    }

    @Override
    public void FetchChangePassWordSuccess(ChangePassWordBean changePassWordBean) {
        if (changePassWordBean.getStatus() == 1) {
            ToastUtils.showShortToast("修改成功");
            finish();
        } else {
            ToastUtils.showShortToast(changePassWordBean.getMsg());
        }
    }

    @Override
    public void showError(String message) {

    }
}
