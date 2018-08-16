package com.smg.art.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.EmptyUtils;
import com.smg.art.R;
import com.smg.art.base.BaseApplication;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.db.database.RongUserInfoEntityDao;
import com.smg.art.db.entity.RongUserInfoEntity;
import com.smg.art.presenter.contract.fragment.RecentMessageContract;
import com.smg.art.presenter.impl.fragment.RecentMessagePresenter;
import com.smg.art.ui.activity.ConversationActivity;
import com.smg.art.ui.activity.MainActivity;
import com.smg.art.ui.activity.OrderMessageActivity;
import com.smg.art.ui.activity.SystemMessageActivity;
import com.smg.art.ui.adapter.RecentMessageApadter;
import com.smg.art.utils.GreenDaoUtil;
import com.smg.art.utils.LocalAppConfigUtil;
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
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.rong.imkit.RongIM;
import io.rong.imkit.emoticon.AndroidEmoji;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.utils.RongDateUtils;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import io.rong.message.TextMessage;

/**
 * Created by Administrator on 2018/8/7.
 */

public class RecentMessageFragment extends BaseFragment implements RecentMessageContract.View,
        RongIMClient.OnReceiveMessageListener, SwipeItemClickListener {

    @Inject
    RecentMessagePresenter mPresenter;

    @BindView(R.id.rv)
    SwipeMenuRecyclerView rv;

    private RecentMessageApadter apadter;
    private List<Conversation> data = new ArrayList<>();
    private String systemMeesageContent, systemMeesageTime, ordMeesageContent, ordMeesageTime;
    private int systemMessageCont, ordMessageCont;
    private TextView tvSystemMeeageContent, tvSystemMeeageTime, tvSystemMeeageCount, tvOrdMeeageContent, tvOrdMeeageTime, tvOrdMeeageCount;
    private String SYSTEMID;
    private String ORDERID;
    private Conversation.ConversationType SYSTEMTYPE, ORDERTYPE;

    private RongUserInfoEntityDao collectionInfoDao;

    @Override
    public void loadData() {
        setState(Constant.STATE_SUCCESS);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_recent_message;
    }

    @Override
    public void attachView() {
        mPresenter.attachView(this, getActivity());
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    protected void initView(Bundle bundle) {
        super.initView(bundle);
        RongIM.setOnReceiveMessageListener(this);
        mPresenter.getConversationList();
        apadter = new RecentMessageApadter(data, getActivity());
        apadter.setHeaderView(getSystemHeaderView());
        apadter.addHeaderView(getOrderHeaderView());
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setSwipeItemClickListener(this);
        rv.setSwipeMenuCreator(swipeMenuCreator);
        rv.setSwipeMenuItemClickListener(mMenuItemClickListener);
        rv.setAdapter(apadter);
        this.collectionInfoDao = GreenDaoUtil.getDaoSession().getRongUserInfoEntityDao();
    }

    @Override
    public boolean onReceived(Message message, int i) {
        mPresenter.getConversationList();
        return false;
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getConversationList();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void getConversationListSuccess(List<Conversation> conversationBean) {

        if (data.size() != 0) data.clear();
        for (int i = 0; i < conversationBean.size(); i++) {
            Conversation conversation = conversationBean.get(i);
            if (conversation.getTargetId().equals("1")) {
                if (conversation.getLatestMessage() instanceof TextMessage)
                    systemMeesageContent = String.valueOf(AndroidEmoji.ensure(((TextMessage) conversation.getLatestMessage()).getContent()));
                systemMessageCont = conversation.getUnreadMessageCount();
                systemMeesageTime = RongDateUtils.getConversationListFormatDate(conversation.getReceivedTime(), BaseApplication.getBaseApplication());
                tvSystemMeeageContent.setText(systemMeesageContent);
                tvSystemMeeageTime.setText(systemMeesageTime);
                if (systemMessageCont > 0) {
                    tvSystemMeeageCount.setVisibility(View.VISIBLE);
                    tvSystemMeeageCount.setText(String.valueOf(systemMessageCont));
                } else {
                    tvSystemMeeageCount.setVisibility(View.GONE);
                }
                SYSTEMTYPE = conversation.getConversationType();
                SYSTEMID = conversation.getTargetId();
                conversationBean.remove(i);
                i--;
                continue;
            }
            if (conversation.getTargetId().equals("2")) {
                if (conversation.getLatestMessage() instanceof TextMessage)
                    ordMeesageContent = (String) AndroidEmoji.ensure(((TextMessage) conversation.getLatestMessage()).getContent());
                ordMessageCont = conversation.getUnreadMessageCount();
                ordMeesageTime = RongDateUtils.getConversationListFormatDate(conversation.getReceivedTime(), BaseApplication.getBaseApplication());
                tvOrdMeeageContent.setText(ordMeesageContent);
                tvOrdMeeageTime.setText(ordMeesageTime);
                if (ordMessageCont > 0) {
                    tvOrdMeeageCount.setVisibility(View.VISIBLE);
                    tvOrdMeeageCount.setText(String.valueOf(ordMessageCont));
                } else {
                    tvOrdMeeageCount.setVisibility(View.GONE);
                }
                ORDERID = conversation.getTargetId();
                ORDERTYPE = conversation.getConversationType();
                conversationBean.remove(i);
                i--;
                continue;
            }
        }

        data = conversationBean;

        for(int i =0;i<data.size();i++){
            data.get(i).getTargetId();
        }

        apadter.setNewData(data);

    }

    @Override
    public void showError(String message) {

    }


    @Override
    public void onItemClick(View itemView, int position) {

        RongUserInfoEntity rongUserInfoEntity = collectionInfoDao.queryBuilder().where(
                RongUserInfoEntityDao.Properties.UserId.eq(data.get(position-1).getTargetId())).unique();

        if(EmptyUtils.isNotEmpty(rongUserInfoEntity)){
            Intent i = new Intent(getActivity(), ConversationActivity.class);
            i.putExtra("MemberId", data.get(position - 1).getTargetId());
            i.putExtra("MemberName", rongUserInfoEntity.getUserName());
            MainActivity.mainActivity.startActivityIn(i, getActivity());
        }else {
            Intent i = new Intent(getActivity(), ConversationActivity.class);
            i.putExtra("MemberId", data.get(position - 1).getTargetId());
            i.putExtra("MemberName", data.get(position - 1).getTargetId());
            MainActivity.mainActivity.startActivityIn(i, getActivity());
        }
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
                if (adapterPosition == 0) {
                    apadter.removeAllHeaderView();
                } else {
                    int deletePostion = adapterPosition - 1;
                    RongIMClient.getInstance().removeConversation(data.get(deletePostion).getConversationType(), data.get(deletePostion).getTargetId(), new RongIMClient.ResultCallback<Boolean>() {
                        @Override
                        public void onSuccess(Boolean aBoolean) {
                            mPresenter.getConversationList();
                        }

                        @Override
                        public void onError(RongIMClient.ErrorCode errorCode) {

                        }
                    });
                }
            }
        }
    };


    private View getSystemHeaderView() {
        View headerView;
        headerView = View.inflate(getActivity(), R.layout.recent_system_message_header, null);

        tvSystemMeeageContent = (TextView) headerView.findViewById(R.id.tv_content);
        tvSystemMeeageTime = (TextView) headerView.findViewById(R.id.tv_time);
        tvSystemMeeageCount = (TextView) headerView.findViewById(R.id.tvCount);
        RelativeLayout systemMeeageItem = (RelativeLayout) headerView.findViewById(R.id.rl_system_message);
        systemMeeageItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置会话已读
                RongIMClient.getInstance().clearMessagesUnreadStatus(SYSTEMTYPE, SYSTEMID);
                Intent i = new Intent(getActivity(), SystemMessageActivity.class);
                MainActivity.mainActivity.startActivityIn(i, getActivity());
            }
        });
        return headerView;
    }

    private View getOrderHeaderView() {
        View headerView;
        headerView = View.inflate(getActivity(), R.layout.recent_order_message_header, null);
        tvOrdMeeageContent = (TextView) headerView.findViewById(R.id.tv_content_Order);
        tvOrdMeeageTime = (TextView) headerView.findViewById(R.id.tv_time_Order);
        tvOrdMeeageCount = (TextView) headerView.findViewById(R.id.tvCountOrder);

        RelativeLayout ordMeeageItem = (RelativeLayout) headerView.findViewById(R.id.rl_order_message);

        ordMeeageItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RongIMClient.getInstance().clearMessagesUnreadStatus(ORDERTYPE, ORDERID);
                Intent i = new Intent(getActivity(), OrderMessageActivity.class);
                MainActivity.mainActivity.startActivityIn(i, getActivity());
            }
        });
        return headerView;
    }




}
