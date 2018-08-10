package com.smg.art.ui.personalcenter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.CollectionBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.MyCollectionContract;
import com.smg.art.presenter.impl.activity.MyCollectionPresenter;
import com.smg.art.ui.personalcenter.adapter.MyCollectionAdapter;
import com.smg.art.utils.KeyBoardUtils;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.view.RecyclerViewDivider;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mervin on 2018/7/26 0026.
 */

public class MyCollectionActivity extends BaseActivity implements MyCollectionContract.View {
    @Inject
    MyCollectionPresenter mPresenter;
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
    @BindView(R.id.no_data)
    LinearLayout noData;

    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    MyCollectionAdapter myCollectionAdapter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    int p = 1;
    private int count = 10;
    private List<CollectionBean.DataBean> list = new ArrayList<CollectionBean.DataBean>();

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.my_collection;
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
        actionbarTitle.setText(R.string.mycollection);
        srl.setPrimaryColorsId(R.color.main_color);
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
        myCollectionAdapter = new MyCollectionAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myCollectionAdapter);
        recyclerView.addItemDecoration(new RecyclerViewDivider(
                this, LinearLayoutManager.VERTICAL, 10, ContextCompat.getColor(this, R.color.grey_e5e5e5)));
    }

    private void getData(int p) {
        mPresenter.FetchMyCollection("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()), "page", String.valueOf(p), "rows", String.valueOf(count));
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


    @Override
    public void FetchMyCollectionSuccess(CollectionBean collectionBean) {
        if (collectionBean.getStatus() == 1) {
            list.addAll(collectionBean.getData());
            if (list.size() > 0) {
                srl.setVisibility(View.VISIBLE);
                noData.setVisibility(View.GONE);
                myCollectionAdapter.notifyDataSetChanged();
            } else {
                srl.setVisibility(View.GONE);
                noData.setVisibility(View.VISIBLE);
            }
        } else {
            ToastUtils.showShortToast(collectionBean.getMsg());
        }
    }

    @Override
    public void showError(String message) {

    }
}
