package com.smg.art.ui.personalcenter.address;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.utils.LocationUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.AddressEventBus;
import com.smg.art.bean.AddressListBean;
import com.smg.art.bean.EventBusAddressBean;
import com.smg.art.bean.RongImStateBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.AddressListContract;
import com.smg.art.presenter.impl.activity.AddressListActivityPresenter;
import com.smg.art.ui.adapter.AddressListApadter;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.utils.UIUtils;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class AddressListActivity extends BaseActivity implements AddressListContract.View,
        AddressListApadter.OnEditItemListener, AddressListApadter.OnDeleItemListener, AddressListApadter.OnAddressItemListener {

    @Inject
    AddressListActivityPresenter mPresenter;

    @BindView(R.id.actionbar_title)
    TextView actionbarTitle;
    @BindView(R.id.actionbar_text_action)
    TextView actionbarTextAction;
    @BindView(R.id.rl_back)
    AutoRelativeLayout rlBack;

    @BindView(R.id.rv)
    RecyclerView rv;
    int type;


    private AddressListApadter apadter;
    private List<AddressListBean.DataBean> addressListBeans = new ArrayList<>();
    public  boolean isFts= false;
    public static   AddressListActivity addressListActivity;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_address_list;
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
        type = getIntent().getIntExtra("confirm", 0);
        setSwipeBackEnable(true);
        actionbarTitle.setText("收货地址");
        actionbarTextAction.setText("添加");
        actionbarTextAction.setVisibility(View.VISIBLE);
        actionbarTextAction.setTextColor(UIUtils.getColor(R.color.white));
        apadter = new AddressListApadter(addressListBeans, this);
        apadter.OnDeleItemListener(this);
        apadter.OnEditItemListener(this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(apadter);
        mPresenter.FetchAddressList("memberId",String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()));
        addressListActivity = this;
        apadter.OnAddressItemListener(this);

    }

    @Override
    public void showError(String message) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isFts){
            mPresenter.FetchAddressList("memberId",String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()));
        }
    }

    @Subscribe
    public void getEventBus(final AddressEventBus addressEventBus) {
        mPresenter.FetchAddressList("memberId",String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()));
    }


    @OnClick({R.id.rl_back, R.id.actionbar_text_action})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:  // 返回
                finish();
                break;
            case R.id.actionbar_text_action:  // 添加
                Intent i = new Intent(this, AddAddressActivity.class);
                i.putExtra("type", 0);
                startActivityIn(i, this);
                break;
        }
    }

    /**
     * 编辑地址
     */
    @Override
    public void OnEditItemListener(AddressListBean.DataBean item) {
        Intent i = new Intent(this, AddAddressActivity.class);
        i.putExtra("type", 1);
        i.putExtra("data",new Gson().toJson(item));
        startActivityIn(i, this);
    }

    /**
     * 删除地址
     */
    @Override
    public void OnDeleItemListener(AddressListBean.DataBean item,int postion) {
        mPresenter.FetchDeleteAddress("id",String.valueOf(item.getId()));
        addressListBeans.remove(item);
        apadter.setNewData(addressListBeans);
        if(apadter.getItemCount()==0) apadter.setEmptyView(
                LayoutInflater.from(this).inflate(R.layout.address_empty, null));
    }

    /**
     * 获取收货地址
     */
    @Override
    public void FetchAddressListSuccess(AddressListBean addressListBean) {
       if(addressListBeans.size()!=0)addressListBeans.clear();
        addressListBeans= addressListBean.getData();
        apadter.setNewData(addressListBeans);
    }

    /**
     * 收货地址删除
     */
    @Override
    public void FetchDeleteAddressSuccess() {
    ToastUtils.showLongToast("删除成功");
    }

    @Override
    public AddressListApadter getRecyclerView() {
        return apadter;
    }

    @Override
    public Activity getContext() {
        return this;
    }

    @Override
    public void OnAddressItemListener(AddressListBean.DataBean item, int postion) {
        EventBusAddressBean eventBusAddressBean = new EventBusAddressBean();
        eventBusAddressBean.setType(type);
        eventBusAddressBean.setId(item.getId());
        eventBusAddressBean.setCityId(item.getCityId());
        eventBusAddressBean.setCityName(item.getCityName());
        eventBusAddressBean.setCountyId(item.getCountyId());
        eventBusAddressBean.setCountyName(item.getCountyName());
        eventBusAddressBean.setProvinceName(item.getProvinceName());
        eventBusAddressBean.setProvinceId(item.getProvinceId());
        eventBusAddressBean.setAdress(item.getAdress());
        eventBusAddressBean.setDeliveryName(item.getDeliveryName());
        eventBusAddressBean.setDeliveryPhone(item.getDeliveryPhone());
        eventBusAddressBean.setDefaultFlag(item.getDefaultFlag());
        EventBus.getDefault().post(eventBusAddressBean);
        finish();
    }
}
