package com.smg.art.ui.personalcenter;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
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
import com.smg.art.bean.DeleteCollectionBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.MyCollectionContract;
import com.smg.art.presenter.impl.activity.MyCollectionPresenter;
import com.smg.art.ui.personalcenter.adapter.MyCollectionAdapter;
import com.smg.art.utils.KeyBoardUtils;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.view.RecyclerViewDivider;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mervin on 2018/7/26 0026.
 */

public class MyCollectionActivity extends BaseActivity implements MyCollectionContract.View, SwipeItemClickListener {
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
    SwipeMenuRecyclerView recyclerView;
    int p = 1;
    private int count = 10;
    int position;
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

    /**
     * 菜单创建器，在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            if (viewType == 0) {
                int width = getResources().getDimensionPixelSize(R.dimen.bj_70dp);
                int height = ViewGroup.LayoutParams.MATCH_PARENT;
                SwipeMenuItem deleteItem = new SwipeMenuItem(MyCollectionActivity.this)
                        .setBackground(R.color.color_8d8686)
                        .setText("删除")
                        .setTextColor(Color.WHITE)
                        .setTextSize(16)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加菜单到右侧。
            }
        }
    };

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

    /**
     * RecyclerView的Item的Menu点击监听。
     */
    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            menuBridge.closeMenu();
            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                position = adapterPosition;
                mPresenter.FetchDeleteCollection("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()), "collectsId", String.valueOf(list.get(adapterPosition).getId()));
            }
        }
    };

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
     /*   recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myCollectionAdapter);
     */
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setSwipeItemClickListener(this);
        recyclerView.setSwipeMenuCreator(swipeMenuCreator);
        recyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);
        recyclerView.setAdapter(myCollectionAdapter);
        recyclerView.addItemDecoration(new RecyclerViewDivider(
                this, LinearLayoutManager.VERTICAL, 10, ContextCompat.getColor(this, R.color.grey_e5e5e5)));
    }


    @Override
    public void showError(String message) {

    }

    @Override
    public void FetchMyCollectionSuccess(CollectionBean collectionBean) {
        if (collectionBean.getStatus() == 1) {
            if (p == 1) {
                list.clear();
                if (collectionBean.getData() != null) {
                    list.addAll(collectionBean.getData());
                    srl.setVisibility(View.VISIBLE);
                    noData.setVisibility(View.GONE);
                    myCollectionAdapter.notifyDataSetChanged();
                } else {
                    srl.setVisibility(View.GONE);
                    noData.setVisibility(View.VISIBLE);
                }
            } else {
                if (collectionBean.getData() != null) {
                    list.addAll(collectionBean.getData());
                    myCollectionAdapter.notifyDataSetChanged();
                }
            }
      /*      list.addAll(collectionBean.getData());
            if (collectionBean.getData() != null) {

            }
            if (list.size() > 0) {
                srl.setVisibility(View.VISIBLE);
                noData.setVisibility(View.GONE);
                myCollectionAdapter.notifyDataSetChanged();
            } else {
                srl.setVisibility(View.GONE);
                noData.setVisibility(View.VISIBLE);
            }*/
        } else {
            ToastUtils.showShortToast(collectionBean.getMsg());
        }
    }

    @Override
    public void FetchDeleteCollectionSuccess(DeleteCollectionBean deleteCollectionBean) {
        if (deleteCollectionBean.getStatus() == 1) {
            ToastUtils.showShortToast("已删除");
            list.remove(list.remove(position));
            if (list.size() == 0) {
                srl.setVisibility(View.GONE);
                noData.setVisibility(View.VISIBLE);
            }
            myCollectionAdapter.notifyDataSetChanged();
        } else {
            ToastUtils.showShortToast(deleteCollectionBean.getMsg());
        }
    }

    @Override
    public void onItemClick(View itemView, int position) {

    }

}
