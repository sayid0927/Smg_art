package com.smg.art.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
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
import com.smg.art.bean.RefundBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.fragment.AuctionCentreContract;
import com.smg.art.presenter.impl.fragment.AuctionConterPresenter;
import com.smg.art.ui.adapter.AuctionCentreListApadter;
import com.smg.art.utils.GlideUtils;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.view.CustomDialog;
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
import butterknife.OnClick;

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


    private  int  id;
    private AuctionCenterBean.DataBean.MaxMoneyBean maxMoneyBean;
    private List<AuctionCenterBean.DataBean.ListBean> rowsBeans = new ArrayList<>();
    private AuctionCentreListApadter apadter;
    private ScheduledFuture scheduledFuture;

    public static AuctionCentreFragment getInstance(int  id) {
        AuctionCentreFragment sf = new AuctionCentreFragment();
        sf.id = id;
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

    }

    @Override
    protected void initView(Bundle bundle) {
        super.initView(bundle);
        mPresenter.FetchHomepageGetauctiondetail("id", String.valueOf(id));
        apadter = new AuctionCentreListApadter(getActivity(), rowsBeans);
        rvAcution.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAcution.setAdapter(apadter);

        scheduledFuture = BaseApplication.MAIN_EXECUTOR.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                mPresenter.FetchAuctionCenterList("auctionId", String.valueOf(id));
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * 竞价列表以及最高价
     */
    @Override
    public void FetchAuctionCenterListSuccess(AuctionCenterBean auctionCenterBean) {

        initMaxMoneyView(auctionCenterBean.getData().getMaxMoney());

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
    }

    /**
     * 竞价
     */

    @Override
    public void FetchCreatBiddingSuccess(AuctionCenterBean auctionCenterBean) {
//        ToastUtils.showLongToast("请出价成功");
//        etCreatBidding.setText("");
    }

    /**
     * 拍卖品详情
     */
    @Override
    public void FetchHomepageGetauctiondetailSuccess(AuctionDetailBean auctionDetailBean) {
        webview.loadDataWithBaseURL(null, getNewContent(auctionDetailBean.getData().getAuctionDesc()), "text/html", "utf-8", null);
    }

    /**
     * 验证交易密码(Gumq)
     */

    @Override
    public void FetchvalidteTradePwdSuccess(RefundBean refundBean) {

        mPresenter.FetchCreatBidding("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                "auctionId", String.valueOf(id), "amount", etCreatBidding.getText().toString().trim());

    }

    @Override
    public EditText getetCreatBidding() {
        return etCreatBidding;
    }

    private void initMaxMoneyView(AuctionCenterBean.DataBean.MaxMoneyBean maxMoneyBean) {

        if (rowsBeans.size() == 0) {
            tvMoney.setText(String.valueOf(maxMoneyBean.getStartPrice()));
            etCreatBidding.setHint("最低加价" + String.valueOf(maxMoneyBean.getStepSize()) + "元");
        } else {
            tvName.setText(maxMoneyBean.getMember().getMemberName());
            tvMoney.setText(String.valueOf(maxMoneyBean.getAmount()));
            if (EmptyUtils.isNotEmpty(maxMoneyBean.getMember()) && EmptyUtils.isNotEmpty(maxMoneyBean.getMember().getHeadImg()))
                GlideUtils.loadFitCenter(getActivity(), Constant.BaseImgUrl + maxMoneyBean.getMember().getHeadImg(), ivHread, R.drawable.draw_def);

            if (EmptyUtils.isNotEmpty(maxMoneyBean.getStepSize()))
                etCreatBidding.setHint("最低加价" + String.valueOf(maxMoneyBean.getStepSize()) + "元");
        }
        this.maxMoneyBean = maxMoneyBean;
    }

    @OnClick(R.id.ll_creatBidding)
    public void onViewClicked() {

        if (EmptyUtils.isEmpty(etCreatBidding.getText().toString().trim())) {
            ToastUtils.showLongToast("请输入金额");
            return;
        }

        mPresenter.FetchCreatBidding("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                "auctionId", String.valueOf(id), "amount", etCreatBidding.getText().toString().trim());

//        View dialogview = View.inflate(getActivity(), R.layout.dialog_validtetradepwd, null);
//        Button btPost = dialogview.findViewById(R.id.bt_post);
//        Button btClecn = dialogview.findViewById(R.id.bt_clecn);
//        final EditText edPwd = dialogview.findViewById(R.id.ed_pwd);
//        final CustomDialog mDialogWaiting = new CustomDialog(getActivity(), dialogview, R.style.MyDialog);
//        mDialogWaiting.show();
//        mDialogWaiting.setCancelable(true);
//
//        btClecn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mDialogWaiting.dismiss();
//            }
//        });
//
//        btPost.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String pwd = edPwd.getText().toString().trim();
//                if (EmptyUtils.isNotEmpty(pwd)) {
//                    mPresenter.FetchvalidteTradePwd("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
//                            "tradePwd", pwd);
//                    mDialogWaiting.dismiss();
//                } else {
//                    ToastUtils.showLongToast("请输入交易密码");
//                }
//            }
//        });

    }

    @Override
    public void onDestroyView() {
        if (scheduledFuture != null)
            scheduledFuture.cancel(false);
        if (webview != null) {
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
