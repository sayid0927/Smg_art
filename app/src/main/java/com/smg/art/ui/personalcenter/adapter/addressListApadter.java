package com.smg.art.ui.personalcenter.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smg.art.R;
import com.smg.art.base.Constant;
import com.smg.art.bean.AddressBookFriendsBean;
import com.smg.art.bean.AddressListBean;
import com.smg.art.bean.MySection;
import com.smg.art.utils.GlideUtils;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class addressListApadter extends BaseQuickAdapter<AddressListBean, BaseViewHolder> {

    private Context mContext;
    private List<AddressListBean> data;

    public addressListApadter(List<AddressListBean> data,Context context) {
        super(R.layout.item_contact, data);
        this.mContext = context;

    }


    @Override
    protected void convert(BaseViewHolder helper, AddressListBean item) {

    }
}
