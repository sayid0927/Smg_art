package com.smg.art.view.webview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.smg.art.utils.keyboard.Loading;
import com.smg.art.utils.keyboard.NetUtil;
import com.tencent.smtt.sdk.DownloadListener;

/**
 * 建议使用ALAWebView的Activity继承此基类
 *
 * @author Mervin
 * @time 2016年7月29日
 */

public abstract class BaseWebViewActivity extends Activity {
    //webView基类
    protected BaseWebView baseWebView;
    /**
     * 显示进度条
     */
    Loading loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        initView();
        hasNet();
        baseWebView.setDownloadListener(new MyWebViewDownLoadListener());
    }

    /**
     * 销毁时调用
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        baseWebView.removeAllViews();
//      myWebView.destroy();
    }

    /**
     * webview返回上一级时调用
     */
    @Override
    public void onBackPressed() {
        if (baseWebView.canGoBack()) {
            baseWebView.goBack();
        } else {
            finish();
        }
    }

    /**
     * 设置ContentView
     */
    protected abstract void setContentView();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 判断网络是否连接
     */
    protected boolean hasNet() {
        if (!NetUtil.isConnected(this)) {
            ToastMessage("没有网络连接！");
            return false;
        }
        return true;
    }

    /**
     * 显示Toast形式的提示信息
     *
     * @param msg 信息
     */
    public void ToastMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showLoading() {
        if (loading == null) {
            loading = Loading.newLoading(this, "加载中...");
        }
        loading.show();
    }

    /**
     * 隐藏进度条
     */
    public void stopLoading() {
        if (loading != null) {
            loading.cancel();
        }
    }

    private class MyWebViewDownLoadListener implements DownloadListener {

        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
                                    long contentLength) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

    }

}
