package com.smg.art.utils.keyboard;

import android.app.AlertDialog;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.smg.art.R;


/**
 * 加载时的提示框
 * Created by THINK on 2018/1/22.
 */

public class Loading extends AlertDialog {

    private Context context;
    private ImageView ivProgress;
    private TextView tvText;

    private Loading(Context context, String text) {
        super(context, R.style.MyDialog);
        setCanceledOnTouchOutside(false);//设置点击外部不可以取消;
        show();
        getWindow().setContentView(R.layout.loading_dialog);
        ivProgress = (ImageView) findViewById(R.id.ivProgress);
        tvText = (TextView) findViewById(R.id.tvText);
        tvText.setText(text);
    }

    public static Loading newLoading(Context context) {
        return newLoading(context, "加载中...");
    }

    public static Loading newLoading(Context context, String text) {
        Loading dialog = new Loading(context, text);
        dialog.context = context;
        return dialog;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.loading_progress);
            ivProgress.startAnimation(animation);
        }
    }

}
