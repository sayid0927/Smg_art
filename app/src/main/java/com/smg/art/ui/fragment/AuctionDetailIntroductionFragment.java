package com.smg.art.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.Gson;
import com.smg.art.R;
import com.smg.art.bean.AuctionBuyerDepositBean;
import com.smg.art.bean.AuctionDetailBean;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.bean.FindCustomerServiceBean;
import com.smg.art.bean.AuctionGoodsBean;
import com.smg.art.bean.SaveCollectsBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.db.database.RongUserInfoEntityDao;
import com.smg.art.db.entity.RongUserInfoEntity;
import com.smg.art.presenter.contract.fragment.AuctionDeatilIntroductionContract;
import com.smg.art.presenter.impl.fragment.AuctionDetailIntroductionPresenter;
import com.smg.art.ui.activity.AuctionBuyerDepositActivity;
import com.smg.art.ui.activity.AuctionDeatilActivity;
import com.smg.art.ui.adapter.ServiceDialogApadter;
import com.smg.art.utils.GlideUtils;
import com.smg.art.utils.GreenDaoUtil;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.utils.TimeTools;
import com.smg.art.view.CustomDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
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
import io.rong.imkit.RongIM;
import io.rong.imlib.model.CSCustomServiceInfo;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Lenovo on 2018/7/26.
 */

public class AuctionDetailIntroductionFragment extends BaseFragment implements AuctionDeatilIntroductionContract.View {


    @Inject
    AuctionDetailIntroductionPresenter mPresenter;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.banner)
    BGABanner banner;
    @BindView(R.id.tv_nowprice)
    TextView tvNowprice;
    @BindView(R.id.tv_startPrice)
    TextView tvStartPrice;
    @BindView(R.id.tv_frontMoneyAmount)
    TextView tvFrontMoneyAmount;
    @BindView(R.id.tv_actionName)
    TextView tvActionName;
    @BindView(R.id.tv_collectioin)
    TextView tvCollectioin;
    @BindView(R.id.phone_service)
    TextView phoneService;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_now_action)
    TextView tvNowAction;
    @BindView(R.id.tv_hh)
    TextView tvHh;
    @BindView(R.id.tv_mm)
    TextView tvMm;
    @BindView(R.id.tv_ss)
    TextView tvSs;
    private AuctionDetailBean auctionDetailBean;
    private AuctionGoodsBean.DataBean.RowsBean data;
    private List<FindCustomerServiceBean.DataBean> serviceDatas = new ArrayList<>();
    private ServiceDialogApadter apadter;
    private int depositStatus;
    private CountDownTimer countDownTimer;
    private int id;

    private RongUserInfoEntityDao collectionInfoDao;

    public static AuctionDetailIntroductionFragment getInstance(AuctionGoodsBean.DataBean.RowsBean data) {
        AuctionDetailIntroductionFragment sf = new AuctionDetailIntroductionFragment();
        sf.data = data;
        return sf;
    }

    public static AuctionDetailIntroductionFragment getInstance(int id) {
        AuctionDetailIntroductionFragment sf = new AuctionDetailIntroductionFragment();
        sf.id = id;
        return sf;
    }


    @Override
    public void loadData() {
        setState(Constant.STATE_SUCCESS);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_auction_detail_introduction;
    }

    @Override
    public void attachView() {
        mPresenter.attachView(this, getActivity());
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }


    @Override
    public void showError(String message) {
        ToastUtils.showLongToast(message);
    }

    @Override
    protected void initView(Bundle bundle) {
        super.initView(bundle);
        EventBus.getDefault().register(this);
        this.collectionInfoDao = GreenDaoUtil.getDaoSession().getRongUserInfoEntityDao();
        if (id > 0) {
            mPresenter.FetchHomepageGetauctiondetail("id", String.valueOf(id));
        } else {
            mPresenter.FetchHomepageGetauctiondetail("id", String.valueOf(data.getId()));
        }
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.endsWith(".jpg") || url.endsWith(".png")) {
                    final View dialogview = View.inflate(getActivity(), R.layout.dialog_img, null);
                    final CustomDialog mDialogWaiting = new CustomDialog(getActivity(), dialogview, R.style.MyDialog);
                    mDialogWaiting.show();
                    mDialogWaiting.setCancelable(true);
                    PhotoView ivurl = dialogview.findViewById(R.id.iv_dialog);

                    ivurl.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                        @Override
                        public void onPhotoTap(View view, float x, float y) {
                            mDialogWaiting.dismiss();
                        }
                    });
                    GlideUtils.load(getActivity(), url, ivurl);
                }
                return true;
            }
        });

//         解决页面渲染闪烁问题.
//        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
//        webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (webview != null) {
            webview.removeAllViews();
            webview.destroy();
            webview = null;
        }
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    /**
     * 拍卖品详情
     */
    @Override
    public void FetchHomepageGetauctiondetailSuccess(AuctionDetailBean auctionDetailBean) {
        this.auctionDetailBean = auctionDetailBean;
        tvStartPrice.setText(String.valueOf(auctionDetailBean.getData().getStartPrice()));
        tvFrontMoneyAmount.setText(String.valueOf(auctionDetailBean.getData().getBuyerEnsureAmount()));
        tvNowprice.setText(String.valueOf(String.format("%.2f", auctionDetailBean.getData().getNowprice())));
        tvActionName.setText(String.valueOf(auctionDetailBean.getData().getActionName()));
        depositStatus = auctionDetailBean.getData().getDepositStatus();

        long time;
        if (auctionDetailBean.getData().getSysDate() > 0) {
            time = auctionDetailBean.getData().getSysDate();
        } else {
            time = System.currentTimeMillis();//获取系统时间的10位的时间戳
        }
        if (time < auctionDetailBean.getData().getEndTime()) {
            long countTime = auctionDetailBean.getData().getEndTime() - time;
            countDownTimer = new CountDownTimer(countTime, 1000) {
                public void onTick(long millisUntilFinished) {
                    String hour = TimeTools.getCountTimeByLong(millisUntilFinished);
                    String[] array = hour.split(":");
                    tvHh.setText(array[0]);
                    tvMm.setText(array[1]);
                    tvSs.setText(array[2]);
                }

                public void onFinish() {
                    tvHh.setText("00");
                    tvSs.setText("00");
                    tvMm.setText("00");
                }
            }.start();
        } else if (time > auctionDetailBean.getData().getStartTime()) {
            tvHh.setText("00");
            tvSs.setText("00");
            tvMm.setText("00");
        }
        if (depositStatus == 0) {
            tvNowAction.setText("交保证金参与");
        } else {
            tvNowAction.setText("立即竟价");
        }

        String[] split = auctionDetailBean.getData().getPictureUrl().split(",");
        List<String> imgUrls = new ArrayList<>();

        for (int i = 0; i < split.length; i++) {
            imgUrls.add(Constant.BaseImgUrl + split[i]);
        }

        banner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                GlideUtils.loadFitCenter(getActivity(), model, itemView);
            }
        });
        banner.setData(imgUrls, null);
        webview.loadDataWithBaseURL(null, getNewContent(auctionDetailBean.getData().getAuctionDesc()), "text/html", "utf-8", null);


    }

    /**
     * 新增收藏商品
     */
    @Override
    public void FetchMembercollectspageSaveSuccess(SaveCollectsBean saveCollectsBean) {
        ToastUtils.showLongToast(saveCollectsBean.getMsg());
    }

    /**
     * 查询客服信息
     */
    @Override
    public void FetchFindCustomerServiceSuccess(FindCustomerServiceBean findCustomerServiceBean) {
        if (this.serviceDatas.size() != 0) serviceDatas.clear();
        serviceDatas = findCustomerServiceBean.getData();
        apadter.setNewData(serviceDatas);
        if (findCustomerServiceBean.getData().size() != 0) {

            for (int i = 0; i < findCustomerServiceBean.getData().size(); i++) {
                RongUserInfoEntity r = collectionInfoDao.queryBuilder().where(
                        RongUserInfoEntityDao.Properties.UserId.eq(String.valueOf(findCustomerServiceBean.getData().get(i).getMemberId()))).unique();

                if (r != null) {
                    RongUserInfoEntity rongUserInfoEntity = new RongUserInfoEntity();
                    rongUserInfoEntity.setId(r.getId());
                    rongUserInfoEntity.setUserId(String.valueOf(findCustomerServiceBean.getData().get(i).getMemberId()));
                    rongUserInfoEntity.setUserName(findCustomerServiceBean.getData().get(i).getMemberName());
                    rongUserInfoEntity.setUserPortraitUri(Constant.BaseImgUrl + findCustomerServiceBean.getData().get(i).getHeadImg());
                    collectionInfoDao.update(rongUserInfoEntity);
                } else {
                    RongUserInfoEntity rongUserInfoEntity = new RongUserInfoEntity();
                    rongUserInfoEntity.setUserId(String.valueOf(findCustomerServiceBean.getData().get(i).getMemberId()));
                    rongUserInfoEntity.setUserName(findCustomerServiceBean.getData().get(i).getMemberName());
                    rongUserInfoEntity.setUserPortraitUri(Constant.BaseImgUrl + findCustomerServiceBean.getData().get(i).getHeadImg());
                    collectionInfoDao.insert(rongUserInfoEntity);

                }
            }
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


    @OnClick({R.id.tv_collectioin, R.id.phone_service, R.id.tv_phone, R.id.tv_now_action})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_collectioin:  //  客服

                apadter = new ServiceDialogApadter(serviceDatas, getActivity());
                View dialogview = View.inflate(getActivity(), R.layout.dialog_service, null);
                RecyclerView rv = dialogview.findViewById(R.id.rv_service);
                rv.setLayoutManager(new LinearLayoutManager(getActivity()));

                rv.setAdapter(apadter);
                final CustomDialog mDialogWaiting = new CustomDialog(getActivity(), dialogview, R.style.MyDialog);
                mDialogWaiting.show();
                mDialogWaiting.setCancelable(true);
                mPresenter.FetchFindCustomerService();
                TextView tvCencl = dialogview.findViewById(R.id.tv_cencl);
                tvCencl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mDialogWaiting != null) mDialogWaiting.dismiss();
                    }
                });

                apadter.OnServiceItemListener(new ServiceDialogApadter.OnServiceItemListener() {
                    @Override
                    public void OnServiceItemListener(FindCustomerServiceBean.DataBean item) {
                        CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
                        CSCustomServiceInfo csInfo = csBuilder
                                .nickName("融云").build();
                        RongIM.getInstance().startCustomerServiceChat(getActivity(), item.getMemberNo(), item.getMemberName(), csInfo);
                        if (mDialogWaiting != null) mDialogWaiting.dismiss();
                    }
                });
                break;
            case R.id.phone_service: //电话

                break;
            case R.id.tv_phone:      // 收藏

                if (auctionDetailBean != null)
                    mPresenter.FetchMembercollectspageSave("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                            "goodsId", String.valueOf(auctionDetailBean.getData().getGoodsId()));
                break;
            case R.id.tv_now_action:  // 交保证金参与
                if (auctionDetailBean != null) {
                    if (depositStatus == 0) {
                        Intent intent = new Intent(getActivity(), AuctionBuyerDepositActivity.class);
                        intent.putExtra("data", new Gson().toJson(auctionDetailBean));
                        intent.putExtra("type", 2);
                        AuctionDeatilActivity.auctionDeatilActivity.startActivityIn(intent, getActivity());
                    } else {
                        AuctionDeatilActivity.auctionDeatilActivity.setCurrentItem(1);
                    }
                }
                break;
        }
    }

    @Subscribe
    public void getEventBus(AuctionBuyerDepositBean auctionBuyerDepositBean) {
        //支付保证金回来
        tvNowAction.setText("立即竟价");
        depositStatus = 1;
        AuctionDeatilActivity.auctionDeatilActivity.setdepositStatus(1);
    }
}
