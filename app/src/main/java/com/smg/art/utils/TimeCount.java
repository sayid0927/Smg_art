package com.smg.art.utils;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by qibo on 16/8/9.
 */
public class TimeCount extends CountDownTimer {

    private TextView textView;

    public TimeCount(long millisInFuture, long countDownInterval, TextView textView) {
        super(millisInFuture, countDownInterval);
        this.textView = textView;
    }


    @Override
    public void onTick(long millisUntilFinished) {
        textView.setTextColor(Color.parseColor("#FFFFFF"));
        textView.setClickable(false);
        textView.setText("重新发送" + "(" + millisUntilFinished / 1000 + "s)");
        textView.setTextSize(10);
    }

    @Override
    public void onFinish() {
        textView.setText("获取验证码");
        textView.setClickable(true);
        textView.setTextColor(Color.parseColor("#FFFFFF"));
        textView.setTextSize(13);
    }
}
