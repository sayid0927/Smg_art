package com.smg.art.ui.activity;


import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smg.art.R;
import com.smg.art.base.AnnouncementAuctionListBean;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.CreatWordsBean;
import com.smg.art.bean.HotWordsListBean;
import com.smg.art.bean.LoginBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.SearchContract;
import com.smg.art.presenter.impl.activity.SearchActivityPresenter;
import com.smg.art.ui.adapter.GoodsListApadter;
import com.smg.art.ui.adapter.HistoricalSearchApadter;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.view.flexbox.adapter.StringTagAdapter;
import com.smg.art.view.flexbox.widget.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements SearchContract.View,
        HistoricalSearchApadter.OnClearItemListener, HistoricalSearchApadter.OnWordItemListener,
        OnLoadmoreListener, OnRefreshListener,StringTagAdapter.OnTagViewItemListener {

    @Inject
    SearchActivityPresenter mPresenter;

    @BindView(R.id.ll_back)
    RelativeLayout llBack;
    @BindView(R.id.rv_historical_search)
    RecyclerView rvHistoricalSearch;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.flex_layout)
    TagFlowLayout flexLayout;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.et_SearchContent)
    EditText etSearchContent;
    @BindView(R.id.nsv)
    NestedScrollView nsv;
    @BindView(R.id.rv_goods)
    RecyclerView rvGoods;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.tv_search)
    TextView tvSearch;

    private HistoricalSearchApadter historicalSearchApadter;
    private List<HotWordsListBean.DataBean.RecentlyWordsBean> historicalSearch = new ArrayList<>();

    private View headerView;
    private int page = 1;
    private int rows = 10;
    private int postion;
    private boolean isClearAll = false;
    private String textWord;
    private String status;

    private GoodsListApadter mAdapter;
    private StringTagAdapter adapter;
    private List<String> sourceData;
    private List<AnnouncementAuctionListBean.DataBean.RowsBean> rowsBeans = new ArrayList<>();

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
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
        setSwipeBackEnable(true);
        status = getIntent().getStringExtra("status");

        if (status.equals("3")) {
            mAdapter = new GoodsListApadter(rowsBeans, this);
            rvGoods.setLayoutManager(new GridLayoutManager(this, 2));
        }

        rvGoods.setAdapter(mAdapter);

        srl.setOnRefreshListener(this);
        srl.setOnLoadmoreListener(this);

        adapter = new StringTagAdapter(this, sourceData);
        adapter.OnTagViewItemListener(this);
        historicalSearchApadter = new HistoricalSearchApadter(historicalSearch, this);
        historicalSearchApadter.addHeaderView(getHeaderView());
        rvHistoricalSearch.setLayoutManager(new LinearLayoutManager(this));
        rvHistoricalSearch.setAdapter(historicalSearchApadter);
        historicalSearchApadter.OnClearItemListener(this);
        historicalSearchApadter.OnWordItemListener(this);
        mPresenter.FetchHotWordsList();



    }

    private View getHeaderView() {
        headerView = View.inflate(this, R.layout.historicalsearch_header, null);
        TextView btDelete = headerView.findViewById(R.id.bt_delete);
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClearAll = true;
                mPresenter.FetchDeleteWordById("word", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()));
            }
        });
        return headerView;
    }

    @OnClick({R.id.ll_back, R.id.iv_search, R.id.tv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back: //返回
                finish();
                break;
            case R.id.iv_search: //搜索
                page = 1;
                textWord = etSearchContent.getText().toString().trim();
                mPresenter.FetchAuctionListByName("actionName", textWord,
                        "status", status, "page", String.valueOf(page), "rows", String.valueOf(rows));
                break;
            case R.id.tv_search:  // 搜索框
                if (tvSearch.getVisibility() == View.VISIBLE && etSearchContent.getVisibility() == View.GONE) {
                    tvSearch.setVisibility(View.GONE);
                    etSearchContent.setVisibility(View.VISIBLE);
                    etSearchContent.setText("");
                }
                if (nsv.getVisibility() == View.GONE && srl.getVisibility() == View.VISIBLE) {
                    nsv.setVisibility(View.VISIBLE);
                    srl.setVisibility(View.GONE);
                    rowsBeans.clear();
                    mAdapter.setNewData(rowsBeans);
                    mAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public void showError(String message) {
        ToastUtils.showLongToast(message);
    }

    /**
     * 获取搜索列表
     */
    @Override
    public void FetchHotWordsListSuccess(final HotWordsListBean hotWordsListBean) {

        if (hotWordsListBean.getData().getHotWords().size() != 0) {
            for (int i = 0; i < hotWordsListBean.getData().getHotWords().size(); i++) {

                View view = View.inflate(this, R.layout.company_grid_item, null);
                TextView textView = (TextView) view.findViewById(R.id.text);
                textView.setText(hotWordsListBean.getData().getHotWords().get(i).getWord());
                flexLayout.addView(view);


                final int finalI = i;
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textWord = hotWordsListBean.getData().getHotWords().get(finalI).getWord();
                        mPresenter.FetchAuctionListByName("actionName", textWord,
                                "status", status, "page", String.valueOf(page), "rows", String.valueOf(rows));
                    }
                });
            }
        }

        if (hotWordsListBean.getData().getRecentlyWords().size() != 0) {
            if (historicalSearch.size() != 0) historicalSearch.clear();
            historicalSearch = hotWordsListBean.getData().getRecentlyWords();
            historicalSearchApadter.setNewData(historicalSearch);
        }
    }

    /**
     * 重新登录
     */
    @Override
    public void onRestartLoging() {
        mPresenter.FetchLogin("account", LocalAppConfigUtil.getInstance().getUserTelephone(),
                "password", LocalAppConfigUtil.getInstance().getPassword());
    }

    /**
     * 重新登录成功
     */
    @Override
    public void FetchLoginSuccess(LoginBean loginBean) {
        if (loginBean.getStatus() == 1) {
            LocalAppConfigUtil.getInstance().setAccessToken(loginBean.getData().getRCToken());
            LocalAppConfigUtil.getInstance().setCurrentMerberId(loginBean.getData().getMemberId());
            LocalAppConfigUtil.getInstance().setCurrentMerberNo(loginBean.getData().getMemberNo());
            LocalAppConfigUtil.getInstance().setJsessionidShiro(loginBean.getData().getJSESSIONID_SHIRO());
            LocalAppConfigUtil.getInstance().setJsessionId(loginBean.getData().getJSESSIONID());
            LocalAppConfigUtil.getInstance().setRCToken(loginBean.getData().getRCToken());
            mPresenter.FetchHotWordsList();
        } else {
            ToastUtils.showShortToast(loginBean.getMsg());
        }
    }

    /**
     * 首页搜索框查询
     *
     * @param announcementAuctionListBean
     */
    @Override
    public void FetchAuctionListByNameSuccess(AnnouncementAuctionListBean announcementAuctionListBean) {
        mPresenter.FetchCreatWordsBean("word", textWord);
        if (nsv.getVisibility() == View.VISIBLE && srl.getVisibility() == View.GONE) {
            if (tvSearch.getVisibility() == View.GONE && etSearchContent.getVisibility() == View.VISIBLE) {
                tvSearch.setVisibility(View.VISIBLE);
                etSearchContent.setVisibility(View.GONE);
            }
            nsv.setVisibility(View.GONE);
            srl.setVisibility(View.VISIBLE);
        }
        if (status.equals("3")) {
            if (srl.isLoading()) {
                mAdapter.addData(announcementAuctionListBean.getData().getRows());
                srl.finishLoadmore();
            } else {
                if (rowsBeans.size() != 0) rowsBeans.clear();
                if (srl.isRefreshing()) srl.finishRefresh();
                rowsBeans = announcementAuctionListBean.getData().getRows();
                mAdapter.setNewData(rowsBeans);
            }
        } else if (status.equals("4")) {


        }
    }

    /**
     * 新增热门搜索字段
     */
    @Override
    public void FetchCreatWordsBeanSuccess(CreatWordsBean creatWordsBean) {

    }

    /**
     * 搜索字段删除接口
     */
    @Override
    public void FetchDeleteWordByIdSuccess(CreatWordsBean creatWordsBean) {
        if (isClearAll) {
            historicalSearch.clear();
            historicalSearchApadter.setNewData(historicalSearch);
            historicalSearchApadter.notifyDataSetChanged();
        } else {
            historicalSearch.remove(creatWordsBean);
            historicalSearchApadter.remove(postion - 1);
            historicalSearchApadter.setNewData(historicalSearch);
            historicalSearchApadter.notifyDataSetChanged();
        }
    }

    /**
     * 删除一个热门搜索字段
     */
    @Override
    public void OnClearItemListener(HotWordsListBean.DataBean.RecentlyWordsBean item, int postion) {
        this.postion = postion;
        this.isClearAll = false;
        mPresenter.FetchDeleteWordById("id", String.valueOf(item.getId()),
                "word", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()));
    }

    /**
     * 跳转搜索结果
     */
    @Override
    public void OnWordItemListener(HotWordsListBean.DataBean.RecentlyWordsBean item) {
        page = 1;
        textWord = item.getWord();
        mPresenter.FetchAuctionListByName("actionName", textWord,
                "status", status, "page", String.valueOf(page), "rows", String.valueOf(rows));
    }

    /**
     * 加载更多
     *
     * @param refreshlayout
     */
    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
        mPresenter.FetchAuctionListByName("actionName", textWord,
                "status", status, "page", String.valueOf(page), "rows", String.valueOf(rows));
    }

    /**
     * 刷新
     *
     * @param refreshlayout
     */
    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        mPresenter.FetchAuctionListByName("actionName", textWord,
                "status", status, "page", String.valueOf(page), "rows", String.valueOf(rows));
    }

    /**
     * 选中热门搜索
     */
    @Override
    public void OnTagViewItemListener(String item) {
        this.textWord =item;
        mPresenter.FetchAuctionListByName("actionName", textWord,
                "status", status, "page", String.valueOf(page), "rows", String.valueOf(rows));
    }
}
