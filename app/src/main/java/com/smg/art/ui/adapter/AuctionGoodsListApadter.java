package com.smg.art.ui.adapter;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smg.art.R;
import com.smg.art.bean.AuctionGoodsBean;
import com.smg.art.bean.TimerItem;
import com.smg.art.utils.TimeTools;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class AuctionGoodsListApadter extends RecyclerView.Adapter<AuctionGoodsListApadter.ViewHolder> {

    private List<TimerItem> mDatas;
    //用于退出activity,避免countdown，造成资源浪费。
    private SparseArray<CountDownTimer> countDownMap;
    private Context mContext;
    private List<AuctionGoodsBean> data;

    /*   public AuctionGoodsListApadter(List<AuctionGoodsBean> data, Context mContext) {
           super(R.layout.item_auction_goods, data);
           this.mContext = mContext;
           this.data = data;
       }
   */
    private OnAuctionGoodsItemListener onAuctionGoodsItemListener;

 /*   @Override
    protected void convert(BaseViewHolder helper, final AuctionGoodsBean item) {
//        helper.setText(R.id.file_title,item.getFileName());
//        helper.setText(R.id.down_label,item.getFilePath());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onAuctionGoodsItemListener!=null)
                onAuctionGoodsItemListener.OnAuctionGoodsItemListener(item);
            }
        });
    }*/

    public AuctionGoodsListApadter(Context context, List<TimerItem> datas) {
        mDatas = datas;
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
        final TimerItem data = mDatas.get(position);
        //  holder.statusTv.setText(data.name);
        long time = data.expirationTime;
        time = time - System.currentTimeMillis();
        //将前一个缓存清除
        if (holder.countDownTimer != null) {
            holder.countDownTimer.cancel();
        }
        if (time > 0) {
            holder.countDownTimer = new CountDownTimer(time, 1000) {
                public void onTick(long millisUntilFinished) {
                    String hour = TimeTools.getCountTimeByLong(millisUntilFinished);
                    holder.tv_hour.setText(hour.substring(0, 2));
                    holder.tv_min.setText(hour.substring(3, 5));
                    holder.tv_second.setText(hour.substring(6, 8));
                }

                public void onFinish() {
                    holder.time.setVisibility(View.GONE);
                    holder.end_text.setVisibility(View.VISIBLE);
                    holder.immediately_auction.setVisibility(View.GONE);
                }
            }.start();

            countDownMap.put(holder.tv_hour.hashCode(), holder.countDownTimer);
        } else {
            holder.time.setVisibility(View.GONE);
            holder.end_text.setVisibility(View.VISIBLE);
            holder.immediately_auction.setVisibility(View.GONE);
        }
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

        public ViewHolder(View itemView) {
            super(itemView);
            item_list = (LinearLayout) itemView.findViewById(R.id.item_list);
            tv_hour = (TextView) itemView.findViewById(R.id.tv_hour);
            tv_min = (TextView) itemView.findViewById(R.id.tv_min);
            tv_second = (TextView) itemView.findViewById(R.id.tv_second);
            end_text = (TextView) itemView.findViewById(R.id.end_text);
            time = (LinearLayout) itemView.findViewById(R.id.time);
            immediately_auction = (LinearLayout) itemView.findViewById(R.id.immediately_auction);
        }
    }

}
