package com.smg.art.ui.activity;


import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.EmptyUtils;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smg.art.R;
import com.smg.art.bean.AnnouncementAuctionListBean;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.CreatWordsBean;
import com.smg.art.bean.HotWordsListBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.SearchContract;
import com.smg.art.presenter.impl.activity.SearchActivityPresenter;
import com.smg.art.ui.adapter.HistoricalSearchApadter;
import com.smg.art.ui.adapter.SearchGoodsListApadter;
import com.smg.art.utils.KeyBoardUtils;
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
        OnLoadmoreListener, OnRefreshListener, StringTagAdapter.OnTagViewItemListener, SearchGoodsListApadter.OnAuctionItemListener, SearchGoodsListApadter.OnGoodsItemListener {

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

    private SearchGoodsListApadter mAdapter;
    private StringTagAdapter adapter;
    private List<String> sourceData;
    private List<AnnouncementAuctionListBean.DataBean.RowsBean> rowsBeans = new ArrayList<>();
    private List<HotWordsListBean.DataBean.HotWordsBean> hotWordsBeans = new ArrayList<>();
    private boolean isView = false;

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

        mAdapter = new SearchGoodsListApadter(this, rowsBeans);
        if (status.equals("3")) {
            rvGoods.setLayoutManager(new GridLayoutManager(this, 2));
        } else if (status.equals("4")) {
            rvGoods.setLayoutManager(new LinearLayoutManager(this));
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
        mAdapter.OnAuctionItemListener(this);
        mAdapter.OnGoodsItemListener(this);
        mPresenter.FetchHotWordsList();

        ivSearch.setVisibility(View.GONE);
        etSearchContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    page = 1;
                    textWord = etSearchContent.getText().toString().trim();
                    mPresenter.FetchAuctionListByName("actionName", textWord,
                            "status", status, "page", String.valueOf(page), "rows", String.valueOf(rows));
                    return true;
                }
                return false;
            }
        });
    }

    private View getHeaderView() {
        headerView = View.inflate(this, R.layout.historicalsearch_header, null);
        TextView btDelete = headerView.findViewById(R.id.bt_delete);
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClearAll = true;
                mPresenter.FetchDeleteWordById("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()));
            }
        });
        return headerView;
    }

    @OnClick({R.id.ll_back, R.id.iv_search, R.id.tv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back: //返回
                KeyBoardUtils.hiddenKeyboart(this);
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
                    etSearchContent.setText(textWord);
                    etSearchContent.setSelection(textWord.length());
                }
                if (nsv.getVisibility() == View.GONE && srl.getVisibility() == View.VISIBLE) {
                    nsv.setVisibility(View.VISIBLE);
                    srl.setVisibility(View.GONE);
                    mPresenter.FetchHotWordsList();
                    rowsBeans.clear();
                    mAdapter.setNewData(rowsBeans);
                    mAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public void showError(String message) {
//        ToastUtils.showLongToast(message);
    }

    /**
     * 获取搜索列表
     */
    @Override
    public void FetchHotWordsListSuccess(final HotWordsListBean hotWordsListBean) {
        if (!isView) {
            if (hotWordsBeans.size() != 0) hotWordsBeans.clear();
            this.hotWordsBeans = hotWordsListBean.getData().getHotWords();
            if (hotWordsBeans.size() != 0) {
                for (int i = 0; i < hotWordsBeans.size(); i++) {
                    isView = true;
                    View view = View.inflate(this, R.layout.company_grid_item, null);
                    TextView textView = (TextView) view.findViewById(R.id.text);
                    textView.setText(hotWordsBeans.get(i).getWord());
                    flexLayout.addView(view);
                    final int finalI = i;
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            textWord = hotWordsBeans.get(finalI).getWord();
                            mPresenter.FetchAuctionListByName("actionName", textWord,
                                    "status", status, "page", String.valueOf(page), "rows", String.valueOf(rows));
                        }
                    });
                }
            }
        }
        if (hotWordsListBean.getData().getRecentlyWords().size() != 0) {
            if (historicalSearch.size() != 0) historicalSearch.clear();
            historicalSearch = hotWordsListBean.getData().getRecentlyWords();
            historicalSearchApadter.setNewData(historicalSearch);
        }
    }
    /**
     * 首页搜索框查询
     */
    @Override
    public void FetchAuctionListByNameSuccess(AnnouncementAuctionListBean announcementAuctionListBean) {
        KeyBoardUtils.hiddenKeyboart(this);
        if (EmptyUtils.isNotEmpty(textWord))
            mPresenter.FetchCreatWordsBean("word", textWord);
        if (nsv.getVisibility() == View.VISIBLE && srl.getVisibility() == View.GONE) {
            if (tvSearch.getVisibility() == View.GONE && etSearchContent.getVisibility() == View.VISIBLE) {
                tvSearch.setVisibility(View.VISIBLE);
                tvSearch.setText(textWord);
                etSearchContent.setVisibility(View.GONE);
            }
            nsv.setVisibility(View.GONE);
            srl.setVisibility(View.VISIBLE);
        }

        if (srl.isLoading()) {
            if (status.equals("3")) {
                for (int i = 0; i < announcementAuctionListBean.getData().getRows().size(); i++) {
                    announcementAuctionListBean.getData().getRows().get(i).setType(AnnouncementAuctionListBean.DataBean.RowsBean.GOODS);
                }
            } else if (status.equals("4")) {
                for (int i = 0; i < announcementAuctionListBean.getData().getRows().size(); i++) {
                    announcementAuctionListBean.getData().getRows().get(i).setType(AnnouncementAuctionListBean.DataBean.RowsBean.AUCTION);
                }
            }
            mAdapter.addData(announcementAuctionListBean.getData().getRows());
            srl.finishLoadmore();
        } else {

            if (EmptyUtils.isEmpty(announcementAuctionListBean.getData().getRows()) || announcementAuctionListBean.getData().getRows().size() == 0) {
                // 空界面
                mAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.basefragment_state_empty, null));

            } else {
                if (rowsBeans.size() != 0) rowsBeans.clear();
                if (srl.isRefreshing()) srl.finishRefresh();
                rowsBeans = announcementAuctionListBean.getData().getRows();
                if (status.equals("3")) {
                    for (int i = 0; i < announcementAuctionListBean.getData().getRows().size(); i++) {
                        announcementAuctionListBean.getData().getRows().get(i).setType(AnnouncementAuctionListBean.DataBean.RowsBean.GOODS);
                    }
                } else if (status.equals("4")) {
                    for (int i = 0; i < announcementAuctionListBean.getData().getRows().size(); i++) {
                        announcementAuctionListBean.getData().getRows().get(i).setType(AnnouncementAuctionListBean.DataBean.RowsBean.AUCTION);
                    }
                }
                mAdapter.setNewData(rowsBeans);
            }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cancelAllTimers();
    }

    /**
     * 删除一个热门搜索字段
     */
    @Override
    public void OnClearItemListener(HotWordsListBean.DataBean.RecentlyWordsBean item, int postion) {
        this.postion = postion;
        this.isClearAll = false;
        mPresenter.FetchDeleteWordById("id", String.valueOf(item.getId()),
                "word", item.getWord());
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
     */
    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
        mPresenter.FetchAuctionListByName("actionName", textWord,
                "status", status, "page", String.valueOf(page), "rows", String.valueOf(rows));
    }

    /**
     * 刷新
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
        this.textWord = item;
        mPresenter.FetchAuctionListByName("actionName", textWord,
                "status", status, "page", String.valueOf(page), "rows", String.valueOf(rows));
    }

    @Override
    public void OnAuctionItemListener(AnnouncementAuctionListBean.DataBean.RowsBean item) {
        Intent i = new Intent(this, AuctionDeatilActivity.class);
        i.putExtra("type",1);
        i.putExtra("data", new Gson().toJson(item));
        MainActivity.mainActivity.startActivityIn(i, this);
    }

    @Override
    public void OnGoodsItemListener(AnnouncementAuctionListBean.DataBean.RowsBean item) {
        Intent i = new Intent(this, GoodsDetailActivity.class);
        i.putExtra("postion", item.getId());
        MainActivity.mainActivity.startActivityIn(i, this);
    }
}
