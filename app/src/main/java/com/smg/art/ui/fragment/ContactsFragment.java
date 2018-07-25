package com.smg.art.ui.fragment;

import com.smg.art.R;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.component.AppComponent;

/**
 * Created by Lenovo on 2018/7/25.
 */

public class ContactsFragment extends BaseFragment {

    @Override
    public void loadData() {
        setState(Constant.STATE_SUCCESS);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_contacts;
    }

    @Override
    public void attachView() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }
}
