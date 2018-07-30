package com.smg.art.ui.personalcenter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.component.AppComponent;
import com.smg.art.ui.personalcenter.adapter.MyCollectionAdapter;
import com.smg.art.utils.KeyBoardUtils;
import com.smg.art.utils.TimerItemUtil;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mervin on 2018/7/26 0026.
 */

public class MyCollectionActivity extends BaseActivity {
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

    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    MyCollectionAdapter myCollectionAdapter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.my_collection;
    }

    @Override
    public void attachView() {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void initView() {
        actionbarTitle.setText(R.string.mycollection);
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
        myCollectionAdapter = new MyCollectionAdapter(this, TimerItemUtil.getTimerItemList());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myCollectionAdapter);
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
    protected void onDestroy() {
        super.onDestroy();
        if (myCollectionAdapter != null) {
            myCollectionAdapter.cancelAllTimers();
        }
    }


}
