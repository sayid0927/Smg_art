package com.smg.art.ui.adapter;

import android.content.Context;
import android.os.CountDownTimer;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smg.art.R;
import com.smg.art.base.AnnouncementAuctionListBean;
import com.smg.art.base.AnnouncementAuctionListBean.DataBean.RowsBean;
import com.smg.art.base.Constant;
import com.smg.art.utils.GlideCommonUtils;
import com.smg.art.utils.GlideUtils;
import com.smg.art.utils.TimeTools;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class SearchGoodsListApadter extends BaseMultiItemQuickAdapter<AnnouncementAuctionListBean.DataBean.RowsBean, SearchGoodsListApadter.ViewHolder> {


    private List<AnnouncementAuctionListBean.DataBean.RowsBean> data;
    private OnGoodsItemListener onGoodsItemListener;
    private OnAuctionItemListener onAuctionItemListener;
    private   CountDownTimer countDownTimer = null;

    //用于退出activity,避免countdown，造成资源浪费。
    private SparseArray<CountDownTimer> countDownMap = new SparseArray<>();

    public SearchGoodsListApadter(Context context, List<AnnouncementAuctionListBean.DataBean.RowsBean> data) {
        super(data);
        addItemType(RowsBean.GOODS, R.layout.item_goods);
        addItemType(RowsBean.AUCTION, R.layout.item_auction_goods);
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

    public class ViewHolder extends BaseViewHolder{

        public CountDownTimer countDownTimer;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    protected void convert(ViewHolder helper, RowsBean item) {

        switch (helper.getItemViewType()) {
            case RowsBean.GOODS:
                helper.setText(R.id.tv_actionName, item.getActionName());
                GlideUtils.loadFitCenter(mContext, Constant.BaseImgUrl + item.getPictureUrl(), (ImageView) helper.getView(R.id.iv_actioniv));
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onGoodsItemListener != null)
                            onGoodsItemListener.OnGoodsItemListener(item);
                    }
                });

                break;

            case RowsBean.AUCTION:

                long time;
                if (item.getSysDate() > 0) {
                    time = item.getSysDate();
                } else {
                    time = System.currentTimeMillis();//获取系统时间的10位的时间戳
                }
                if (time < item.getEndTime()) {
                    long countTime = item.getEndTime() - time;
                    if (countTime > 0) {
                        //将前一个缓存清除
                        if (helper.countDownTimer != null) {
                            helper.countDownTimer.cancel();
                        }
                     helper.countDownTimer = new CountDownTimer(countTime, 1000) {
                            public void onTick(long millisUntilFinished) {
                                String hour = TimeTools.getCountTimeByLong(millisUntilFinished);
                                String[] array = hour.split(":");
                                helper.setText(R.id.tv_hour, array[0]);
                                helper.setText(R.id.tv_min, array[1]);
                                helper.setText(R.id.tv_second, array[2]);
                                helper.getView(R.id.fl_auction).setVisibility(View.VISIBLE);
                                helper.getView(R.id.iv_auction).setBackgroundResource(R.drawable.auction_red);
                            }
                            public void onFinish() {
                                helper.setText(R.id.tv_hour, "00");
                                helper.setText(R.id.tv_min, "00");
                                helper.setText(R.id.tv_second, "00");
                                helper.setText(R.id.detail, "拍卖结束");
                                helper.getView(R.id.fl_auction).setVisibility(View.VISIBLE);
                                helper.getView(R.id.iv_auction).setBackgroundResource(R.drawable.auction_gray);
                            }
                        }.start();
                        countDownMap.put(helper.getView(R.id.tv_hour).hashCode(), countDownTimer);
                    } else {
                        helper.setText(R.id.tv_hour, "00");
                        helper.setText(R.id.tv_min, "00");
                        helper.setText(R.id.tv_second, "00");
                        helper.setText(R.id.detail, "拍卖结束");
                        helper.getView(R.id.tv_time).setVisibility(View.GONE);
                        helper.getView(R.id.fl_auction).setVisibility(View.VISIBLE);
                        helper.getView(R.id.iv_auction).setBackgroundResource(R.drawable.auction_gray);
                    }
                } else if (time > item.getEndTime()) {
                    helper.setText(R.id.tv_hour, "00");
                    helper.setText(R.id.tv_min, "00");
                    helper.setText(R.id.tv_second, "00");
                    helper.setText(R.id.detail, "拍卖结束");
                    helper.getView(R.id.tv_time).setVisibility(View.GONE);
                    helper.getView(R.id.fl_auction).setVisibility(View.VISIBLE);
                    helper.getView(R.id.iv_auction).setBackgroundResource(R.drawable.auction_gray);
                }
                if (!TextUtils.isEmpty(item.getPictureUrl())) {
                    GlideCommonUtils.showSquarePic(mContext, item.getPictureUrl(), (ImageView) helper.getView(R.id.shop_iv));
                } else {
                    helper.setImageResource(R.id.shop_iv, R.mipmap.defaut_square);
                }
                helper.setText(R.id.auction_name, item.getActionName());

                if (TextUtils.isEmpty(item.getAuctionDesc())) {
                    helper.setText(R.id.auction_tv, "拍卖方:" + item.getAuctionDesc());
                }
                helper.getView(R.id.detail).setVisibility(View.GONE);
                helper.getView(R.id.auction_num).setVisibility(View.GONE);
                helper.getView(R.id.auction_tv).setVisibility(View.GONE);
                helper.getView(R.id.tv_search_en).setVisibility(View.VISIBLE);
                helper.getView(R.id.view_line).setVisibility(View.VISIBLE);
                helper.getView(R.id.item_list).setBackgroundResource(R.drawable.item_witeh_bg);

                helper.setText(R.id.auction_num, "编码: " + item.getBidNo());
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onAuctionItemListener != null)
                            onAuctionItemListener.OnAuctionItemListener(item);
                    }
                });

                break;
        }
    }

    public void OnGoodsItemListener(OnGoodsItemListener onGoodsItemListener) {
        this.onGoodsItemListener = onGoodsItemListener;
    }

    public interface OnGoodsItemListener {
        void OnGoodsItemListener(RowsBean item);
    }

    public void OnAuctionItemListener(OnAuctionItemListener onAuctionItemListener) {
        this.onAuctionItemListener = onAuctionItemListener;
    }

    public interface OnAuctionItemListener {
        void OnAuctionItemListener(RowsBean item);
    }

}
