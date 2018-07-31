package com.smg.art.ui.personalcenter;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.CashDepositiBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.CashDepositContract;
import com.smg.art.presenter.impl.activity.CashDepositPresenter;
import com.smg.art.ui.personalcenter.adapter.CashDepositAdapter;
import com.smg.art.utils.KeyBoardUtils;
import com.smg.art.utils.LocalAppConfigUtil;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mervin on 2018/7/26 0026.
 */

public class CashDepositActivity extends BaseActivity implements CashDepositContract.View {
    @Inject
    CashDepositPresenter mPresenter;

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
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
    int p = 1;
    private CashDepositAdapter cashDepositAdapter;
    private int count = 10;
    private List<CashDepositiBean.DataBean> list = new ArrayList<CashDepositiBean.DataBean>();


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.cash_deposit;
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
        actionbarTitle.setText(R.string.cashdeposit);
        srl.setPrimaryColorsId(R.color.main_color);
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                list.clear();
                getData(p = 1);
                srl.finishRefresh();
            }

        });
        srl.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                getData(++p);
                srl.finishLoadmore();
            }
        });
        getData(p = 1);
        cashDepositAdapter = new CashDepositAdapter(this, list);
        listView.setAdapter(cashDepositAdapter);
    }

    private void getData(int p) {
        mPresenter.FetchCashDeposit("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()), "page", String.valueOf(p), "rows", String.valueOf(count));
    }

    @OnClick({R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                KeyBoardUtils.hiddenKeyboart(this);
                finish();
                break;
        }
    }

    @Override
    public void FetchCashDepositSuccess(CashDepositiBean cashDepositiBean) {
        if (cashDepositiBean.getStatus() == 1) {
            list.addAll(cashDepositiBean.getData());
            cashDepositAdapter.notifyDataSetChanged();
        } else {
            ToastUtils.showShortToast(cashDepositiBean.getMsg());
        }
    }

    @Override
    public void showError(String message) {

    }
}
