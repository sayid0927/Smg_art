package com.smg.art.ui.fragment;

import android.content.Intent;
import android.view.View;
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
import com.smg.art.ui.activity.MainActivity;
import com.smg.art.ui.personalcenter.CashDepositActivity;
import com.smg.art.ui.personalcenter.MyWalletActivity;
import com.smg.art.ui.personalcenter.SettingActivity;

import butterknife.BindView;
import butterknife.OnClick;

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
    Intent intent;

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

    @OnClick({R.id.my_wallte, R.id.my_bond, R.id.my_collection, R.id.realnameauthentication, R.id.setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_wallte:
                intent = new Intent(getActivity(), MyWalletActivity.class);
                MainActivity.mainActivity.startActivityIn(intent, getActivity());
                break;
            case R.id.my_bond:
                intent = new Intent(getActivity(), CashDepositActivity.class);
                MainActivity.mainActivity.startActivityIn(intent, getActivity());
                break;
            case R.id.my_collection:
                // startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.realnameauthentication:
                // startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.setting:
                intent = new Intent(getActivity(), SettingActivity.class);
                MainActivity.mainActivity.startActivityIn(intent, getActivity());
                break;

        }
    }


}
