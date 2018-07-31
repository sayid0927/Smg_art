package com.smg.art.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.TimeUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.smg.art.R;
import com.smg.art.base.AuctionBuyerDepositBean;
import com.smg.art.base.AuctionDetailBean;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.SaveCollectsBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.GoodsDetailContract;
import com.smg.art.presenter.impl.activity.GoodsDetailActivityPresenter;
import com.smg.art.utils.CallPhone;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.utils.ValidateTime;
import com.smg.art.view.MyBridgeWebView;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;

public class GoodsDetailActivity extends BaseActivity implements GoodsDetailContract.View {

    @Inject
    GoodsDetailActivityPresenter mPresenter;
    @BindView(R.id.viewpager)
    NestedScrollView viewpager;
    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.tv_collectioin)
    TextView tvCollectioin;
    @BindView(R.id.phone_service)
    TextView phoneService;
    @BindView(R.id.tv_actionName)
    TextView tvActionName;
    @BindView(R.id.webview)
    MyBridgeWebView webview;
    @BindView(R.id.banner)
    BGABanner banner;
    @BindView(R.id.tv_startPrice)
    TextView tvStartPrice;
    @BindView(R.id.tv_frontMoneyAmount)
    TextView tvFrontMoneyAmount;
    @BindView(R.id.tv_show)
    TextView tvShow;
    @BindView(R.id.tv_hh)
    TextView tvHh;
    @BindView(R.id.tv_mm)
    TextView tvMm;
    @BindView(R.id.tv_ss)
    TextView tvSs;
    @BindView(R.id.tv_auction)
    TextView tvAuction;
    @BindView(R.id.bt_auction)
    Button btAuction;

    private int postion;
    private AuctionDetailBean detailBean;
    private static final String BaseImgUrl = "http://192.168.1.56:8080/art-world";

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
        webview.setBackgroundColor(0);
        postion = getIntent().getIntExtra("postion", 0);
        mPresenter.FetchHomepageGetauctiondetail("id", String.valueOf(postion));

        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Rect rect = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                int endOffset = appBarLayout.getHeight() - toolbar.getHeight();
                int startOffset = 0;
                int offset = Math.abs(verticalOffset);
                if (offset <= startOffset) {
                    toolbar.getBackground().setAlpha(0);
                    toolbarTitle.setVisibility(View.GONE);
                } else if (offset > startOffset && offset < endOffset) {
                    float precent = (float) (offset - startOffset) / endOffset;
                    int alpha = Math.round(precent * 255);
                    toolbar.getBackground().setAlpha(alpha);
                    toolbarTitle.setVisibility(View.GONE);
                    if (alpha <= 126) {
                        toolbarBack.setImageResource(R.drawable.arrow_back);
                    } else {
                        toolbarBack.setImageResource(R.drawable.arrow_back);
                    }
                } else if (offset >= endOffset) {
                    toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    toolbar.getBackground().setAlpha(255);
                    toolbarTitle.setVisibility(View.VISIBLE);
                    toolbarTitle.setText("详情介绍");
                    toolbarBack.setImageResource(R.drawable.arrow_back);

                }
            }
        });
    }

    @Override
    public void showError(String message) {
        ToastUtils.showLongToast(message);
    }


    /**
     * 新增收藏商品
     */
    @Override
    public void FetchMembercollectspageSaveSuccess(SaveCollectsBean saveCollectsBean) {
        ToastUtils.showLongToast(saveCollectsBean.getMsg());
    }

    /**
     * 保证金支付
     */
    @Override
    public void FetchAuctionBuyerDepositSuccess(AuctionBuyerDepositBean auctionBuyerDepositBean) {
        ToastUtils.showLongToast(auctionBuyerDepositBean.getMsg());
    }

    /**
     * 拍卖品详情
     */
    @Override
    public void FetchHomepageGetauctiondetailSuccess(AuctionDetailBean auctionDetailBean) {
        this.detailBean = auctionDetailBean;
        tvActionName.setText(auctionDetailBean.getData().getActionName());
        tvStartPrice.setText("￥ " + String.valueOf(auctionDetailBean.getData().getStartPrice()));
        tvFrontMoneyAmount.setText("￥ " + String.valueOf(auctionDetailBean.getData().getFrontMoneyAmount()));

        if (!ValidateTime.TimeCompare(TimeUtils.getNowTimeString(), auctionDetailBean.getData().getStartTime())) {
            String timfe = ValidateTime.getDistanceTime(auctionDetailBean.getData().getStartTime(), TimeUtils.getNowTimeString());
            String[] times = timfe.split(",");
            tvShow.setText("预展中");
            tvAuction.setText("距拍卖开始");
            if (times.length == 3) {
                if (Integer.valueOf(times[0]) < 10) {
                    tvHh.setText("0" + times[0]);
                } else {
                    tvHh.setText(times[0]);
                }
                if (Integer.valueOf(times[1]) < 10) {
                    tvMm.setText("0" + times[1]);
                } else {
                    tvMm.setText(times[1]);
                }
                if (Integer.valueOf(times[2]) < 10) {
                    tvSs.setText("0" + times[2]);
                } else {
                    tvSs.setText(times[2]);
                }
            }
        } else {
            tvShow.setText("预展结束");
            tvAuction.setText("拍卖结束");
            tvHh.setText("00");
            tvMm.setText("00");
            tvSs.setText("00");

        }

        String[] split = auctionDetailBean.getData().getPictureUrl().split(",");
        List<String> imgUrls = new ArrayList<>();

        for (int i = 0; i < split.length; i++) {
            imgUrls.add(BaseImgUrl + split[i]);
        }

        banner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                Glide.with(GoodsDetailActivity.this).load(model).error(R.mipmap.ic_launcher).dontAnimate().centerCrop().into(itemView);
            }
        });
        banner.setData(imgUrls, null);
        webview.loadDataWithBaseURL(null, getNewContent(auctionDetailBean.getData().getAuctionDesc()), "text/html", "utf-8", null);
    }

    @Override
    public void finish() {
        webview.removeAllViews();
        webview.destroy();
        webview = null;
        super.finish();
    }

    @SuppressLint("MissingPermission")
    @OnClick({R.id.tv_collectioin, R.id.phone_service, R.id.toolbar_back, R.id.bt_auction})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back: //返回
                finish();
                break;
            case R.id.tv_collectioin:  //收藏
                if (detailBean != null)
                    mPresenter.FetchMembercollectspageSave("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                            "goodsId", String.valueOf(detailBean.getData().getGoodsId()));
                break;
            case R.id.bt_auction:  // 交保证金参与
                if (detailBean != null)
                    mPresenter.FetchAuctionBuyerDeposit("auctionId", String.valueOf(detailBean.getData().getId()),
                            "goodsId", String.valueOf(detailBean.getData().getGoodsId()),
                            "memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                            "amount", String.valueOf(detailBean.getData().getFrontMoneyAmount()));
                break;

            case R.id.phone_service:  //客服
                if (LocalAppConfigUtil.getInstance().isLogin()) {
                    if (!TextUtils.isEmpty(LocalAppConfigUtil.getInstance().getServiceTel())) {
                        CallPhone.callPhone(this, LocalAppConfigUtil.getInstance().getServiceTel());
                    } else {
                        CallPhone.callPhone(this, "0755-82714092");
                    }
                } else {
                    CallPhone.callPhone(this, "0755-82714092");
                }
                break;
        }
    }


    private String getNewContent(String htmltext) {
        Document doc = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width", "100%").attr("height", "auto");
        }
        return doc.toString();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        AndPermission.onRequestPermissionsResult(requestCode, permissions, grantResults, listener);
    }

    private PermissionListener listener = new PermissionListener() {
        @SuppressLint("MissingPermission")
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。
            if (!TextUtils.isEmpty(LocalAppConfigUtil.getInstance().getServiceTel())) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + LocalAppConfigUtil.getInstance().getServiceTel());
                intent.setData(data);
                startActivity(intent);
            } else {
                Intent intent2 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:0755-82714092"));
                startActivity(intent2);
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {

        }
    };





}
