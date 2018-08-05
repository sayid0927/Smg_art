package com.smg.art.ui.personalcenter;

import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.ChangeNickBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.ChangeNickContract;
import com.smg.art.presenter.impl.activity.ChangeNickNamePresenter;
import com.smg.art.utils.KeyBoardUtils;
import com.smg.art.utils.LocalAppConfigUtil;
import com.zhy.autolayout.AutoRelativeLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

/**
 * Created by Mervin on 2018/7/26 0026.
 */

public class ChangeNickNameActivity extends BaseActivity implements ChangeNickContract.View {
    @Inject
    ChangeNickNamePresenter mPresenter;
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
    @BindView(R.id.save)
    TextView save;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.change_name;
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
        actionbarTitle.setText(R.string.change_nickname);
    }

    @OnClick({R.id.rl_back, R.id.save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                KeyBoardUtils.hiddenKeyboart(this);
                finish();
                break;
            case R.id.save:
                if (TextUtils.isEmpty(editText.getText().toString())) {
                    ToastUtils.showShortToast("请输入昵称");
                } else {
                    mPresenter.FetchChangeNick("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()), "memberName", editText.getText().toString());
                    LocalAppConfigUtil.getInstance().setRongUserName(editText.getText().toString());
                    RongIM.getInstance().refreshUserInfoCache(new UserInfo(
                            LocalAppConfigUtil.getInstance().getRongUserId(),LocalAppConfigUtil.getInstance().getRongUserName(), Uri.parse(LocalAppConfigUtil.getInstance().getRongUserHeadImg())
                    ));
                }
                break;
        }
    }

    @Override
    public void FetchChangeNickSuccess(ChangeNickBean changeNickBean) {
        if (changeNickBean.getStatus() == 1) {
            ToastUtils.showShortToast("修改成功");
            finish();
        } else {
            ToastUtils.showShortToast(changeNickBean.getMsg());
        }
    }

    @Override
    public void showError(String message) {

    }
}
