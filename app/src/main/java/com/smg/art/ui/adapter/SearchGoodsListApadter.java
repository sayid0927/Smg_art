package com.smg.art.ui.adapter;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
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

public class SearchGoodsListApadter extends BaseMultiItemQuickAdapter<AnnouncementAuctionListBean.DataBean.RowsBean, BaseViewHolder> {


    private List<AnnouncementAuctionListBean.DataBean.RowsBean> data;
    private OnGoodsItemListener onGoodsItemListener;

    //用于退出activity,避免countdown，造成资源浪费。
    private SparseArray<CountDownTimer> countDownMap = new SparseArray<>();

    public SearchGoodsListApadter(Context context, List<AnnouncementAuctionListBean.DataBean.RowsBean> data) {
        super(data);
        addItemType(RowsBean.GOODS, R.layout.item_goods);
        addItemType(RowsBean.AUCTION, R.layout.item_auction_goods);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final AnnouncementAuctionListBean.DataBean.RowsBean item) {

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
                CountDownTimer countDownTimer = null;
                long time;
                if (item.getSysDate() > 0) {
                    time = item.getSysDate();
                } else {
                    time = System.currentTimeMillis();//获取系统时间的10位的时间戳
                }
                if (time < item.getEndTime()) {
                    long countTime = item.getEndTime() - time;
                    //将前一个缓存清除
                    if (countDownTimer != null) {
                        countDownTimer.cancel();
                    }
                    if (countTime > 0) {
                        countDownTimer = new CountDownTimer(countTime, 1000) {
                            public void onTick(long millisUntilFinished) {
                                String hour = TimeTools.getCountTimeByLong(millisUntilFinished);
                                String[] array = hour.split(":");
                                helper.setText(R.id.tv_hour,array[0]);
                                helper.setText(R.id.tv_min,array[1]);
                                helper.setText(R.id.tv_second,array[2]);
                            }
                            public void onFinish() {
                                helper.setText(R.id.tv_hour,"00");
                                helper.setText(R.id.tv_min,"00");
                                helper.setText(R.id.tv_second,"00");
                                helper.setText(R.id.detail,"拍卖结束");
                            }
                        }.start();
                        countDownMap.put(helper.getView(R.id.tv_hour).hashCode(), countDownTimer);
                    } else {
                        helper.setText(R.id.tv_hour,"00");
                        helper.setText(R.id.tv_min,"00");
                        helper.setText(R.id.tv_second,"00");
                        helper.setText(R.id.detail,"拍卖结束");
                        helper.getView(R.id.tv_time).setVisibility(View.GONE);
                    }
                } else if (time > item.getEndTime()) {
                    helper.setText(R.id.tv_hour,"00");
                    helper.setText(R.id.tv_min,"00");
                    helper.setText(R.id.tv_second,"00");
                    helper.setText(R.id.detail,"拍卖结束");
                    helper.getView(R.id.tv_time).setVisibility(View.GONE);
                }
                if (!TextUtils.isEmpty(item.getPictureUrl())) {
                    GlideCommonUtils.showSquarePic(mContext, item.getPictureUrl(), (ImageView) helper.getView(R.id.shop_iv));
                } else {
                    helper.setImageResource(R.id.shop_iv,R.mipmap.defaut_square);
                }
                helper.setText(R.id.auction_name,item.getActionName());

                if (TextUtils.isEmpty(item.getAuctionDesc())) {
                    helper.setText(R.id.auction_tv,"拍卖方:" + item.getAuctionDesc());
                }
                helper.setText(R.id.auction_num,"编码: " + item.getBidNo());
                break;
        }
    }



    public void OnGoodsItemListener(OnGoodsItemListener onGoodsItemListener) {
        this.onGoodsItemListener = onGoodsItemListener;
    }

    public interface OnGoodsItemListener {
        void OnGoodsItemListener(RowsBean item);
    }

}
