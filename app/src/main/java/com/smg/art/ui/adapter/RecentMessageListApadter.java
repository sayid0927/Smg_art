package com.smg.art.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lqr.emoji.MoonUtils;
import com.smg.art.R;
import com.smg.art.utils.TimeUtils;
import com.smg.art.utils.UIUtils;

import java.util.List;

import io.rong.imlib.model.Conversation;
import io.rong.message.ImageMessage;
import io.rong.message.TextMessage;
import io.rong.message.VoiceMessage;


/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class RecentMessageListApadter extends BaseQuickAdapter<Conversation, BaseViewHolder> {

    private Context mContext;
    private List<Conversation> data;
    private OnMessageItemListener onMessageItemListener;

    public RecentMessageListApadter(List<Conversation> data, Context mContext) {
        super(R.layout.item_recent_message, data);
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final Conversation item) {

        if (item.getConversationType() == Conversation.ConversationType.PRIVATE) {
            ImageView ivHeader = helper.getView(R.id.ivHeader);
            if (item.getPortraitUrl() != null && !item.getPortraitUrl().equals("")) {
                Glide.with(mContext).load(item.getPortraitUrl()).centerCrop().into(ivHeader);
            }

            helper.setText(R.id.tvDisplayName, item.getSenderUserName())
                    .setText(R.id.tvTime, TimeUtils.getMsgFormatTime(item.getReceivedTime()));

//            helper.setBackgroundColor(R.id.flRoot, item.isTop() ? R.color.gray8 : android.R.color.white)
            helper  .setText(R.id.tvCount, item.getUnreadMessageCount() + "");

            if (item.getUnreadMessageCount() > 0) {
                helper.getView(R.id.tvCount).setVisibility(View.VISIBLE);
            } else {
                helper.getView(R.id.tvCount).setVisibility(View.GONE);
            }

            TextView tvContent = helper.getView(R.id.tvContent);
            if (!TextUtils.isEmpty(item.getDraft())) {
                MoonUtils.identifyFaceExpression(mContext, tvContent, item.getDraft(), ImageSpan.ALIGN_BOTTOM);
                helper.getView(R.id.tvDraft).setVisibility(View.VISIBLE);
                return;
            } else {
                helper.getView(R.id.tvDraft).setVisibility(View.GONE);
            }

            if (item.getLatestMessage() instanceof TextMessage) {
                tvContent.setText(((TextMessage) item.getLatestMessage()).getContent());
//                MoonUtils.identifyFaceExpression(mContext, tvContent, ((TextMessage) item.getLatestMessage()).getContent(), ImageSpan.ALIGN_BOTTOM);
            } else if (item.getLatestMessage() instanceof ImageMessage) {
                tvContent.setText("[" + UIUtils.getString(R.string.picture) + "]");
            } else if (item.getLatestMessage() instanceof VoiceMessage) {
                tvContent.setText("[" + UIUtils.getString(R.string.voice) + "]");
            }
        }

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onMessageItemListener != null)
                    onMessageItemListener.OnMessageItemListener(item);
            }
        });
    }

    public void OnMessageItemListener(OnMessageItemListener onMessageItemListener) {
        this.onMessageItemListener = onMessageItemListener;
    }

    public interface OnMessageItemListener {
        void OnMessageItemListener(Conversation item);
    }

}
