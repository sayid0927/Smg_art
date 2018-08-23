package com.smg.art.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smg.art.R;
import com.smg.art.base.AnnouncementAuctionListBean;
import com.smg.art.base.AnnouncementAuctionListBean.DataBean.RowsBean;
import com.smg.art.base.Constant;
import com.smg.art.bean.GoodsBean;
import com.smg.art.utils.GlideCommonUtils;
import com.smg.art.utils.GlideUtils;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class GoodsListApadter extends BaseQuickAdapter<AnnouncementAuctionListBean.DataBean.RowsBean, BaseViewHolder> {

    private Context mContext;
    private List<AnnouncementAuctionListBean.DataBean.RowsBean> data;
    private OnGoodsItemListener onGoodsItemListener;

    public GoodsListApadter(List<AnnouncementAuctionListBean.DataBean.RowsBean> data, Context mContext) {
        super(R.layout.item_goods, data);
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final AnnouncementAuctionListBean.DataBean.RowsBean item) {
        helper.setText(R.id.tv_actionName, item.getActionName());
//        GlideUtils.loadFitCenter(mContext, Constant.BaseImgUrl + item.getPictureUrl(), (ImageView) helper.getView(R.id.iv_actioniv));
        GlideCommonUtils.showSquarePic(mContext,  item.getPictureUrl(), (ImageView) helper.getView(R.id.iv_actioniv));
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onGoodsItemListener != null)
                    onGoodsItemListener.OnGoodsItemListener(item);
            }
        });
    }

    public void OnGoodsItemListener(OnGoodsItemListener onGoodsItemListener) {
        this.onGoodsItemListener = onGoodsItemListener;
    }

    public interface OnGoodsItemListener {
        void OnGoodsItemListener(AnnouncementAuctionListBean.DataBean.RowsBean item);
    }

}
