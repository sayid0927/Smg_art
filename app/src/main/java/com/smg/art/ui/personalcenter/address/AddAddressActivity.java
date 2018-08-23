package com.smg.art.ui.personalcenter.address;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.EmptyUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.Gson;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.AddressEventBus;
import com.smg.art.bean.AddressListBean;
import com.smg.art.bean.SearchAreaEventBus;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.AddAddressListContract;
import com.smg.art.presenter.impl.activity.AddAddressListActivityPresenter;
import com.smg.art.ui.adapter.AddressListApadter;
import com.smg.art.utils.CommonUtil;
import com.smg.art.utils.LocalAppConfigUtil;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class AddAddressActivity extends BaseActivity implements AddAddressListContract.View {

    @Inject
    AddAddressListActivityPresenter mPresenter;

    @BindView(R.id.actionbar_title)
    TextView actionbarTitle;
    @BindView(R.id.rl_back)
    AutoRelativeLayout rlBack;

    @BindView(R.id.checkbox)
    CheckBox checkBox;
    @BindView(R.id.bt_post)
    Button btPost;
    @BindView(R.id.et_receiver_name)
    EditText etName;
    @BindView(R.id.et_receiver_phone)
    EditText etPhone;
    @BindView(R.id.et_receiver_address)
    TextView tvAddress;
    @BindView(R.id.et_receiver_detail_address)
    EditText etDetail;
    @BindView(R.id.ll_receiver_address)
    LinearLayout llSelectAddress;
    private AddressListBean.DataBean data;
    private int type;
    private SearchAreaEventBus searchAreaEventBus;


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_address;
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
        type = getIntent().getIntExtra("type", 1);
        if (type == 0)
            actionbarTitle.setText("添加地址");
        else {
            actionbarTitle.setText("编辑地址");
            data = new Gson().fromJson(getIntent().getStringExtra("data"), AddressListBean.DataBean.class);
            if (EmptyUtils.isNotEmpty(data)) {
                etName.setText(data.getDeliveryName());
                etPhone.setText(data.getDeliveryPhone());
                etDetail.setText(data.getProvinceName() + data.getCityName() + data.getAdress());
                tvAddress.setText(data.getProvinceName() + " " + data.getCityName() + " ");
                if(data.getDefaultFlag()==0){
                    checkBox.setChecked(true);
                }else {
                    checkBox.setChecked(false);
                }
            }
        }
        Drawable drawable = this.getResources().getDrawable(R.drawable.checkbox_style);
        drawable.setBounds(0, 0, 40, 40);
        checkBox.setCompoundDrawables(drawable, null, null, null);

    }

    @Override
    public void showError(String message) {

    }


    @OnClick({R.id.rl_back, R.id.bt_post, R.id.ll_receiver_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:  // 返回
                finish();
                break;
            case R.id.ll_receiver_address:  // 选择地区
                Intent i = new Intent(this, SelectAreaActivity.class);
                startActivityIn(i, this);
                break;
            case R.id.bt_post:  // 提交
                if (checkUp()) {
                    String name = etName.getText().toString().trim();
                    String phone = etPhone.getText().toString().trim();
                    String adress = etDetail.getText().toString().trim();
                    int defaultFlag = 0;
                    if (checkBox.isChecked()) {
                        defaultFlag = 0;
                    } else {
                        defaultFlag = 1;
                    }

                    if (type == 0) {
                        if (searchAreaEventBus != null) {
                            mPresenter.FetchCreateAddress("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                                    "deliveryName", name, "deliveryPhone", phone, "provinceId", String.valueOf(searchAreaEventBus.getProvinceId()),
                                    "cityId", String.valueOf(searchAreaEventBus.getCityId()), "countyId", String.valueOf(searchAreaEventBus.getCountyId()),
                                    "adress", adress, "defaultFlag", String.valueOf(defaultFlag));
                        }
                    } else {
                        if (data != null) {
                            mPresenter.FetchUpdateAddress("id", String.valueOf(data.getId()),
                                    "memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                                    "deliveryName", name, "deliveryPhone", phone, "provinceId", String.valueOf(data.getProvinceId()),
                                    "cityId", String.valueOf(data.getCityId()), "countyId", String.valueOf(data.getCountyId()),
                                    "adress", adress, "defaultFlag", String.valueOf(defaultFlag));
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void FetchCreateAddressSuccess(AddressListBean addressListBean) {
        ToastUtils.showLongToast("添加成功");
        EventBus.getDefault().post(new AddressEventBus());
    }

    @Override
    public void FetchUpdateAddressSuccess() {
        ToastUtils.showLongToast("修改成功");
        EventBus.getDefault().post(new AddressEventBus());
    }


    @Subscribe
    public void getEventBus(final SearchAreaEventBus searchAreaEventBus) {
        this.searchAreaEventBus = searchAreaEventBus;
        tvAddress.setText(searchAreaEventBus.getProvinceName() + "  " + searchAreaEventBus.getCityName() + " " + searchAreaEventBus.getCountyName());
    }


    private boolean checkUp() {
        if (TextUtils.isEmpty(etName.getText().toString().replaceAll(" ", ""))) {
            ToastUtils.showLongToast("请输入用户名");
            return false;
        }
        if (!CommonUtil.isMobileNO(etPhone.getText().toString().replaceAll(" ", ""))) {
            ToastUtils.showLongToast("请输入正确11位手机号码");
            return false;
        }

        if (type == 0) {
            if (TextUtils.isEmpty(tvAddress.getText().toString().replaceAll(" ", ""))) {
                ToastUtils.showLongToast("请选择所在区域");
                return false;
            }
            if (searchAreaEventBus == null) {
                ToastUtils.showLongToast("请选择所在区域");
                return false;
            }
        }


        if (TextUtils.isEmpty(etDetail.getText().toString().replaceAll(" ", ""))) {
            ToastUtils.showLongToast("请输入详情地址");
            return false;
        }
        return true;
    }
}
