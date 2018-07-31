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

public class HotSearchApadter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context mContext;
    private List<String> data;

    public HotSearchApadter(List<String> data, Context mContext) {
        super(R.layout.item_hot_search, data);
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, final String item) {

        helper.setText(R.id.flow_text,item);
//        helper.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(onGoodsItemListener!=null)
//                onGoodsItemListener.OnGoodsItemListener(item,helper.getPosition());
//            }
//        });
    }



    private  OnGoodsItemListener  onGoodsItemListener;

    public void OnGoodsItemListener (OnGoodsItemListener  onGoodsItemListener){
        this.onGoodsItemListener =onGoodsItemListener;
    }

    public  interface  OnGoodsItemListener {
        void  OnGoodsItemListener(GoodsBean item, int postion);
    }

}
