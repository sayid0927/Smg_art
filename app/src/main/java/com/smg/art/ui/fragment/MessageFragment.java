package com.smg.art.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.smg.art.R;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.BaseFragmentPageAdapter;
import com.smg.art.base.Constant;
import com.smg.art.component.AppComponent;
import com.smg.art.ui.activity.MainActivity;
import com.smg.art.ui.activity.SearchContactsActivity;
import com.smg.art.view.NoScrollViewPager;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.model.UIConversation;
import io.rong.imlib.model.Conversation;

/**
 * Created by Lenovo on 2018/7/25.
 */

public class MessageFragment extends BaseFragment {


    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.vp)
    NoScrollViewPager vp;

    @BindView(R.id.ll_addfriend)
    LinearLayout llAddfriend;

    private ArrayList<String> mTitleList = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void initView(Bundle bundle) {
        super.initView(bundle);

        mTitleList.add(getString(R.string.recent_message));
        mTitleList.add(getString(R.string.address_list));

        mFragments.add(new RecentMessageFragment());
        mFragments.add(new ContactsFragment());

        BaseFragmentPageAdapter myAdapter = new BaseFragmentPageAdapter(getChildFragmentManager(), mFragments, mTitleList);
        vp.setAdapter(myAdapter);
        vp.setNoScroll(true);
        myAdapter.notifyDataSetChanged();
        tabLayout.setViewPager(vp);

    }

    @Override
    public void loadData() {
        setState(Constant.STATE_SUCCESS);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_message;
    }

    @Override
    public void attachView() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @OnClick(R.id.ll_addfriend)
    public void onViewClicked() {
        MainActivity.mainActivity.startActivityIn(new Intent(getActivity(), SearchContactsActivity.class), getActivity());
    }

}
