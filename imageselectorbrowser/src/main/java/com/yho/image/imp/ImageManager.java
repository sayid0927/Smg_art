package com.yho.image.imp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;

public class ImageManager {
    private static final String TAG = ImageManager.class.getName();
    private static final boolean D = true;

    /**
     * Methods: show Description: 显示图片
     *
     * @param context
     * @param file       图片文件
     * @param resErrorId 错误RESID
     * @param height     显示高度
     * @param width      显示宽度
     * @param view       显示组件 void
     * @throws null
     */

    public static void show(Context context, File file, int resErrorId, int height, int width, ImageView view) {
        Picasso.with(context).load(file).placeholder(resErrorId).resize(height, width).centerCrop().into(view);
    }

    /**
     * Methods: setImagePath
     * Description: 显示图片
     *
     * @param context
     * @param imageView
     * @param url       void
     * @throws null
     */

    public static void setImagePath(Context context, ImageView imageView, String url) {
        Picasso.with(context).load(url).into(imageView);
    }

    public static void setImagePath(Context context, String url, final int width, int height, final ImageView view, final CallRefresh callRefresh, int defaluteDraw) {
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                    ImageView imageView = (ImageView) findViewById(R.id.image);
                view.setImageBitmap(bitmap);
                view.invalidate();
                callRefresh.callReflush();
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                if (errorDrawable != null) {
                    view.setImageDrawable(errorDrawable);
                }
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                if (placeHolderDrawable != null) {
                    view.setImageDrawable(placeHolderDrawable);
                }
            }
        };

        if (defaluteDraw != -1) {
            Picasso.with(context).load(url).placeholder(defaluteDraw).error(defaluteDraw).resize(width, height).centerInside().into(target);
        } else {
            Picasso.with(context).load(url).resize(width, height).centerInside().into(target);
        }
//		target = null;
//		System.gc();
    }

    /**
     * Methods: setImagePath
     * Description: 显示图片
     *
     * @param context
     * @param imageView
     * @param file      void
     * @throws null
     */

    public static void setImagePath(Context context, ImageView imageView, File file) {
        Picasso.with(context).load(file).into(imageView);
    }

    public static void resumeTag(Context context) {
        final Picasso picasso = Picasso.with(context);
//		picasso.resumeTag(context);
    }

    public static void pauseTag(Context context) {
        final Picasso picasso = Picasso.with(context);
//		picasso.pauseTag(context);
    }

    public interface CallRefresh {
        void callReflush();
    }

}
