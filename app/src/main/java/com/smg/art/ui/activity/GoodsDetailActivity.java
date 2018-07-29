package com.smg.art.ui.activity;

import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;

import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.jaeger.library.StatusBarUtil;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.GoodsDetailContract;
import com.smg.art.presenter.impl.activity.GoodsDetailActivityPresenter;

import java.util.Map;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;

public class GoodsDetailActivity extends BaseActivity implements GoodsDetailContract.View {

    @Inject
    GoodsDetailActivityPresenter mPresenter;
    @BindView(R.id.viewpager)
    NestedScrollView viewpager;
    @BindView(R.id.hero_bg_pic)
    ImageView heroBgPic;
    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.webview)
    BridgeWebView webview;
    @BindView(R.id.tv_collectioin)
    TextView tvCollectioin;
    @BindView(R.id.phone_service)
    TextView phoneService;


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_detail;
    }

    @Override
    public void attachView() {
        mPresenter.attachView(this, this);
    }

    @Override
    public void detachView() {
        mPresenter.detachView();
    }

    @Override
    public void initView() {
        setSwipeBackEnable(true);
        webview.setWebViewClient(new MyWebViewClient(webview));
        // 打开页面，也可以支持网络url
        webview.loadUrl("https://www.hao123.com/");
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Rect rect = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                int endOffset = appBarLayout.getHeight() - toolbar.getHeight();
                int startOffset = 0;
                int offset = Math.abs(verticalOffset);
                if (offset <= startOffset) {  //alpha为0
                    toolbar.getBackground().setAlpha(0);
                    toolbarTitle.setVisibility(View.GONE);
                } else if (offset > startOffset && offset < endOffset) { //alpha为0到255
                    float precent = (float) (offset - startOffset) / endOffset;
                    int alpha = Math.round(precent * 255);
                    toolbar.getBackground().setAlpha(alpha);
                    toolbarTitle.setVisibility(View.GONE);
                    if (alpha <= 126) {
                        toolbarBack.setImageResource(R.drawable.arrow_back);
                    } else {
                        toolbarBack.setImageResource(R.drawable.arrow_back);
                    }
                } else if (offset >= endOffset) {  //alpha为255
                    toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    toolbar.getBackground().setAlpha(255);
                    toolbarTitle.setVisibility(View.VISIBLE);
                    toolbarTitle.setText("商品详情");
                    toolbarBack.setImageResource(R.drawable.arrow_back);

                }
            }
        });
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void finish() {
        webview.removeAllViews();
        webview.destroy();
        webview = null;
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @OnClick({R.id.tv_collectioin, R.id.phone_service,R.id.toolbar_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.tv_collectioin:
                break;
            case R.id.phone_service:
                break;
        }
    }

    public class MyWebViewClient extends BridgeWebViewClient {

        public MyWebViewClient(BridgeWebView webView) {
            super(webView);
        }
    }
}
