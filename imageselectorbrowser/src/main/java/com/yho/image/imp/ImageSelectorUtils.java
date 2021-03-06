package com.yho.image.imp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.yho.image.imageselectorbrowser.ImageSelectorActivity;
import com.yho.image.imageselectorbrowser.ImageSelectorActivity.Mode;
import com.yho.image.imageselectorbrowser.ImageSelectorConstant;

import java.util.ArrayList;

/**
 * ClassName: ImageSelectorUtils Description: 图片选择器工具类
 *
 * @author wuqionghai
 * @version 1.0 2015-9-17
 */
public class ImageSelectorUtils {
    /**
     * Methods: showSimple  单选
     * Description: 显示图片选择器
     *
     * @param context 上下文参数
     * @throws null
     */
    public static void showSimple(Context context) {
        show(context, Mode.MODE_SINGLE, true, 1);
    }

    /**
     * Methods: show
     * Description: 显示图片选择器
     *
     * @param context 上下文参数
     * @throws null
     */
    public static void show(Context context) {
        show(context, Mode.MODE_MULTI, true, 9);
    }

    /**
     * Methods: show
     * Description: 显示图片选择器
     *
     * @param context 上下文参数
     * @param mode    选择模式 {@link ImageSelectorActivity.Mode}
     * @throws null
     */
    public static void show(Context context, int mode) {
        show(context, mode, false, 9);
    }

    /**
     * Methods: show
     * Description:  显示图片选择器
     *
     * @param context      上下文参数
     * @param isShowCamera 是否显示照相机
     * @param mode         选择模式 {@link ImageSelectorActivity.Mode}
     * @throws null
     */
    public static void show(Context context, int mode, boolean isShowCamera) {
        show(context, mode, isShowCamera, 9);
    }

    public static void shows(Context context, int mode, boolean isShowCamera, int requestCode) {
        shows(context, mode, isShowCamera, 9, requestCode);
    }


    /**
     * Methods: show
     * Description: 显示图片选择器
     *
     * @param context      上下文参数
     * @param isShowCamera 是否显示照相机
     * @param count        最多图片的个数
     * @param mode         选择模式 {@link ImageSelectorActivity.Mode}
     * @throws null
     */
    public static void show(Context context, int mode, boolean isShowCamera, int count) {
        ImageSelectorActivity.showImageSelector(context, mode, isShowCamera, count);
    }

    public static void shows(Context context, int mode, boolean isShowCamera, int count, int requestCode) {
        ImageSelectorActivity.showImageSelector(context, mode, isShowCamera, count, requestCode);
    }

    public static void show(Fragment context, int mode, boolean isShowCamera, int count) {
        ImageSelectorActivity.showImageSelector(context, mode, isShowCamera, count);
    }

    /**
     * Methods: getImageSelectorList
     * Description: 显示图片选择器
     *
     * @param requestCode 請求參數
     * @param resultCode  返回参数
     * @param data        返回　Intent
     * @return data 最多图片的组合
     * @throws null
     */

    public static ArrayList<String> getImageSelectorList(int requestCode, int resultCode, Intent data) {
        ArrayList<String> list = null;
        if (requestCode == ImageSelectorConstant.REQUEST_IMAGE && resultCode == Activity.RESULT_OK) {
            list = data.getStringArrayListExtra(ImageSelectorConstant.EXTRA_RESULT);
        }
        return list;
    }

    public static ArrayList<String> getImageSelectorList(int requestCode, int resultCode, Intent data, int request) {
        ArrayList<String> list = null;
        if (requestCode == request && resultCode == Activity.RESULT_OK) {
            list = data.getStringArrayListExtra(ImageSelectorConstant.EXTRA_RESULT);
        }
        return list;
    }

    public static byte[] getImageClipBitmapBytes(int requestCode, int resultCode, Intent data) {
        byte[] bytes = null;
        if (requestCode == ImageSelectorConstant.REQUEST_IMAGE && resultCode == Activity.RESULT_OK) {
            bytes = data.getByteArrayExtra(ImageSelectorConstant.EXTRA_RESULT_BITMAP);
        }
        return bytes;
    }

    /**
     * Methods: showClip  单选截图
     * Description: 显示图片选择器
     *
     * @param context 上下文参数
     * @throws null
     */
    public static void showClip(Context context, int width, int height) {
        ImageSelectorActivity.showImageSelector(context, Mode.MODE_CLIP, true, 1, width, height);
    }
}
