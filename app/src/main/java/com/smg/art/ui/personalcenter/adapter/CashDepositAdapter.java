package com.smg.art.ui.personalcenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smg.art.R;

import java.util.List;

/**
 * Created by Mervin on 2018/7/26 0026.
 */

public class CashDepositAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    //private List<ConsumerDetails.DataBean> mList;
    private List<String> mList;

    public CashDepositAdapter(Context context, List<String> list) {
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
            convertView = mInflater.inflate(R.layout.cash_deposit_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
   /*     final ConsumerDetails.DataBean dataBean = mList.get(position);
        viewHolder.info.setText(dataBean.getInfo());
        viewHolder.time.setText(DateFormatUtil.getDateStringByTimeSTamp(dataBean.getCreate_time(), "yyyy-MM-dd HH:mm"));
        viewHolder.price.setText(dataBean.getValue());*/

        return convertView;
    }

    public static class ViewHolder {
        private TextView shop_name;
        private TextView time;
        private TextView price;
        private TextView status_text;
        private TextView status_btn;

        public ViewHolder(View view) {
            shop_name = (TextView) view.findViewById(R.id.shop_name);
            time = (TextView) view.findViewById(R.id.time);
            price = (TextView) view.findViewById(R.id.price);
            status_text = (TextView) view.findViewById(R.id.status_text);
            status_btn = (TextView) view.findViewById(R.id.status_btn);
        }
    }

}
