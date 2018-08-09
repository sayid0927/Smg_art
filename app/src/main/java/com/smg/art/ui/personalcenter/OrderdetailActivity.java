package com.smg.art.ui.personalcenter;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.OderDetailBean;
import com.smg.art.bean.RefundBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.OderdetailContract;
import com.smg.art.presenter.impl.activity.OrderDetailPresenter;
import com.smg.art.ui.personalcenter.adapter.GridViewAdapter;
import com.smg.art.utils.CommonDpUtils;
import com.smg.art.utils.DateFormatUtil;
import com.smg.art.utils.GlideCommonUtils;
import com.smg.art.utils.KeyBoardUtils;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.utils.TimeTools;
import com.smg.art.view.NoScrollGridView;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.Arrays;
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
    @BindView(R.id.bidding_failure)
    LinearLayout biddingFailure;
    @BindView(R.id.bidding_success)
    LinearLayout biddingSuccess;
    @BindView(R.id.is_the_delivery)
    LinearLayout isTheDelivery;
    @BindView(R.id.tv_hour)
    TextView tvHour;
    @BindView(R.id.tv_min)
    TextView tvMin;
    @BindView(R.id.tv_second)
    TextView tvSecond;
    @BindView(R.id.time)
    LinearLayout time;
    @BindView(R.id.shop_status)
    TextView shopStatus;
    @BindView(R.id.onSale)
    LinearLayout onSale;
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
    @BindView(R.id.girdview)
    NoScrollGridView girdview;
    @BindView(R.id.refund_success)
    LinearLayout refundSuccess;
    @BindView(R.id.success_pic)
    ImageView successPic;
    @BindView(R.id.success_shop_name)
    TextView successShopName;
    @BindView(R.id.success_price)
    TextView successPrice;
    @BindView(R.id.success_oder_num)
    TextView successOderNum;
    @BindView(R.id.success_order_time)
    TextView successOrderTime;
    @BindView(R.id.delivery_pic)
    ImageView deliveryPic;
    @BindView(R.id.delivery_shop_name)
    TextView deliveryShopName;
    @BindView(R.id.delivery_price)
    TextView deliveryPrice;
    @BindView(R.id.delivery_oder_num)
    TextView deliveryOderNum;
    @BindView(R.id.delivery_order_time)
    TextView deliveryOrderTime;
    @BindView(R.id.delivery_time)
    TextView deliveryTime;
    @BindView(R.id.onSale_pic)
    ImageView onSalePic;
    @BindView(R.id.onSale_shop_name)
    TextView onSaleShopName;
    @BindView(R.id.onSale_price)
    TextView onSalePrice;
    @BindView(R.id.onSale_oder_num)
    TextView onSaleOderNum;
    @BindView(R.id.onSale_order_time)
    TextView onSaleOrderTime;
    @BindView(R.id.check_auction)
    TextView checkAuction;
    CountDownTimer timer;
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
    GridViewAdapter gridViewAdapter;
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
        type = getIntent().getIntExtra("typeId", 0);
        getdata();
    }

    private void getdata() {
        if (type == 1) {//投诉接口
            actionbarTitle.setText(R.string.complaint_detail);
            mPresenter.FetchRefundCenter("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()), "complainId", String.valueOf(id));
        } else {
            actionbarTitle.setText(R.string.order_detail);
            mPresenter.FetchQueryAuctionInfoCenter("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()), "auctionId", String.valueOf(id));
        }
    }

    @OnClick({R.id.rl_back, R.id.check_auction})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                KeyBoardUtils.hiddenKeyboart(this);
                finish();
                break;
            case R.id.check_auction:
                ToastUtils.showShortToast("查看竞拍");
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
        if (refundBean.getStatus() == 1) {
            RefundBean.DataBean dataBean = refundBean.getData();
            refundSuccess.setVisibility(View.VISIBLE);
            isTheDelivery.setVisibility(View.GONE);
            biddingSuccess.setVisibility(View.GONE);
            biddingFailure.setVisibility(View.GONE);
            onSale.setVisibility(View.GONE);
            if (!TextUtils.isEmpty(dataBean.getPictureUrl())) {
                String[] pic = dataBean.getPictureUrl().split(",");
                GlideCommonUtils.showSquarePic(this, pic[0], complaintPic);
            } else {
                complaintPic.setImageResource(R.mipmap.defaut_square);
            }
            complaintShopName.setText(dataBean.getActionName());
            //交易单号
            complaintOderNum.setText(dataBean.getBidNo());
            //下单时间
            complaintOrderTime.setText(dataBean.getCreateTime());
            //交割时间
            complaintDeliveryTime.setText(DateFormatUtil.getFormatDate(dataBean.getDeliveryTime(), "yyyy-MM-dd HH:mm:ss"));
            //投诉时间
            complaintTime.setText(DateFormatUtil.getFormatDate(dataBean.getComplainTime(), "yyyy-MM-dd HH:mm:ss"));
            //投诉内容
            complaintContent.setText(dataBean.getComplain());
            if (!TextUtils.isEmpty(dataBean.getPictureUrl())) {
                String[] pic = dataBean.getPictureUrl().split(",");
                // String数组转List集合
                images = Arrays.asList(pic);
            }
            gridViewAdapter = new GridViewAdapter(this, images);
            girdview.setAdapter(gridViewAdapter);
        } else {
            ToastUtils.showShortToast(refundBean.getMsg());
        }
    }

    private void setData(OderDetailBean oderDetailBean) {
        OderDetailBean.DataBean dataBean = oderDetailBean.getData();
        if (!TextUtils.isEmpty(dataBean.getStatus())) {
            if (("6").equals(dataBean.getStatus())) {//已交割或投诉
                if (!TextUtils.isEmpty(dataBean.getComplainStatus())) {
                    if (dataBean.getComplainStatus().equals("1")) {//1代表已投诉

                    } else {//0代表未投诉  或者dataBean.getComplainFlag()
                        isTheDelivery.setVisibility(View.VISIBLE);
                        refundSuccess.setVisibility(View.GONE);
                        biddingSuccess.setVisibility(View.GONE);
                        biddingFailure.setVisibility(View.GONE);
                        onSale.setVisibility(View.GONE);
                        if (!TextUtils.isEmpty(dataBean.getPictureUrl())) {
                            String[] pic = dataBean.getPictureUrl().split(",");
                            GlideCommonUtils.showSquarePic(this, pic[0], deliveryPic);
                        } else {
                            deliveryPic.setImageResource(R.mipmap.defaut_square);
                        }
                        deliveryShopName.setText(dataBean.getActionName());
                        deliveryPrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", dataBean.getNowprice()))));
                        //交易单号
                        deliveryOderNum.setText(dataBean.getBidNo());
                        //下单时间
                        deliveryOrderTime.setText(dataBean.getCreateTime());
                        //交割时间
                        deliveryTime.setText(DateFormatUtil.getFormatDate(dataBean.getDeliveryTime(), "yyyy-MM-dd HH:mm:ss"));
                    }
                } else {
                    /*viewHolder.auction.setVisibility(View.GONE);
                    viewHolder.complaint.setVisibility(View.GONE);*/
                }

            } else if (("5").equals(dataBean.getStatus())) {//待交割
                biddingSuccess.setVisibility(View.VISIBLE);
                refundSuccess.setVisibility(View.GONE);
                biddingFailure.setVisibility(View.GONE);
                isTheDelivery.setVisibility(View.GONE);
                onSale.setVisibility(View.GONE);
                if (!TextUtils.isEmpty(dataBean.getPictureUrl())) {
                    String[] pic = dataBean.getPictureUrl().split(",");
                    GlideCommonUtils.showSquarePic(this, pic[0], successPic);
                } else {
                    successPic.setImageResource(R.mipmap.defaut_square);
                }
                successShopName.setText(dataBean.getActionName());
                successPrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", dataBean.getNowprice()))));
                //交易单号
                successOderNum.setText(dataBean.getBidNo());
                //下单时间
                successOrderTime.setText(dataBean.getCreateTime());
            } else if ("4".equals(dataBean.getStatus())) {//参拍中
                if (dataBean.getSysDate() != null) {
                    if (dataBean.getSysDate() < dataBean.getEndTime()) {
                        biddingSuccess.setVisibility(View.GONE);
                        biddingFailure.setVisibility(View.GONE);
                        isTheDelivery.setVisibility(View.GONE);
                        refundSuccess.setVisibility(View.GONE);
                        onSale.setVisibility(View.VISIBLE);
                        long countTime = dataBean.getEndTime() - dataBean.getSysDate();
                        if (countTime > 0) {
                            countDown(countTime);
                            timer.start();
                        }
                        if (!TextUtils.isEmpty(dataBean.getPictureUrl())) {
                            String[] pic = dataBean.getPictureUrl().split(",");
                            GlideCommonUtils.showSquarePic(this, pic[0], onSalePic);
                        } else {
                            onSalePic.setImageResource(R.mipmap.defaut_square);
                        }
                        onSaleShopName.setText(dataBean.getActionName());
                        onSalePrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", dataBean.getNowprice()))));
                        //交易单号
                        onSaleOderNum.setText(dataBean.getBidNo());
                        //下单时间
                        onSaleOrderTime.setText(dataBean.getCreateTime());
                    } else {
                        if (!TextUtils.isEmpty(dataBean.getBuyerMemberId())) {
                            if (dataBean.getBuyerMemberId().equals(String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()))) {
                                biddingSuccess.setVisibility(View.VISIBLE);
                                biddingFailure.setVisibility(View.GONE);
                                isTheDelivery.setVisibility(View.GONE);
                                onSale.setVisibility(View.GONE);
                                if (!TextUtils.isEmpty(dataBean.getPictureUrl())) {
                                    String[] pic = dataBean.getPictureUrl().split(",");
                                    GlideCommonUtils.showSquarePic(this, pic[0], successPic);
                                } else {
                                    successPic.setImageResource(R.mipmap.defaut_square);
                                }
                                successShopName.setText(dataBean.getActionName());
                                successPrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", dataBean.getNowprice()))));
                                //交易单号
                                successOderNum.setText(dataBean.getBidNo());
                                //下单时间
                                successOrderTime.setText(dataBean.getCreateTime());
                                //  viewHolder.detail.setText("已中标");
                                // viewHolder.detail.setBackgroundResource(R.drawable.shape_red);
                            } else {
                                biddingSuccess.setVisibility(View.GONE);
                                refundSuccess.setVisibility(View.GONE);
                                biddingFailure.setVisibility(View.VISIBLE);
                                isTheDelivery.setVisibility(View.GONE);
                                onSale.setVisibility(View.GONE);
                                if (!TextUtils.isEmpty(dataBean.getPictureUrl())) {
                                    String[] pic = dataBean.getPictureUrl().split(",");
                                    GlideCommonUtils.showSquarePic(this, pic[0], failurePic);
                                } else {
                                    failurePic.setImageResource(R.mipmap.defaut_square);
                                }
                                failureShopName.setText(dataBean.getActionName());
                                failurePrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", dataBean.getNowprice()))));
                                //交易单号
                                failureOderNum.setText(dataBean.getBidNo());
                                //下单时间
                                failureOrderTime.setText(dataBean.getCreateTime());
                                //退款时间
                                failureRefundTime.setText(dataBean.getReturnFrontMoneyTime());
                                // viewHolder.detail.setText("未中标");
                                // viewHolder.detail.setBackgroundResource(R.drawable.shape_faint_yellow);
                            }
                        }
                    }
                } else {
                    long time = System.currentTimeMillis();
                    if (time < dataBean.getEndTime()) {
                        biddingSuccess.setVisibility(View.GONE);
                        refundSuccess.setVisibility(View.GONE);
                        biddingFailure.setVisibility(View.GONE);
                        isTheDelivery.setVisibility(View.GONE);
                        onSale.setVisibility(View.VISIBLE);
                        long countTime = dataBean.getEndTime() - time;
                        if (countTime > 0) {
                            countDown(countTime);
                            timer.start();
                        }
                        if (!TextUtils.isEmpty(dataBean.getPictureUrl())) {
                            String[] pic = dataBean.getPictureUrl().split(",");
                            GlideCommonUtils.showSquarePic(this, pic[0], onSalePic);
                        } else {
                            onSalePic.setImageResource(R.mipmap.defaut_square);
                        }
                        onSaleShopName.setText(dataBean.getActionName());
                        onSalePrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", dataBean.getNowprice()))));
                        //交易单号
                        onSaleOderNum.setText(dataBean.getBidNo());
                        //下单时间
                        onSaleOrderTime.setText(dataBean.getCreateTime());
                    } else {
                        if (!TextUtils.isEmpty(dataBean.getBuyerMemberId())) {
                            if (dataBean.getBuyerMemberId().equals(String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()))) {
                                biddingSuccess.setVisibility(View.VISIBLE);
                                refundSuccess.setVisibility(View.GONE);
                                biddingFailure.setVisibility(View.GONE);
                                isTheDelivery.setVisibility(View.GONE);
                                onSale.setVisibility(View.GONE);
                                if (!TextUtils.isEmpty(dataBean.getPictureUrl())) {
                                    String[] pic = dataBean.getPictureUrl().split(",");
                                    GlideCommonUtils.showSquarePic(this, pic[0], successPic);
                                } else {
                                    successPic.setImageResource(R.mipmap.defaut_square);
                                }
                                successShopName.setText(dataBean.getActionName());
                                successPrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", dataBean.getNowprice()))));
                                //交易单号
                                successOderNum.setText(dataBean.getBidNo());
                                //下单时间
                                successOrderTime.setText(dataBean.getCreateTime());
                                //  viewHolder.detail.setText("已中标");
                                // viewHolder.detail.setBackgroundResource(R.drawable.shape_red);
                            } else {
                                biddingSuccess.setVisibility(View.GONE);
                                refundSuccess.setVisibility(View.GONE);
                                biddingFailure.setVisibility(View.VISIBLE);
                                isTheDelivery.setVisibility(View.GONE);
                                onSale.setVisibility(View.GONE);
                                if (!TextUtils.isEmpty(dataBean.getPictureUrl())) {
                                    String[] pic = dataBean.getPictureUrl().split(",");
                                    GlideCommonUtils.showSquarePic(this, pic[0], failurePic);
                                } else {
                                    failurePic.setImageResource(R.mipmap.defaut_square);
                                }
                                failureShopName.setText(dataBean.getActionName());
                                failurePrice.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", dataBean.getNowprice()))));
                                //交易单号
                                failureOderNum.setText(dataBean.getBidNo());
                                //下单时间
                                failureOrderTime.setText(dataBean.getCreateTime());
                                //退款时间
                                failureRefundTime.setText(dataBean.getReturnFrontMoneyTime());
                                // viewHolder.detail.setText("未中标");
                                // viewHolder.detail.setBackgroundResource(R.drawable.shape_faint_yellow);
                            }
                        }
                    }
                }
            }
        }
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
