package com.smg.art.ui.personalcenter.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smg.art.R;
import com.smg.art.bean.AddressListBean;
import com.smg.art.bean.AuctionOrderBean;
import com.smg.art.utils.GlideCommonUtils;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class ComplaintIngApadter extends BaseQuickAdapter<AuctionOrderBean.DataBean, BaseViewHolder> {

    private Context mContext;
    private List<AddressListBean> data;
    private OnComplaintPostItemListener onComplaintPostItemListener;

    public ComplaintIngApadter(List<AuctionOrderBean.DataBean> data, Context context) {
        super(R.layout.item_complaint_ing, data);
        this.mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, final AuctionOrderBean.DataBean item) {
        helper.setText(R.id.tv_actionName, item.getActionName());
        helper.setText(R.id.tv_orderStatus, "投诉原因: "+ item.getComplain ());
        String[] pic = item.getPictureUrl().split(",");
        GlideCommonUtils.showSquarePic(mContext, pic[0], (ImageView) helper.getView(R.id.iv_header));

        helper.getView(R.id.bt_auction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onComplaintPostItemListener!=null) onComplaintPostItemListener.OnComplaintPostItemListener(item);
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
