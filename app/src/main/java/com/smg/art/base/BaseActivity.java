package com.smg.art.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.jaeger.library.StatusBarUtil;
import com.smg.art.R;
import com.smg.art.component.AppComponent;
import com.smg.art.view.CustomDialog;
import com.smg.art.view.SwipeBackActivity.SwipeBackActivity;
import com.smg.art.view.SwipeBackActivity.SwipeBackLayout;

import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseActivity extends SwipeBackActivity {


    public final static List<AppCompatActivity> mActivities = new LinkedList<>();
    private SwipeBackLayout mSwipeBackLayout;
    private CustomDialog mDialogWaiting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        ButterKnife.bind(this);
        setupActivityComponent(BaseApplication.getBaseApplication().getAppComponent());
        //沉浸式状态栏
        StatusBarUtil.setColor(this,getResources().getColor(R.color.colorPrimaryDark), 20);
        attachView();
        initView();

        synchronized (mActivities) {
            mActivities.add(this);
        }
    }




    @Override
    protected void onResume() {
        super.onResume();

    }

    public void onPause() {
        super.onPause();

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        return super.dispatchTouchEvent(event);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        synchronized (mActivities) {
            mActivities.remove(this);
        }
        detachView();

    }

    @SuppressWarnings("deprecation")
    public void initSwipeBackLayout() {
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    public void setEdgeTrackingEnabled(int size, int position) {
        if (size == 0) {
        }
        // 只有一个fragment  - 左右滑关闭
        else if (size == 1 && position == 0) {
            mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_ALL);
        }
        // 多个fragment  - 位于左侧尽头 - 只可左滑关闭
        else if (size != 1 && position == 0) {
            mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        }
        // 多个fragment  - 位于右侧尽头 - 只可右滑关闭
        else if (size != 1 && position == size - 1) {
            mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_RIGHT);
        }
        // 多个fragment  - 位于中间 - 屏蔽所有左右滑关闭事件
        else {
            mSwipeBackLayout.setEdgeTrackingEnabled(0);
        }
    }

    protected void finishActivity() {
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
    }

    public   void startActivityIn(Intent intent, Activity curAct) {
        if (intent != null) {
            curAct.startActivity(intent);
            curAct.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
        }
    }

    @Override
    public void finish() {
        super.finish();
        finishActivity();
    }



    protected abstract void setupActivityComponent(AppComponent appComponent);

    public abstract int getLayoutId();

    public abstract void attachView();

    public abstract void detachView();

    public abstract void initView();

}
