package com.smg.art.ui.personalcenter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smg.art.R;
import com.smg.art.bean.LogisticInfo;
import com.smg.art.ui.personalcenter.LogisticsInformationActivity;
import com.smg.art.utils.DateFormatUtil;

import java.util.List;

/**
 * Created by Mervin on 2018/7/10 0010.  LogisticsAdapter
 */

public class LogisticsAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private LogisticsInformationActivity mContext;
    private List<LogisticInfo.DataBean.AdListBean> listData;


    public LogisticsAdapter(LogisticsInformationActivity context, List<LogisticInfo.DataBean.AdListBean> listData) {
        super();
        this.mContext = context;
        this.listData = listData;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.logistics_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        LogisticInfo.DataBean.AdListBean kDataBean = listData.get(position);
        holder.logistics_time.setText(DateFormatUtil.forString(kDataBean.getDate(), "yyyy-MM-dd HH:mm:ss"));
        holder.logistics_name.setText(kDataBean.getInfo());
        if (listData.size() > 0) {
            if (position == listData.size() - 1) {
                holder.line_botton.setVisibility(View.INVISIBLE);
                holder.cycle.setVisibility(View.VISIBLE);
                holder.line_top.setVisibility(View.VISIBLE);
            } else {
                holder.line_botton.setVisibility(View.VISIBLE);
                holder.cycle.setVisibility(View.VISIBLE);
                holder.line_top.setVisibility(View.VISIBLE);
            }
        } else {
            holder.line_botton.setVisibility(View.INVISIBLE);
            holder.line_top.setVisibility(View.INVISIBLE);
            holder.cycle.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }

    public static class ViewHolder {
        TextView logistics_time;
        View line_top, line_botton;
        TextView logistics_name;
        ImageView cycle;

        public ViewHolder(View view) {
            logistics_time = (TextView) view.findViewById(R.id.logistics_time);
            line_top = view.findViewById(R.id.line_top);
            line_botton = view.findViewById(R.id.line_botton);
            logistics_name = (TextView) view.findViewById(R.id.logistics_name);
            cycle = (ImageView) view.findViewById(R.id.cycle);
        }
    }
}
