package com.smg.art.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smg.art.R;
import com.smg.art.base.Constant;
import com.smg.art.bean.HomePageImgBean;
import com.smg.art.utils.GlideCommonUtils;
import com.smg.art.utils.GlideUtils;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class HomeIconApadter extends BaseQuickAdapter<HomePageImgBean.DataBean.CategoryListBean, BaseViewHolder> {

    private Context mContext;
    private List<HomePageImgBean.DataBean.CategoryListBean> data;
    private OnHomeIconItemListener onHomeIconItemListener;

    public HomeIconApadter(List<HomePageImgBean.DataBean.CategoryListBean> data, Context mContext) {
        super(R.layout.item_home_icon, data);
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final HomePageImgBean.DataBean.CategoryListBean item) {

        if(item.getCategoryName().equals("全部")){
            GlideUtils.loadLocation(mContext,R.drawable.mor,(ImageView)helper.getView(R.id.iv_draw));
        }else {
//            GlideUtils.loadFitCenter(mContext,Constant.BaseImgUrl+item.getIco(),(ImageView)helper.getView(R.id.iv_draw),R.drawable.draw_def);
            GlideCommonUtils.showHeadPic(mContext,item.getIco(),(ImageView)helper.getView(R.id.iv_draw));

        }
        helper.setText(R.id.tv_title,item.getCategoryName());

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onHomeIconItemListener != null)
                    onHomeIconItemListener.OnHomeIconItemListener(item ,helper.getPosition());
            }
        });
    }

    public void OnHomeIconItemListener(OnHomeIconItemListener onHomeIconItemListener) {
        this.onHomeIconItemListener = onHomeIconItemListener;
    }

    public interface OnHomeIconItemListener {
        void OnHomeIconItemListener(HomePageImgBean.DataBean.CategoryListBean item,int postion);
    }
}
