package com.smg.art.ui.personalcenter;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.EmptyUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.base.BaseApplication;
import com.smg.art.bean.OderDetailBean;
import com.smg.art.bean.RefundBean;
import com.smg.art.bean.ServiceBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.OderdetailContract;
import com.smg.art.presenter.impl.activity.OrderDetailPresenter;
import com.smg.art.ui.activity.AuctionDeatilActivity;
import com.smg.art.utils.CommonDpUtils;
import com.smg.art.utils.DateFormatUtil;
import com.smg.art.utils.GlideCommonUtils;
import com.smg.art.utils.KeyBoardUtils;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.utils.TimeTools;
import com.smg.art.view.NoScrollGridView;
import com.smg.art.view.NumberDialog;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mervin on 2018/8/8 0008.
 */

public class OrderdetailActivity extends BaseActivity implements OderdetailContract.View {
    @Inject
    OrderDetailPresenter mPresenter;

    int id = 0;//投诉ID或者商品id
    int type = 0;//区分投诉还是不是投诉
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
    @BindView(R.id.failure_pic)
    ImageView failurePic;
    @BindView(R.id.failure_shop_name)
    TextView failureShopName;
    @BindView(R.id.failure_price)
    TextView failurePrice;
    @BindView(R.id.failure_oder_num)
    TextView failureOderNum;
    @BindView(R.id.failure_order_time)
    TextView failureOrderTime;
    @BindView(R.id.failure_refund_time)
    TextView failureRefundTime;
    @BindView(R.id.bidding_failure)
    LinearLayout biddingFailure;
    @BindView(R.id.obligation_tv_hour)
    TextView obligationTvHour;
    @BindView(R.id.obligation_tv_min)
    TextView obligationTvMin;
    @BindView(R.id.obligation_tv_second)
    TextView obligationTvSecond;
    @BindView(R.id.obligation_time)
    LinearLayout obligationTime;
    @BindView(R.id.obligation_pic)
    ImageView obligationPic;
    @BindView(R.id.obligation_shop_name)
    TextView obligationShopName;
    @BindView(R.id.obligation_deposit)
    TextView obligationDeposit;
    @BindView(R.id.obligation_price)
    TextView obligationPrice;
    @BindView(R.id.obligation_oder_num)
    TextView obligationOderNum;
    @BindView(R.id.obligation_order_time)
    TextView obligationOrderTime;
    @BindView(R.id.go_obligation)
    TextView goObligation;
    @BindView(R.id.obligation)
    LinearLayout obligation;
    @BindView(R.id.deliver_pic)
    ImageView deliverPic;
    @BindView(R.id.deliver_shop_name)
    TextView deliverShopName;
    @BindView(R.id.deliver_onSale_price)
    TextView deliverOnSalePrice;
    @BindView(R.id.delivery_oder_num)
    TextView deliveryOderNum;
    @BindView(R.id.delivery_order_time)
    TextView deliveryOrderTime;
    @BindView(R.id.delivery_time)
    TextView deliveryTime;
    @BindView(R.id.logistics_info)
    LinearLayout logisticsInfo;
    @BindView(R.id.to_send_the_goods)
    LinearLayout toSendTheGoods;
    @BindView(R.id.receiving_pic)
    ImageView receivingPic;
    @BindView(R.id.receiving_shop_name)
    TextView receivingShopName;
    @BindView(R.id.receiving_price)
    TextView receivingPrice;
    @BindView(R.id.receiving_oder_num)
    TextView receivingOderNum;
    @BindView(R.id.receiving_order_time)
    TextView receivingOrderTime;
    @BindView(R.id.receiving_time)
    TextView receivingTime;
    @BindView(R.id.receiving_deliver_time)
    TextView receivingDeliverTime;
    @BindView(R.id.receiving_logistics_info)
    LinearLayout receivingLogisticsInfo;
    @BindView(R.id.confirm_receipt)
    TextView confirmReceipt;
    @BindView(R.id.receiving)
    LinearLayout receiving;
    @BindView(R.id.confirmed_pic)
    ImageView confirmedPic;
    @BindView(R.id.confirmed_shop_name)
    TextView confirmedShopName;
    @BindView(R.id.confirmed_price)
    TextView confirmedPrice;
    @BindView(R.id.confirmed_oder_num)
    TextView confirmedOderNum;
    @BindView(R.id.confirmed_order_time)
    TextView confirmedOrderTime;
    @BindView(R.id.confirmed_time)
    TextView confirmedTime;
    @BindView(R.id.confirmed_deliver_time)
    TextView confirmedDeliverTime;
    @BindView(R.id.confirmed_final_time)
    TextView confirmedFinalTime;
    @BindView(R.id.confirmed_logistics_info)
    LinearLayout confirmedLogisticsInfo;
    @BindView(R.id.confirmed)
    LinearLayout confirmed;
    @BindView(R.id.overdue_pic)
    ImageView overduePic;
    @BindView(R.id.overdue_shop_name)
    TextView overdueShopName;
    @BindView(R.id.overdue_deposit)
    TextView overdueDeposit;
    @BindView(R.id.overdue_price)
    TextView overduePrice;
    @BindView(R.id.overdue_oder_num)
    TextView overdueOderNum;
    @BindView(R.id.overdue_order_time)
    TextView overdueOrderTime;
    @BindView(R.id.overdue_time)
    TextView overdueTime;
    @BindView(R.id.overdue_btn)
    TextView overdueBtn;
    @BindView(R.id.overdue)
    LinearLayout overdue;
    @BindView(R.id.tv_hour)
    TextView tvHour;
    @BindView(R.id.tv_min)
    TextView tvMin;
    @BindView(R.id.tv_second)
    TextView tvSecond;
    @BindView(R.id.time)
    LinearLayout time;
    @BindView(R.id.top_price)
    TextView topPrice;
    @BindView(R.id.onSale_pic)
    ImageView onSalePic;
    @BindView(R.id.onSale_shop_name)
    TextView onSaleShopName;
    @BindView(R.id.onSale_deposit)
    TextView onSaleDeposit;
    @BindView(R.id.onSale_price)
    TextView onSalePrice;
    @BindView(R.id.onSale_oder_num)
    TextView onSaleOderNum;
    @BindView(R.id.onSale_order_time)
    TextView onSaleOrderTime;
    @BindView(R.id.additional_bid)
    TextView additionalBid;
    @BindView(R.id.onSale)
    LinearLayout onSale;
    @BindView(R.id.complaint_pic)
    ImageView complaintPic;
    @BindView(R.id.complaint_shop_name)
    TextView complaintShopName;
    @BindView(R.id.complaint_oder_num)
    TextView complaintOderNum;
    @BindView(R.id.complaint_order_time)
    TextView complaintOrderTime;
    @BindView(R.id.complaint_delivery_time)
    TextView complaintDeliveryTime;
    @BindView(R.id.complaint_time)
    TextView complaintTime;
    @BindView(R.id.complaint_content)
    TextView complaintContent;
    @BindView(R.id.complaint_dispose)
    TextView complaintDispose;
    @BindView(R.id.ll_dispose)
    LinearLayout llDispose;
    @BindView(R.id.girdview)
    NoScrollGridView girdview;
    @BindView(R.id.refund_success)
    LinearLayout refundSuccess;
    CountDownTimer timer;
    OderDetailBean.DataBean orderDataBean;
    private List<String> images = new ArrayList<>();

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.order_detail;
    }

    @Override
    public void attachView() {
        mPresenter.attachView(this, this);
    }

    @Override
    public void detachView() {
        mPresenter.detachView();
        if (timer != null) {
            timerCancel();
        }
    }

    @Override
    public void initView() {
        id = getIntent().getIntExtra("id", 0);
        getdata();
    }

    private void getdata() {
        actionbarTitle.setText(R.string.order_detail);
        mPresenter.FetchQueryAuctionInfoCenter("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()), "auctionId", String.valueOf(id));
    /*    if (type == 1) {//投诉接口
            actionbarTitle.setText(R.string.complaint_detail);
            mPresenter.FetchRefundCenter("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()), "complainId", String.valueOf(id));
        } else {

        }*/
    }

    @OnClick({R.id.rl_back, R.id.additional_bid, R.id.logistics_info, R.id.go_obligation, R.id.receiving_logistics_info, R.id.confirm_receipt, R.id.confirmed_logistics_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                KeyBoardUtils.hiddenKeyboart(this);
                finish();
                break;
            case R.id.additional_bid:
                Intent i = new Intent(this, AuctionDeatilActivity.class);
                i.putExtra("type", 2);
                i.putExtra("id", orderDataBean.getId());
                startActivityIn(i, this);
                break;
            case R.id.logistics_info://待发货查看物流
                Intent intent1 = new Intent(this, LogisticsInformationActivity.class);
                intent1.putExtra("id", orderDataBean.getId());
                startActivityIn(intent1, this);
                break;

            case R.id.go_obligation://去付款

                Intent intent4 = new Intent(this, ConfirmOrderActivity.class);
                intent4.putExtra("id", orderDataBean.getId());
                startActivityIn(intent4, this);

                break;
            case R.id.receiving_logistics_info://待收货查看物流
                Intent intent2 = new Intent(this, LogisticsInformationActivity.class);
                intent2.putExtra("id", orderDataBean.getId());
                startActivityIn(intent2, this);
                break;
            case R.id.confirm_receipt://确认收货

                final NumberDialog numberDialog = new NumberDialog(this);
                numberDialog.show();
                numberDialog.OnbtAuctionClick(new NumberDialog.OnbtAuctionClick() {
                    @Override
                    public void OnbtAuctionClick(String pwd) {
                        if (EmptyUtils.isNotEmpty(pwd)) {
                            mPresenter.FetchConfirmBuyerGoods("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                                    "auctionId",String.valueOf(id),"pwd",pwd);
                            numberDialog.dismiss();
                        }
                    }
                });

                break;
            case R.id.confirmed_logistics_info://已确认查看物流
                Intent intent3 = new Intent(this, LogisticsInformationActivity.class);
                intent3.putExtra("id", orderDataBean.getId());
                startActivityIn(intent3, this);
                break;
        }
    }

    @Override
    public void FetchQueryAuctionInfoSuccess(OderDetailBean oderDetailBean) {
        if (oderDetailBean.getStatus() == 1) {
            setData(oderDetailBean);
        } else {
            ToastUtils.showShortToast(oderDetailBean.getMsg());
        }
    }

    @Override
    public void FetchRefundSuccess(RefundBean refundBean) {

    }

    /**
     * 确认收货
     */
    @Override
    public void FetchConfirmBuyerGoodsSuccess(ServiceBean serviceBean) {

    }

    private void setData(OderDetailBean oderDetailBean) {
        orderDataBean = oderDetailBean.getData();
        if (!TextUtils.isEmpty(orderDataBean.getStatus())) {
            if (("4").equals(orderDataBean.getStatus())) {//参拍中
                onSale.setVisibility(View.VISIBLE);//参拍中
                biddingFailure.setVisibility(View.GONE);//竞拍失败
                obligation.setVisibility(View.GONE);//待付款
                toSendTheGoods.setVisibility(View.GONE);//待发货
                receiving.setVisibility(View.GONE);//待收货
                confirmed.setVisibility(View.GONE);//已确认
                overdue.setVisibility(View.GONE);//已逾期
                if (orderDataBean.getSysDate() != null) {
                    long countTime = orderDataBean.getEndTime() - orderDataBean.getSysDate();
                    if (countTime > 0) {
                        countDown(countTime);
                        timer.start();
                    }
                    if (!TextUtils.isEmpty(orderDataBean.getPictureUrl())) {
                        String[] pic = orderDataBean.getPictureUrl().split(",");
                        GlideCommonUtils.showSquarePic(this, pic[0], onSalePic);
                    } else {
                        onSalePic.setImageResource(R.mipmap.defaut_square);
                    }
                    topPrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", orderDataBean.getNowprice()))));
                    onSaleShopName.setText(orderDataBean.getActionName());
                    onSalePrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", orderDataBean.getNowprice()))));
                    //交易单号
                    onSaleOderNum.setText(orderDataBean.getBidNo());
                    //下单时间
                    onSaleOrderTime.setText(orderDataBean.getCreateTime());
                } else {
                    long time = System.currentTimeMillis();
                    if (time < orderDataBean.getEndTime()) {
                        long countTime = orderDataBean.getEndTime() - time;
                        if (countTime > 0) {
                            countDown(countTime);
                            timer.start();
                        }
                        if (!TextUtils.isEmpty(orderDataBean.getPictureUrl())) {
                            String[] pic = orderDataBean.getPictureUrl().split(",");
                            GlideCommonUtils.showSquarePic(this, pic[0], onSalePic);
                        } else {
                            onSalePic.setImageResource(R.mipmap.defaut_square);
                        }
                        onSaleShopName.setText(orderDataBean.getActionName());
                        onSalePrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", orderDataBean.getNowprice()))));
                        topPrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", orderDataBean.getMemberNowPrice()))));
                        //交易单号
                        onSaleOderNum.setText(orderDataBean.getBidNo());
                        //下单时间
                        onSaleOrderTime.setText(orderDataBean.getCreateTime());
                    }
                }
            } else if (Integer.parseInt(orderDataBean.getStatus()) >= 5) {//已获拍或 未获拍  buyerMemberId等于用户ID为已获拍。反之
                if (!TextUtils.isEmpty(orderDataBean.getOrderStatus())) {
                    switch (orderDataBean.getOrderStatus()) {
                        case "1"://待支付
                            onSale.setVisibility(View.GONE);//参拍中
                            biddingFailure.setVisibility(View.GONE);//竞拍失败
                            obligation.setVisibility(View.VISIBLE);//待付款
                            toSendTheGoods.setVisibility(View.GONE);//待发货
                            receiving.setVisibility(View.GONE);//待收货
                            confirmed.setVisibility(View.GONE);//已确认
                            overdue.setVisibility(View.GONE);//已逾期
                            long time;
                            if (orderDataBean.getSysDate() > 0) {
                                time = orderDataBean.getSysDate();
                            } else {
                                time = System.currentTimeMillis();//获取系统时间的10位的时间戳
                            }
                            if (time < orderDataBean.getPaybiddingFinalTime()) {
                                long countTime = orderDataBean.getPaybiddingFinalTime() - orderDataBean.getSysDate();
                                if (countTime > 0) {
                                    countDownPay(countTime);
                                    timer.start();
                                }
                            }
                            if (!TextUtils.isEmpty(orderDataBean.getPictureUrl())) {
                                String[] pic = orderDataBean.getPictureUrl().split(",");
                                GlideCommonUtils.showSquarePic(this, pic[0], obligationPic);
                            } else {
                                obligationPic.setImageResource(R.mipmap.defaut_square);
                            }
                            obligationDeposit.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", orderDataBean.getBuyerEnsureAmount()))));
                            onSaleShopName.setText(orderDataBean.getActionName());
                            onSalePrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", orderDataBean.getNowprice()))));
                            //交易单号
                            obligationOderNum.setText(orderDataBean.getBidNo());
                            //下单时间
                            obligationOrderTime.setText(orderDataBean.getCreateTime());
                            break;
                        case "2"://待发货
                            onSale.setVisibility(View.GONE);//参拍中
                            biddingFailure.setVisibility(View.GONE);//竞拍失败
                            obligation.setVisibility(View.GONE);//待付款
                            toSendTheGoods.setVisibility(View.VISIBLE);//待发货
                            receiving.setVisibility(View.GONE);//待收货
                            confirmed.setVisibility(View.GONE);//已确认
                            overdue.setVisibility(View.GONE);//已逾期
                            if (!TextUtils.isEmpty(orderDataBean.getPictureUrl())) {
                                String[] pic = orderDataBean.getPictureUrl().split(",");
                                GlideCommonUtils.showSquarePic(this, pic[0], deliverPic);
                            } else {
                                deliverPic.setImageResource(R.mipmap.defaut_square);
                            }
                            deliverShopName.setText(orderDataBean.getActionName());
                            deliverOnSalePrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", orderDataBean.getPaybiddingMoney()))));
                            //交易单号
                            deliveryOderNum.setText(orderDataBean.getBidNo());
                            //下单时间
                            deliveryOrderTime.setText(orderDataBean.getCreateTime());
                            //付款时间
                            deliveryTime.setText(orderDataBean.getPaymentTime());
                            break;
                        case "3"://待收货
                            onSale.setVisibility(View.GONE);//参拍中
                            biddingFailure.setVisibility(View.GONE);//竞拍失败
                            obligation.setVisibility(View.GONE);//待付款
                            toSendTheGoods.setVisibility(View.GONE);//待发货
                            receiving.setVisibility(View.VISIBLE);//待收货
                            confirmed.setVisibility(View.GONE);//已确认
                            overdue.setVisibility(View.GONE);//已逾期
                            if (!TextUtils.isEmpty(orderDataBean.getPictureUrl())) {
                                String[] pic = orderDataBean.getPictureUrl().split(",");
                                GlideCommonUtils.showSquarePic(this, pic[0], receivingPic);
                            } else {
                                receivingPic.setImageResource(R.mipmap.defaut_square);
                            }
                            receivingShopName.setText(orderDataBean.getActionName());
                            receivingPrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", orderDataBean.getPaybiddingMoney()))));
                            receivingOderNum.setText(orderDataBean.getBidNo());
                            //下单时间
                            receivingOrderTime.setText(orderDataBean.getCreateTime());
                            //付款时间
                            receivingTime.setText(orderDataBean.getPaymentTime());
                            //发货时间
                            receivingDeliverTime.setText(orderDataBean.getSendOutTime());
                            break;
                        case "4"://已确认
                            onSale.setVisibility(View.GONE);//参拍中
                            biddingFailure.setVisibility(View.GONE);//竞拍失败
                            obligation.setVisibility(View.GONE);//待付款
                            toSendTheGoods.setVisibility(View.GONE);//待发货
                            receiving.setVisibility(View.GONE);//待收货
                            confirmed.setVisibility(View.VISIBLE);//已确认
                            overdue.setVisibility(View.GONE);//已逾期
                            if (!TextUtils.isEmpty(orderDataBean.getPictureUrl())) {
                                String[] pic = orderDataBean.getPictureUrl().split(",");
                                GlideCommonUtils.showSquarePic(this, pic[0], confirmedPic);
                            } else {
                                confirmedPic.setImageResource(R.mipmap.defaut_square);
                            }
                            confirmedShopName.setText(orderDataBean.getActionName());
                            confirmedPrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", orderDataBean.getPaybiddingMoney()))));
                            //交易单号
                            confirmedOderNum.setText(orderDataBean.getBidNo());
                            //下单时间
                            confirmedOrderTime.setText(orderDataBean.getCreateTime());
                            //付款时间
                            confirmedTime.setText(orderDataBean.getPaymentTime());
                            //发货时间
                            confirmedDeliverTime.setText(orderDataBean.getSendOutTime());
                            break;
                        case "5"://超时未确认
                            onSale.setVisibility(View.GONE);//参拍中
                            biddingFailure.setVisibility(View.GONE);//竞拍失败
                            obligation.setVisibility(View.GONE);//待付款
                            toSendTheGoods.setVisibility(View.GONE);//待发货
                            receiving.setVisibility(View.GONE);//待收货
                            confirmed.setVisibility(View.GONE);//已确认
                            overdue.setVisibility(View.VISIBLE);//已逾期
                            if (!TextUtils.isEmpty(orderDataBean.getPictureUrl())) {
                                String[] pic = orderDataBean.getPictureUrl().split(",");
                                GlideCommonUtils.showSquarePic(this, pic[0], overduePic);
                            } else {
                                overduePic.setImageResource(R.mipmap.defaut_square);
                            }
                            overdueShopName.setText(orderDataBean.getActionName());
                            //保证金
                            overdueDeposit.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", orderDataBean.getBuyerEnsureAmount()))));
                            //出的竞拍价
                            overduePrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", orderDataBean.getNowprice()))));
                            //交易单号
                            overdueOderNum.setText(orderDataBean.getBidNo());
                            //下单时间
                            overdueOrderTime.setText(orderDataBean.getCreateTime());
                            //逾期时间
                            overdueTime.setText(DateFormatUtil.forString(orderDataBean.getPaybiddingFinalTime(), "yyyy-MM-dd HH:mm:ss"));
                            break;
                        case "6"://未获拍
                            onSale.setVisibility(View.GONE);//参拍中
                            biddingFailure.setVisibility(View.VISIBLE);//竞拍失败
                            obligation.setVisibility(View.GONE);//待付款
                            toSendTheGoods.setVisibility(View.GONE);//待发货
                            receiving.setVisibility(View.GONE);//待收货
                            confirmed.setVisibility(View.GONE);//已确认
                            overdue.setVisibility(View.GONE);//已逾期
                            if (!TextUtils.isEmpty(orderDataBean.getPictureUrl())) {
                                String[] pic = orderDataBean.getPictureUrl().split(",");
                                GlideCommonUtils.showSquarePic(this, pic[0], failurePic);
                            } else {
                                failurePic.setImageResource(R.mipmap.defaut_square);
                            }
                            failureShopName.setText(orderDataBean.getActionName());
                            failurePrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", orderDataBean.getBuyerEnsureAmount()))));
                            //交易单号
                            failureOderNum.setText(orderDataBean.getBidNo());
                            //下单时间
                            failureOrderTime.setText(orderDataBean.getCreateTime());
                            //退款时间时间
                            failureRefundTime.setText(orderDataBean.getCreateTime());
                            break;
                    }
                }
            }
        }

       /* if (!TextUtils.isEmpty(orderDataBean.getStatus())) {
            if (("6").equals(orderDataBean.getStatus())) {//已交割或投诉
                if (!TextUtils.isEmpty(orderDataBean.getComplainStatus())) {
                    if (orderDataBean.getComplainStatus().equals("1")) {//1代表已投诉

                    } else {//0代表未投诉  或者dataBean.getComplainFlag()

                        if (!TextUtils.isEmpty(orderDataBean.getBuyerMemberId())) {
                            if (orderDataBean.getBuyerMemberId().equals(String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()))) {
                                isTheDelivery.setVisibility(View.VISIBLE);
                                refundSuccess.setVisibility(View.GONE);
                                biddingSuccess.setVisibility(View.GONE);
                                biddingFailure.setVisibility(View.GONE);
                                onSale.setVisibility(View.GONE);
                                if (!TextUtils.isEmpty(orderDataBean.getPictureUrl())) {
                                    String[] pic = orderDataBean.getPictureUrl().split(",");
                                    GlideCommonUtils.showSquarePic(this, pic[0], deliveryPic);
                                } else {
                                    deliveryPic.setImageResource(R.mipmap.defaut_square);
                                }
                                deliveryShopName.setText(orderDataBean.getActionName());
                                deliveryPrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", orderDataBean.getNowprice()))));
                                //交易单号
                                deliveryOderNum.setText(orderDataBean.getBidNo());
                                //下单时间
                                deliveryOrderTime.setText(orderDataBean.getCreateTime());
                                //交割时间
                                deliveryTime.setText(DateFormatUtil.getFormatDate(orderDataBean.getDeliveryTime(), "yyyy-MM-dd HH:mm:ss"));
                            } else {
                                //竞拍失败详情
                                auctionFalse();
                            }
                        }
                    }
                } else {
                    *//*viewHolder.auction.setVisibility(View.GONE);
                    viewHolder.complaint.setVisibility(View.GONE);*//*
                }

            } else if (("5").equals(orderDataBean.getStatus())) {//待交割
                if (!TextUtils.isEmpty(orderDataBean.getBuyerMemberId())) {
                    if (orderDataBean.getBuyerMemberId().equals(String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()))) {
                        biddingSuccess.setVisibility(View.VISIBLE);
                        refundSuccess.setVisibility(View.GONE);
                        biddingFailure.setVisibility(View.GONE);
                        isTheDelivery.setVisibility(View.GONE);
                        onSale.setVisibility(View.GONE);
                        if (!TextUtils.isEmpty(orderDataBean.getPictureUrl())) {
                            String[] pic = orderDataBean.getPictureUrl().split(",");
                            GlideCommonUtils.showSquarePic(this, pic[0], successPic);
                        } else {
                            successPic.setImageResource(R.mipmap.defaut_square);
                        }
                        successShopName.setText(orderDataBean.getActionName());
                        successPrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", orderDataBean.getNowprice()))));
                        //交易单号
                        successOderNum.setText(orderDataBean.getBidNo());
                        //下单时间
                        successOrderTime.setText(orderDataBean.getCreateTime());
                    } else {
                        //竞拍失败详情
                        auctionFalse();
                    }
                }

            } else if ("4".equals(orderDataBean.getStatus())) {//参拍中
                if (orderDataBean.getSysDate() != null) {
                    if (orderDataBean.getSysDate() < orderDataBean.getEndTime()) {
                        biddingSuccess.setVisibility(View.GONE);
                        biddingFailure.setVisibility(View.GONE);
                        isTheDelivery.setVisibility(View.GONE);
                        refundSuccess.setVisibility(View.GONE);
                        onSale.setVisibility(View.VISIBLE);
                        long countTime = orderDataBean.getEndTime() - orderDataBean.getSysDate();
                        if (countTime > 0) {
                            countDown(countTime);
                            timer.start();
                        }
                        if (!TextUtils.isEmpty(orderDataBean.getPictureUrl())) {
                            String[] pic = orderDataBean.getPictureUrl().split(",");
                            GlideCommonUtils.showSquarePic(this, pic[0], onSalePic);
                        } else {
                            onSalePic.setImageResource(R.mipmap.defaut_square);
                        }
                        onSaleShopName.setText(orderDataBean.getActionName());
                        onSalePrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", orderDataBean.getNowprice()))));
                        //交易单号
                        onSaleOderNum.setText(orderDataBean.getBidNo());
                        //下单时间
                        onSaleOrderTime.setText(orderDataBean.getCreateTime());
                    } else {
                        if (!TextUtils.isEmpty(orderDataBean.getBuyerMemberId())) {
                            if (orderDataBean.getBuyerMemberId().equals(String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()))) {
                                biddingSuccess.setVisibility(View.VISIBLE);
                                biddingFailure.setVisibility(View.GONE);
                                isTheDelivery.setVisibility(View.GONE);
                                onSale.setVisibility(View.GONE);
                                if (!TextUtils.isEmpty(orderDataBean.getPictureUrl())) {
                                    String[] pic = orderDataBean.getPictureUrl().split(",");
                                    GlideCommonUtils.showSquarePic(this, pic[0], successPic);
                                } else {
                                    successPic.setImageResource(R.mipmap.defaut_square);
                                }
                                successShopName.setText(orderDataBean.getActionName());
                                successPrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", orderDataBean.getNowprice()))));
                                //交易单号
                                successOderNum.setText(orderDataBean.getBidNo());
                                //下单时间
                                successOrderTime.setText(orderDataBean.getCreateTime());
                                //  viewHolder.detail.setText("已中标");
                                // viewHolder.detail.setBackgroundResource(R.drawable.shape_red);
                            } else {
                                biddingSuccess.setVisibility(View.GONE);
                                refundSuccess.setVisibility(View.GONE);
                                biddingFailure.setVisibility(View.VISIBLE);
                                isTheDelivery.setVisibility(View.GONE);
                                onSale.setVisibility(View.GONE);
                                if (!TextUtils.isEmpty(orderDataBean.getPictureUrl())) {
                                    String[] pic = orderDataBean.getPictureUrl().split(",");
                                    GlideCommonUtils.showSquarePic(this, pic[0], failurePic);
                                } else {
                                    failurePic.setImageResource(R.mipmap.defaut_square);
                                }
                                failureShopName.setText(orderDataBean.getActionName());
                                failurePrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", orderDataBean.getNowprice()))));
                                //交易单号
                                failureOderNum.setText(orderDataBean.getBidNo());
                                //下单时间
                                failureOrderTime.setText(orderDataBean.getCreateTime());
                                //退款时间
                                failureRefundTime.setText(orderDataBean.getReturnFrontMoneyTime());
                                // viewHolder.detail.setText("未中标");
                                // viewHolder.detail.setBackgroundResource(R.drawable.shape_faint_yellow);
                            }
                        }
                    }
                } else {
                    long time = System.currentTimeMillis();
                    if (time < orderDataBean.getEndTime()) {
                        biddingSuccess.setVisibility(View.GONE);
                        refundSuccess.setVisibility(View.GONE);
                        biddingFailure.setVisibility(View.GONE);
                        isTheDelivery.setVisibility(View.GONE);
                        onSale.setVisibility(View.VISIBLE);
                        long countTime = orderDataBean.getEndTime() - time;
                        if (countTime > 0) {
                            countDown(countTime);
                            timer.start();
                        }
                        if (!TextUtils.isEmpty(orderDataBean.getPictureUrl())) {
                            String[] pic = orderDataBean.getPictureUrl().split(",");
                            GlideCommonUtils.showSquarePic(this, pic[0], onSalePic);
                        } else {
                            onSalePic.setImageResource(R.mipmap.defaut_square);
                        }
                        onSaleShopName.setText(orderDataBean.getActionName());
                        onSalePrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", orderDataBean.getNowprice()))));
                        //交易单号
                        onSaleOderNum.setText(orderDataBean.getBidNo());
                        //下单时间
                        onSaleOrderTime.setText(orderDataBean.getCreateTime());
                    } else {
                        if (!TextUtils.isEmpty(orderDataBean.getBuyerMemberId())) {
                            if (orderDataBean.getBuyerMemberId().equals(String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()))) {
                                biddingSuccess.setVisibility(View.VISIBLE);
                                refundSuccess.setVisibility(View.GONE);
                                biddingFailure.setVisibility(View.GONE);
                                isTheDelivery.setVisibility(View.GONE);
                                onSale.setVisibility(View.GONE);
                                if (!TextUtils.isEmpty(orderDataBean.getPictureUrl())) {
                                    String[] pic = orderDataBean.getPictureUrl().split(",");
                                    GlideCommonUtils.showSquarePic(this, pic[0], successPic);
                                } else {
                                    successPic.setImageResource(R.mipmap.defaut_square);
                                }
                                successShopName.setText(orderDataBean.getActionName());
                                successPrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", orderDataBean.getNowprice()))));
                                //交易单号
                                successOderNum.setText(orderDataBean.getBidNo());
                                //下单时间
                                successOrderTime.setText(orderDataBean.getCreateTime());
                                //  viewHolder.detail.setText("已中标");
                                // viewHolder.detail.setBackgroundResource(R.drawable.shape_red);
                            } else {
                                biddingSuccess.setVisibility(View.GONE);
                                refundSuccess.setVisibility(View.GONE);
                                biddingFailure.setVisibility(View.VISIBLE);
                                isTheDelivery.setVisibility(View.GONE);
                                onSale.setVisibility(View.GONE);
                                if (!TextUtils.isEmpty(orderDataBean.getPictureUrl())) {
                                    String[] pic = orderDataBean.getPictureUrl().split(",");
                                    GlideCommonUtils.showSquarePic(this, pic[0], failurePic);
                                } else {
                                    failurePic.setImageResource(R.mipmap.defaut_square);
                                }
                                failureShopName.setText(orderDataBean.getActionName());
                                failurePrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", orderDataBean.getNowprice()))));
                                //交易单号
                                failureOderNum.setText(orderDataBean.getBidNo());
                                //下单时间
                                failureOrderTime.setText(orderDataBean.getCreateTime());
                                //退款时间
                                failureRefundTime.setText(orderDataBean.getReturnFrontMoneyTime());
                                // viewHolder.detail.setText("未中标");
                                // viewHolder.detail.setBackgroundResource(R.drawable.shape_faint_yellow);
                            }
                        }
                    }
                }
            }
        }*/
    }

    private void auctionFalse() {

    }


    @Override
    public void showError(String message) {

    }

    /**
     * 倒数计时器
     */

    private void countDown(long millisInFuture) {
        timer = new CountDownTimer(millisInFuture, 1000) {
            /**
             * 固定间隔被调用,就是每隔countDownInterval会回调一次方法onTick
             *
             * @param millisUntilFinished
             */
            @Override
            public void onTick(long millisUntilFinished) {
                String hour = TimeTools.getCountTimeByLong(millisUntilFinished);
                String[] array = hour.split(":");
                tvHour.setText(array[0]);
                tvMin.setText(array[1]);
                tvSecond.setText(array[2]);
            }

            /**
             * 倒计时完成时被调用
             */
            @Override
            public void onFinish() {
                tvHour.setText("00");
                tvMin.setText("00");
                tvSecond.setText("00");
            }
        };
    }

    /**
     * 待付款倒数计时器
     */

    private void countDownPay(long millisInFuture) {
        timer = new CountDownTimer(millisInFuture, 1000) {
            /**
             * 固定间隔被调用,就是每隔countDownInterval会回调一次方法onTick
             *
             * @param millisUntilFinished
             */
            @Override
            public void onTick(long millisUntilFinished) {
                String hour = TimeTools.getCountTimeByLong(millisUntilFinished);
                String[] array = hour.split(":");
                obligationTvHour.setText(array[0]);
                obligationTvMin.setText(array[1]);
                obligationTvSecond.setText(array[2]);
            }

            /**
             * 倒计时完成时被调用
             */
            @Override
            public void onFinish() {
                obligationTvHour.setText("00");
                obligationTvMin.setText("00");
                obligationTvSecond.setText("00");
            }
        };
    }


    /**
     * 取消倒计时
     */
    public void timerCancel() {
        timer.cancel();
    }

    /**
     * 开始倒计时
     */
    public void timerStart() {
        timer.start();
    }


}
