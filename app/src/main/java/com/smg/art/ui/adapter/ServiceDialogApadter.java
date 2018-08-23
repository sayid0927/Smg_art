package com.smg.art.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smg.art.R;
import com.smg.art.base.Constant;
import com.smg.art.bean.FindCustomerServiceBean;
import com.smg.art.utils.GlideUtils;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class ServiceDialogApadter extends BaseItemDraggableAdapter<FindCustomerServiceBean.DataBean, BaseViewHolder> {

    private Context mContext;
    private List<FindCustomerServiceBean.DataBean> data;
    private OnServiceItemListener onServiceItemListener;

    public ServiceDialogApadter(List<FindCustomerServiceBean.DataBean> data, Context mContext) {
        super(R.layout.item_service, data);
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, final FindCustomerServiceBean.DataBean item) {
        helper.setText(R.id.tv_title,item.getMemberName());
        GlideUtils.loadFitCenter(mContext, Constant.BaseImgUrl + item.getHeadImg(), (ImageView) helper.getView(R.id.iv_draw),R.drawable.draw_def);
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onServiceItemListener!=null) onServiceItemListener.OnServiceItemListener(item);
            }
        });
    }

    public void OnServiceItemListener (OnServiceItemListener  onServiceItemListener){
        this.onServiceItemListener =onServiceItemListener;
    }

    public  interface  OnServiceItemListener {
        void  OnServiceItemListener(FindCustomerServiceBean.DataBean item);
    }

}
