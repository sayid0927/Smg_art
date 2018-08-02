package com.smg.art.ui.personalcenter.fragemnt;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.blankj.utilcode.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smg.art.R;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.bean.BalanceOfPayBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.fragment.BalanceContract;
import com.smg.art.presenter.impl.fragment.BalanceOfPayPresenter;
import com.smg.art.ui.personalcenter.adapter.FragmentAdapter;
import com.smg.art.utils.LocalAppConfigUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Mervin on 2018/7/26 0026.
 */

public class BalanceFragment extends BaseFragment implements BalanceContract.View {
    @Inject
    BalanceOfPayPresenter mPresenter;

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.no_data)
    LinearLayout noData;
    int p = 1;
    int type;
    String upType;
    private FragmentAdapter fragmentAdapter;
    private int count = 10;
    private List<BalanceOfPayBean.DataBean> list = new ArrayList<BalanceOfPayBean.DataBean>();

    @Override
    public void loadData() {
        setState(Constant.STATE_SUCCESS);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_item;
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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (listView != null && isVisibleToUser) {
            getData(p = 1);
        }
    }

    @Override
    protected void initView(Bundle bundle) {
        super.initView(bundle);
        type = bundle.getInt("type", 0);
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
        fragmentAdapter = new FragmentAdapter(getActivity(), list);
        listView.setAdapter(fragmentAdapter);
        getData(p = 1);
    }

    public void getData(int p) {
        if (type == 0) {
            upType = "";
        } else {
            upType = String.valueOf(type);
        }
        mPresenter.FetchBalanceOfPayment("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()), "type", upType, "page", String.valueOf(p), "rows", String.valueOf(count));
    }


    @Override
    public void FetchBalanceOfPaymentSuccess(BalanceOfPayBean balanceOfPayBean) {
        if (balanceOfPayBean.getStatus() == 1) {
            if (p == 1) {
                list.clear();
            }
            list.addAll(balanceOfPayBean.getData());
            if (list.size() > 0) {
                srl.setVisibility(View.VISIBLE);
                noData.setVisibility(View.GONE);
                fragmentAdapter.notifyDataSetChanged();
            } else {
                srl.setVisibility(View.GONE);
                noData.setVisibility(View.VISIBLE);
            }

        } else {
            ToastUtils.showShortToast(balanceOfPayBean.getMsg());
        }
    }

    @Override
    public void showError(String message) {

    }

}
