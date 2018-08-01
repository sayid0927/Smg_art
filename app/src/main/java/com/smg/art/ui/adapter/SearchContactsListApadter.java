package com.smg.art.ui.adapter;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smg.art.R;
import com.smg.art.base.Constant;
import com.smg.art.bean.GoodsBean;
import com.smg.art.bean.SearchMemberBean;
import com.smg.art.utils.GlideCommonUtils;
import com.smg.art.utils.GlideUtils;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class SearchContactsListApadter extends BaseQuickAdapter<SearchMemberBean.DataBean, BaseViewHolder> {

    private Context mContext;
    private List<SearchMemberBean.DataBean> data;
    private OnAddFindsListener onAddFindsListener;

    public SearchContactsListApadter(List<SearchMemberBean.DataBean> data, Context mContext) {
        super(R.layout.item_search_contacts, data);
        this.mContext = mContext;
        this.data = data;

    }

    @Override
    protected void convert(final BaseViewHolder helper, final SearchMemberBean.DataBean item) {
         helper.setText(R.id.tv_name,item.getMemberName());
//        GlideCommonUtils.showHeadPic(mContext, Constant.BaseImgUrl+item.getHeadImg(),helper.getView(R.id.iv_head));
        GlideUtils.load(mContext, Constant.BaseImgUrl+item.getHeadImg(),helper.getView(R.id.iv_head));
        helper.getView(R.id.tv_add_finds).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onAddFindsListener!=null){
                    onAddFindsListener.OnAddFindsListener(item);
                }
            }
        });
    }

    public void OnAddFindsListener (OnAddFindsListener  onAddFindsListener){
        this.onAddFindsListener =onAddFindsListener;
    }

    public  interface  OnAddFindsListener {
        void  OnAddFindsListener(SearchMemberBean.DataBean item);
    }

}
