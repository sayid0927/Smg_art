package com.smg.art.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smg.art.R;
import com.smg.art.base.Constant;
import com.smg.art.base.HomePageImgBean;
import com.smg.art.utils.GlideUtils;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class HomeUnderListApadter extends BaseQuickAdapter<HomePageImgBean.DataBean.UnderListBean, BaseViewHolder> {

    private Context mContext;
    private List<HomePageImgBean.DataBean.UnderListBean> data;
    private OnUnderItemListener onUnderItemListener;

    public HomeUnderListApadter(List<HomePageImgBean.DataBean.UnderListBean> data, Context mContext) {
        super(R.layout.item_home_under, data);
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, final HomePageImgBean.DataBean.UnderListBean item) {
        GlideUtils.loadFitCenter(mContext, Constant.BaseImgUrl + item.getImgPath(), (ImageView) helper.getView(R.id.iv_under));
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onUnderItemListener!=null) onUnderItemListener.OnUnderItemListener(item);
            }
        });
    }

    public void OnUnderItemListener (OnUnderItemListener  onUnderItemListener){
        this.onUnderItemListener =onUnderItemListener;
    }
    public  interface  OnUnderItemListener {
        void  OnUnderItemListener(HomePageImgBean.DataBean.UnderListBean item);
    }

}
