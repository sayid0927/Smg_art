package com.smg.art.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.jaeger.library.StatusBarUtil;
import com.smg.art.R;
import com.smg.art.bean.RongImStateBean;
import com.smg.art.component.AppComponent;
import com.smg.art.ui.activity.GuideActivity;
import com.smg.art.ui.login.LoginActivity;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.utils.MyConnectionStatusListener;
import com.smg.art.utils.RongIMCStateful;
import com.smg.art.utils.RongIMCUtils;
import com.smg.art.view.CustomDialog;
import com.smg.art.view.SwipeBackActivity.SwipeBackActivity;
import com.smg.art.view.SwipeBackActivity.SwipeBackLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;
import io.rong.imkit.RongIM;

public abstract class BaseActivity extends SwipeBackActivity {


    public final static List<AppCompatActivity> mActivities = new LinkedList<>();
    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        setupActivityComponent(BaseApplication.getBaseApplication().getAppComponent());
        //沉浸式状态栏
        if (Build.VERSION.SDK_INT > 19) {
            StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimaryDark), 10);
        }
        attachView();
        initView();
        EventBus.getDefault().register(this);
        synchronized (mActivities) {
            mActivities.add(this);
        }
    }

    @Subscribe
    public void getEventBus(final RongImStateBean rongImStateBean) {
        switch (RongIMCUtils.state) {
            case RongIMCUtils.KICKED_OFFLINE_BY_OTHER_CLIENT:
                Looper.getMainLooper();
                new Handler(BaseApplication.getBaseApplication().getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {

                        View view = View.inflate(BaseActivity.this, R.layout.dialog_rong_state, null);
                        final CustomDialog mDialogWaiting = new CustomDialog(BaseActivity.this, view, R.style.MyDialog);
                        mDialogWaiting.show();
                        mDialogWaiting.setCancelable(true);
                        TextView tvCencls = (TextView) view.findViewById(R.id.tv_cencls);
                        tvCencls.setText(rongImStateBean.getMsg());

                        Button btPost =(Button)view.findViewById(R.id.bt_post);
                        Button btClecn = (Button)view.findViewById(R.id.bt_clecn);
                        btClecn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mDialogWaiting.dismiss();
                            }
                        });

                        btPost.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                RongIM.getInstance().logout();
                                LocalAppConfigUtil.getInstance().setAccessToken("");
                                LocalAppConfigUtil.getInstance().setCurrentMerberId(0);
                                LocalAppConfigUtil.getInstance().setCurrentMerberNo("");
                                LocalAppConfigUtil.getInstance().setJsessionidShiro("");
                                LocalAppConfigUtil.getInstance().setJsessionId("");
                                LocalAppConfigUtil.getInstance().setRCToken("");
                                LocalAppConfigUtil.getInstance().setUserTelephone("");
                                LocalAppConfigUtil.getInstance().setPassword("");
                                killAll();

                                mDialogWaiting.dismiss();
                                Intent intent = new Intent();
                                intent.setClass(BaseActivity.this, LoginActivity.class);
                                startActivityIn(intent,BaseActivity.this);

                            }
                        });
                    }
                });
                break;

        }
    }


    public void killAll() {
        // 复制了一份mActivities 集合
        List<AppCompatActivity> copy;
        synchronized (mActivities) {
            copy = new LinkedList<>(mActivities);
        }
        for (AppCompatActivity activity : copy) {
            activity.finish();
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
        EventBus.getDefault().unregister(this);
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

    public void startActivityIn(Intent intent, Activity curAct) {
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
