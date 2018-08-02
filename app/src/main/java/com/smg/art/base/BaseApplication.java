package com.smg.art.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.PinyinUtils;
import com.blankj.utilcode.utils.Utils;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerAppComponent;
import com.smg.art.message.DeleteContactMessage;
import com.smg.art.message.RedPacketMessage;
import com.smg.art.module.ApiModule;
import com.smg.art.module.AppModule;
import com.smg.art.utils.PreferUtil;

import io.rong.imkit.RongIM;


public class BaseApplication extends Application  {

    public  static BaseApplication baseApplication;
    private static AppComponent appComponent;


    public static BaseApplication getContext() {
        return baseApplication;
    }

    /**
     * 获取BaseApplication实例
     *
     * @return
     */

    public static BaseApplication getBaseApplication() {
        return baseApplication;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        //将我们自己的MyApplication中的所有逻辑放在这里，例如初始化一些第三方
        initCompoent();
        initLogger();
        Utils.init(this);
//        CrashHandler.getInstance(this).init();
        PreferUtil.getInstance().init(this);
        //初始化融云
        initRongCloud();
        //初始化红包
//        initRedPacket();

    }

    private void initCompoent() {
        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .build();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    private void initLogger() {
        Logger.init("ART").methodCount(2).methodOffset(0).logLevel(LogLevel.FULL).hideThreadInfo();
    }

    private void initRongCloud() {
        /**
         * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIMClient 的进程和 Push 进程执行了 init。
         * io.rong.push 为融云 push 进程名称，不可修改。
         */
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {
//            RongIMClient.init(this);
            RongIM.init(this);

        }

        //监听接收到的消息
//        RongIMClient.setOnReceiveMessageListener(this);
//        try {
//            RongIMClient.registerMessageType(RedPacketMessage.class);
//            RongIMClient.registerMessageType(DeleteContactMessage.class);
//        } catch (AnnotationNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

//    @Override
//    public boolean onReceived(Message message, int i) {
//        MessageContent messageContent = message.getContent();
//        if (messageContent instanceof ContactNotificationMessage) {
//            ContactNotificationMessage contactNotificationMessage = (ContactNotificationMessage) messageContent;
//            if (contactNotificationMessage.getOperation().equals(ContactNotificationMessage.CONTACT_OPERATION_REQUEST)) {
//                //对方发来好友邀请
////                BroadcastManager.getInstance(UIUtils.getContext()).sendBroadcast(AppConst.UPDATE_RED_DOT);
//            } else {
//                //对方同意我的好友请求
////                ContactNotificationMessageData c = null;
////                try {
////                    c = JsonMananger.jsonToBean(contactNotificationMessage.getExtra(), ContactNotificationMessageData.class);
////                } catch (HttpException e) {
////                    e.printStackTrace();
////                    return false;
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                    return false;
////                }
////                if (c != null) {
////                    if (DBManager.getInstance().isMyFriend(contactNotificationMessage.getSourceUserId()))
////                        return false;
////                    DBManager.getInstance().saveOrUpdateFriend(
////                            new Friend(contactNotificationMessage.getSourceUserId(),
////                                    c.getSourceUserNickname(),
////                                    null, c.getSourceUserNickname(), null, null,
////                                    null, null,
////                                    PinyinUtils.getPinyin(c.getSourceUserNickname()),
////                                    PinyinUtils.getPinyin(c.getSourceUserNickname())
////                            )
////                    );
////                    BroadcastManager.getInstance(UIUtils.getContext()).sendBroadcast(AppConst.UPDATE_FRIEND);
////                    BroadcastManager.getInstance(UIUtils.getContext()).sendBroadcast(AppConst.UPDATE_RED_DOT);
////                }
//            }
//        } else if (messageContent instanceof DeleteContactMessage) {
//            DeleteContactMessage deleteContactMessage = (DeleteContactMessage) messageContent;
//            String contact_id = deleteContactMessage.getContact_id();
////            RongIMClient.getInstance().getConversation(Conversation.ConversationType.PRIVATE, contact_id, new RongIMClient.ResultCallback<Conversation>() {
////                @Override
////                public void onSuccess(Conversation conversation) {
////                    RongIMClient.getInstance().clearMessages(Conversation.ConversationType.PRIVATE, contact_id, new RongIMClient.ResultCallback<Boolean>() {
////                        @Override
////                        public void onSuccess(Boolean aBoolean) {
////                            RongIMClient.getInstance().removeConversation(Conversation.ConversationType.PRIVATE, contact_id, null);
////                            BroadcastManager.getInstance(getContext()).sendBroadcast(AppConst.UPDATE_CONVERSATIONS);
////                        }
////
////                        @Override
////                        public void onError(RongIMClient.ErrorCode errorCode) {
////
////                        }
////                    });
////                }
////
////                @Override
////                public void onError(RongIMClient.ErrorCode errorCode) {
////
////                }
////            });
////            DBManager.getInstance().deleteFriendById(contact_id);
////            BroadcastManager.getInstance(getContext()).sendBroadcast(AppConst.UPDATE_FRIEND);
//        } else if (messageContent instanceof GroupNotificationMessage) {
////            GroupNotificationMessage groupNotificationMessage = (GroupNotificationMessage) messageContent;
////            String groupId = message.getTargetId();
////            GroupNotificationMessageData data = null;
////            try {
////                String curUserId = UserCache.getId();
////                try {
////                    data = jsonToBean(groupNotificationMessage.getData());
////                } catch (Exception e) {
////                    e.printStackTrace();
////                }
////                if (groupNotificationMessage.getOperation().equals(GroupNotificationMessage.GROUP_OPERATION_CREATE)) {
////                    DBManager.getInstance().getGroups(groupId);
////                    DBManager.getInstance().getGroupMember(groupId);
////                } else if (groupNotificationMessage.getOperation().equals(GroupNotificationMessage.GROUP_OPERATION_DISMISS)) {
////                    handleGroupDismiss(groupId);
////                } else if (groupNotificationMessage.getOperation().equals(GroupNotificationMessage.GROUP_OPERATION_KICKED)) {
////                    if (data != null) {
////                        List<String> memberIdList = data.getTargetUserIds();
////                        if (memberIdList != null) {
////                            for (String userId : memberIdList) {
////                                if (curUserId.equals(userId)) {
////                                    RongIMClient.getInstance().removeConversation(Conversation.ConversationType.GROUP, message.getTargetId(), new RongIMClient.ResultCallback<Boolean>() {
////                                        @Override
////                                        public void onSuccess(Boolean aBoolean) {
////                                            LogUtils.sf("Conversation remove successfully.");
////                                        }
////
////                                        @Override
////                                        public void onError(RongIMClient.ErrorCode e) {
////
////                                        }
////                                    });
////                                }
////                            }
////                        }
////                        List<String> kickedUserIDs = data.getTargetUserIds();
////                        DBManager.getInstance().deleteGroupMembers(groupId, kickedUserIDs);
//                        //因为操作存在异步，故不在这里发送广播
////                        BroadcastManager.getInstance(getContext()).sendBroadcast(AppConst.UPDATE_GROUP_MEMBER, groupId);
////                        BroadcastManager.getInstance(getContext()).sendBroadcast(AppConst.UPDATE_CONVERSATIONS);
////                    }
////                } else if (groupNotificationMessage.getOperation().equals(GroupNotificationMessage.GROUP_OPERATION_ADD)) {
////                    DBManager.getInstance().getGroups(groupId);
////                    DBManager.getInstance().getGroupMember(groupId);
////                    //因为操作存在异步，故不在这里发送广播
//////                    BroadcastManager.getInstance(getContext()).sendBroadcast(AppConst.UPDATE_GROUP_MEMBER, groupId);
////                } else if (groupNotificationMessage.getOperation().equals(GroupNotificationMessage.GROUP_OPERATION_QUIT)) {
////                    if (data != null) {
////                        List<String> quitUserIDs = data.getTargetUserIds();
////                        DBManager.getInstance().deleteGroupMembers(groupId, quitUserIDs);
////                        //因为操作存在异步，故不在这里发送广播
//////                        BroadcastManager.getInstance(getContext()).sendBroadcast(AppConst.UPDATE_GROUP_MEMBER, groupId);
//////                        BroadcastManager.getInstance(getContext()).sendBroadcast(AppConst.UPDATE_CONVERSATIONS);
////                    }
////                } else if (groupNotificationMessage.getOperation().equals(GroupNotificationMessage.GROUP_OPERATION_RENAME)) {
////                    if (data != null) {
////                        String targetGroupName = data.getTargetGroupName();
////                        DBManager.getInstance().updateGroupsName(groupId, targetGroupName);
////                        //更新群名
////                        BroadcastManager.getInstance(getContext()).sendBroadcast(AppConst.UPDATE_CURRENT_SESSION_NAME);
////                        BroadcastManager.getInstance(getContext()).sendBroadcast(AppConst.UPDATE_CONVERSATIONS);
////                    }
////                }
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////            BroadcastManager.getInstance(getContext()).sendBroadcast(AppConst.UPDATE_CONVERSATIONS);
////        } else {
////            //TODO:还有其他类型的信息
////            BroadcastManager.getInstance(getContext()).sendBroadcast(AppConst.UPDATE_CONVERSATIONS);
////            BroadcastManager.getInstance(getContext()).sendBroadcast(AppConst.UPDATE_CURRENT_SESSION, message);
//        }
//        return false;
//    }
}
