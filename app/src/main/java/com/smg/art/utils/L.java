package com.smg.art.utils;

import android.util.Log;

/**
 * 我的日志
 * Created by THINK on 2017/12/27.
 */

public class L {
    private static final String TAG = "**********";
    private static boolean isLog = false;

    public static void isLog(boolean isLog) {
        L.isLog = isLog;
    }

    public static void i(String text) {
        if (isLog) {
            Log.i(TAG, text);
        }
    }

    public static void i(String text, String tag) {
        if (isLog) {
            Log.i(tag, text);
        }
    }

    public static void w(String text) {
        if (isLog) {
            Log.w(TAG, text);
        }
    }

    public static void w(String text, String tag) {
        if (isLog) {
            Log.w(tag, text);
        }
    }

    public static void e(String text) {
        if (isLog) {
            Log.e(TAG, text);
        }
    }

    public static void e(String text, String tag) {
        if (isLog) {
            Log.e(tag, text);
        }
    }
}
