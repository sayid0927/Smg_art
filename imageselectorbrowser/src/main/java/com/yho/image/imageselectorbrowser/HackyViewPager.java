package com.yho.image.imageselectorbrowser;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * ClassName: HackyViewPager
 * Description: 该类解决图片缩放报错 java.lang.IllegalArgumentException: pointerIndex out of range
 *
 * @author wuqionghai
 * @version 1.0  2015-9-17
 */
public class HackyViewPager extends ViewPager {

    public HackyViewPager(Context context) {
        super(context);
    }

    public HackyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //java.lang.IllegalArgumentException: pointerIndex out of range这个bug是由于Android系统原因
        //这里我们捕获非法参数异常
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return false;
    }
}
