package com.smg.art.ui.personalcenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smg.art.R;

import java.util.ArrayList;
import java.util.List;

public class AddImageGridAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater; // 视图容器

    private OnItemClickListener onItemClickListener;

    private List<String> mImagePaths;

    private int mLimit = 6;

    public AddImageGridAdapter(Context context, List<String> imagePaths, int limit) {
        this.mContext = context;
        this.mLimit = limit;
        this.mImagePaths = makeUrls(imagePaths);
        inflater = LayoutInflater.from(context);
    }

    public void update(List<String> imagePaths) {
        this.mImagePaths = makeUrls(imagePaths);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public int getCount() {
        return mImagePaths.size();
    }

    public Object getItem(int arg0) {

        return null;
    }

    public long getItemId(int arg0) {
        return 0;
    }

    /**
     * ListView Item设置
     */
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.grid_item_add_image,
                    parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.iv_grid_item_add_image);
            holder.del = (ImageView) convertView.findViewById(R.id.del);
//            holder.cancelIcon = (ImageView) convertView.findViewById(R.id.iv_grid_item_new_forum_cancel);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String url = mImagePaths.get(position);

        if (url.contains("drawable")) {
            Glide.with(mContext)
                    .load(R.drawable.default_up_pic)
                    .dontAnimate()
                    .crossFade()
                    .into(holder.image);
            holder.del.setVisibility(View.GONE);

        } else {
            Glide.with(mContext).load(url).into(holder.image);
            holder.del.setVisibility(View.VISIBLE);
        }

//        holder.cancelIcon.setVisibility(View.VISIBLE);

//        if (mImagePaths.size() < 6) {
//            if (position == mImagePaths.size() - 1) {
//                holder.cancelIcon.setVisibility(View.INVISIBLE);
//            }
//        }

//        holder.cancelIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(onItemClickListener != null){
//                    onItemClickListener.onItemCancelClick(position);
//                }
//            }
//        });

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemImageClick(position);
                }
            }
        });

        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemCancelClick(position);
                }
            }
        });
  /*      holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImagePaths.remove(position-1);
                notifyDataSetChanged();
            }
        });*/

        return convertView;
    }

    private List<String> makeUrls(List<String> photoPath) {
        List<String> urls = new ArrayList<String>();
        for (int i = 0; i < photoPath.size(); i++) {
            String url = photoPath.get(i);
            urls.add(url);
        }
        if (photoPath.size() < mLimit) {
            String url = "drawable://" + R.mipmap.defaut_square;
            urls.add(url);
        }
        return urls;
    }

    public interface OnItemClickListener {
        void onItemImageClick(int position);

        void onItemCancelClick(int position);
    }

    public class ViewHolder {
        ImageView image;
        ImageView del;
//        ImageView cancelIcon;
    }
}