package com.smg.art.ui.personalcenter.fragemnt;

import android.os.Bundle;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smg.art.R;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.component.AppComponent;
import com.smg.art.presenter.contract.fragment.AuctionOrderConstrat;
import com.smg.art.ui.personalcenter.adapter.AuctionAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Mervin on 2018/7/27 0027.
 */

public class AuctionOrderFragment extends BaseFragment implements AuctionOrderConstrat.View {
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    AuctionAdapter auctionAdapter;


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

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected void initView(Bundle bundle) {
        super.initView(bundle);
        srl.setPrimaryColorsId(R.color.main_color);
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                srl.finishRefresh();
            }

        });
        srl.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                srl.finishLoadmore();
            }
        });
        List<String> mlist = new ArrayList<>();
        mlist.add("1");
        mlist.add("1");
        mlist.add("1");
        mlist.add("1");
        mlist.add("1");
        mlist.add("1");
        mlist.add("1");
        mlist.add("1");
        mlist.add("1");
        mlist.add("1");
        mlist.add("1");
        mlist.add("1");
        auctionAdapter = new AuctionAdapter(getActivity(), mlist);
        listView.setAdapter(auctionAdapter);
    }

    @Override
    public void showError(String message) {

    }
}
