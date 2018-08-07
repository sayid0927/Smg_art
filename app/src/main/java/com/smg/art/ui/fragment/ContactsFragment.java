package com.smg.art.ui.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.R;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.bean.AddFriendBean;
import com.smg.art.bean.AddressBookFriendsBean;
import com.smg.art.bean.SaveCollectsBean;
import com.smg.art.bean.UpudterMessageBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.fragment.ContactsFragmentContract;
import com.smg.art.presenter.impl.fragment.ContactsFragmentPresenter;
import com.smg.art.ui.activity.ConversationActivity;
import com.smg.art.ui.activity.MainActivity;
import com.smg.art.ui.adapter.ContactsApadter;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.view.contactsView.ContactSortModel;
import com.smg.art.view.contactsView.PinyinComparator;
import com.smg.art.view.contactsView.PinyinUtils;
import com.smg.art.view.contactsView.SideBar;
import com.smg.art.view.contactsView.SortAdapter;
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
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Lenovo on 2018/7/25.
 */

public class ContactsFragment extends BaseFragment implements ContactsFragmentContract.View, SwipeItemClickListener {

    @Inject
    ContactsFragmentPresenter mPresenter;
    @BindView(R.id.rv)
    SwipeMenuRecyclerView rv;
    @BindView(R.id.dialog)
    TextView dialog;
    @BindView(R.id.sidrbar)
    SideBar sideBar;

    private SortAdapter adapter;
    private List<ContactSortModel> SourceDateList = new ArrayList<>();
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

        initDatas();
        setAdapter();
        mPresenter.FetchAddressBookFriends("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()));

    }

    private void initDatas() {
        sideBar.setTextView(dialog);
    }


    private void setAdapter() {
        adapter = new SortAdapter(getActivity(), SourceDateList);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setSwipeItemClickListener(this);
        rv.setSwipeMenuCreator(swipeMenuCreator);
        rv.setSwipeMenuItemClickListener(mMenuItemClickListener);
        rv.setAdapter(adapter);

    }

    private void initEvents() {
        //设置右侧触摸监听
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
        mPresenter.FetchAddressBookFriends("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()));
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

        if (SourceDateList.size() != 0) SourceDateList.clear();

        SourceDateList = filledData(addressBookFriendsBean.getData());
        Collections.sort(SourceDateList, new PinyinComparator());
        adapter.setListDaa(SourceDateList);
        adapter.setNewData(SourceDateList);

    }

    /**
     * 删除通讯录好友
     */
    @Override
    public void FetchUpdateFriendRelationSuccess(AddFriendBean addFriendBean) {
        ToastUtils.showLongToast(addFriendBean.getMsg());
        SourceDateList.remove(deletePostion);
        adapter.setNewData(SourceDateList);
        adapter.notifyDataSetChanged();
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
                mPresenter.FetchUpdateFriendRelation("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                        "friendId", String.valueOf(SourceDateList.get(adapterPosition).getMemberId()));
            }
        }
    };

    @Override
    public void onItemClick(View itemView, int position) {
        Intent i = new Intent(getActivity(), ConversationActivity.class);
        i.putExtra("MemberId", String.valueOf(SourceDateList.get(position).getMemberId()));
        i.putExtra("MemberName", String.valueOf(SourceDateList.get(position).getName()));
        MainActivity.mainActivity.startActivityIn(i, getActivity());
    }


    private List<ContactSortModel> filledData(List<AddressBookFriendsBean.DataBean> date) {
        List<ContactSortModel> mSortList = new ArrayList<>();
        ArrayList<String> indexString = new ArrayList<>();

        for (int i = 0; i < date.size(); i++) {
            ContactSortModel sortModel = new ContactSortModel();
            sortModel.setName(date.get(i).getMemberName());
            String pinyin = PinyinUtils.getPingYin(date.get(i).getMemberName());
            String sortString = pinyin.substring(0, 1).toUpperCase();
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
                sortModel.setMemberId(date.get(i).getMemberId());
                if (!indexString.contains(sortString)) {
                    indexString.add(sortString);
                }
            } else {
                sortModel.setSortLetters(sortString.toUpperCase());
                sortModel.setMemberId(date.get(i).getMemberId());
                if (!indexString.contains(sortString)) {
                    indexString.add(sortString);
                }
            }
            mSortList.add(sortModel);
        }
        Collections.sort(indexString);
        sideBar.setIndexText(indexString);
        return mSortList;
    }
}
