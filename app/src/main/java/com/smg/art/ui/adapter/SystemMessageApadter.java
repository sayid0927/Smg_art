package com.smg.art.ui.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smg.art.R;
import com.smg.art.bean.RecentMessageBean;
import com.smg.art.bean.SystemMessageBean;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class SystemMessageApadter extends BaseQuickAdapter<SystemMessageBean.DataBean.RowsBean, BaseViewHolder> {

    private Context mContext;
    private List<SystemMessageBean.DataBean.RowsBean> data;

    public SystemMessageApadter(List<SystemMessageBean.DataBean.RowsBean> data, Context mContext) {
        super(R.layout.item_system_message, data);
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, final SystemMessageBean.DataBean.RowsBean item) {
        helper.setText(R.id.tv_time,item.getPublishTime());
        helper.setText(R.id.tv_newsTitle,item.getNewsTitle());
        helper.setText(R.id.tv_summary,item.getSummary());
        helper.setText(R.id.tv_detailContent,item.getDetailContent());
    }
}
