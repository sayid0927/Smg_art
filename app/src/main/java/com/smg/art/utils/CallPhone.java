package com.smg.art.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.yanzhenjie.permission.AndPermission;

/**
 * Created by THINK on 2018/5/3.
 */

public class CallPhone {
    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public static void diallPhone(Context context, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);
    }

    /**
     * 拨打电话（直接拨打电话）
     *
     * @param phoneNum 电话号码
     */
    @SuppressLint("MissingPermission")
    public static void callPhone(Activity context, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        if(AndPermission.hasPermission(context, Manifest.permission.CALL_PHONE)) {
            // 有权限，直接do anything.
            context.startActivity(intent);
        } else {
            // 申请权限。
            AndPermission.with(context)
                    .requestCode(100)
                    .permission(Manifest.permission.CALL_PHONE)
                    .send();
        }
    }
}
