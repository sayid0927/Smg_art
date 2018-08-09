package com.smg.art.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smg.art.R;
import com.smg.art.base.BaseApplication;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.fragment.RecentMessageContract;
import com.smg.art.presenter.impl.fragment.RecentMessagePresenter;
import com.smg.art.ui.activity.ConversationActivity;
import com.smg.art.ui.activity.MainActivity;
import com.smg.art.ui.activity.OrderMessageActivity;
import com.smg.art.ui.activity.SystemMessageActivity;
import com.smg.art.ui.adapter.RecentMessageApadter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.rong.imkit.RongIM;
import io.rong.imkit.emoticon.AndroidEmoji;
import io.rong.imkit.utils.RongDateUtils;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;

/**
 * Created by Administrator on 2018/8/7.
 */

public class RecentMessageFragment extends BaseFragment implements RecentMessageContract.View, RecentMessageApadter.OnMessageItemListener,RongIMClient.OnReceiveMessageListener {

    @Inject
    RecentMessagePresenter mPresenter;

    @BindView(R.id.rv)
    RecyclerView rv;

    private RecentMessageApadter apadter;
    private List<Conversation> data = new ArrayList<>();
    private  String systemMeesageContent,systemMeesageTime,ordMeesageContent,ordMeesageTime;
    private  int systemMessageCont,ordMessageCont;

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
        apadter.addHeaderView(getHeaderView());
        apadter.OnMessageItemListener(this);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(apadter);
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
    public void getConversationListSuccess(List<Conversation> conversationBean) {
        for(Conversation conversation  :conversationBean){
            if(conversation.getTargetId().equals("1") && conversation.getLatestMessage() instanceof TextMessage){
                systemMeesageContent = String.valueOf(AndroidEmoji.ensure(((TextMessage) conversation.getLatestMessage()).getContent()));
                systemMessageCont = conversation.getUnreadMessageCount();
                systemMeesageTime = RongDateUtils.getConversationListFormatDate(conversation.getReceivedTime(), BaseApplication.getBaseApplication());
            }
            if(conversation.getTargetId().equals("2") && conversation.getLatestMessage() instanceof TextMessage){
                ordMeesageContent = (String) AndroidEmoji.ensure(((TextMessage) conversation.getLatestMessage()).getContent());
                ordMessageCont = conversation.getUnreadMessageCount();
                ordMeesageTime = RongDateUtils.getConversationListFormatDate(conversation.getReceivedTime(), BaseApplication.getBaseApplication());
            }
        }
        data = conversationBean;
        apadter.setNewData(data);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void OnMessageItemListener(Conversation item) {
        Intent i = new Intent(getActivity(), ConversationActivity.class);
        i.putExtra("MemberId", item.getTargetId());
        i.putExtra("MemberName", item.getLatestMessage().getUserInfo().getName());
        com.smg.art.ui.activity.MainActivity.mainActivity.startActivityIn(i, getActivity());
    }
    private View getHeaderView() {
        View headerView;
        headerView = View.inflate(getActivity(), R.layout.recent_message_header, null);

        TextView tvSystemMeeageContent = (TextView) headerView.findViewById(R.id.tv_content);
        TextView tvSystemMeeageTime = (TextView) headerView.findViewById(R.id.tv_time);
        TextView tvSystemMeeageCount = (TextView) headerView.findViewById(R.id.tvCount);
        TextView tvOrdMeeageContent = (TextView) headerView.findViewById(R.id.tv_content_Order);
        TextView tvOrdMeeageTime = (TextView) headerView.findViewById(R.id.tv_time_Order);
        TextView tvOrdMeeageCount = (TextView) headerView.findViewById(R.id.tvCountOrder);

        RelativeLayout systemMeeageItem = (RelativeLayout) headerView.findViewById(R.id.rl_system_message);
        RelativeLayout ordMeeageItem = (RelativeLayout) headerView.findViewById(R.id.rl_order_message);


        tvSystemMeeageContent.setText(systemMeesageContent);
        tvSystemMeeageTime.setText(systemMeesageTime);
        tvSystemMeeageCount.setText(String.valueOf(systemMessageCont));
        tvOrdMeeageContent.setText(ordMeesageContent);
        tvOrdMeeageTime.setText(ordMeesageTime);
        tvOrdMeeageCount.setText(String.valueOf(ordMessageCont));

        systemMeeageItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SystemMessageActivity.class);
                MainActivity.mainActivity.startActivityIn(i, getActivity());
            }
        });
        ordMeeageItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), OrderMessageActivity.class);
                MainActivity.mainActivity.startActivityIn(i, getActivity());
            }
        });
        return headerView;
    }
}
