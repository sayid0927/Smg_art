package com.smg.art.ui.adapter;

import android.content.Context;
import android.view.View;

import com.blankj.utilcode.utils.EmptyUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.smg.art.R;
import com.smg.art.base.BaseApplication;
import com.smg.art.db.database.RongUserInfoEntityDao;
import com.smg.art.db.entity.RongUserInfoEntity;
import com.smg.art.utils.GlideUtils;
import com.smg.art.utils.GreenDaoUtil;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.utils.UIUtils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import io.rong.common.RLog;
import io.rong.imkit.RongContext;
import io.rong.imkit.emoticon.AndroidEmoji;
import io.rong.imkit.model.ConversationProviderTag;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.utils.RongDateUtils;
import io.rong.imkit.widget.adapter.ConversationListAdapter;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Conversation;
import io.rong.message.ImageMessage;
import io.rong.message.LocationMessage;
import io.rong.message.TextMessage;
import io.rong.message.VoiceMessage;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class RecentMessageApadter extends BaseQuickAdapter<Conversation, BaseViewHolder> {

    private Context mContext;
    private List<Conversation> data;
    private RongUserInfoEntityDao collectionInfoDao;

    public RecentMessageApadter(List<Conversation> data, Context mContext) {
        super(R.layout.item_recent_message, data);
        this.mContext = mContext;
        this.data = data;
        this.collectionInfoDao = GreenDaoUtil.getDaoSession().getRongUserInfoEntityDao();
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
                    if (data.getUnreadMessageCount() > 0) {
                        helper.getView(R.id.tvCount).setVisibility(View.VISIBLE);
                        helper.setText(R.id.tvCount, String.valueOf(data.getUnreadMessageCount()));
                    } else {
                        helper.getView(R.id.tvCount).setVisibility(View.GONE);
                    }
                    String str = RongDateUtils.getConversationListFormatDate(data.getReceivedTime(), BaseApplication.getBaseApplication());
                    helper.setText(R.id.tv_time, str);

                    if (EmptyUtils.isNotEmpty(data.getLatestMessage().getUserInfo())) {

                        if (!data.getLatestMessage().getUserInfo().getUserId().equals(LocalAppConfigUtil.getInstance().getRongUserId())) {
                            String urlImg = String.valueOf(data.getLatestMessage().getUserInfo().getPortraitUri());
                            if (EmptyUtils.isNotEmpty(urlImg)) {
                                GlideUtils.loadFitCenter(mContext, urlImg, helper.getView(R.id.ivHeader));
                            }
                            if (EmptyUtils.isNotEmpty(data.getLatestMessage().getUserInfo().getName()))
                                helper.setText(R.id.tv_name, data.getLatestMessage().getUserInfo().getName());
                            //  用户信息添加到本地

                            RongUserInfoEntity r = collectionInfoDao.queryBuilder().where(
                                    RongUserInfoEntityDao.Properties.UserId.eq(data.getTargetId())).unique();

                            if(r!=null){
                                RongUserInfoEntity rongUserInfoEntity = new RongUserInfoEntity();
                                rongUserInfoEntity.setId(r.getId());
                                rongUserInfoEntity.setUserId(data.getLatestMessage().getUserInfo().getUserId());
                                rongUserInfoEntity.setUserName(data.getLatestMessage().getUserInfo().getName());
                                rongUserInfoEntity.setUserPortraitUri(String.valueOf(data.getLatestMessage().getUserInfo().getPortraitUri()));
                                collectionInfoDao.update(rongUserInfoEntity);
                            }else {
                                RongUserInfoEntity rongUserInfoEntity = new RongUserInfoEntity();
                                rongUserInfoEntity.setUserId(data.getLatestMessage().getUserInfo().getUserId());
                                rongUserInfoEntity.setUserName(data.getLatestMessage().getUserInfo().getName());
                                rongUserInfoEntity.setUserPortraitUri(String.valueOf(data.getLatestMessage().getUserInfo().getPortraitUri()));
                                collectionInfoDao.insert(rongUserInfoEntity);
                            }
                        } else {
                            // 在本地中查找用户信息
                            RongUserInfoEntity r = collectionInfoDao.queryBuilder().where(
                                    RongUserInfoEntityDao.Properties.UserId.eq(data.getTargetId())).unique();
                            if (r!=null) {
                                String urlImg = r.getUserPortraitUri();
                                if (EmptyUtils.isNotEmpty(urlImg)) {
                                    GlideUtils.loadFitCenter(mContext, urlImg, helper.getView(R.id.ivHeader));
                                }
                                if (EmptyUtils.isNotEmpty(r.getUserName()))
                                    helper.setText(R.id.tv_name, r.getUserName());
                            } else {
                                helper.setText(R.id.tv_name, data.getTargetId());
                            }
                        }
                    } else {
                        helper.setText(R.id.tv_name, data.getTargetId());
                    }

                    helper.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (onMessageItemListener != null) {
                                onMessageItemListener.OnMessageItemListener(data);
                            }
                        }
                    });

                    helper.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            if (onMessageItemLongListener != null) {
                                onMessageItemLongListener.OnMessageItemLongListener(data);
                            }
                            return false;
                        }
                    });
                }
            }
        }
    }


    private OnMessageItemListener onMessageItemListener;

    public void OnMessageItemListener(OnMessageItemListener onMessageItemListener) {
        this.onMessageItemListener = onMessageItemListener;
    }

    public interface OnMessageItemListener {
        void OnMessageItemListener(Conversation item);
    }


    private OnMessageItemLongListener onMessageItemLongListener;

    public void OnMessageItemLongListener(OnMessageItemLongListener onMessageItemLongListener) {
        this.onMessageItemLongListener = onMessageItemLongListener;
    }

    public interface OnMessageItemLongListener {
        void OnMessageItemLongListener(Conversation item);
    }
}
