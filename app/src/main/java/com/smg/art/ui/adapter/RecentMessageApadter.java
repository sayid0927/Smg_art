package com.smg.art.ui.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smg.art.R;

import java.util.List;

import io.rong.common.RLog;
import io.rong.imkit.RongContext;
import io.rong.imkit.model.ConversationProviderTag;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.widget.adapter.ConversationListAdapter;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Conversation;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class RecentMessageApadter extends BaseQuickAdapter<Conversation, BaseViewHolder> {

    private Context mContext;
    private List<Conversation> data;

    public RecentMessageApadter(List<Conversation> data, Context mContext) {
        super(R.layout.rc_item_conversation, data);
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, final Conversation data) {

        if(data != null) {
            IContainerItemProvider provider = RongContext.getInstance().getConversationTemplate(data.getConversationType().getName());
            if(provider == null) {
                RLog.e("ConversationListAdapter", "provider is null");
            } else {
                View view =    helper.getView(R.id.rc_content);
//                View view = holder.contentView.inflate(provider);
                provider.bindView(view, helper.getPosition(), data);
//                if(data.isTop()) {
//
//                    holder.layout.setBackgroundDrawable(this.mContext.getResources().getDrawable(io.rong.imkit.R.drawable.rc_item_top_list_selector));
//                } else {
//                    holder.layout.setBackgroundDrawable(this.mContext.getResources().getDrawable(io.rong.imkit.R.drawable.rc_item_list_selector));
//                }

                ConversationProviderTag tag = RongContext.getInstance().getConversationProviderTag(data.getConversationType().getName());
                int defaultId;
                if(data.getConversationType().equals(Conversation.ConversationType.GROUP)) {
                    defaultId = io.rong.imkit.R.drawable.rc_default_group_portrait;
                } else if(data.getConversationType().equals(Conversation.ConversationType.DISCUSSION)) {
                    defaultId = io.rong.imkit.R.drawable.rc_default_discussion_portrait;
                } else {
                    defaultId = io.rong.imkit.R.drawable.rc_default_portrait;
                }

                if(tag.portraitPosition() == 1) {
                    helper.getView(R.id.rc_item1).setVisibility(View.VISIBLE);
//                    holder.leftImageLayout.setVisibility(0);
//                    holder.leftImageLayout.setOnClickListener(new View.OnClickListener() {
//                        public void onClick(View v) {
//                            if(ConversationListAdapter.this.mOnPortraitItemClick != null) {
//                                ConversationListAdapter.this.mOnPortraitItemClick.onPortraitItemClick(v, data);
//                            }
//
//                        }
//                    });
//                    holder.leftImageLayout.setOnLongClickListener(new OnLongClickListener() {
//                        public boolean onLongClick(View v) {
//                            if(ConversationListAdapter.this.mOnPortraitItemClick != null) {
//                                ConversationListAdapter.this.mOnPortraitItemClick.onPortraitItemLongClick(v, data);
//                            }
//
//                            return true;
//                        }
//                    });
//                    if(data.getConversationGatherState()) {
//                        holder.leftImageView.setAvatar((String)null, defaultId);
//                    } else
//                        if(data.getIconUrl() != null) {
//                        holder.leftImageView.setAvatar(data.getIconUrl().toString(), defaultId);
//                    } else {
//                        holder.leftImageView.setAvatar((String)null, defaultId);
//                    }
//
//                    if(data.getUnReadMessageCount() > 0) {
//                        holder.unReadMsgCountIcon.setVisibility(0);
//                        this.setUnReadViewLayoutParams(holder.leftUnReadView, data.getUnReadType());
//                        if(data.getUnReadType().equals(UIConversation.UnreadRemindType.REMIND_WITH_COUNTING)) {
//                            if(data.getUnReadMessageCount() > 99) {
//                                holder.unReadMsgCount.setText(this.mContext.getResources().getString(io.rong.imkit.R.string.rc_message_unread_count));
//                            } else {
//                                holder.unReadMsgCount.setText(Integer.toString(data.getUnReadMessageCount()));
//                            }
//
//                            holder.unReadMsgCount.setVisibility(0);
//                            holder.unReadMsgCountIcon.setImageResource(io.rong.imkit.R.drawable.rc_unread_count_bg);
//                        } else {
//                            holder.unReadMsgCount.setVisibility(8);
//                            holder.unReadMsgCountIcon.setImageResource(io.rong.imkit.R.drawable.rc_unread_remind_list_count);
//                        }
//                    } else {
//                        holder.unReadMsgCountIcon.setVisibility(8);
//                        holder.unReadMsgCount.setVisibility(8);
//                    }
//
//                    holder.rightImageLayout.setVisibility(8);
//                } else if(tag.portraitPosition() == 2) {
//                    holder.rightImageLayout.setVisibility(0);
//                    holder.rightImageLayout.setOnClickListener(new View.OnClickListener() {
//                        public void onClick(View v) {
//                            if(ConversationListAdapter.this.mOnPortraitItemClick != null) {
//                                ConversationListAdapter.this.mOnPortraitItemClick.onPortraitItemClick(v, data);
//                            }
//
//                        }
//                    });
//
//                    if(data.getConversationGatherState()) {
//                        holder.rightImageView.setAvatar((String)null, defaultId);
//                    } else if(data.getIconUrl() != null) {
//                        holder.rightImageView.setAvatar(data.getIconUrl().toString(), defaultId);
//                    } else {
//                        holder.rightImageView.setAvatar((String)null, defaultId);
//                    }
//
//                    if(data.getUnReadMessageCount() > 0) {
//                        holder.unReadMsgCountRightIcon.setVisibility(0);
//                        this.setUnReadViewLayoutParams(holder.rightUnReadView, data.getUnReadType());
//                        if(data.getUnReadType().equals(UnreadRemindType.REMIND_WITH_COUNTING)) {
//                            holder.unReadMsgCount.setVisibility(0);
//                            if(data.getUnReadMessageCount() > 99) {
//                                holder.unReadMsgCountRight.setText(this.mContext.getResources().getString(io.rong.imkit.R.string.rc_message_unread_count));
//                            } else {
//                                holder.unReadMsgCountRight.setText(Integer.toString(data.getUnReadMessageCount()));
//                            }
//
//                            holder.unReadMsgCountRightIcon.setImageResource(io.rong.imkit.R.drawable.rc_unread_count_bg);
//                        } else {
//                            holder.unReadMsgCount.setVisibility(8);
//                            holder.unReadMsgCountRightIcon.setImageResource(io.rong.imkit.R.drawable.rc_unread_remind_without_count);
//                        }
//                    } else {
//                        holder.unReadMsgCountIcon.setVisibility(8);
//                        holder.unReadMsgCount.setVisibility(8);
//                    }
//
//                    holder.leftImageLayout.setVisibility(8);
//                } else {
//                    if(tag.portraitPosition() != 3) {
//                        throw new IllegalArgumentException("the portrait position is wrong!");
//                    }
////                    holder.rightImageLayout.setVisibility(8);
////                    holder.leftImageLayout.setVisibility(8);
                }
            }
        }
    }



    private  OnMessageItemListener  onMessageItemListener;

    public void OnMessageItemListener (OnMessageItemListener  onMessageItemListener){
        this.onMessageItemListener =onMessageItemListener;
    }

    public  interface  OnMessageItemListener {
        void  OnMessageItemListener(Conversation item);
    }



}
