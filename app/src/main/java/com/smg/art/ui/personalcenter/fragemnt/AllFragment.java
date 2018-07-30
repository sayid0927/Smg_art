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
import com.smg.art.ui.personalcenter.adapter.FragmentAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Mervin on 2018/7/26 0026.
 */

public class AllFragment extends BaseFragment {
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    private FragmentAdapter fragmentAdapter;

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
                //initDate(++p);
                srl.finishLoadmore();
            }
        });

        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        fragmentAdapter = new FragmentAdapter(getActivity(), list);
        listView.setAdapter(fragmentAdapter);
    }
}
