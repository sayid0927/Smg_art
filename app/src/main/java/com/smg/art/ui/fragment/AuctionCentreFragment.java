package com.smg.art.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.EmptyUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.R;
import com.smg.art.base.AuctionDetailBean;
import com.smg.art.base.BaseApplication;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.bean.AuctionCenterBean;
import com.smg.art.bean.AuctionGoodsBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.fragment.AuctionCentreContract;
import com.smg.art.presenter.impl.fragment.AuctionConterPresenter;
import com.smg.art.ui.adapter.AuctionCentreListApadter;
import com.smg.art.utils.GlideUtils;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.view.MyBridgeWebView;
import com.smg.art.view.RoundImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Lenovo on 2018/7/26.
 */

public class AuctionCentreFragment extends BaseFragment implements AuctionCentreContract.View {

    @Inject
    AuctionConterPresenter mPresenter;
    @BindView(R.id.rv_acution)
    RecyclerView rvAcution;

    @BindView(R.id.webview)
    MyBridgeWebView webview;
    @BindView(R.id.iv_hread)
    RoundImageView ivHread;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.et_creatBidding)
    EditText etCreatBidding;

    @BindView(R.id.ll_creatBidding)
    LinearLayout llCreatBidding;


    private AuctionGoodsBean.DataBean.RowsBean data;
    private AuctionCenterBean.DataBean.MaxMoneyBean maxMoneyBean;
    private List<AuctionCenterBean.DataBean.ListBean> rowsBeans = new ArrayList<>();
    private AuctionCentreListApadter apadter;
    private ScheduledFuture scheduledFuture;

    public static AuctionCentreFragment getInstance(AuctionGoodsBean.DataBean.RowsBean data) {
        AuctionCentreFragment sf = new AuctionCentreFragment();
        sf.data = data;
        return sf;
    }


    @Override
    public void loadData() {
        setState(Constant.STATE_SUCCESS);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_auction_conter;
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
        mPresenter.FetchHomepageGetauctiondetail("id", String.valueOf(data.getId()));
        apadter = new AuctionCentreListApadter(getActivity(), rowsBeans);
        rvAcution.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAcution.setAdapter(apadter);
        scheduledFuture = BaseApplication.MAIN_EXECUTOR.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                mPresenter.FetchAuctionCenterList("auctionId", String.valueOf(data.getId()));
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * 竞价列表以及最高价
     */
    @Override
    public void FetchAuctionCenterListSuccess(AuctionCenterBean auctionCenterBean) {
        this.maxMoneyBean = auctionCenterBean.getData().getMaxMoney();
        initMaxMoneyView(maxMoneyBean);
        if (rowsBeans.size() != 0) rowsBeans.clear();
        this.rowsBeans = auctionCenterBean.getData().getList();
        for (int i = 0; i < rowsBeans.size(); i++) {
            if (rowsBeans.get(i).getMemberId() == LocalAppConfigUtil.getInstance().getCurrentMerberId()) {
                rowsBeans.get(i).setItemType(AuctionCenterBean.DataBean.ListBean.RIGHT);
            } else {
                rowsBeans.get(i).setItemType(AuctionCenterBean.DataBean.ListBean.LEFT);
            }
        }
        apadter.setNewData(rowsBeans);
        rvAcution.smoothScrollToPosition(apadter.getItemCount() - 1);
    }

    /**
     * 竞价
     */
    @Override
    public void FetchCreatBiddingSuccess(AuctionCenterBean auctionCenterBean) {
        ToastUtils.showLongToast("请出价成功");
        etCreatBidding.setText("");
    }

    /**
     * 拍卖品详情
     */
    @Override
    public void FetchHomepageGetauctiondetailSuccess(AuctionDetailBean auctionDetailBean) {
        webview.loadDataWithBaseURL(null, getNewContent(auctionDetailBean.getData().getAuctionDesc()), "text/html", "utf-8", null);

    }

    private void initMaxMoneyView(AuctionCenterBean.DataBean.MaxMoneyBean maxMoneyBean) {
        GlideUtils.loadFitCenter(getActivity(), Constant.BaseImgUrl + maxMoneyBean.getMember().getHeadImg(), ivHread, R.drawable.draw_def);
        tvName.setText(maxMoneyBean.getMember().getMemberName());
        tvMoney.setText(String.valueOf(maxMoneyBean.getAmount()));
    }

    @OnClick(R.id.ll_creatBidding)
    public void onViewClicked() {
        String creatBidding = etCreatBidding.getText().toString().trim();
        if (!TextUtils.isEmpty(creatBidding)) {
            mPresenter.FetchCreatBidding("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                    "auctionId", String.valueOf(data.getId()), "amount", creatBidding);
        } else {
            ToastUtils.showLongToast("请出价高干200元");
        }
    }

    @Override
    public void onDestroyView() {
        if (scheduledFuture != null)
            scheduledFuture.cancel(false);
        if(webview!=null) {
            webview.removeAllViews();
            webview.destroy();
            webview = null;
        }
        super.onDestroyView();
    }


    private String getNewContent(String htmltext) {
        Document doc = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width", "100%").attr("height", "auto");
        }
        return doc.toString();
    }
}
