package com.smg.art.ui.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.R;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.bean.AddFriendBean;
import com.smg.art.bean.AddressBookFriendsBean;
import com.smg.art.bean.UpudterMessageBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.fragment.ContactsFragmentContract;
import com.smg.art.presenter.impl.fragment.ContactsFragmentPresenter;
import com.smg.art.ui.activity.ConversationActivity;
import com.smg.art.ui.activity.MainActivity;
import com.smg.art.ui.activity.SearchActivity;
import com.smg.art.ui.adapter.ContactsApadter;
import com.smg.art.utils.DividerItemDecoration;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.utils.TitleItemDecoration;
import com.smg.art.view.IndexBar;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.rong.imkit.RongIM;

/**
 * Created by Lenovo on 2018/7/25.
 */

public class ContactsFragment extends BaseFragment implements ContactsFragmentContract.View, SwipeItemClickListener {

    @Inject
    ContactsFragmentPresenter mPresenter;
    @BindView(R.id.rv)
    SwipeMenuRecyclerView rv;
    @BindView(R.id.indexBar)
    IndexBar indexBar;
    @BindView(R.id.tvSideBarHint)
    TextView tvSideBarHint;

    private List<AddressBookFriendsBean.DataBean> mData = new ArrayList<>();
    private ContactsApadter mAdapter;
    private LinearLayoutManager mManager;
    private int deletePostion;

    @Override
    public void loadData() {
        setState(Constant.STATE_SUCCESS);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_contacts;
    }

    @Override
    public void attachView() {
        mPresenter.attachView(this, getActivity());
    }

    @Override
    protected void initView(Bundle bundle) {
        super.initView(bundle);
        EventBus.getDefault().register(this);
        mManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(mManager);
        mAdapter = new ContactsApadter(mData, getActivity());
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
//        rv.addItemDecoration(new DefaultItemDecoration(ContextCompat.getColor(getActivity(), R.color.divider_color)));
        rv.setSwipeItemClickListener(this);
        rv.setSwipeMenuCreator(swipeMenuCreator);
        rv.setSwipeMenuItemClickListener(mMenuItemClickListener);

        rv.setAdapter(mAdapter);

        rv.addItemDecoration(new TitleItemDecoration(getActivity(), mData));
        //如果add两个，那么按照先后顺序，依次渲染。
        //mRv.addItemDecoration(new TitleItemDecoration2(this,mDatas));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        //使用indexBar
        indexBar.setmPressedShowTextView(tvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                .setmSourceDatas(mData);//设置数据源

        mPresenter.FetchAddressBookFriends("memberId", LocalAppConfigUtil.getInstance().getRCMemberId());

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public void showError(String message) {
        ToastUtils.showLongToast(message);
    }

    @Subscribe
    public void getEventBus(UpudterMessageBean upudterMessageBean) {
        mPresenter.FetchAddressBookFriends("memberId", LocalAppConfigUtil.getInstance().getRCMemberId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 查询通讯录好友列表
     */
    @Override
    public void FetchAddressBookFriendsSuccess(AddressBookFriendsBean addressBookFriendsBean) {
        if (mData.size() != 0) {
            mData.clear();
        }
        mData = addressBookFriendsBean.getData();
        mAdapter.setNewData(mData);
        mAdapter.notifyDataSetChanged();

        rv.addItemDecoration(new TitleItemDecoration(getActivity(), mData));
        //如果add两个，那么按照先后顺序，依次渲染。
        //mRv.addItemDecoration(new TitleItemDecoration2(this,mDatas));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        //使用indexBar
        indexBar.setmPressedShowTextView(tvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                .setmSourceDatas(mData);//设置数据源
    }

    /**
     * 删除通讯录好友
     */
    @Override
    public void FetchUpdateFriendRelationSuccess(AddFriendBean addFriendBean) {
        ToastUtils.showLongToast(addFriendBean.getMsg());
        mData.remove(deletePostion);
        mAdapter.setNewData(mData);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 菜单创建器，在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.bj_70dp);
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity())
                    .setBackground(R.color.color_8d8686)
                    .setText("删除")
                    .setTextColor(Color.WHITE)
                    .setTextSize(16)
                    .setWidth(width)
                    .setHeight(height);

            swipeRightMenu.addMenuItem(deleteItem);// 添加菜单到右侧。
        }
    };

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
                deletePostion = adapterPosition;
                mPresenter.FetchUpdateFriendRelation("memberId", LocalAppConfigUtil.getInstance().getRCMemberId(),
                        "friendId", String.valueOf(mData.get(adapterPosition).getMemberId()));
            }
        }
    };

    @Override
    public void onItemClick(View itemView, int position) {
        Intent i = new Intent( getActivity(),ConversationActivity.class);
        i.putExtra("MemberId",String.valueOf(mData.get(position).getMemberId()));
        i.putExtra("MemberName",String.valueOf(mData.get(position).getMemberName()));
        MainActivity.mainActivity.startActivityIn(i,getActivity());

//        RongIM.getInstance().startPrivateChat(getActivity(),
//                String.valueOf(mData.get(position).getMemberId()), mData.get(position).getMemberName());

    }
}
