package com.smg.art.ui.personalcenter.adapter;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smg.art.R;
import com.smg.art.bean.AuctionOrderBean;
import com.smg.art.utils.CommonDpUtils;
import com.smg.art.utils.DateFormatUtil;
import com.smg.art.utils.GlideCommonUtils;
import com.smg.art.utils.TimeTools;

import java.util.List;

/**
 * Created by Mervin on 2018/7/27 0027.
 */

public class AuctionAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<AuctionOrderBean.DataBean> mList;
    //用于退出activity,避免countdown，造成资源浪费。
    private SparseArray<CountDownTimer> countDownMap;

    public AuctionAdapter(Context context, List<AuctionOrderBean.DataBean> list) {
        this.mContext = context;
        this.mList = list;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        countDownMap = new SparseArray<>();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 清空资源
     */
    public void cancelAllTimers() {
        if (countDownMap == null) {
            return;
        }
        //  Log.e("TAG",  "size :  " + countDownMap.size());
        for (int i = 0, length = countDownMap.size(); i < length; i++) {
            CountDownTimer cdt = countDownMap.get(countDownMap.keyAt(i));
            if (cdt != null) {
                cdt.cancel();
            }
        }
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.auction_fragment_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final AuctionOrderBean.DataBean dataBean = mList.get(position);
        if (!TextUtils.isEmpty(dataBean.getStatus())) {
            if (("4").equals(dataBean.getStatus())) {//参拍中
                viewHolder.end_time.setText("结拍时间: " + DateFormatUtil.forString(dataBean.getEndTime(), "MM月dd日 HH:mm"));
                viewHolder.detail.setText("查看详情");
                viewHolder.time.setVisibility(View.GONE);
                viewHolder.shop_status.setText("参拍中");
                viewHolder.shop_status.setBackgroundResource(R.drawable.shape_re_red);
                viewHolder.shop_name.setText(dataBean.getActionName());
                viewHolder.price.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", dataBean.getNowprice()))));
                if (dataBean.getEndTime() > 0) {
                    viewHolder.end_time.setText("结拍时间: " + DateFormatUtil.forString(dataBean.getEndTime(), "MM月dd日 HH:mm"));
                }
            } else if (Integer.parseInt(dataBean.getStatus()) >= 5) {//已获拍或 未获拍  buyerMemberId等于用户ID为已获拍。反之
                if (!TextUtils.isEmpty(dataBean.getOrderStatus())) {
                    switch (dataBean.getOrderStatus()) {
                        case "1"://待支付
                            viewHolder.detail.setText("待支付");
                            long time;
                            if (dataBean.getSysDate() > 0) {
                                time = dataBean.getSysDate();
                            } else {
                                time = System.currentTimeMillis();//获取系统时间的10位的时间戳
                            }
                            if (time < dataBean.getPaybiddingFinalTime()) {
                                long countTime = dataBean.getPaybiddingFinalTime() - time;
                                //将前一个缓存清除
                                if (viewHolder.countDownTimer != null) {
                                    viewHolder.countDownTimer.cancel();
                                }
                                if (countTime > 0) {
                                    final ViewHolder finalViewHolder = viewHolder;
                                    final ViewHolder finalViewHolder1 = viewHolder;
                                    viewHolder.countDownTimer = new CountDownTimer(countTime, 1000) {
                                        public void onTick(long millisUntilFinished) {
                                            String hour = TimeTools.getCountTimeByLong(millisUntilFinished);
                                            String[] array = hour.split(":");
                                            finalViewHolder.end_time.setText("距付款结束: ");
                                            finalViewHolder.tv_hour.setText(array[0]);
                                            finalViewHolder.tv_min.setText(array[1]);
                                            finalViewHolder.tv_second.setText(array[2]);
                                            finalViewHolder.shop_status.setText("已获拍");
                                        }

                                        public void onFinish() {
                                            finalViewHolder.time.setVisibility(View.GONE);
                                            // holder.end_text.setVisibility(View.VISIBLE);
                                            finalViewHolder.end_time.setText("付款结束: " + DateFormatUtil.formartData(dataBean.getPaybiddingFinalTime(), "MM月dd日 HH:mm") + "已结束");
                                            finalViewHolder.shop_status.setText("已结束");
                                            finalViewHolder1.shop_status.setBackgroundResource(R.drawable.shape_grey);
                                        }
                                    }.start();
                                    countDownMap.put(viewHolder.tv_hour.hashCode(), viewHolder.countDownTimer);
                                }
                            } else {
                                viewHolder.time.setVisibility(View.GONE);
                                // holder.end_text.setVisibility(View.VISIBLE);
                                viewHolder.end_time.setText("距付款结束: " + DateFormatUtil.formartData(dataBean.getPaybiddingFinalTime(), "MM月dd日 HH:mm") + "已结束");
                                viewHolder.shop_status.setText("已结束");
                                viewHolder.shop_status.setBackgroundResource(R.drawable.shape_grey);
                            }
                            viewHolder.shop_name.setText(dataBean.getActionName());
                            viewHolder.price.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", dataBean.getNowprice()))));
                            viewHolder.normal.setVisibility(View.VISIBLE);
                            viewHolder.overdue.setVisibility(View.GONE);
                            viewHolder.shop_status.setText("已获拍");
                            viewHolder.shop_status.setBackgroundResource(R.drawable.have_pat_brown);
                            break;
                        case "2"://待发货
                            viewHolder.shop_status.setText("已获拍");
                            viewHolder.detail.setText("待发货");
                            viewHolder.time.setVisibility(View.GONE);
                            if (dataBean.getPaymentTime() > 0) {
                                viewHolder.end_time.setText("付款时间: " + DateFormatUtil.formartData(dataBean.getPaymentTime(), "MM月dd日 HH:mm"));
                            }
                            viewHolder.shop_name.setText(dataBean.getActionName());
                            viewHolder.price.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", dataBean.getNowprice()))));
                            viewHolder.normal.setVisibility(View.VISIBLE);
                            viewHolder.overdue.setVisibility(View.GONE);
                            viewHolder.shop_status.setBackgroundResource(R.drawable.have_pat_brown);
                            break;
                        case "3"://待收货
                            viewHolder.shop_status.setText("已获拍");
                            viewHolder.detail.setText("待收货");
                            viewHolder.time.setVisibility(View.GONE);
                            if (dataBean.getSendOutTime() > 0) {
                                viewHolder.end_time.setText("发货时间: " + DateFormatUtil.formartData(dataBean.getSendOutTime(), "MM月dd日 HH:mm"));
                            }
                            viewHolder.shop_name.setText(dataBean.getActionName());
                            viewHolder.price.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", dataBean.getNowprice()))));
                            viewHolder.normal.setVisibility(View.VISIBLE);
                            viewHolder.overdue.setVisibility(View.GONE);
                            viewHolder.shop_status.setBackgroundResource(R.drawable.have_pat_brown);
                            break;
                        case "4"://已确认
                            viewHolder.shop_status.setText("已获拍");
                            viewHolder.detail.setText("已确认");
                            viewHolder.time.setVisibility(View.GONE);
                            if (dataBean.getReceiveTime() > 0) {
                                viewHolder.end_time.setText("收货时间: " + DateFormatUtil.formartData(dataBean.getReceiveTime(), "MM月dd日 HH:mm"));
                            }
                            viewHolder.shop_name.setText(dataBean.getActionName());
                            viewHolder.price.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", dataBean.getNowprice()))));
                            viewHolder.normal.setVisibility(View.VISIBLE);
                            viewHolder.overdue.setVisibility(View.GONE);
                            viewHolder.shop_status.setBackgroundResource(R.drawable.have_pat_brown);
                            break;
                        case "5"://超时未确认
                            viewHolder.shop_status.setText("已获拍");
                            viewHolder.time.setVisibility(View.GONE);

                            viewHolder.overdue.setVisibility(View.VISIBLE);
                            viewHolder.normal.setVisibility(View.GONE);
                            viewHolder.overdue_btn.setText("逾期未支付");
                            viewHolder.overdue_btn.setBackgroundResource(R.drawable.shape_grd);
                            viewHolder.overdue_shop_name.setText(dataBean.getActionName());
                            viewHolder.shop_status.setBackgroundResource(R.drawable.have_pat_brown);

                            if (dataBean.getPaybiddingFinalTime() > 0) {
                                viewHolder.overdue_end_time.setText("逾期支付:" + DateFormatUtil.forString(dataBean.getPaybiddingFinalTime(), "MM月dd日 HH:mm"));
                            }
                            break;
                        case "6"://未获拍
                            viewHolder.normal.setVisibility(View.VISIBLE);
                            viewHolder.overdue.setVisibility(View.GONE);
                            viewHolder.shop_status.setText("未获拍");
                            viewHolder.time.setVisibility(View.GONE);
                            viewHolder.shop_status.setBackgroundResource(R.drawable.no_pat_grey);
                            if (dataBean.getEndTime() > 0) {
                                viewHolder.end_time.setText("结拍时间: " + DateFormatUtil.forString(dataBean.getEndTime(), "MM月dd日 HH:mm"));
                            }
                            viewHolder.detail.setText("查看详情");
                            viewHolder.shop_name.setText(dataBean.getActionName());
                            viewHolder.price.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", dataBean.getNowprice()))));
                            break;

                        case  "7":

                            viewHolder.shop_status.setText("已获拍");
                            viewHolder.time.setVisibility(View.GONE);
                            viewHolder.overdue_btn.setText("逾期未发货");
                            viewHolder.overdue.setVisibility(View.VISIBLE);
                            viewHolder.normal.setVisibility(View.GONE);
                            viewHolder.overdueText.setText("卖家未按时发货");

                            viewHolder.overdue_shop_name.setText(dataBean.getActionName());
                            viewHolder.shop_status.setBackgroundResource(R.drawable.have_pat_brown);
                            viewHolder.overdue_btn.setBackgroundResource(R.drawable.shape_grd);
                            if (dataBean.getPaybiddingFinalTime() > 0) {
                                viewHolder.overdue_end_time.setText("逾期发货: " + DateFormatUtil.forString(dataBean.getPaybiddingFinalTime(), "MM月dd日 HH:mm"));
                            }

                            break;

                    }
                }
            }
            if (!TextUtils.isEmpty(dataBean.getPictureUrl())) {
                String[] pic = dataBean.getPictureUrl().split(",");
                GlideCommonUtils.showSquarePic(mContext, pic[0], viewHolder.iv);
            } else {
                viewHolder.iv.setImageResource(R.mipmap.defaut_square);
            }

        }

       /* if (!TextUtils.isEmpty(dataBean.getStatus())) {
            if (("6").equals(dataBean.getStatus())) {//已交割或投诉
                if (!TextUtils.isEmpty(dataBean.getComplainStatus())) {
                    if (dataBean.getComplainStatus().equals("1")) {//1代表已投诉
                        viewHolder.auction.setVisibility(View.GONE);
                        viewHolder.complaint.setVisibility(View.VISIBLE);
                        viewHolder.complaint_name.setText(dataBean.getActionName());
                        viewHolder.complaint_cause.setText(dataBean.getComplain());
                        viewHolder.detail.setVisibility(View.VISIBLE);
                        viewHolder.detail.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //查看详情
                            }
                        });
                        if (!TextUtils.isEmpty(dataBean.getBuyerMemberId())) {
                            if (dataBean.getBuyerMemberId().equals(String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()))) {
                                viewHolder.shop_status.setText("已中标");
                                viewHolder.detail.setBackgroundResource(R.drawable.shape_red);
                            } else {
                                viewHolder.shop_status.setText("已结束");
                                viewHolder.shop_status.setBackgroundResource(R.drawable.shape_yellow);
                                viewHolder.detail.setBackgroundResource(R.drawable.shape_faint_yellow);
                            }
                        }
                    } else {//0代表未投诉  或者dataBean.getComplainFlag()
                        if (!TextUtils.isEmpty(dataBean.getBuyerMemberId())) {
                            if (dataBean.getBuyerMemberId().equals(String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()))) {
                                viewHolder.shop_status.setText("已交割");
                                viewHolder.shop_status.setBackgroundResource(R.drawable.shape_re_red);
                                viewHolder.detail.setBackgroundResource(R.drawable.shape_red);
                                viewHolder.detail.setVisibility(View.VISIBLE);
                                viewHolder.detail.setText("查看详情");
                                viewHolder.complaint_btn.setVisibility(View.VISIBLE);
                                viewHolder.complaint_btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(mContext, StartComplaintActivity.class);
                                        intent.putExtra("auctionId", dataBean.getId());
                                        intent.putExtra("goodsId", dataBean.getGoodsId());
                                        mContext.startActivity(intent);

                                    }
                                });
                            } else {
                                viewHolder.shop_status.setText("已结束");
                                viewHolder.shop_status.setBackgroundResource(R.drawable.shape_yellow);
                                viewHolder.detail.setText("未中标");
                                viewHolder.detail.setBackgroundResource(R.drawable.shape_faint_yellow);
                                viewHolder.detail.setVisibility(View.VISIBLE);
                                viewHolder.complaint_btn.setVisibility(View.GONE);
                            }
                        }
                        viewHolder.auction.setVisibility(View.VISIBLE);
                        viewHolder.complaint.setVisibility(View.GONE);

                        if (!TextUtils.isEmpty(dataBean.getPictureUrl())) {
                            String[] pic = dataBean.getPictureUrl().split(",");
                            GlideCommonUtils.showSquarePic(mContext, pic[0], viewHolder.iv);
                        } else {
                            viewHolder.iv.setImageResource(R.mipmap.defaut_square);
                        }
                        viewHolder.shop_name.setText(dataBean.getActionName());
                        viewHolder.end_time.setText("结束时间: " + DateFormatUtil.forString(dataBean.getEndTime(), "MM月dd日 HH:mm"));
                        viewHolder.price.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", dataBean.getNowprice()))));
                    }
                } else {
                    viewHolder.auction.setVisibility(View.GONE);
                    viewHolder.complaint.setVisibility(View.GONE);
                }

            } else if (("5").equals(dataBean.getStatus())) {//待交割
                if (!TextUtils.isEmpty(dataBean.getBuyerMemberId())) {
                    if (dataBean.getBuyerMemberId().equals(String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()))) {
                        viewHolder.detail.setBackgroundResource(R.drawable.shape_red);
                        viewHolder.shop_status.setText("待交割");
                        viewHolder.detail.setText("查看详情");
                    } else {
                        viewHolder.detail.setText("未中标");
                        viewHolder.detail.setBackgroundResource(R.drawable.shape_faint_yellow);
                        viewHolder.shop_status.setText("已结束");
                        viewHolder.shop_status.setBackgroundResource(R.drawable.shape_yellow);
                    }
                }
                viewHolder.auction.setVisibility(View.VISIBLE);
                viewHolder.complaint.setVisibility(View.GONE);
                if (!TextUtils.isEmpty(dataBean.getPictureUrl())) {
                    String[] pic = dataBean.getPictureUrl().split(",");
                    GlideCommonUtils.showSquarePic(mContext, pic[0], viewHolder.iv);
                } else {
                    viewHolder.iv.setImageResource(R.mipmap.defaut_square);
                }

                viewHolder.shop_name.setText(dataBean.getActionName());
                viewHolder.end_time.setText("结束时间: " + DateFormatUtil.forString(dataBean.getEndTime(), "MM月dd日 HH:mm"));
                viewHolder.price.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", dataBean.getNowprice()))));
                viewHolder.detail.setVisibility(View.VISIBLE);
                viewHolder.complaint_btn.setVisibility(View.GONE);
            } else if ("4".equals(dataBean.getStatus())) {//参拍中
                viewHolder.auction.setVisibility(View.VISIBLE);
                viewHolder.complaint.setVisibility(View.GONE);
                if (!TextUtils.isEmpty(dataBean.getPictureUrl())) {
                    String[] pic = dataBean.getPictureUrl().split(",");
                    GlideCommonUtils.showSquarePic(mContext, pic[0], viewHolder.iv);
                } else {
                    viewHolder.iv.setImageResource(R.mipmap.defaut_square);
                }

                if (dataBean.getSysDate() != null) {
                    if (dataBean.getSysDate() < dataBean.getEndTime()) {
                        viewHolder.shop_status.setText("拍卖中");
                        viewHolder.shop_status.setBackgroundResource(R.drawable.shape_re_red);
                    } else {
                        viewHolder.shop_status.setText("已结束");
                        viewHolder.shop_status.setBackgroundResource(R.drawable.shape_yellow);
                        if (!TextUtils.isEmpty(dataBean.getBuyerMemberId())) {
                            if (dataBean.getBuyerMemberId().equals(String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()))) {
                                viewHolder.detail.setText("已中标");
                                viewHolder.detail.setBackgroundResource(R.drawable.shape_red);
                            } else {
                                viewHolder.detail.setText("未中标");
                                viewHolder.detail.setBackgroundResource(R.drawable.shape_faint_yellow);
                            }
                        }
                    }
                } else {
                    long time = System.currentTimeMillis();
                    if (time < dataBean.getEndTime()) {
                        viewHolder.shop_status.setText("拍卖中");
                        viewHolder.shop_status.setBackgroundResource(R.drawable.shape_re_red);
                    } else {
                        viewHolder.shop_status.setText("已结束");
                        viewHolder.shop_status.setBackgroundResource(R.drawable.shape_yellow);
                        if (!TextUtils.isEmpty(dataBean.getBuyerMemberId())) {
                            if (dataBean.getBuyerMemberId().equals(String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()))) {
                                viewHolder.detail.setText("已中标");
                                viewHolder.detail.setBackgroundResource(R.drawable.shape_red);
                            } else {
                                viewHolder.detail.setText("未中标");
                                viewHolder.detail.setBackgroundResource(R.drawable.shape_faint_yellow);
                            }
                        }
                    }
                }

                viewHolder.shop_name.setText(dataBean.getActionName());
                viewHolder.end_time.setText("结束时间: " + DateFormatUtil.forString(dataBean.getEndTime(), "MM月dd日 HH:mm"));
                viewHolder.price.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", dataBean.getNowprice()))));
                viewHolder.detail.setVisibility(View.VISIBLE);
                viewHolder.complaint_btn.setVisibility(View.GONE);
            }
        }*/
        return convertView;
    }

    public static class ViewHolder {
        public CountDownTimer countDownTimer;
        public TextView tv_hour;
        public TextView tv_min;
        public TextView tv_second;
        public LinearLayout time;
        private LinearLayout auction;
        private ImageView iv;
        private TextView shop_status;
        private TextView shop_name, overdue_shop_name;
        private TextView end_time, overdue_end_time;
        private TextView price;
        private TextView detail;//查看详情
        private LinearLayout overdue, normal;
        private TextView overdue_btn;
        private  TextView overdueText;

        public ViewHolder(View view) {
            iv = (ImageView) view.findViewById(R.id.shop_iv);
            shop_name = (TextView) view.findViewById(R.id.shop_name);
            shop_status = (TextView) view.findViewById(R.id.shop_status);
            price = (TextView) view.findViewById(R.id.price);
            end_time = (TextView) view.findViewById(R.id.end_time);
            detail = (TextView) view.findViewById(R.id.detail);
            auction = (LinearLayout) view.findViewById(R.id.auction);
            time = (LinearLayout) view.findViewById(R.id.time);
            tv_hour = (TextView) view.findViewById(R.id.tv_hour);
            tv_min = (TextView) view.findViewById(R.id.tv_min);
            tv_second = (TextView) view.findViewById(R.id.tv_second);
            overdue = (LinearLayout) view.findViewById(R.id.overdue);
            normal = (LinearLayout) view.findViewById(R.id.normal);
            overdue_btn = (TextView) view.findViewById(R.id.overdue_btn);
            overdue_shop_name = (TextView) view.findViewById(R.id.overdue_shop_name);
            overdue_end_time = (TextView) view.findViewById(R.id.overdue_end_time);
            overdueText = (TextView) view.findViewById(R.id.overdue_text);
        }
    }

}

