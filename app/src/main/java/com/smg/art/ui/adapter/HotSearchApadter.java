package com.smg.art.ui.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smg.art.R;
import com.smg.art.bean.GoodsBean;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class HotSearchApadter extends BaseQuickAdapter<GoodsBean, BaseViewHolder> {

    private Context mContext;
    private List<GoodsBean> data;
    private OnGoodsItemListener onGoodsItemListener;

    public HotSearchApadter(List<GoodsBean> data, Context mContext) {
        super(R.layout.item_goods, data);
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final GoodsBean item) {
//        helper.setText(R.id.file_title,item.getFileName());
//        helper.setText(R.id.down_label,item.getFilePath());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onGoodsItemListener!=null)
                onGoodsItemListener.OnGoodsItemListener(item,helper.getPosition());
            }
        });
    }

    public void OnGoodsItemListener (OnGoodsItemListener  onGoodsItemListener){
        this.onGoodsItemListener =onGoodsItemListener;
    }

    public  interface  OnGoodsItemListener {
        void  OnGoodsItemListener(GoodsBean item, int postion);
    }

}
