package com.smg.art.ui.personalcenter.address;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.SearchAreaBean;
import com.smg.art.bean.SearchAreaEventBus;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.SelectAreaContract;
import com.smg.art.presenter.impl.activity.SelectAreaPresenter;
import com.smg.art.ui.adapter.SearchAreaApadter;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectAreaActivity extends BaseActivity implements SelectAreaContract.View, SearchAreaApadter.OnAreaItemListener {

    @Inject
    SelectAreaPresenter mPresenter;

    @BindView(R.id.actionbar_title)
    TextView actionbarTitle;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rl_back)
    AutoRelativeLayout rlBack;

    @BindView(R.id.rv)
    RecyclerView rv;

    //选择次数：当选择次数为3的时候，再选择就返回上一个活动
    private int count = 1;
    //已选择省id
    private int provence_id;
    //已选择市id
    private int city_id;
    //已选择区id
    private int county_id;
    //已选择省名称
    private String provence_name;
    //已选择市名称
    private String city_name;
    //已选择区名称
    private String county_name;

    private SearchAreaApadter apadter;
    private List<SearchAreaBean.DataBean> searchAreaBeans = new ArrayList<>();


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_selectarea;
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
        actionbarTitle.setText("选择地址");

        apadter = new SearchAreaApadter(searchAreaBeans, this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(apadter);
        apadter.OnAreaItemListener(this);
        apadter.setCount(count);
        mPresenter.FetchRegionInfo();
    }

    @Override
    public void showError(String message) {

    }

    @OnClick({R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:  // 返回
                finish();
                break;
        }
    }

    @Override
    public void FetchRegionInfoSuccess(SearchAreaBean searchAreaBean) {
        if (searchAreaBeans.size() != 0) searchAreaBeans.clear();
        searchAreaBeans = searchAreaBean.getData();
        apadter.setNewData(searchAreaBeans);
    }

    @Override
    public void OnAreaItemListener(SearchAreaBean.DataBean item, String text) {
        switch (count) {
            case 1:
                provence_id = item.getProvinceId();
                provence_name = item.getProvinceName();
                title.setText(provence_name);
                mPresenter.FetchRegionInfo("provinceId", String.valueOf(provence_id));
                break;
            case 2:
                city_id  = item.getCityId();
                city_name = item.getCityName();
                title.setText(city_name);
                mPresenter.FetchRegionInfo("provinceId", String.valueOf(provence_id),"cityId", String.valueOf(city_id));
                break;
            case 3:
                SearchAreaEventBus searchAreaEventBus = new SearchAreaEventBus();
                searchAreaEventBus.setProvinceId(provence_id);
                searchAreaEventBus.setProvinceName(provence_name);
                searchAreaEventBus.setCityId(city_id);
                searchAreaEventBus.setCityName(city_name);
                searchAreaEventBus.setCountyId(item.getCountyId());
                searchAreaEventBus.setCountyName(item.getCountyName());
                EventBus.getDefault().post(searchAreaEventBus);
                finish();

                break;
        }
        count++;
        apadter.setCount(count);
    }
}
