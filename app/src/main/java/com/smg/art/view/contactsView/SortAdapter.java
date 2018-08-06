package com.smg.art.view.contactsView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smg.art.R;

import java.util.List;

public class SortAdapter extends BaseQuickAdapter<ContactSortModel, BaseViewHolder> implements SectionIndexer {
    private List<ContactSortModel> list = null;
    private Context mContext;

    public SortAdapter(Context mContext, List<ContactSortModel> list) {
        super(R.layout.item_contact, list);
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    protected void convert(BaseViewHolder helper, ContactSortModel item) {
        int section = getSectionForPosition(helper.getPosition());

        if (helper.getPosition() == getPositionForSection(section)) {
            helper.getView(R.id.tv_catagory).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_catagory, item.getSortLetters());
        } else {
            helper.getView(R.id.tv_catagory).setVisibility(View.GONE);
        }
        helper.setText(R.id.tv_city_name, item.getName());
    }

    public int getSectionForPosition(int position) {
        return list.get(position).getSortLetters().charAt(0);
    }

    public int getPositionForSection(int section) {
        for (int i = 0; i < this.list.size(); i++) {
            String sortStr = list.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Object[] getSections() {
        return null;
    }
}