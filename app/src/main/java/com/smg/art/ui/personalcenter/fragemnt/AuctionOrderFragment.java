package com.smg.art.ui.personalcenter.fragemnt;

import android.os.Bundle;
import android.widget.ListView;

import com.blankj.utilcode.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smg.art.R;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.bean.AuctionOrderBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.fragment.AuctionOrderConstrat;
import com.smg.art.presenter.impl.fragment.AuctionOrderPresenter;
import com.smg.art.ui.personalcenter.adapter.AuctionAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Mervin on 2018/7/27 0027.
 */

public class AuctionOrderFragment extends BaseFragment implements AuctionOrderConstrat.View {
    @Inject
    AuctionOrderPresenter mPresenter;

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    AuctionAdapter auctionAdapter;
    int p = 1;
    private int count = 10;
    private List<AuctionOrderBean.DataBean> list = new ArrayList<AuctionOrderBean.DataBean>();


    @Override
    public void loadData() {
        setState(Constant.STATE_SUCCESS);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.auction_fragment_order;
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
    protected void initView(Bundle bundle) {
        super.initView(bundle);
        int type = bundle.getInt("type", 0);
        srl.setPrimaryColorsId(R.color.main_color);
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
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
        auctionAdapter = new AuctionAdapter(getActivity(), list);
        listView.setAdapter(auctionAdapter);
    }

    public void getData(int p) {
        mPresenter.FetchAuctionOrder("memberId", "78", "type", "1", "page", String.valueOf(p), "rows", String.valueOf(count));
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void FetchAuctionOrderSuccess(AuctionOrderBean auctionOrderBean) {
        if (auctionOrderBean.getStatus() == 1) {
            list.addAll(auctionOrderBean.getData());
            auctionAdapter.notifyDataSetChanged();
        } else {
            ToastUtils.showShortToast(auctionOrderBean.getMsg());
        }
    }
}
