package com.smg.art.view.webview;

import android.content.res.Configuration;
import android.view.View;
import android.widget.TextView;

import com.smg.art.R;
import com.smg.art.utils.keyboard.Loading;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.zhy.autolayout.AutoRelativeLayout;

import java.lang.reflect.InvocationTargetException;

/**
 * 公用的webviewactivity
 *
 * @author Mervin
 * @time 2016年7月29日
 */
public class PublicWebViewActivity extends BaseWebViewActivity implements View.OnClickListener {
    AutoRelativeLayout rl_back;
    TextView actionbar_title;
    /**
     * 显示进度条
     */
    Loading dialogLoading;
    private String url;//地址
    private String title;//标题

    /**
     * 设置布局
     */
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_webview);
    }

    /**
     * 初始化
     */
    @Override
    protected void initView() {
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        rl_back = findViewById(R.id.rl_back);
        actionbar_title = (TextView) findViewById(R.id.actionbar_title);
        baseWebView = (BaseWebView) findViewById(R.id.base_webView);
        actionbar_title.setText(title);
        rl_back.setOnClickListener(this);
        synCookies(url);
        if (!url.contains("http")) {
            baseWebView.loadDataWithBaseURL(null, url.toString(), "text/html", "utf-8", null);
        } else {
            baseWebView.loadUrl(url);
        }
    }

    /**
     * 同步一下cookie
     */
    public void synCookies(String url) {
        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();//移除
        int userId = 555;
        //  String token = LocalAppConfigUtil.getInstance().getDataInterfaceToken().replace(" ", "");
        //  cookieManager.setCookie(url,"Token="+token);//cookies是在HttpClient中获得的cookie
        CookieSyncManager.getInstance().sync();
    }

    /**
     * 点击监听
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            baseWebView.getClass().getMethod("onPause").invoke(baseWebView, (Object[]) null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            baseWebView.getClass().getMethod("onResume").invoke(baseWebView, (Object[]) null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void showLoading() {
        if (dialogLoading == null) {
            dialogLoading = Loading.newLoading(this, "加载中...");
        }
        dialogLoading.show();
    }

    /**
     * 隐藏进度条
     */
    public void stopLoading() {
        if (dialogLoading != null) {
            dialogLoading.cancel();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            ToastUtils.show(this,"横屏");
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//            ToastUtils.show(this,"竖屏");
        }

    }
}
