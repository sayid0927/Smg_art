package com.smg.art.ui.personalcenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smg.art.R;
import com.smg.art.bean.AuctionOrderBean;

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
   /*     final ConsumerDetails.DataBean dataBean = mList.get(position);
        viewHolder.info.setText(dataBean.getInfo());
        viewHolder.time.setText(DateFormatUtil.getDateStringByTimeSTamp(dataBean.getCreate_time(), "yyyy-MM-dd HH:mm"));
        viewHolder.price.setText(dataBean.getValue());*/
        AuctionOrderBean.DataBean dataBean = mList.get(position);
        if (dataBean.getStatus() == 6) {
            viewHolder.auction.setVisibility(View.GONE);
            viewHolder.complaint.setVisibility(View.VISIBLE);
            //   viewHolder.complaint_name.setText();
        } else {
            viewHolder.auction.setVisibility(View.VISIBLE);
            viewHolder.complaint.setVisibility(View.GONE);
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
        private TextView detail;
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
            auction = (LinearLayout) view.findViewById(R.id.auction);
            complaint = (LinearLayout) view.findViewById(R.id.complaint);
            complaint_name = (TextView) view.findViewById(R.id.complaint_name);
            complaint_cause = (TextView) view.findViewById(R.id.complaint_cause);
            complaint_detail = (TextView) view.findViewById(R.id.complaint_detail);
        }
    }

}

