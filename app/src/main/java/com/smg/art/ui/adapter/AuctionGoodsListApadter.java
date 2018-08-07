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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smg.art.R;
import com.smg.art.bean.AuctionGoodsBean;
import com.smg.art.utils.GlideCommonUtils;
import com.smg.art.utils.TimeTools;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class AuctionGoodsListApadter extends RecyclerView.Adapter<AuctionGoodsListApadter.ViewHolder> {

    //用于退出activity,避免countdown，造成资源浪费。
    private SparseArray<CountDownTimer> countDownMap;
    private Context mContext;
    private List<AuctionGoodsBean.DataBean.RowsBean> mDatas;


    private OnAuctionGoodsItemListener onAuctionGoodsItemListener;


    public AuctionGoodsListApadter(Context context, List<AuctionGoodsBean.DataBean.RowsBean> datas) {
        mDatas = datas;
        mContext = context;
        countDownMap = new SparseArray<>();
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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_auction_goods, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAuctionGoodsItemListener != null)
                    onAuctionGoodsItemListener.OnAuctionGoodsItemListener(position);
            }
        });

        final AuctionGoodsBean.DataBean.RowsBean data = mDatas.get(position);
        long time;
        if (data.getSysDate() > 0) {
            time = data.getSysDate();
        } else {
            time = System.currentTimeMillis();//获取系统时间的10位的时间戳
        }
        if (time < data.getEndTime()) {
            long countTime = data.getEndTime() - time;
            //将前一个缓存清除
            if (holder.countDownTimer != null) {
                holder.countDownTimer.cancel();
            }
            if (countTime > 0) {
                holder.countDownTimer = new CountDownTimer(countTime, 1000) {
                    public void onTick(long millisUntilFinished) {
                        String hour = TimeTools.getCountTimeByLong(millisUntilFinished);
                        String[] array = hour.split(":");
                        holder.tv_hour.setText(array[0]);
                        holder.tv_min.setText(array[1]);
                        holder.tv_second.setText(array[2]);

                    }

                    public void onFinish() {
                        holder.tv_hour.setText("00");
                        holder.tv_min.setText("00");
                        holder.tv_second.setText("00");
                        holder.detail.setText("拍卖结束");
                    }
                }.start();

                countDownMap.put(holder.tv_hour.hashCode(), holder.countDownTimer);
            } else {
                holder.tv_hour.setText("00");
                holder.tv_min.setText("00");
                holder.tv_second.setText("00");
                holder.detail.setText("拍卖结束");
                holder.time.setVisibility(View.GONE);
            }
        } else if (time > data.getEndTime()) {
            holder.tv_hour.setText("00");
            holder.tv_min.setText("00");
            holder.tv_second.setText("00");
            holder.detail.setText("拍卖结束");
            holder.time.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(data.getPictureUrl())) {
            GlideCommonUtils.showSquarePic(mContext, data.getPictureUrl(), holder.shop_iv);
        } else {
            holder.shop_iv.setImageResource(R.mipmap.defaut_square);
        }
        holder.auction_name.setText(data.getActionName());
        if (!TextUtils.isEmpty(data.getSellerMemberName())) {
            holder.auction_tv.setText("拍卖方:" + data.getSellerMemberName());
        }
        holder.auction_num.setText("编码: " + data.getBidNo());

    }

    @Override
    public int getItemCount() {
        if (mDatas != null && !mDatas.isEmpty()) {
            return mDatas.size();
        }
        return 0;
    }

    public void OnAuctionGoodsItemListener(OnAuctionGoodsItemListener onAuctionGoodsItemListener) {
        this.onAuctionGoodsItemListener = onAuctionGoodsItemListener;
    }

    public interface OnAuctionGoodsItemListener {
        void OnAuctionGoodsItemListener(int item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout item_list;
        public TextView timeTv;
        public CountDownTimer countDownTimer;
        public TextView tv_hour;
        public TextView tv_min;
        public TextView tv_second;
        public LinearLayout time;
        public TextView end_text;
        public LinearLayout immediately_auction;
        public ImageView shop_iv;
        public TextView detail;
        TextView auction_name;
        TextView auction_tv;
        TextView auction_num;


        public ViewHolder(View itemView) {
            super(itemView);
            shop_iv = (ImageView) itemView.findViewById(R.id.shop_iv);
            item_list = (LinearLayout) itemView.findViewById(R.id.item_list);
            tv_hour = (TextView) itemView.findViewById(R.id.tv_hour);
            tv_min = (TextView) itemView.findViewById(R.id.tv_min);
            tv_second = (TextView) itemView.findViewById(R.id.tv_second);
            end_text = (TextView) itemView.findViewById(R.id.end_text);
            time = (LinearLayout) itemView.findViewById(R.id.time);
            immediately_auction = (LinearLayout) itemView.findViewById(R.id.immediately_auction);
            detail = (TextView) itemView.findViewById(R.id.detail);
            auction_name = (TextView) itemView.findViewById(R.id.auction_name);
            auction_tv = (TextView) itemView.findViewById(R.id.auction_tv);
            auction_num = (TextView) itemView.findViewById(R.id.auction_num);
        }
    }

}
