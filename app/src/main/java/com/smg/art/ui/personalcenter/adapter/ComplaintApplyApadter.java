package com.smg.art.ui.personalcenter.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smg.art.R;
import com.smg.art.base.Constant;
import com.smg.art.bean.AddressListBean;
import com.smg.art.bean.AuctionOrderBean;
import com.smg.art.bean.ComplaintApplyBean;
import com.smg.art.ui.adapter.AddressListApadter;
import com.smg.art.utils.GlideCommonUtils;
import com.smg.art.utils.GlideUtils;
import com.smg.art.utils.UIUtils;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class ComplaintApplyApadter extends BaseQuickAdapter<AuctionOrderBean.DataBean, BaseViewHolder> {

    private Context mContext;
    private List<AuctionOrderBean.DataBean> data;
    private OnComplaintPostItemListener onComplaintPostItemListener;

    public ComplaintApplyApadter(List<AuctionOrderBean.DataBean> data, Context context) {
        super(R.layout.item_complaint_apply, data);
        this.mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, final AuctionOrderBean.DataBean item) {
        helper.setText(R.id.tv_actionName, item.getActionName());
        String[] pic = item.getPictureUrl().split(",");
        GlideCommonUtils.showSquarePic(mContext, pic[0], (ImageView) helper.getView(R.id.iv_header));
        int orderStatus = item.getOrderStatus();
        TextView tvOrderStatus = helper.getView(R.id.tv_orderStatus);
         Button  btAuction = helper.getView(R.id.bt_auction);
        switch (orderStatus) {
            case 0:   // 0未投诉
                tvOrderStatus.setVisibility(View.GONE);
                break;
            case 1:  //  orderStatus=1投诉中；

                tvOrderStatus.setText("您已申请投诉，正在处理中");
                tvOrderStatus.setTextColor(UIUtils.getColor(R.color.red_b20002));
                btAuction.setTextColor(UIUtils.getColor(R.color.red_F1D1D1));
                helper.getView(R.id.bt_auction).setBackgroundResource(R.drawable.text_red_p_bg);
                break;
            case 2:  // orderStatus=2投诉完成
                tvOrderStatus.setText("您已申请投诉，投诉完成");
                tvOrderStatus.setTextColor(UIUtils.getColor(R.color.black));
                helper.getView(R.id.bt_auction).setBackgroundResource(R.drawable.text_red_p_bg);
                break;
            case 3:  // 超过售后期

                Drawable drawable_n = mContext.getResources().getDrawable(R.drawable.address_n_icon);
                drawable_n.setBounds(0, 0, drawable_n.getMinimumWidth(),drawable_n.getMinimumHeight());
                tvOrderStatus.setCompoundDrawables(null, null, drawable_n, null);
                tvOrderStatus.setText("该商品已超过售后期");
                tvOrderStatus.setTextColor(UIUtils.getColor(R.color.black_666666));
                btAuction.setTextColor(UIUtils.getColor(R.color.red_F1D1D1));
                btAuction.setBackgroundResource(R.drawable.text_red_p_bg);

                break;
        }

        btAuction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onComplaintPostItemListener != null)
                        onComplaintPostItemListener.OnComplaintPostItemListener(item);
                }
            });


    }


    public void OnComplaintPostItemListener(OnComplaintPostItemListener onComplaintPostItemListener) {
        this.onComplaintPostItemListener = onComplaintPostItemListener;
    }

    public interface OnComplaintPostItemListener {
        void OnComplaintPostItemListener(AuctionOrderBean.DataBean item);
    }

}
