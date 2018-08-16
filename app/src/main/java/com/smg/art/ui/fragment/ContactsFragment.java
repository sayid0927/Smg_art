package com.smg.art.ui.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.orhanobut.logger.Logger;
import com.smg.art.R;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.bean.AddFriendBean;
import com.smg.art.bean.AddressBookFriendsBean;
import com.smg.art.bean.MySection;
import com.smg.art.bean.UpudterMessageBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.db.database.RongUserInfoEntityDao;
import com.smg.art.db.entity.RongUserInfoEntity;
import com.smg.art.presenter.contract.fragment.ContactsFragmentContract;
import com.smg.art.presenter.impl.fragment.ContactsFragmentPresenter;
import com.smg.art.ui.activity.ConversationActivity;
import com.smg.art.ui.activity.MainActivity;
import com.smg.art.ui.adapter.ContactsApadter;
import com.smg.art.utils.GreenDaoUtil;
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

/**
 * Created by Lenovo on 2018/7/25.
 */

public class ContactsFragment extends BaseFragment implements ContactsFragmentContract.View, SwipeItemClickListener {

    @Inject
    ContactsFragmentPresenter mPresenter;
    @BindView(R.id.rv)
    SwipeMenuRecyclerView rv;

    //    private SortAdapter adapter;
//    private List<ContactSortModel> SourceDateList = new ArrayList<>();
    private int deletePostion;
    private ContactsApadter contactsApadter;
    private List<MySection> mySections = new ArrayList<>();
    private List<AddressBookFriendsBean.DataBean> dataBeans = new ArrayList<>();


    private RongUserInfoEntityDao collectionInfoDao;

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
        this.collectionInfoDao = GreenDaoUtil.getDaoSession().getRongUserInfoEntityDao();
        setAdapter();
        mPresenter.FetchAddressBookFriends("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()));

    }



    private void setAdapter() {

        contactsApadter = new ContactsApadter(mySections, getActivity());
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setSwipeItemClickListener(this);
        rv.setSwipeMenuCreator(swipeMenuCreator);
        rv.setSwipeMenuItemClickListener(mMenuItemClickListener);
        rv.setAdapter(contactsApadter);
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
        dataBeans = addressBookFriendsBean.getData();
        filledData(dataBeans);
        Collections.sort(dataBeans, new PinyinComparator());
        mySections = filledData(dataBeans);
        contactsApadter.setNewData(mySections);
        if (addressBookFriendsBean.getData().size() != 0) {

            for (int i = 0; i < addressBookFriendsBean.getData().size(); i++) {
                RongUserInfoEntity r = collectionInfoDao.queryBuilder().where(
                        RongUserInfoEntityDao.Properties.UserId.eq(String.valueOf(addressBookFriendsBean.getData().get(i).getMemberId()))).unique();

                if (r != null) {
                    RongUserInfoEntity rongUserInfoEntity = new RongUserInfoEntity();
                    rongUserInfoEntity.setId(r.getId());
                    rongUserInfoEntity.setUserId(String.valueOf(addressBookFriendsBean.getData().get(i).getMemberId()));
                    rongUserInfoEntity.setUserName(addressBookFriendsBean.getData().get(i).getMemberName());
                    rongUserInfoEntity.setUserPortraitUri(Constant.BaseImgUrl + addressBookFriendsBean.getData().get(i).getHeadImg());
                    collectionInfoDao.update(rongUserInfoEntity);
                } else {
                    RongUserInfoEntity rongUserInfoEntity = new RongUserInfoEntity();
                    rongUserInfoEntity.setUserId(String.valueOf(addressBookFriendsBean.getData().get(i).getMemberId()));
                    rongUserInfoEntity.setUserName(addressBookFriendsBean.getData().get(i).getMemberName());
                    rongUserInfoEntity.setUserPortraitUri(Constant.BaseImgUrl + addressBookFriendsBean.getData().get(i).getHeadImg());
                    collectionInfoDao.insert(rongUserInfoEntity);
                }
            }
        }
    }

    /**
     * 删除通讯录好友
     */
    @Override
    public void FetchUpdateFriendRelationSuccess(AddFriendBean addFriendBean) {
        mPresenter.FetchAddressBookFriends("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()));
    }

    /**
     * 菜单创建器，在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            if (viewType == 0) {
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
                Logger.e(String.valueOf(deletePostion)+">>>  " + String.valueOf(mySections.get(deletePostion).t.getMemberId()));
                mPresenter.FetchUpdateFriendRelation("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                        "friendId", String.valueOf(mySections.get(deletePostion).t.getMemberId()));
            }
        }
    };

    @Override
    public void onItemClick(View itemView, int position) {
        Intent i = new Intent(getActivity(), ConversationActivity.class);
        i.putExtra("MemberId", String.valueOf(mySections.get(position).t.getMemberId()));
        i.putExtra("MemberName", String.valueOf(mySections.get(position).t.getMemberName()));
        MainActivity.mainActivity.startActivityIn(i, getActivity());
    }


    private List<MySection> filledData(List<AddressBookFriendsBean.DataBean> date) {
        List<MySection> list = new ArrayList<>();
        String tmp = "";
        for (int i = 0; i < date.size(); i++) {
            String pinyin = PinyinUtils.getPingYin(date.get(i).getMemberName());
            String sortString = pinyin.substring(0, 1).toUpperCase();
            if (!tmp.equals(sortString)) {
                MySection mySection = new MySection(true, sortString);
                list.add(mySection);
                tmp = sortString;
            }
            date.get(i).setSortLetters(sortString.toUpperCase());
            MySection item = new MySection(date.get(i));
            list.add(item);
        }
        return list;
    }
}
