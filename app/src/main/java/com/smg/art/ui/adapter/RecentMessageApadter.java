package com.smg.art.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smg.art.R;
import com.smg.art.base.BaseApplication;
import com.smg.art.utils.GlideUtils;
import com.smg.art.utils.UIUtils;

import java.util.List;

import io.rong.imkit.emoticon.AndroidEmoji;
import io.rong.imkit.utils.RongDateUtils;
import io.rong.imlib.model.Conversation;
import io.rong.message.ImageMessage;
import io.rong.message.TextMessage;
import io.rong.message.VoiceMessage;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class RecentMessageApadter extends BaseQuickAdapter<Conversation, BaseViewHolder> {

    private Context mContext;
    private List<Conversation> data;
    private OnMessageItemListener onMessageItemListener;

    public RecentMessageApadter(List<Conversation> data, Context mContext) {
        super(R.layout.item_recent_message, data);
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, final Conversation data) {

        if (data != null) {
            if (data.getConversationType() == Conversation.ConversationType.PRIVATE) {
                if (!data.getTargetId().equals("1") && !data.getTargetId().equals("2")) {
                    if (data.getLatestMessage() instanceof TextMessage) {
                        helper.setText(R.id.tv_content, AndroidEmoji.ensure(((TextMessage) data.getLatestMessage()).getContent()));
                    } else if (data.getLatestMessage() instanceof ImageMessage) {
                        helper.setText(R.id.tv_content, "[" + UIUtils.getString(R.string.picture) + "]");
                    } else if (data.getLatestMessage() instanceof VoiceMessage) {
                        helper.setText(R.id.tv_content, "[" + UIUtils.getString(R.string.voice) + "]");
                    }
                    if(data.getUnreadMessageCount()>0){
                        helper.getView(R.id.tvCount).setVisibility(View.VISIBLE);
                        helper.setText(R.id.tvCount,String.valueOf(data.getUnreadMessageCount()));
                    }else {
                        helper.getView(R.id.tvCount).setVisibility(View.GONE);
                    }
                    String str = RongDateUtils.getConversationListFormatDate(data.getReceivedTime(),BaseApplication.getBaseApplication());
                    helper.setText(R.id.tv_time, str);
                    helper.setText(R.id.tv_name, data.getLatestMessage().getUserInfo().getName());
                    String urlImg = String.valueOf(data.getLatestMessage().getUserInfo().getPortraitUri());
                    GlideUtils.loadFitCenter(mContext, urlImg, (ImageView) helper.getView(R.id.ivHeader));

                    helper.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(onMessageItemListener!=null){
                                onMessageItemListener.OnMessageItemListener(data);
                            }
                        }
                    });
                }
            }
        }
    }

    public void OnMessageItemListener(OnMessageItemListener onMessageItemListener) {
        this.onMessageItemListener = onMessageItemListener;
    }

    public interface OnMessageItemListener {
        void OnMessageItemListener(Conversation item);
    }
}
