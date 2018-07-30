package com.smg.art.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.github.lzyzsd.jsbridge.BridgeWebView;

/**
 * Created by Administrator on 2018/7/30.
 */

public class MyBridgeWebView extends BridgeWebView {
    public MyBridgeWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyBridgeWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyBridgeWebView(Context context) {
        super(context);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
