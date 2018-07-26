package com.smg.art.ui.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smg.art.R;
import com.smg.art.bean.AuctionGoodsBean;
import com.smg.art.bean.GoodsBean;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class AuctionGoodsListApadter extends BaseQuickAdapter<AuctionGoodsBean, BaseViewHolder> {

    private Context mContext;
    private List<AuctionGoodsBean> data;

    public AuctionGoodsListApadter(List<AuctionGoodsBean> data, Context mContext) {
        super(R.layout.item_auction_goods, data);
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, final AuctionGoodsBean item) {
//        helper.setText(R.id.file_title,item.getFileName());
//        helper.setText(R.id.down_label,item.getFilePath());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onAuctionGoodsItemListener!=null)
                onAuctionGoodsItemListener.OnAuctionGoodsItemListener(item);
            }
        });
    }



    private  OnAuctionGoodsItemListener  onAuctionGoodsItemListener;

    public void OnAuctionGoodsItemListener (OnAuctionGoodsItemListener  onAuctionGoodsItemListener){
        this.onAuctionGoodsItemListener =onAuctionGoodsItemListener;
    }

    public  interface  OnAuctionGoodsItemListener {
        void  OnAuctionGoodsItemListener(AuctionGoodsBean item);
    }

}
