package com.smg.art.ui.personalcenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.smg.art.R;
import com.smg.art.utils.GlideCommonUtils;

import java.util.List;

/**
 * Created by Mervin on 2018/8/9 0009.
 */

public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<String> mList;

    public GridViewAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.grid_view, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GlideCommonUtils.showSquarePic(mContext, mList.get(position), viewHolder.gridview_iv);
        return convertView;
    }

    public static class ViewHolder {
        ImageView gridview_iv;

        public ViewHolder(View view) {
            gridview_iv = (ImageView) view.findViewById(R.id.gridview_iv);
        }
    }

}
