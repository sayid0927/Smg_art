package com.smg.art.ui.personalcenter.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
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
import com.smg.art.utils.LocalAppConfigUtil;

import java.util.List;

/**
 * Created by Mervin on 2018/7/27 0027.
 */

public class AuctionAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<AuctionOrderBean.DataBean> mList;

    public AuctionAdapter(Context context, List<AuctionOrderBean.DataBean> list) {
        this.mContext = context;
        this.mList = list;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                    } else {//0代表未投诉  或者dataBean.getComplainFlag()
                        viewHolder.auction.setVisibility(View.VISIBLE);
                        viewHolder.complaint.setVisibility(View.GONE);
                        if (!TextUtils.isEmpty(dataBean.getPictureUrl())) {
                            String[] pic = dataBean.getPictureUrl().split(",");
                            GlideCommonUtils.showSquarePic(mContext, pic[0], viewHolder.iv);
                        } else {
                            viewHolder.iv.setImageResource(R.mipmap.defaut_square);
                        }
                        viewHolder.shop_status.setText("已交割");
                        viewHolder.shop_name.setText(dataBean.getActionName());
                        viewHolder.end_time.setText("结束时间: " + DateFormatUtil.toHour(dataBean.getEndTime()));
                        viewHolder.price.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", dataBean.getNowprice()))));
                        viewHolder.detail.setVisibility(View.VISIBLE);
                        viewHolder.complaint_btn.setVisibility(View.VISIBLE);
                        viewHolder.complaint_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(mContext, StartComplaintActivity.class);
                                intent.putExtra("auctionId", dataBean.getId());
                                intent.putExtra("goodsId",dataBean.getGoodsId());
                                mContext.startActivity(intent);
                            }
                        });
                    }
                } else {
                    viewHolder.auction.setVisibility(View.GONE);
                    viewHolder.complaint.setVisibility(View.GONE);
                }

            } else if (("5").equals(dataBean.getStatus())) {//待交割
                viewHolder.auction.setVisibility(View.VISIBLE);
                viewHolder.complaint.setVisibility(View.GONE);
                if (!TextUtils.isEmpty(dataBean.getPictureUrl())) {
                    String[] pic = dataBean.getPictureUrl().split(",");
                    GlideCommonUtils.showSquarePic(mContext, pic[0], viewHolder.iv);
                } else {
                    viewHolder.iv.setImageResource(R.mipmap.defaut_square);
                }
                viewHolder.shop_status.setText("待交割");
                viewHolder.shop_name.setText(dataBean.getActionName());
                viewHolder.end_time.setText("结束时间: " + DateFormatUtil.toHour(dataBean.getEndTime()));
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
                viewHolder.end_time.setText("结束时间: " + DateFormatUtil.toHour(dataBean.getEndTime()));
                viewHolder.price.setText(CommonDpUtils.priceText(String.valueOf(String.format("%.2f", dataBean.getNowprice()))));
                viewHolder.detail.setVisibility(View.VISIBLE);
                viewHolder.complaint_btn.setVisibility(View.GONE);
            }
        }
        return convertView;
    }

    public static class ViewHolder {
        private LinearLayout auction;
        private ImageView iv;
        private TextView shop_status;
        private TextView shop_name;
        private TextView end_time;
        private TextView price;
        private TextView detail;//查看详情
        private TextView complaint_btn;//发起投诉
        private LinearLayout complaint;
        private TextView complaint_name;//投诉
        private TextView complaint_cause;//投诉原因
        private TextView complaint_detail;//投诉详情

        public ViewHolder(View view) {
            iv = (ImageView) view.findViewById(R.id.shop_iv);
            shop_name = (TextView) view.findViewById(R.id.shop_name);
            shop_status = (TextView) view.findViewById(R.id.shop_status);
            price = (TextView) view.findViewById(R.id.price);
            end_time = (TextView) view.findViewById(R.id.end_time);
            detail = (TextView) view.findViewById(R.id.detail);
            complaint_btn = (TextView) view.findViewById(R.id.complaint_btn);
            auction = (LinearLayout) view.findViewById(R.id.auction);
            complaint = (LinearLayout) view.findViewById(R.id.complaint);
            complaint_name = (TextView) view.findViewById(R.id.complaint_name);
            complaint_cause = (TextView) view.findViewById(R.id.complaint_cause);
            complaint_detail = (TextView) view.findViewById(R.id.complaint_detail);
        }
    }

}

