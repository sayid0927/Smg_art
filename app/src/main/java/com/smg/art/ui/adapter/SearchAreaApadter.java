package com.smg.art.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smg.art.R;
import com.smg.art.bean.SearchAreaBean;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class SearchAreaApadter extends BaseQuickAdapter<SearchAreaBean.DataBean, BaseViewHolder> {

    private Context mCotext;
    private OnAreaItemListener onAreaItemListener;
    private int  count;

    public SearchAreaApadter(List<SearchAreaBean.DataBean> data, Context context) {
        super(R.layout.item_search_area, data);
        this.mCotext = context;
    }

    public void  setCount(int count){
       this.count = count;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final SearchAreaBean.DataBean item) {
        final TextView text=  helper.getView(R.id.text);
        switch (count){
            case 1:
                text.setText(item.getProvinceName());
                break;
            case 2:
                text.setText(item.getCityName());
                break;
            case 3:
                text.setText(item.getCountyName());
                break;
        }

         helper.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(onAreaItemListener!=null) onAreaItemListener.OnAreaItemListener(item,text.getText().toString());
             }
         });
    }

    public void OnAreaItemListener(OnAreaItemListener onAreaItemListener) {
        this.onAreaItemListener = onAreaItemListener;
    }

    public interface OnAreaItemListener {
        void OnAreaItemListener(SearchAreaBean.DataBean item ,String text);
    }

}
