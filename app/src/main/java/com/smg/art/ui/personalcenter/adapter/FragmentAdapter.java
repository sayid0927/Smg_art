package com.smg.art.ui.personalcenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smg.art.R;
import com.smg.art.bean.BalanceOfPayBean;

import java.util.List;

/**
 * Created by Mervin on 2018/5/11 0011.
 */

public class FragmentAdapter extends BaseAdapter {

    private Context mContext;
    private List<BalanceOfPayBean.DataBean> mList;
    private LayoutInflater mInflater;

    public FragmentAdapter(Context context, List<BalanceOfPayBean.DataBean> list) {
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
            convertView = mInflater.inflate(R.layout.fragment_item_adapter, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        BalanceOfPayBean.DataBean dataBean = mList.get(position);
        viewHolder.info.setText(dataBean.getDescn());
        viewHolder.time.setText(dataBean.getCreateTime());
        if (dataBean.getType() == 1) {//收入
            viewHolder.price.setText("+" + String.format("%.2f", dataBean.getAmount()));
            viewHolder.price.setTextColor(mContext.getResources().getColor(R.color.green_1bca65));
        } else if (dataBean.getType() == 2) {//支出
            viewHolder.price.setText("-" + String.format("%.2f", dataBean.getAmount()));
            viewHolder.price.setTextColor(mContext.getResources().getColor(R.color.red_fb002f));
        }

        return convertView;
    }

    public static class ViewHolder {
        private TextView info;
        private TextView time;
        private TextView price;

        public ViewHolder(View view) {
            info = (TextView) view.findViewById(R.id.info);
            time = (TextView) view.findViewById(R.id.time);
            price = (TextView) view.findViewById(R.id.price);
        }
    }

}
