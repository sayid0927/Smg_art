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

public class SystemMessageApadter extends BaseQuickAdapter<SystemMessageBean, BaseViewHolder> {

    private Context mContext;
    private List<SystemMessageBean> data;

    public SystemMessageApadter(List<SystemMessageBean> data, Context mContext) {
        super(R.layout.item_system_message, data);
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, final SystemMessageBean item) {
//        helper.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(onMessageItemListener!=null)
//                    onMessageItemListener.OnMessageItemListener(item);
//            }
//        });
    }



//    private  OnMessageItemListener  onMessageItemListener;
//
//    public void OnMessageItemListener (OnMessageItemListener  onMessageItemListener){
//        this.onMessageItemListener =onMessageItemListener;
//    }
//
//    public  interface  OnMessageItemListener {
//        void  OnMessageItemListener(SystemMessageBean item);
//    }



}
