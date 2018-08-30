package com.smg.art.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smg.art.R;
import com.smg.art.base.Constant;
import com.smg.art.bean.AuctionCenterBean;
import com.smg.art.utils.GlideUtils;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class AuctionCentreListApadter extends BaseMultiItemQuickAdapter<AuctionCenterBean.DataBean.ListBean, BaseViewHolder> {

    private  Context context;
    public AuctionCentreListApadter(Context context, List data) {
        super(data);
        this.context = context;
        addItemType(AuctionCenterBean.DataBean.ListBean.LEFT, R.layout.item_auction_conter_left);
        addItemType(AuctionCenterBean.DataBean.ListBean.RIGHT, R.layout.item_auction_conter_right);

    }

    @Override
    protected void convert(BaseViewHolder helper, AuctionCenterBean.DataBean.ListBean item) {
        switch (helper.getItemViewType()) {
            case AuctionCenterBean.DataBean.ListBean.LEFT:
                GlideUtils.loadFitCenter(mContext, Constant.BaseImgUrl + item.getMember().getHeadImg(), (ImageView) helper.getView(R.id.iv_head), R.drawable.draw_def);
                helper.setText(R.id.tv_name, item.getMember().getMemberName());
                helper.setText(R.id.tv_amount,"出价: "+String.valueOf(String.format("%.2f",item.getAmount())));
                break;

            case AuctionCenterBean.DataBean.ListBean.RIGHT:
                GlideUtils.loadFitCenter(mContext, Constant.BaseImgUrl + item.getMember().getHeadImg(), (ImageView) helper.getView(R.id.iv_head), R.drawable.draw_def);
                helper.setText(R.id.tv_name,  item.getMember().getMemberName());
                helper.setText(R.id.tv_amount,"出价: "+String.valueOf(String.format("%.2f",item.getAmount())));
                break;
        }
    }
}
