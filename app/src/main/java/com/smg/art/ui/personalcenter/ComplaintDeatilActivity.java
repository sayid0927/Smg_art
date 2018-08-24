package com.smg.art.ui.personalcenter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.TimeUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.base.BaseFragmentPageAdapter;
import com.smg.art.bean.AuctionOrderBean;
import com.smg.art.bean.ComplaintBean;
import com.smg.art.bean.TabEntity;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.ComplaintActivityContract;
import com.smg.art.presenter.impl.activity.ComplaintActivityPresenter;
import com.smg.art.ui.personalcenter.adapter.ComplaintImgApadter;
import com.smg.art.ui.personalcenter.fragemnt.ComplaintApplyFragment;
import com.smg.art.ui.personalcenter.fragemnt.ComplaintListFragment;
import com.smg.art.ui.personalcenter.fragemnt.ComplaintRecordFragment;
import com.smg.art.utils.GlideCommonUtils;
import com.smg.art.view.NoScrollViewPager;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ComplaintDeatilActivity extends BaseActivity {


    @BindView(R.id.actionbar_title)
    TextView actionbarTitle;
    @BindView(R.id.rl_back)
    AutoRelativeLayout rlBack;

    private AuctionOrderBean.DataBean dataBean;


    @BindView(R.id.tv_status)
    TextView tvStatus;

    @BindView(R.id.tv_orderStatus)
    TextView tvOrderStatus;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_ord)
    TextView tvOrd;
    @BindView(R.id.tv_ord_time)
    TextView tvOrdTime;
    @BindView(R.id.tv_auc_time)
    TextView tvAucTime;
    @BindView(R.id.tv_com_time)
    TextView tvComTime;
    @BindView(R.id.tv_complainResult)
    TextView tvComplainResult;
    @BindView(R.id.tv_complain)
    TextView tvComplain;

    @BindView(R.id.iv_header)
    ImageView ivHeader;

    @BindView(R.id.iv_he)
    ImageView ivHe;
    @BindView(R.id.rv)
    RecyclerView rv;

    private ComplaintImgApadter apadter;
    private List<String> strings = new ArrayList<>();

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_complaint_deatil;
    }

    @Override
    public void attachView() {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void initView() {
        setSwipeBackEnable(true);
        actionbarTitle.setText("投诉详情");
        String data = getIntent().getStringExtra("data");
        dataBean = new Gson().fromJson(data, AuctionOrderBean.DataBean.class);
        apadter = new ComplaintImgApadter(strings, this);
        rv.setLayoutManager(new GridLayoutManager(this, 3));
        rv.setAdapter(apadter);
        if (dataBean != null) {
            if (dataBean.getOrderStatus() != null && dataBean.getOrderStatus().equals("1")) {
                tvStatus.setText("未处理");
                tvOrderStatus.setText("平台未处理");
                ivHe.setVisibility(View.GONE);
            } else  if(dataBean.getOrderStatus() != null && dataBean.getOrderStatus().equals("2")){
                tvStatus.setText("已处理");
                ivHe.setVisibility(View.VISIBLE);
                tvOrderStatus.setText("您与平台已达成协议");
            }

            tvTitle.setText(dataBean.getActionName());
            tvOrd.setText("交易单号:  " + dataBean.getBidNo());
            tvOrdTime.setText("下单时间:  " + TimeUtils.millis2String(dataBean.getComplainTime(), "yyyy-MM-dd HH:mm:ss"));
            tvAucTime.setText("交割时间: " + TimeUtils.millis2String(dataBean.getDeliveryTime(), "yyyy-MM-dd HH:mm:ss"));
            tvComTime.setText("投诉时间: " + TimeUtils.millis2String(dataBean.getComplainTime(), "yyyy-MM-dd HH:mm:ss"));
            tvComplainResult.setText(dataBean.getComplainResult());
            tvComplain.setText(dataBean.getComplain());
            String[] pic = dataBean.getPictureUrl().split(",");
            GlideCommonUtils.showSquarePic(this, pic[0], ivHeader);
            String imgUrl = dataBean.getComplainImageUrl();

            if (imgUrl != null) {
                String[] imgUrls = dataBean.getComplainImageUrl().split(";");
                for (int i = 0; i < imgUrls.length; i++) {
                    strings.add(imgUrls[i]);
                }
                apadter.setNewData(strings);
            }
        }
    }


    @OnClick({R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:  // 返回
                finish();
                break;
        }
    }
}
