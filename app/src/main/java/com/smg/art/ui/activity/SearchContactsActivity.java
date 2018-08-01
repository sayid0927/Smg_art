package com.smg.art.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.AddFriendBean;
import com.smg.art.bean.SearchMemberBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.SearchContactsContract;
import com.smg.art.presenter.impl.activity.SearchContactsActivityPresenter;
import com.smg.art.ui.adapter.SearchContactsListApadter;
import com.smg.art.utils.LocalAppConfigUtil;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchContactsActivity extends BaseActivity implements SearchContactsContract.View, SearchContactsListApadter.OnAddFindsListener {

    @Inject
    SearchContactsActivityPresenter mPesenter;

    @BindView(R.id.ll_back)
    RelativeLayout llBack;
    @BindView(R.id.et_SearchContent)
    EditText etSearchContent;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.rv_search)
    RecyclerView rvSearch;


    private TextView tvCont;
    private SearchContactsListApadter mAdapter;
    private List<SearchMemberBean.DataBean> dataBeans = new ArrayList<>();
    private View headrerView;


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search_contacts;
    }

    @Override
    public void attachView() {
        mPesenter.attachView(this, this);
    }

    @Override
    public void detachView() {
        mPesenter.detachView();
    }

    @Override
    public void initView() {

        mAdapter = new SearchContactsListApadter(dataBeans, this);
        rvSearch.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.addHeaderView(getHeaderView());
        mAdapter.OnAddFindsListener(this);
        rvSearch.setAdapter(mAdapter);
        headrerView.setVisibility(View.GONE);

    }

    private View getHeaderView() {
        headrerView = View.inflate(this, R.layout.header_serach_contacts, null);
        tvCont = (TextView) headrerView.findViewById(R.id.tv_conts);
        return headrerView;
    }

    @OnClick({R.id.ll_back, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:  // 返回
                finish();
                break;
            case R.id.tv_search:  //搜索
                mPesenter.FetchSearchMember("memberId", LocalAppConfigUtil.getInstance().getRCMemberId(),
                        "condition", etSearchContent.getText().toString().trim());
                break;
        }
    }

    /**
     * 搜索平台会员
     *
     * @param searchMemberBean
     */
    @Override
    public void FetchSearchMemberSuccess(SearchMemberBean searchMemberBean) {
        mAdapter.setNewData(searchMemberBean.getData());
        headrerView.setVisibility(View.VISIBLE);
        tvCont.setText(String.valueOf(searchMemberBean.getData().size()));
    }

    /**
     * 新增通讯录好友
     * @param addFriendBean
     */
    @Override
    public void FetchAddFriendSuccess(AddFriendBean addFriendBean) {
           ToastUtils.showLongToast(addFriendBean.getMsg());
    }

    @Override
    public void showError(String message) {
        ToastUtils.showLongToast(message);
    }

    /**
     *  添加到通讯录
     * @param item
     */
    @Override
    public void OnAddFindsListener(SearchMemberBean.DataBean item) {
        mPesenter.FetchAddFriend("memberId",LocalAppConfigUtil.getInstance().getRCMemberId(),
                 "friendId",String.valueOf(item.getMemberId()));
    }
}
