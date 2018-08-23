package com.smg.art.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smg.art.R;
import com.smg.art.base.Constant;
import com.smg.art.bean.AddressBookFriendsBean;
import com.smg.art.bean.GoodsBean;
import com.smg.art.bean.MySection;
import com.smg.art.utils.GlideUtils;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class ContactsApadter extends BaseSectionQuickAdapter<MySection, BaseViewHolder> {

    private Context mContext;
    private List<MySection> data;

    public ContactsApadter(List<MySection> data, Context mContext) {
        super(R.layout.item_contact, R.layout.header_contacts, data);
        this.mContext = mContext;
        this.data = data;
    }



    @Override
    protected void convert(BaseViewHolder helper, final MySection item) {
        AddressBookFriendsBean.DataBean dataBean = item.t;
         helper.setText(R.id.tv_city_name,dataBean.getMemberName());
        GlideUtils.load(mContext, Constant.BaseImgUrl + dataBean.getHeadImg(), (ImageView) helper.getView(R.id.ivHeader));
    }

    @Override
    protected void convertHead(BaseViewHolder helper, MySection item) {
        helper.setText(R.id.tv_section, item.header);
    }
}
