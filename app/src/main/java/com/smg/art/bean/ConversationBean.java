package com.smg.art.bean;


import android.os.Parcel;
import android.os.Parcelable;

import android.text.TextUtils;
import io.rong.common.ParcelUtils;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.Message.ReceivedStatus;
import io.rong.imlib.model.Message.SentStatus;
import io.rong.imlib.model.MessageContent;

/**
 * Created by Lenovo on 2018/7/26.
 */

public class ConversationBean implements Parcelable {
    private io.rong.imlib.model.Conversation.ConversationType conversationType;
    private String targetId;
    private String conversationTitle;
    private String portraitUrl;
    private int unreadMessageCount;
    private boolean isTop;
    private ReceivedStatus receivedStatus;
    private SentStatus sentStatus;
    private long receivedTime;
    private long sentTime;
    private String objectName;
    private String senderUserId;
    private String senderUserName;
    private int latestMessageId;
    private MessageContent latestMessage;
    private String draft;
    private io.rong.imlib.model.Conversation.ConversationNotificationStatus notificationStatus;
    private int mentionedCount;
    public static final Creator<io.rong.imlib.model.Conversation> CREATOR = new Creator<io.rong.imlib.model.Conversation>() {
        public io.rong.imlib.model.Conversation createFromParcel(Parcel source) {
            return new io.rong.imlib.model.Conversation(source);
        }

        public io.rong.imlib.model.Conversation[] newArray(int size) {
            return new io.rong.imlib.model.Conversation[size];
        }
    };

    public ConversationBean() {
    }

    public static io.rong.imlib.model.Conversation obtain(io.rong.imlib.model.Conversation.ConversationType type, String id, String title) {
        io.rong.imlib.model.Conversation model = new io.rong.imlib.model.Conversation();
        model.setConversationType(type);
        model.setTargetId(id);
        model.setConversationTitle(title);
        return model;
    }

    public String getPortraitUrl() {
        return this.portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public io.rong.imlib.model.Conversation.ConversationType getConversationType() {
        return this.conversationType;
    }

    public void setConversationType(io.rong.imlib.model.Conversation.ConversationType conversationType) {
        this.conversationType = conversationType;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getConversationTitle() {
        return this.conversationTitle;
    }

    public void setConversationTitle(String conversationTitle) {
        this.conversationTitle = conversationTitle;
    }

    public int getUnreadMessageCount() {
        return this.unreadMessageCount;
    }

    public void setUnreadMessageCount(int unreadMessageCount) {
        this.unreadMessageCount = unreadMessageCount;
    }

    public boolean isTop() {
        return this.isTop;
    }

    public void setTop(boolean isTop) {
        this.isTop = isTop;
    }

    public Message.ReceivedStatus getReceivedStatus() {
        return this.receivedStatus;
    }

    public void setReceivedStatus(ReceivedStatus receivedStatus) {
        this.receivedStatus = receivedStatus;
    }

    public SentStatus getSentStatus() {
        return this.sentStatus;
    }

    public void setSentStatus(SentStatus sentStatus) {
        this.sentStatus = sentStatus;
    }

    public long getReceivedTime() {
        return this.receivedTime;
    }

    public void setReceivedTime(long receivedTime) {
        this.receivedTime = receivedTime;
    }

    public long getSentTime() {
        return this.sentTime;
    }

    public void setSentTime(long sentTime) {
        this.sentTime = sentTime;
    }

    public String getDraft() {
        return this.draft;
    }

    public void setDraft(String draft) {
        this.draft = draft;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public int getLatestMessageId() {
        return this.latestMessageId;
    }

    public void setLatestMessageId(int latestMessageId) {
        this.latestMessageId = latestMessageId;
    }

    public MessageContent getLatestMessage() {
        return this.latestMessage;
    }

    public void setLatestMessage(MessageContent latestMessage) {
        this.latestMessage = latestMessage;
    }

    public String getSenderUserId() {
        return this.senderUserId;
    }

    public void setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getSenderUserName() {
        return this.senderUserName;
    }

    public void setSenderUserName(String senderUserName) {
        this.senderUserName = senderUserName;
    }

    public io.rong.imlib.model.Conversation.ConversationNotificationStatus getNotificationStatus() {
        return this.notificationStatus;
    }

    public void setNotificationStatus(io.rong.imlib.model.Conversation.ConversationNotificationStatus notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    public void setMentionedCount(int id) {
        this.mentionedCount = id;
    }

    public int getMentionedCount() {
        return this.mentionedCount;
    }

    public int describeContents() {
        return 0;
    }

    public ConversationBean(Parcel in) {
        String className = ParcelUtils.readFromParcel(in);
        this.setConversationType(io.rong.imlib.model.Conversation.ConversationType.setValue(ParcelUtils.readIntFromParcel(in).intValue()));
        this.setTargetId(ParcelUtils.readFromParcel(in));
        this.setConversationTitle(ParcelUtils.readFromParcel(in));
        this.setUnreadMessageCount(ParcelUtils.readIntFromParcel(in).intValue());
        this.setTop(ParcelUtils.readIntFromParcel(in).intValue() == 1);
        this.setLatestMessageId(ParcelUtils.readIntFromParcel(in).intValue());
        this.setReceivedStatus(new ReceivedStatus(ParcelUtils.readIntFromParcel(in).intValue()));
        this.setSentStatus(SentStatus.setValue(ParcelUtils.readIntFromParcel(in).intValue()));
        this.setReceivedTime(ParcelUtils.readLongFromParcel(in).longValue());
        this.setSentTime(ParcelUtils.readLongFromParcel(in).longValue());
        this.setObjectName(ParcelUtils.readFromParcel(in));
        this.setSenderUserId(ParcelUtils.readFromParcel(in));
        this.setSenderUserName(ParcelUtils.readFromParcel(in));
        if(!TextUtils.isEmpty(className)) {
            Class loader = null;

            try {
                loader = Class.forName(className);
                this.setLatestMessage((MessageContent)ParcelUtils.readFromParcel(in, loader));
            } catch (ClassNotFoundException var5) {
                var5.printStackTrace();
            }
        } else {
            this.setLatestMessage((MessageContent)ParcelUtils.readFromParcel(in, MessageContent.class));
        }

        this.setDraft(ParcelUtils.readFromParcel(in));
        this.setPortraitUrl(ParcelUtils.readFromParcel(in));
        int status = ParcelUtils.readIntFromParcel(in).intValue();
        if(status != -1) {
            this.setNotificationStatus(io.rong.imlib.model.Conversation.ConversationNotificationStatus.setValue(status));
        }

        int mentionedId = ParcelUtils.readIntFromParcel(in).intValue();
        if(mentionedId > 0) {
            this.setMentionedCount(mentionedId);
        }

    }

    public void writeToParcel(Parcel dest, int flags) {
        ParcelUtils.writeToParcel(dest, this.getLatestMessage() == null?null:this.getLatestMessage().getClass().getName());
        ParcelUtils.writeToParcel(dest, Integer.valueOf(this.getConversationType().getValue()));
        ParcelUtils.writeToParcel(dest, this.getTargetId());
        ParcelUtils.writeToParcel(dest, this.getConversationTitle());
        ParcelUtils.writeToParcel(dest, Integer.valueOf(this.getUnreadMessageCount()));
        ParcelUtils.writeToParcel(dest, Integer.valueOf(this.isTop()?1:0));
        ParcelUtils.writeToParcel(dest, Integer.valueOf(this.getLatestMessageId()));
        ParcelUtils.writeToParcel(dest, Integer.valueOf(this.getReceivedStatus() == null?0:this.getReceivedStatus().getFlag()));
        ParcelUtils.writeToParcel(dest, Integer.valueOf(this.getSentStatus() == null?0:this.getSentStatus().getValue()));
        ParcelUtils.writeToParcel(dest, Long.valueOf(this.getReceivedTime()));
        ParcelUtils.writeToParcel(dest, Long.valueOf(this.getSentTime()));
        ParcelUtils.writeToParcel(dest, this.getObjectName());
        ParcelUtils.writeToParcel(dest, this.getSenderUserId());
        ParcelUtils.writeToParcel(dest, this.getSenderUserName());
        ParcelUtils.writeToParcel(dest, this.getLatestMessage());
        ParcelUtils.writeToParcel(dest, this.getDraft());
        ParcelUtils.writeToParcel(dest, this.getPortraitUrl());
        ParcelUtils.writeToParcel(dest, Integer.valueOf(this.getNotificationStatus() == null?-1:this.getNotificationStatus().getValue()));
        ParcelUtils.writeToParcel(dest, Integer.valueOf(this.getMentionedCount()));
    }

    public static enum ConversationNotificationStatus {
        DO_NOT_DISTURB(0),
        NOTIFY(1);

        private int value = 1;

        private ConversationNotificationStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static ConversationNotificationStatus setValue(int code) {
           ConversationNotificationStatus[] arr$ = values();
            int len$ = arr$.length;

            for(int i$ = 0; i$ < len$; ++i$) {
             ConversationNotificationStatus c = arr$[i$];
                if(code == c.getValue()) {
                    return c;
                }
            }

            return NOTIFY;
        }
    }

    public static enum ConversationType {
        NONE(0, "none"),
        PRIVATE(1, "private"),
        DISCUSSION(2, "discussion"),
        GROUP(3, "group"),
        CHATROOM(4, "chatroom"),
        CUSTOMER_SERVICE(5, "customer_service"),
        SYSTEM(6, "system"),
        APP_PUBLIC_SERVICE(7, "app_public_service"),
        PUBLIC_SERVICE(8, "public_service"),
        PUSH_SERVICE(9, "push_service");

        private int value = 1;
        private String name = "";

        private ConversationType(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public int getValue() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }

        public static ConversationType setValue(int code) {
           ConversationType[] arr$ = values();
            int len$ = arr$.length;

            for(int i$ = 0; i$ < len$; ++i$) {
             ConversationType c = arr$[i$];
                if(code == c.getValue()) {
                    return c;
                }
            }

            return PRIVATE;
        }
    }

    public static enum PublicServiceType {
        APP_PUBLIC_SERVICE(7, "app_public_service"),
        PUBLIC_SERVICE(8, "public_service");

        private int value = 1;
        private String name = "";

        private PublicServiceType(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public int getValue() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }

        public static ConversationBean.PublicServiceType setValue(int code) {
            ConversationBean. PublicServiceType[] arr$ = values();
            int len$ = arr$.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                ConversationBean.   PublicServiceType c = arr$[i$];
                if(code == c.getValue()) {
                    return c;
                }
            }
            return null;
        }
    }
}
