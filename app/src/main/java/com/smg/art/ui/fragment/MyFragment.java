package com.smg.art.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smg.art.R;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.bean.Apk_UpdateBean;
import com.smg.art.component.AppComponent;
import com.smg.art.presenter.contract.fragment.HomeContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Mervin on 2018/7/24 0024.
 */

public class MyFragment extends BaseFragment implements HomeContract.View {
    @BindView(R.id.mine_head)
    ImageView mineHead;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_id)
    TextView userId;
    @BindView(R.id.head)
    RelativeLayout head;
    @BindView(R.id.check_all)
    TextView checkAll;
    @BindView(R.id.liner_auction)
    LinearLayout linerAuction;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.compete)
    LinearLayout compete;
    @BindView(R.id.for_the_delivery)
    LinearLayout forTheDelivery;
    @BindView(R.id.is_the_delivery)
    LinearLayout isTheDelivery;
    @BindView(R.id.after_sale)
    LinearLayout afterSale;
    @BindView(R.id.memu)
    LinearLayout memu;
    @BindView(R.id.memu_navigate)
    RelativeLayout memuNavigate;
    @BindView(R.id.icon1)
    ImageView icon1;
    @BindView(R.id.my_wallte)
    RelativeLayout myWallte;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.icon2)
    ImageView icon2;
    @BindView(R.id.my_bond)
    RelativeLayout myBond;
    @BindView(R.id.line4)
    View line4;
    @BindView(R.id.icon3)
    ImageView icon3;
    @BindView(R.id.my_collection)
    RelativeLayout myCollection;
    @BindView(R.id.team1)
    LinearLayout team1;
    @BindView(R.id.icon4)
    ImageView icon4;
    @BindView(R.id.realnameauthentication)
    RelativeLayout realnameauthentication;
    @BindView(R.id.line7)
    View line7;
    @BindView(R.id.icon5)
    ImageView icon5;
    @BindView(R.id.setting)
    RelativeLayout setting;
    Unbinder unbinder;

    @Override
    public void ApkUpdateS(Apk_UpdateBean.DataBean dataBean) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void loadData() {
        setState(Constant.STATE_SUCCESS);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.my_fragment;
    }

    @Override
    public void attachView() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
