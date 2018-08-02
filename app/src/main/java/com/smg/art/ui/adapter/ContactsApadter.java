package com.smg.art.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smg.art.R;
import com.smg.art.base.Constant;
import com.smg.art.bean.AddressBookFriendsBean;
import com.smg.art.bean.GoodsBean;
import com.smg.art.utils.GlideUtils;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class ContactsApadter extends BaseItemDraggableAdapter<AddressBookFriendsBean.DataBean, BaseViewHolder> {

    private Context mContext;
    private List<AddressBookFriendsBean.DataBean> data;
    private OnGoodsItemListener onGoodsItemListener;

    public ContactsApadter(List<AddressBookFriendsBean.DataBean> data, Context mContext) {
        super(R.layout.item_contact, data);
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, final AddressBookFriendsBean.DataBean item) {

         helper.setText(R.id.tv_name,item.getMemberName());
        GlideUtils.load(mContext, Constant.BaseImgUrl + item.getHeadImg(), (ImageView) helper.getView(R.id.iv_head));

    }

    public void OnGoodsItemListener (OnGoodsItemListener  onGoodsItemListener){
        this.onGoodsItemListener =onGoodsItemListener;
    }

    public  interface  OnGoodsItemListener {
        void  OnGoodsItemListener(GoodsBean item, int postion);
    }

}
