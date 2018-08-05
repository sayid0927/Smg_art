package com.smg.art.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.adapter.LQRViewHolderForRecyclerView;
import com.lqr.emoji.MoonUtils;
import com.smg.art.R;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.message.RedPacketMessage;
import com.smg.art.presenter.contract.activity.RecentMessageContract;
import com.smg.art.presenter.impl.fragment.RecentMessagePresenter;
import com.smg.art.ui.activity.ConversationActivity;
import com.smg.art.ui.activity.MainActivity;
import com.smg.art.ui.adapter.RecentMessageListApadter;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.utils.TimeUtils;
import com.smg.art.utils.UIUtils;
import com.smg.art.view.CustomDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.rong.imkit.model.GroupNotificationMessageData;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import io.rong.message.FileMessage;
import io.rong.message.GroupNotificationMessage;
import io.rong.message.ImageMessage;
import io.rong.message.LocationMessage;
import io.rong.message.TextMessage;
import io.rong.message.VoiceMessage;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by Lenovo on 2018/7/25.
 */

public class RecentMessageFragment extends BaseFragment implements RecentMessageContract.View, RecentMessageListApadter.OnMessageItemListener {

    @Inject
    RecentMessagePresenter mPresenter;
    @BindView(R.id.rv)
    RecyclerView rv;

    private RecentMessageListApadter apadter;
    private List<Conversation> data = new ArrayList<>();
    private View headerView;


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getConversationList();

    }

    @Override
    protected void initView(Bundle bundle) {
        super.initView(bundle);

        apadter = new RecentMessageListApadter(data, getActivity());
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        apadter.OnMessageItemListener(this);
        apadter.addHeaderView(getHeaderView());
        rv.setAdapter(apadter);

        mPresenter.getConversationList();
    }

    private View getHeaderView() {
        headerView = View.inflate(getActivity(), R.layout.header_recent_message, null);
        TextView btDelete = headerView.findViewById(R.id.bt_delete);

//        btDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                isClearAll = true;
//                mPresenter.FetchDeleteWordById("word", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()));
//            }
//        });
        return headerView;
    }

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

    /***
     *  获取最近会话列表成功
     * @param conversationBean
     */
    @Override
    public void getConversationListSuccess(List<Conversation> conversationBean) {
        if(data.size()!=0) data.clear();
        data = conversationBean;
        apadter.setNewData(data);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void OnMessageItemListener(Conversation item) {
        Intent i = new Intent(getActivity(), ConversationActivity.class);
        i.putExtra("MemberId", String.valueOf(item.getTargetId()));
        i.putExtra("MemberName", String.valueOf(item.getSenderUserName()));
        MainActivity.mainActivity.startActivityIn(i, getActivity());
    }

}