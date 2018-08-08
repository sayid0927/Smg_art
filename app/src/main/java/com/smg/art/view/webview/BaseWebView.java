package com.smg.art.view.webview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.AbsoluteLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.smg.art.R;
import com.smg.art.utils.DensityUtils;
import com.smg.art.utils.keyboard.NetUtil;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;


/**
 * 自定义webview，加载进度条
 *
 * @author Mervin
 * @version 1.0
 * @time on 2016/7/29
 */
public class BaseWebView extends WebView {
    private ProgressBar progressbar;
    private Context context;
    private OnTitleChangeListener onTitleChangeListener = null;

    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setProgressDrawable(getResources().getDrawable(R.drawable.mywebview_progress_drawable));
        int progressbarHeight = DensityUtils.dp2px(context, 2);
        progressbar.setLayoutParams(new AbsoluteLayout.LayoutParams(LayoutParams.FILL_PARENT, progressbarHeight, 0, 0));
        addView(progressbar);
        setWebChromeClient(new WebChromeClient());
        setWebViewClient(new WebViewClient());
        initListener();
        initSetting();
    }

    @Override
    public void loadUrl(String url) {
        Log.i("MyWebView:", "开始加载网页..." + url);
        super.loadUrl(url);
    }

    private void initListener() {

    }

    private void initSetting() {
        WebSettings webSettings = getSettings();
        if (Build.VERSION.SDK_INT >= 19) {
            webSettings.setLoadsImagesAutomatically(true);
        } else {
            webSettings.setLoadsImagesAutomatically(false);
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//Android webview 从Lollipop(5.0)开始webview默认不允许混合模式，https当中不能加载http资源，需要设置开启。
//            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//        }
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");
//        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

    }


    // 判断网络是否连接
    protected boolean baseHasNet() {
        if (!NetUtil.isConnected(getContext())) {
            Toast.makeText(getContext(), "没有网络连接", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        AbsoluteLayout.LayoutParams lp = (AbsoluteLayout.LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public void setOnTitleChangeListener(OnTitleChangeListener listener) {
        onTitleChangeListener = listener;
    }

    public interface OnTitleChangeListener {
        void OnTitleChange(String title);
    }

    public class WebViewClient extends com.tencent.smtt.sdk.WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url); // 在当前的webview中跳转到新的url
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (!getSettings().getLoadsImagesAutomatically()) {
                getSettings().setLoadsImagesAutomatically(true);
            }
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            view.loadUrl("file:///android_asset/noWifi.html");
        }
    }

    public class WebChromeClient extends com.tencent.smtt.sdk.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);
                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView webView, String title) {
            super.onReceivedTitle(webView, title);
            Log.i("MyWebView:", "获得web标题..." + title);
            try {
                onTitleChangeListener.OnTitleChange(title);
            } catch (Exception e) {

            }

        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            result.confirm();// 因为没有绑定事件，需要强行confirm,否则页面会变黑显示不了内容。
            return true;
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            return super.onJsConfirm(view, url, message, result);

        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

    }

}


