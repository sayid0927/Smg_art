package com.smg.art.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.smg.art.bean.ServiceBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.fragment.MyFragmentContract;
import com.smg.art.presenter.impl.fragment.MyFragmentPresenter;
import com.smg.art.ui.activity.MainActivity;
import com.smg.art.ui.personalcenter.AuthenticationActivity;
import com.smg.art.ui.personalcenter.CashDepositActivity;
import com.smg.art.ui.personalcenter.ComplaintActivity;
import com.smg.art.ui.personalcenter.MyCollectionActivity;
import com.smg.art.ui.personalcenter.MyOrderActivity;
import com.smg.art.ui.personalcenter.MyWalletActivity;
import com.smg.art.ui.personalcenter.SettingActivity;
import com.smg.art.ui.personalcenter.address.AddressListActivity;
import com.smg.art.utils.CallPhone;
import com.smg.art.utils.GlideUtils;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.view.RoundImageView;

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
    RoundImageView mineHead;
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
    /*  @BindView(R.id.after_sale)
      LinearLayout afterSale;*/
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
    LinearLayout setting;
    @BindView(R.id.consultation_hotline)
    RelativeLayout consultation_hotline;
    @BindView(R.id.phone)
    TextView phone;


    Intent intent;
    int isReal = 0;


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


    private void getdata() {
        mPresenter.FetchPersonalCenter("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()));
        mPresenter.FetchService();
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

 /*   @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mineHead != null && isVisibleToUser) {
            getdata();
        }
    }*/

   /* @Override
    public void onResume() {
        super.onResume();
        getdata();
    }*/

    @Override
    protected void initView(Bundle bundle) {
        super.initView(bundle);
        getdata();
    }

    @OnClick({R.id.check_all, R.id.compete, R.id.for_the_delivery, R.id.is_the_delivery, R.id.my_wallte, R.id.my_bond, R.id.my_collection, R.id.realnameauthentication, R.id.setting, R.id.consultation_hotline,R.id.rl_address,R.id.rl_complaint})
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
      /*      case R.id.after_sale:
                intent = new Intent(getActivity(), MyOrderActivity.class);
                intent.putExtra("index", 4);
                MainActivity.mainActivity.startActivityIn(intent, getActivity());
                break;*/
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
                if (isReal == 1) {
                    ToastUtils.showShortToast("您已认证");
                } else {
                    intent = new Intent(getActivity(), AuthenticationActivity.class);
                    MainActivity.mainActivity.startActivityIn(intent, getActivity());
                }
                break;
            case R.id.setting:
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivityForResult(intent, 10);
                //  MainActivity.mainActivity.startActivityIn(intent, getActivity());
                break;
            case R.id.consultation_hotline:
                if (!TextUtils.isEmpty(phone.getText().toString())) {
                    CallPhone.diallPhone(getActivity(), phone.getText().toString());
                }
                break;
            case R.id.rl_address:  // 收货地址
                intent = new Intent(getActivity(), AddressListActivity.class);
                MainActivity.mainActivity.startActivityIn(intent, getActivity());
                break;

            case R.id.rl_complaint:  // 售后投诉
                intent = new Intent(getActivity(), ComplaintActivity.class);
                MainActivity.mainActivity.startActivityIn(intent, getActivity());
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            getdata();
        }
    }


    @Override
    public void FetchPersonalCenterSuccess(PersonalCenterBean announcementAuctionListBean) {
        if (announcementAuctionListBean.getStatus() == 1) {
            userName.setText(announcementAuctionListBean.getData().getMemberName());
            userId.setText("ID: " + announcementAuctionListBean.getData().getMemberId());
            GlideUtils.load(getActivity(), Constant.BaseImgUrl + LocalAppConfigUtil.getInstance().getHeadImg(), mineHead);
            //   GlideCommonUtils.showHead(getActivity(), announcementAuctionListBean.getData().getHeadImg(), );
            isReal = announcementAuctionListBean.getData().getIsReal();
        } else {
            ToastUtils.showShortToast(announcementAuctionListBean.getMsg());
        }
    }

    @Override
    public void FetchServiceSuccess(ServiceBean serviceBean) {
        if (serviceBean.getStatus() == 1) {
            phone.setText(serviceBean.getData().getHotline());
        } else {
            ToastUtils.showShortToast(serviceBean.getMsg());
        }
    }
}
