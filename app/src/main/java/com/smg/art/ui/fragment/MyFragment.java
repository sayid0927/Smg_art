package com.smg.art.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.R;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.bean.PersonalCenterBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.photo.CircleImageView;
import com.smg.art.presenter.contract.fragment.MyFragmentContract;
import com.smg.art.presenter.impl.fragment.MyFragmentPresenter;
import com.smg.art.ui.activity.MainActivity;
import com.smg.art.ui.personalcenter.CashDepositActivity;
import com.smg.art.ui.personalcenter.MyCollectionActivity;
import com.smg.art.ui.personalcenter.MyOrderActivity;
import com.smg.art.ui.personalcenter.MyWalletActivity;
import com.smg.art.ui.personalcenter.SettingActivity;
import com.smg.art.utils.GlideCommonUtils;
import com.smg.art.utils.LocalAppConfigUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mervin on 2018/7/24 0024.
 */

public class MyFragment extends BaseFragment implements MyFragmentContract.View {
    @Inject
    MyFragmentPresenter mPresenter;
    @BindView(R.id.mine_head)
    CircleImageView mineHead;
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
        mPresenter.attachView(this, getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        getdata();
    }

    private void getdata() {
        mPresenter.FetchPersonalCenter("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()));
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @OnClick({R.id.check_all, R.id.compete, R.id.for_the_delivery, R.id.is_the_delivery, R.id.after_sale, R.id.my_wallte, R.id.my_bond, R.id.my_collection, R.id.realnameauthentication, R.id.setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.check_all:
                intent = new Intent(getActivity(), MyOrderActivity.class);
                intent.putExtra("index", 0);
                MainActivity.mainActivity.startActivityIn(intent, getActivity());
                break;
            case R.id.compete:
                intent = new Intent(getActivity(), MyOrderActivity.class);
                intent.putExtra("index", 1);
                MainActivity.mainActivity.startActivityIn(intent, getActivity());
                break;
            case R.id.for_the_delivery:
                intent = new Intent(getActivity(), MyOrderActivity.class);
                intent.putExtra("index", 2);
                MainActivity.mainActivity.startActivityIn(intent, getActivity());
                break;
            case R.id.is_the_delivery:
                intent = new Intent(getActivity(), MyOrderActivity.class);
                intent.putExtra("index", 3);
                MainActivity.mainActivity.startActivityIn(intent, getActivity());
                break;
            case R.id.after_sale:
                intent = new Intent(getActivity(), MyOrderActivity.class);
                intent.putExtra("index", 4);
                MainActivity.mainActivity.startActivityIn(intent, getActivity());
                break;
            case R.id.my_wallte:
                intent = new Intent(getActivity(), MyWalletActivity.class);
                MainActivity.mainActivity.startActivityIn(intent, getActivity());
                break;
            case R.id.my_bond:
                intent = new Intent(getActivity(), CashDepositActivity.class);
                MainActivity.mainActivity.startActivityIn(intent, getActivity());
                break;
            case R.id.my_collection:
                intent = new Intent(getActivity(), MyCollectionActivity.class);
                MainActivity.mainActivity.startActivityIn(intent, getActivity());
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


    @Override
    public void FetchPersonalCenterSuccess(PersonalCenterBean announcementAuctionListBean) {
        if (announcementAuctionListBean.getStatus() == 1) {
            userName.setText(announcementAuctionListBean.getData().getMemberName());
            userId.setText("ID: " + announcementAuctionListBean.getData().getMemberNo());
            GlideCommonUtils.showHead(getActivity(), announcementAuctionListBean.getData().getHeadImg(), mineHead);
        } else {
            ToastUtils.showShortToast(announcementAuctionListBean.getMsg());
        }
    }
}
