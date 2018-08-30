package com.smg.art.ui.personalcenter;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.EmptyUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.ComfirmOrderBean;
import com.smg.art.bean.EventBusAddressBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.ComfirmOrderContract;
import com.smg.art.presenter.impl.activity.ComfirmOrderPresente;
import com.smg.art.ui.personalcenter.address.AddressListActivity;
import com.smg.art.utils.CommonDpUtils;
import com.smg.art.utils.GlideCommonUtils;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.view.NumberDialog;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lurs on 2018/8/23 0023.
 */

public class ConfirmOrderActivity extends BaseActivity implements ComfirmOrderContract.View {
    @Inject
    ComfirmOrderPresente mPresenter;
    int id;
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
    @BindView(R.id.courier_name)
    TextView courierName;
    @BindView(R.id.courier_phone)
    TextView courierPhone;
    @BindView(R.id.courier_info)
    LinearLayout courierInfo;
    @BindView(R.id.confirmed_pic)
    ImageView confirmedPic;
    @BindView(R.id.confirmed_shop_name)
    TextView confirmedShopName;
    @BindView(R.id.confirmed_price)
    TextView confirmedPrice;
    @BindView(R.id.confirm_receipt)
    TextView confirmReceipt;
    @BindView(R.id.adress)
    TextView adress;
    @BindView(R.id.shop_name)
    TextView shopName;
    @BindView(R.id.deposit_price)
    TextView depositPrice;
    @BindView(R.id.need_pay)
    TextView needPay;
    @BindView(R.id.choose_address)
    LinearLayout choose_address;

    private String addressId;
    private String payAmount;


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.confirm_order;
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

        id = getIntent().getIntExtra("id", 0);
        actionbarTitle.setText("确认订单");
        mPresenter.comfirmOrder("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()), "auctionId", String.valueOf(id));

    }

    @Override
    public void comfirmOrderSuccess(ComfirmOrderBean comfirmOrderBean) {
        if (comfirmOrderBean.getStatus() == 1) {
            if (comfirmOrderBean.getData().getAddress() != null) {
                this.addressId = String.valueOf(comfirmOrderBean.getData().getAddress().getId());
                choose_address.setVisibility(View.GONE);
                courierInfo.setVisibility(View.VISIBLE);
                courierName.setText(comfirmOrderBean.getData().getAddress().getDeliveryName());
                courierPhone.setText(comfirmOrderBean.getData().getAddress().getDeliveryPhone());
                adress.setText("收货地址:" + comfirmOrderBean.getData().getAddress().getProvinceName() + comfirmOrderBean.getData().getAddress().getCityName() + comfirmOrderBean.getData().getAddress().getCountyName() + comfirmOrderBean.getData().getAddress().getAdress());

            } else {
                choose_address.setVisibility(View.VISIBLE);
                courierInfo.setVisibility(View.GONE);
            }

             if(EmptyUtils.isNotEmpty(comfirmOrderBean.getData().getAuction())){
                 shopName.setText(comfirmOrderBean.getData().getAuction().getSellerMemberName());
                 if (!TextUtils.isEmpty(comfirmOrderBean.getData().getAuction().getPictureUrl())) {
                     String[] pic = comfirmOrderBean.getData().getAuction().getPictureUrl().split(",");
                     GlideCommonUtils.showSquarePic(this, pic[0], confirmedPic);
                 } else {
                     confirmedPic.setImageResource(R.mipmap.defaut_square);
                 }

                 confirmedShopName.setText(comfirmOrderBean.getData().getAuction().getActionName());
                 confirmedPrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", comfirmOrderBean.getData().getAuction().getNowprice()))));
                 depositPrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", comfirmOrderBean.getData().getDkPay()))));
                 needPay.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f",comfirmOrderBean.getData().getZfPay()))));
                 this.payAmount = String.valueOf(comfirmOrderBean.getData().getZfPay());
             }
        } else {
            ToastUtils.showShortToast(comfirmOrderBean.getMsg());
        }
    }


    /**
     * 确认支付成功
     */
    @Override
    public void FetchPayBuyerGoodsSuccess(ComfirmOrderBean comfirmOrderBean) {
        ToastUtils.showLongToast("支付成功");
        Intent intent = new Intent(ConfirmOrderActivity.this, OrderdetailActivity.class);
        intent.putExtra("id", id);
        startActivityIn(intent, ConfirmOrderActivity.this);
       this.finish();
    }

    @Override
    public void showError(String message) {

    }

    @OnClick({R.id.courier_info, R.id.rl_back, R.id.confirm_receipt,R.id.choose_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.courier_info:
                Intent intent = new Intent(ConfirmOrderActivity.this, AddressListActivity.class);
                intent.putExtra("confirm", 1);
                startActivityIn(intent, ConfirmOrderActivity.this);
                break;
            case R.id.choose_address:
                Intent intent2 = new Intent(ConfirmOrderActivity.this, AddressListActivity.class);
                intent2.putExtra("confirm", 1);
                startActivityIn(intent2, ConfirmOrderActivity.this);
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.confirm_receipt: //确认支付

//                if(EmptyUtils.isEmpty(addressId)){
//                    ToastUtils.showLongToast("请选择地址");
//                    return;
//                }

                final NumberDialog numberDialog = new NumberDialog(this);
                numberDialog.show();
                numberDialog.OnbtAuctionClick(new NumberDialog.OnbtAuctionClick() {
                    @Override
                    public void OnbtAuctionClick(String pwd) {
                        if (EmptyUtils.isNotEmpty(pwd)) {
                            mPresenter.FetchPayBuyerGoods("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                                    "auctionId", String.valueOf(id), "addressId", addressId, "payAmount", payAmount, "pwd", pwd);
                            numberDialog.dismiss();
                        }
                    }
                });
                break;
        }
    }

    @Subscribe
    public void getEventBus(EventBusAddressBean eventBusAddressBean) {
        if (eventBusAddressBean.getType() == 1) {
            this.addressId = String.valueOf(eventBusAddressBean.getId());
            courierName.setText(eventBusAddressBean.getDeliveryName());
            courierPhone.setText(eventBusAddressBean.getDeliveryPhone());
            adress.setText(eventBusAddressBean.getProvinceName() + eventBusAddressBean.getCityName() +
                    eventBusAddressBean.getCountyName() + eventBusAddressBean.getAdress());

        }
    }
}
