package com.smg.art.ui.personalcenter.adapter;

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
import com.smg.art.bean.CollectionBean;
import com.smg.art.utils.DateFormatUtil;
import com.smg.art.utils.GlideCommonUtils;
import com.smg.art.utils.TimeTools;

import java.util.List;


/**
 * Created by Mervin on 2018/7/27 0027.
 */

public class MyCollectionAdapter extends RecyclerView.Adapter<MyCollectionAdapter.ViewHolder> {

    Context context;
    private List<CollectionBean.DataBean> mDatas;
    //用于退出activity,避免countdown，造成资源浪费。
    private SparseArray<CountDownTimer> countDownMap;

    public MyCollectionAdapter(Context context, List<CollectionBean.DataBean> datas) {
        this.context = context;
        mDatas = datas;
        countDownMap = new SparseArray<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_common, parent, false);
        return new ViewHolder(view);
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final CollectionBean.DataBean data = mDatas.get(position);
        long time;
        if (data.getSysDate() > 0) {
            time = data.getSysDate();
        } else {
            time = System.currentTimeMillis();//获取系统时间的10位的时间戳
        }
        if (time < data.getStartTime()) {
            holder.end_time.setText("开始时间为:  " + DateFormatUtil.formartData(data.getStartTime(), "MM月dd日 HH:mm") + "将开始");
            holder.time.setVisibility(View.GONE);
            holder.shop_status.setText("展览中");
        } else if (time > data.getStartTime() && time < data.getEndTime()) {
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
                        holder.end_time.setText("距拍卖结束: ");
                        holder.tv_hour.setText(array[0]);
                        holder.tv_min.setText(array[1]);
                        holder.tv_second.setText(array[2]);
                        holder.shop_status.setText("拍卖中");
                    }

                    public void onFinish() {
                        holder.time.setVisibility(View.GONE);
                        // holder.end_text.setVisibility(View.VISIBLE);
                        holder.end_time.setText("拍卖已结束: " + DateFormatUtil.formartData(data.getEndTime(), "MM月dd日 HH:mm") + "已结束");
                        holder.shop_status.setText("已结束");
                    }
                }.start();

                countDownMap.put(holder.tv_hour.hashCode(), holder.countDownTimer);
            } else {
                holder.time.setVisibility(View.GONE);
                holder.end_time.setText("拍卖已结束: " + DateFormatUtil.formartData(data.getEndTime(), "MM月dd日 HH:mm") + "已结束");
                holder.shop_status.setText("已结束");
                //  holder.end_text.setVisibility(View.VISIBLE);
            }
        } else if (time > data.getEndTime()) {
            holder.end_time.setText("拍卖已结束: " + DateFormatUtil.formartData(data.getEndTime(), "MM月dd日 HH:mm") + "已结束");
            holder.time.setVisibility(View.GONE);
            holder.shop_status.setText("已结束");
        }
        if (!TextUtils.isEmpty(data.getPictureUrl())) {
            String[] pic = data.getPictureUrl().split(",");
            GlideCommonUtils.showSquarePic(context, pic[0], holder.shop_iv);
        } else {
            holder.shop_iv.setImageResource(R.mipmap.defaut_square);
        }
        holder.shop_name.setText(data.getActionName());

    }

    @Override
    public int getItemCount() {
        if (mDatas != null && !mDatas.isEmpty()) {
            return mDatas.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CountDownTimer countDownTimer;
        public ImageView shop_iv;
        public TextView tv_hour;
        public TextView tv_min;
        public TextView tv_second;
        public LinearLayout time;
        public TextView end_text;
        public TextView end_time;
        public TextView shop_status;
        public TextView shop_name;

        public ViewHolder(View itemView) {
            super(itemView);
            shop_iv = (ImageView) itemView.findViewById(R.id.shop_iv);
            tv_hour = (TextView) itemView.findViewById(R.id.tv_hour);
            tv_min = (TextView) itemView.findViewById(R.id.tv_min);
            tv_second = (TextView) itemView.findViewById(R.id.tv_second);
            end_text = (TextView) itemView.findViewById(R.id.end_text);
            time = (LinearLayout) itemView.findViewById(R.id.time);
            end_time = (TextView) itemView.findViewById(R.id.end_time);
            shop_status = (TextView) itemView.findViewById(R.id.shop_status);
            shop_name = (TextView) itemView.findViewById(R.id.shop_name);
        }
    }
}



