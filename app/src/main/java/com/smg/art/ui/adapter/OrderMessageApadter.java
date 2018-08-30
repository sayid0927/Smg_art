package com.smg.art.ui.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smg.art.R;
import com.smg.art.bean.OrderMessageBean;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class OrderMessageApadter extends BaseQuickAdapter<OrderMessageBean.DataBean.RowsBean, BaseViewHolder> {

    private Context mContext;
    private List<OrderMessageBean.DataBean.RowsBean> data;

    public OrderMessageApadter(List<OrderMessageBean.DataBean.RowsBean> data, Context mContext) {
        super(R.layout.item_system_message, data);
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, final OrderMessageBean.DataBean.RowsBean item) {

        helper.setText(R.id.tv_time,item.getAddTime());
        helper.setText(R.id.tv_newsTitle,item.getNewsTitle());
        helper.setText(R.id.tv_summary,item.getSummary());
        helper.setText(R.id.tv_detailContent,item.getDetailContent());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onOrderItemListener!=null)
                    onOrderItemListener.OnOrderItemListener(item);
            }
        });

    }

    private OnOrderItemListener onOrderItemListener;

    public void OnOrderItemListener(OnOrderItemListener onOrderItemListener) {
        this.onOrderItemListener = onOrderItemListener;
    }

    public interface OnOrderItemListener {
        void OnOrderItemListener(OrderMessageBean.DataBean.RowsBean item);
    }
    
}
