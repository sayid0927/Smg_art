package com.smg.art.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.smg.art.R;
import com.smg.art.base.Constant;

/**
 * 显示图片工具类
 *
 * @author
 */
public class GlideCommonUtils {

    /**
     * @param context
     * @param picPath   显示头像
     * @param imageView
     */
    public static RequestManager showHead(Context context, String picPath, ImageView imageView) {
        RequestManager requestManager = Glide.with(context.getApplicationContext());
        requestManager.load(Constant.API_BASE_URL + picPath).asBitmap().placeholder(R.mipmap.head).error(R.mipmap.head).into(imageView);
//		requestManager.load(picPath).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.test_back).error(R.drawable.test_back).into(imageView);
        return requestManager;
    }

    /**
     * @param context
     * @param picPath   圆形图片
     * @param imageView
     */
    public static RequestManager showHeadPic(Context context, String picPath, ImageView imageView) {
        RequestManager requestManager = Glide.with(context.getApplicationContext());
        requestManager.load(Constant.API_BASE_URL + picPath).asBitmap().placeholder(R.mipmap.iv_round).error(R.mipmap.iv_round).into(imageView);
//		requestManager.load(picPath).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.test_back).error(R.drawable.test_back).into(imageView);
        return requestManager;
    }

    /**
     * @param context
     * @param picPath   默认正方形图片
     * @param imageView
     */
    public static RequestManager showSquarePic(Context context, String picPath, ImageView imageView) {
        RequestManager requestManager = Glide.with(context.getApplicationContext());
        requestManager.load(Constant.API_BASE_URL + picPath).asBitmap().placeholder(R.mipmap.defaut_square).error(R.mipmap.defaut_square).into(imageView);
//		requestManager.load(picPath).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.test_back).error(R.drawable.test_back).into(imageView);
        return requestManager;
    }
    public static RequestManager showTSquarePic(Context context, String picPath, ImageView imageView) {
        RequestManager requestManager = Glide.with(context.getApplicationContext());
        requestManager.load(picPath).asBitmap().placeholder(R.mipmap.defaut_square).error(R.mipmap.defaut_square).into(imageView);
//		requestManager.load(picPath).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.test_back).error(R.drawable.test_back).into(imageView);
        return requestManager;
    }

    /**
     * @param
     * @param picPath   默认长方形图片
     * @param imageView
     */
    public static RequestManager showRectanglePic(Context context, String picPath, ImageView imageView) {
        RequestManager requestManager = Glide.with(context.getApplicationContext());
        requestManager.load(Constant.API_BASE_URL + picPath).asBitmap().placeholder(R.mipmap.defaut_rectangle).error(R.mipmap.defaut_rectangle).into(imageView);
//		requestManager.load(picPath).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.test_back).error(R.drawable.test_back).into(imageView);
        return requestManager;
    }


}
