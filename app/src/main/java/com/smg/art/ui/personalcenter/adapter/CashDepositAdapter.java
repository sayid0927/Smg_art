package com.smg.art.ui.personalcenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smg.art.R;
import com.smg.art.bean.CashDepositiBean;

import java.util.List;

/**
 * Created by Mervin on 2018/7/26 0026.
 */

public class CashDepositAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    //private List<ConsumerDetails.DataBean> mList;
    private List<CashDepositiBean.DataBean> mList;

    public CashDepositAdapter(Context context, List<CashDepositiBean.DataBean> list) {
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
        CashDepositiBean.DataBean dataBean = mList.get(position);
        viewHolder.price.setText(String.format("%.2f", dataBean.getAmount()));
        viewHolder.shop_name.setText(dataBean.getActionName());
        if (dataBean.getStatus() == 0) {
            if(dataBean.getMainStatus()==3){
                viewHolder.status_btn.setVisibility(View.VISIBLE);
                viewHolder.status_text.setVisibility(View.GONE);
                viewHolder.imageView.setVisibility(View.GONE);
                viewHolder.status_btn.setText("预展中");
            }else if(dataBean.getMainStatus()==4){
                viewHolder.status_btn.setVisibility(View.VISIBLE);
                viewHolder.status_text.setVisibility(View.GONE);
                viewHolder.imageView.setVisibility(View.GONE);
                viewHolder.status_btn.setText("拍卖中");
            }else {
                viewHolder.status_btn.setVisibility(View.GONE);
                viewHolder.status_text.setVisibility(View.VISIBLE);
                if(dataBean.getRemark()!=null){
                    if(dataBean.getRemark().equals("逾期未支付，扣除保证金")){
                        viewHolder.imageView.setVisibility(View.VISIBLE);
                    }else {
                        viewHolder.imageView.setVisibility(View.GONE);
                    }
                    viewHolder.status_text.setText(dataBean.getRemark());
                }
            }
        } else if (dataBean.getStatus() == 1) {
            viewHolder.status_btn.setVisibility(View.GONE);
            viewHolder.status_text.setVisibility(View.VISIBLE);
            viewHolder.imageView.setVisibility(View.GONE);
            viewHolder.status_text.setText(dataBean.getRemark());
        }
        viewHolder.time.setText(dataBean.getCreateTime());
        return convertView;
    }

    public static class ViewHolder {
        private TextView shop_name;
        private TextView time;
        private TextView price;
        private TextView status_text;
        private TextView status_btn;
        private ImageView imageView;

        public ViewHolder(View view) {
            shop_name = (TextView) view.findViewById(R.id.shop_name);
            time = (TextView) view.findViewById(R.id.time);
            price = (TextView) view.findViewById(R.id.price);
            status_text = (TextView) view.findViewById(R.id.status_text);
            status_btn = (TextView) view.findViewById(R.id.status_btn);
            imageView=(ImageView) view.findViewById(R.id.red_image);
        }
    }

}
