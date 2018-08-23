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
import com.smg.art.bean.AuctionOrderBean;
import com.smg.art.utils.GlideCommonUtils;
import com.smg.art.utils.UIUtils;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class ComplaintImgApadter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context mContext;
    private List<String> data;

    public ComplaintImgApadter(List<String> data, Context context) {
        super(R.layout.item_img, data);
        this.mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, final String item) {
        GlideCommonUtils.showSquarePic(mContext, item, (ImageView) helper.getView(R.id.img));

    }


}
