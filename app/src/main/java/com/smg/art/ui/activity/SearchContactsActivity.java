package com.smg.art.ui.activity;

import android.view.View;

import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.component.AppComponent;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchContactsActivity extends BaseActivity {


    @BindView(R.id.ll_back)
    AutoRelativeLayout llBack;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search_contacts;
    }

    @Override
    public void attachView() {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void initView() {

    }

    @OnClick({R.id.ll_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }

}
