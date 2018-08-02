package com.smg.art.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.smg.art.R;
import com.smg.art.base.BaseApplication;
import com.smg.art.base.BaseFragment;
import com.smg.art.base.Constant;
import com.smg.art.component.AppComponent;
import com.smg.art.presenter.impl.fragment.RecentMessagePresenter;
import com.smg.art.utils.LocalAppConfigUtil;

import javax.inject.Inject;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * Created by Lenovo on 2018/7/25.
 */

public class RecentMessageFragment extends BaseFragment {

    @Inject
    RecentMessagePresenter mPresenter;


    @Override
    protected void initView(Bundle bundle) {
        super.initView(bundle);
//        isReconnect();
//        String token = LocalAppConfigUtil.getInstance().getRCToken();
//        reconnect(token);
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

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    /**
     * 判断消息是否是 push 消息
     */
    private void isReconnect() {

        Intent intent = getActivity().getIntent();
        String token = LocalAppConfigUtil.getInstance().getRCToken();

        //push，通知或新消息过来
        if (intent != null && intent.getData() != null && intent.getData().getScheme().equals("rong")) {

            //通过intent.getData().getQueryParameter("push") 为true，判断是否是push消息
            if (intent.getData().getQueryParameter("push") != null
                    && intent.getData().getQueryParameter("push").equals("true")) {

                reconnect(token);
            } else {
                //程序切到后台，收到消息后点击进入,会执行这里
                if (RongIM.getInstance() == null || RongIM.getInstance().getRongIMClient() == null) {

                    reconnect(token);
                } else {
                    enterFragment();
                }
            }
        }
    }

    /**
     * 重连
     *
     * @param token
     */
    private void reconnect(String token) {

        if (getActivity().getApplicationInfo().packageName.equals(BaseApplication.getCurProcessName(BaseApplication.getBaseApplication()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {

                }

                @Override
                public void onSuccess(String s) {

                    enterFragment();
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                }
            });
        }
    }

    /**
     * 加载 会话列表 ConversationListFragment
     */
    private void enterFragment() {

        ConversationListFragment fragment = (ConversationListFragment) getChildFragmentManager().findFragmentById(R.id.conversationlist);
        Uri uri = Uri.parse("rong://" + getActivity().getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "true") //设置私聊会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//设置群组会话聚合显示
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置讨论组会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置系统会话非聚合显示
                .build();
        fragment.setUri(uri);
    }
}
