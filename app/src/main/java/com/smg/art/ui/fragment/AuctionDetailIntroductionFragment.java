package com.smg.art.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.R;
import com.smg.art.base.AuctionDetailBean;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.base.FindCustomerServiceBean;
import com.smg.art.bean.AuctionGoodsBean;
import com.smg.art.bean.SaveCollectsBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.fragment.AuctionDeatilIntroductionContract;
import com.smg.art.presenter.impl.fragment.AuctionDetailIntroductionPresenter;
import com.smg.art.ui.activity.AuctionDeatilActivity;
import com.smg.art.ui.adapter.ServiceDialogApadter;
import com.smg.art.utils.CallPhone;
import com.smg.art.utils.GlideUtils;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.view.CustomDialog;
import com.smg.art.view.MyBridgeWebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by Lenovo on 2018/7/26.
 */

public class AuctionDetailIntroductionFragment extends BaseFragment implements AuctionDeatilIntroductionContract.View {


    @Inject
    AuctionDetailIntroductionPresenter mPresenter;
    @BindView(R.id.webview)
    MyBridgeWebView webview;
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


    private AuctionGoodsBean.DataBean.RowsBean data;
    private List<FindCustomerServiceBean.DataBean> serviceDatas = new ArrayList<>();
    private ServiceDialogApadter apadter;

    public static AuctionDetailIntroductionFragment getInstance(AuctionGoodsBean.DataBean.RowsBean data) {
        AuctionDetailIntroductionFragment sf = new AuctionDetailIntroductionFragment();
        sf.data = data;
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
        webview.setBackgroundColor(0);
        mPresenter.FetchHomepageGetauctiondetail("id", String.valueOf(data.getId()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        webview.removeAllViews();
        webview.destroy();
        webview = null;
    }

    /**
     * 拍卖品详情
     */
    @Override
    public void FetchHomepageGetauctiondetailSuccess(AuctionDetailBean auctionDetailBean) {

        tvStartPrice.setText(String.valueOf(auctionDetailBean.getData().getStartPrice()));
        tvFrontMoneyAmount.setText(String.valueOf(auctionDetailBean.getData().getFrontMoneyAmount()));
        tvNowprice.setText(String.valueOf(auctionDetailBean.getData().getNowprice()));
        tvActionName.setText(String.valueOf(auctionDetailBean.getData().getActionName()));

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
                apadter.OnServiceItemListener(new ServiceDialogApadter.OnServiceItemListener() {
                    @Override
                    public void OnServiceItemListener(FindCustomerServiceBean.DataBean item) {
                        // TODO
                    }
                });
                rv.setAdapter(apadter);
                CustomDialog mDialogWaiting = new CustomDialog(getActivity(), dialogview, R.style.MyDialog);
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
                break;
            case R.id.phone_service: //电话

                if (LocalAppConfigUtil.getInstance().isLogin()) {
                    if (!TextUtils.isEmpty(LocalAppConfigUtil.getInstance().getServiceTel())) {
                        CallPhone.callPhone(getActivity(), LocalAppConfigUtil.getInstance().getServiceTel());
                    } else {
                        CallPhone.callPhone(getActivity(), "0755-82714092");
                    }
                } else {
                    CallPhone.callPhone(getActivity(), "0755-82714092");
                }

                break;
            case R.id.tv_phone:      // 收藏

                if (data != null)
                    mPresenter.FetchMembercollectspageSave("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                            "goodsId", String.valueOf(data.getGoodsId()));

                break;
            case R.id.tv_now_action:  // 立即出价
                AuctionDeatilActivity.auctionDeatilActivity.setCurrentItem(1);
                break;
        }
    }
}
