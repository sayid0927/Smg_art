package com.smg.art.ui.activity;

import android.content.Intent;
import android.os.Handler;

import com.jaeger.library.StatusBarUtil;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.component.AppComponent;
import com.smg.art.ui.login.LoginActivity;
import com.smg.art.utils.LocalAppConfigUtil;

import java.util.HashMap;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

/**
 * Created by Mervin on 2018/7/24 0024.
 */

public class GuideActivity extends BaseActivity {
    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.guide;
    }

    @Override
    public void attachView() {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void initView() {
        StatusBarUtil.setTransparentForImageViewInFragment(GuideActivity.this, null);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (LocalAppConfigUtil.getInstance().isLogin()) {

                    Intent intent = new Intent();
                    intent.setClass(GuideActivity.this, MainActivity.class);
                    finish();
                    startActivityIn(intent,GuideActivity.this);

                } else {
                    Intent intent = new Intent();
                    intent.setClass(GuideActivity.this, LoginActivity.class);
                    finish();
                    startActivityIn(intent,GuideActivity.this);

                }

            }
        }, 3000);
    }
}
