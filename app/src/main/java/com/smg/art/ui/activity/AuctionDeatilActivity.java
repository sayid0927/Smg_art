package com.smg.art.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.Gson;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.base.BaseFragmentPageAdapter;
import com.smg.art.bean.AuctionGoodsBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.AuctionDeatilContract;
import com.smg.art.presenter.impl.fragment.AuctionDeatilPresenter;
import com.smg.art.ui.fragment.AuctionCentreFragment;
import com.smg.art.ui.fragment.AuctionDetailIntroductionFragment;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class AuctionDeatilActivity extends BaseActivity implements AuctionDeatilContract.View {


    @Inject
    AuctionDeatilPresenter mPresenter;

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.vp)
    ViewPager vp;


    private ArrayList<String> mTitleList = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    public  static  AuctionDeatilActivity auctionDeatilActivity;
    int type;
    int id;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_auction_deatil;
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
        type =getIntent().getIntExtra("type",0);
        id=getIntent().getIntExtra("id",0);
        if(type==1){
            String bookJson=getIntent().getStringExtra("data");
            AuctionGoodsBean.DataBean.RowsBean data=new Gson().fromJson(bookJson,AuctionGoodsBean.DataBean.RowsBean.class);
            mFragments.add(AuctionDetailIntroductionFragment.getInstance(data));
            mFragments.add(AuctionCentreFragment.getInstance(data));
        }else if(type==2) {
            mFragments.add(AuctionDetailIntroductionFragment.getInstance(id));
            mFragments.add(AuctionDetailIntroductionFragment.getInstance(id));
        }


        mTitleList.add(getString(R.string.DetailIntroduction));
        mTitleList.add(getString(R.string.AuctionCentre));

        BaseFragmentPageAdapter myAdapter = new BaseFragmentPageAdapter(getSupportFragmentManager(), mFragments, mTitleList);
        vp.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        tabLayout.setViewPager(vp);
        auctionDeatilActivity = this;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        auctionDeatilActivity = null;
    }

    public  void  setCurrentItem(int postion){
        vp.setCurrentItem(postion);
    }


    @Override
    public void showError(String message) {

    }

    @OnClick({R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;

        }
    }
}
