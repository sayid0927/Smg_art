package com.smg.art.ui.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smg.art.R;
import com.smg.art.bean.AddressListBean;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class AddressListApadter extends BaseQuickAdapter<AddressListBean.DataBean, BaseViewHolder> {

    private Context mCotext;
    private OnEditItemListener onEditItemListener;
    private OnDeleItemListener onDeleItemListener;

    public AddressListApadter( List<AddressListBean.DataBean> data,Context context) {
        super(R.layout.item_address, data);
        this.mCotext = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final AddressListBean.DataBean item) {

       if(item.getDefaultFlag()==0){
           helper.getView(R.id.tv_def).setVisibility(View.VISIBLE);
       }else {
           helper.getView(R.id.tv_def).setVisibility(View.GONE);
       }
        helper.setText(R.id.tv_name,item.getDeliveryName());
        helper.setText(R.id.tv_phone,item.getDeliveryPhone());

        helper.setText(R.id.tv_adress,item.getProvinceName()+item.getCityName()+ item.getCountyName() +item.getAdress());

        helper.getView(R.id.iv_address_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onEditItemListener!=null) onEditItemListener.OnEditItemListener(item);
            }
        });


        helper.getView(R.id.iv_address_dele).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onDeleItemListener!=null) onDeleItemListener.OnDeleItemListener(item,helper.getLayoutPosition());
            }
        });

    }


    public void OnEditItemListener(OnEditItemListener onEditItemListener) {
        this.onEditItemListener = onEditItemListener;
    }

    public interface OnEditItemListener {
        void OnEditItemListener(AddressListBean.DataBean item);
    }
    

    public void OnDeleItemListener(OnDeleItemListener onDeleItemListener) {
        this.onDeleItemListener = onDeleItemListener;
    }

    public interface OnDeleItemListener {
        void OnDeleItemListener(AddressListBean.DataBean item,int postion);
    }

}
