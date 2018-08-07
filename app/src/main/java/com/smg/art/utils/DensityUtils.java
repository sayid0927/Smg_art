package com.smg.art.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by Mervin on 16/7/21.
 */
public class DensityUtils {
    private DensityUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * dp转px
     *
     * @return
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @return
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @return
     */
    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     *
     * @return
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }


    public static final float getHeightInPx(Context context) {
        final float height = context.getResources().getDisplayMetrics().heightPixels;
        return height;
    }

    public static final float getWidthInPx(Context context) {
        final float width = context.getResources().getDisplayMetrics().widthPixels;
        return width;
    }

    public static final float getHeightInDp(Context context) {
        final float height = context.getResources().getDisplayMetrics().heightPixels;
        float heightInDp = px2dp(context, height);
        return heightInDp;
    }

    public static final float getWidthInDp(Context context) {
        final float height = context.getResources().getDisplayMetrics().heightPixels;
        float widthInDp = px2dp(context, height);
        return widthInDp;
    }


}
