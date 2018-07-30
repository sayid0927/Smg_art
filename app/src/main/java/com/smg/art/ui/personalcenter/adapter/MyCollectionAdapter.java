package com.smg.art.ui.personalcenter.adapter;

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
import com.smg.art.bean.TimerItem;
import com.smg.art.utils.TimeTools;

import java.util.List;


/**
 * Created by Mervin on 2018/7/27 0027.
 */

public class MyCollectionAdapter extends RecyclerView.Adapter<MyCollectionAdapter.ViewHolder> {

    private List<TimerItem> mDatas;
    //用于退出activity,避免countdown，造成资源浪费。
    private SparseArray<CountDownTimer> countDownMap;

    public MyCollectionAdapter(Context context, List<TimerItem> datas) {
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
                }
            }.start();

            countDownMap.put(holder.tv_hour.hashCode(), holder.countDownTimer);
        } else {
            holder.time.setVisibility(View.GONE);
            holder.end_text.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        if (mDatas != null && !mDatas.isEmpty()) {
            return mDatas.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //  public TextView statusTv;
        public TextView timeTv;
        public CountDownTimer countDownTimer;
        public TextView tv_hour;
        public TextView tv_min;
        public TextView tv_second;
        public LinearLayout time;
        public TextView end_text;

        public ViewHolder(View itemView) {
            super(itemView);
            //    statusTv = (TextView) itemView.findViewById(R.id.tv_status);
            //   timeTv = (TextView) itemView.findViewById(R.id.tv_time);
            tv_hour = (TextView) itemView.findViewById(R.id.tv_hour);
            tv_min = (TextView) itemView.findViewById(R.id.tv_min);
            tv_second = (TextView) itemView.findViewById(R.id.tv_second);
            end_text = (TextView) itemView.findViewById(R.id.end_text);
            time = (LinearLayout) itemView.findViewById(R.id.time);
        }
    }
}



