package com.smg.art.ui.personalcenter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.LogisticInfo;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.LogisticInfoContract;
import com.smg.art.presenter.impl.activity.LogisticInfoPresenter;
import com.smg.art.ui.personalcenter.adapter.LogisticsAdapter;
import com.smg.art.utils.GlideCommonUtils;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lurs on 2018/8/21 0021.
 */

public class LogisticsInformationActivity extends BaseActivity implements LogisticInfoContract.View {
    @Inject
    LogisticInfoPresenter mPresenter;
    int id;
    @BindView(R.id.rl_back)
    AutoRelativeLayout rlBack;
    @BindView(R.id.left_title)
    TextView leftTitle;
    @BindView(R.id.actionbar_title)
    TextView actionbarTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.actionbar_text_action)
    TextView actionbarTextAction;
    @BindView(R.id.courier_name)
    TextView courierName;
    @BindView(R.id.courier_phone)
    TextView courierPhone;
    @BindView(R.id.courier_info)
    LinearLayout courierInfo;
    @BindView(R.id.shop_image)
    ImageView shopImage;
    @BindView(R.id.logistic_status_text)
    TextView logisticStatusText;
    @BindView(R.id.logistic_status)
    TextView logisticStatus;
    @BindView(R.id.expressage_name_text)
    TextView expressageNameText;
    @BindView(R.id.expressage_name)
    TextView expressageName;
    @BindView(R.id.order_num_text)
    TextView orderNumText;
    @BindView(R.id.order_num)
    TextView orderNum;
    @BindView(R.id.shop_info)
    LinearLayout shopInfo;
    @BindView(R.id.listView)
    ListView listView;
    LogisticsAdapter logisticsAdapter;
    List<LogisticInfo.DataBean.AdListBean> list = new ArrayList<>();
    @BindView(R.id.courier_address)
    TextView courierAddress;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.logistic_info;
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
        id = getIntent().getIntExtra("id", 0);
        actionbarTitle.setText(R.string.auction_order);
        logisticsAdapter = new LogisticsAdapter(this, list);
        listView.setAdapter(logisticsAdapter);
        mPresenter.logisticInfo("auctionId", String.valueOf(id));
    }

    @Override
    public void logisticInfoSuccess(LogisticInfo logisticInfo) {
        if (logisticInfo.getStatus() == 1) {
            if (logisticInfo.getData().getAdList().size() > 0) {
                list.addAll(logisticInfo.getData().getAdList());
                courierName.setText("收货人:" + logisticInfo.getData().getAddress().getDeliveryName());
                courierPhone.setText(logisticInfo.getData().getAddress().getDeliveryPhone());
                courierAddress.setText("收货地址:" + logisticInfo.getData().getAddress().getProvinceName() + logisticInfo.getData().getAddress().getCityName() + logisticInfo.getData().getAddress().getCountyName() + logisticInfo.getData().getAddress().getAdress());
                expressageName.setText(logisticInfo.getData().getName());
                orderNum.setText(logisticInfo.getData().getCode());
                GlideCommonUtils.showSquarePic(this, logisticInfo.getData().getPictureUrl(), shopImage);
                switch (logisticInfo.getData().getStatus()) {
                    case 0:
                        logisticStatus.setText("已发货");
                        break;
                    case 1:
                        logisticStatus.setText("已揽件");
                        break;
                    case 2:
                        logisticStatus.setText("已发货");
                        break;
                    case 3:
                        logisticStatus.setText("已签收");
                        break;
                    case 4:
                        logisticStatus.setText("已退签");
                        break;
                    case 5:
                        logisticStatus.setText("已派件");
                        break;
                    case 6:
                        logisticStatus.setText("退回中");
                        break;
                }
                logisticsAdapter.notifyDataSetChanged();
            }
        } else {
            ToastUtils.showShortToast(logisticInfo.getMsg());
        }
    }

    @Override
    public void showError(String message) {

    }

    @OnClick({R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
        }
    }


}
