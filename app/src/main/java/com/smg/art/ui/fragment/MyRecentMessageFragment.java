package com.smg.art.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smg.art.R;
import com.smg.art.ui.activity.ConversationActivity;
import com.smg.art.ui.activity.SystemMessageActivity;

import io.rong.imkit.MainActivity;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.widget.adapter.ConversationListAdapter;
import io.rong.imlib.model.Conversation;

/**
 * Created by Lenovo on 2018/7/25.
 */

public class MyRecentMessageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.rong_activity, container, false);
        /**
         * 设置会话列表界面操作的监听器。
         */
        RongIM.setConversationListBehaviorListener(new MyConversationListBehaviorListener());
        ConversationListFragment fragment = new ConversationListFragment();
        fragment.setAdapter(new ConversationListAdapterEx(RongContext.getInstance()));

        Uri uri = Uri.parse("rong://" + getActivity().getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")        //设置私聊会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")             //设置群组会话聚合显示
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置讨论组会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")         //设置系统会话非聚合显示
                .build();

        fragment.setUri(uri);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.rong_content, fragment);
        transaction.commit();
        return view;

    }

    public class ConversationListAdapterEx extends ConversationListAdapter {
        public ConversationListAdapterEx(Context context) {
            super(context);
        }

        @Override
        protected View newView(Context context, int position, ViewGroup group) {
            return super.newView(context, position, group);
        }

        @Override
        protected void bindView(View v, int position, UIConversation data) {
            super.bindView(v, position, data);
        }
    }

    private class MyConversationListBehaviorListener implements RongIM.ConversationListBehaviorListener{
        /**
         * 当点击会话头像后执行。
         *
         * @param context          上下文。
         * @param conversationType 会话类型。
         * @param targetId         被点击的用户id。
         * @return 如果用户自己处理了点击后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式。
         */
        public boolean onConversationPortraitClick(Context context, Conversation.ConversationType conversationType, String targetId){
            return false;
        }

        /**
         * 当长按会话头像后执行。
         *
         * @param context          上下文。
         * @param conversationType 会话类型。
         * @param targetId         被点击的用户id。
         * @return 如果用户自己处理了点击后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式。
         */
        public boolean onConversationPortraitLongClick(Context context, Conversation.ConversationType conversationType, String targetId){
            return false;
        }
        /**
         * 长按会话列表中的 item 时执行。
         *
         * @param context        上下文。
         * @param view           触发点击的 View。
         * @param uiConversation 长按时的会话条目。
         * @return 如果用户自己处理了长按会话后的逻辑处理，则返回 true， 否则返回 false，false 走融云默认处理方式。
         */
        @Override
        public boolean onConversationLongClick(Context context, View view, UIConversation uiConversation) {
            return false;
        }

        /**
         * 点击会话列表中的 item 时执行。
         *
         * @param context        上下文。
         * @param view           触发点击的 View。
         * @param uiConversation 会话条目。
         * @return 如果用户自己处理了点击会话后的逻辑处理，则返回 true， 否则返回 false，false 走融云默认处理方式。
         */
        @Override
        public boolean onConversationClick(Context context, View view, UIConversation uiConversation) {

            if(uiConversation.getConversationTargetId().equals("1")){
                Intent i = new Intent( getActivity(),SystemMessageActivity.class);
                i.putExtra("type",1);
                com.smg.art.ui.activity.MainActivity.mainActivity.startActivityIn(i,getActivity());
                return  true;
            }else if(uiConversation.getConversationTargetId().equals("2")){
                Intent i = new Intent( getActivity(),SystemMessageActivity.class);
                i.putExtra("type",2);
                com.smg.art.ui.activity.MainActivity.mainActivity.startActivityIn(i,getActivity());
                return  true;
            }else {
                Intent i = new Intent( getActivity(),ConversationActivity.class);
                i.putExtra("MemberId",uiConversation.getConversationTargetId());
                i.putExtra("MemberName",uiConversation.getUIConversationTitle());
                com.smg.art.ui.activity.MainActivity.mainActivity.startActivityIn(i,getActivity());
                return  true;
            }
        }
    }
}
