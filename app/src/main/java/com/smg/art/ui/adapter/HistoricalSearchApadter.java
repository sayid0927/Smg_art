package com.smg.art.ui.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smg.art.R;
import com.smg.art.bean.HotWordsListBean.DataBean.RecentlyWordsBean;
import com.smg.art.bean.HotWordsListBean;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class HistoricalSearchApadter extends BaseQuickAdapter<HotWordsListBean.DataBean.RecentlyWordsBean, BaseViewHolder> {

    private Context mContext;
    private List<HotWordsListBean.DataBean.RecentlyWordsBean> data;
    private OnClearItemListener onClearItemListener;
    private OnWordItemListener onWordItemListener;

    public HistoricalSearchApadter(List<HotWordsListBean.DataBean.RecentlyWordsBean> data, Context mContext) {
        super(R.layout.item_historical_search, data);
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final HotWordsListBean.DataBean.RecentlyWordsBean item) {
        helper.setText(R.id.tv_word,item.getWord()); 
        helper.getView(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(onClearItemListener!=null){
                     onClearItemListener.OnClearItemListener(item,helper.getPosition());
                 }
            }
        });
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onWordItemListener!=null){
                    onWordItemListener.OnWordItemListener(item);
                }
            }
        });
    }

    public void OnClearItemListener (OnClearItemListener  onClearItemListener){
        this.onClearItemListener =onClearItemListener;
    }

    public  interface  OnClearItemListener {
        void  OnClearItemListener(HotWordsListBean.DataBean.RecentlyWordsBean item, int pistion);
    }

    public void OnWordItemListener (OnWordItemListener onWordItemListener){
        this.onWordItemListener =onWordItemListener;
    }

    public  interface  OnWordItemListener {
        void  OnWordItemListener(HotWordsListBean.DataBean.RecentlyWordsBean item);
    }
}
