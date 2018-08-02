package com.smg.art.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.utils.LocationUtils;
import com.jaeger.library.StatusBarUtil;
import com.smg.art.R;
import com.smg.art.base.BaseApplication;
import com.smg.art.utils.LocalAppConfigUtil;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * Created by Administrator on 2018/8/1.
 */

public class ConversationActivity extends FragmentActivity {
    @BindView(R.id.rl_back)
    AutoRelativeLayout rlBack;
    @BindView(R.id.actionbar_title)
    TextView actionbarTitle;
    private String mTargetId, title;

    boolean isFromPush = false;

    /**
     * 会话类型
     */
    private Conversation.ConversationType mConversationType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this,getResources().getColor(R.color.colorPrimaryDark), 10);
        Intent intent = getIntent();
        getIntentDate(intent);
        isReconnect(intent);
    }


    /**
     * 展示如何从 Intent 中得到 融云会话页面传递的 Uri
     */
    private void getIntentDate(Intent intent) {
        mTargetId = intent.getData().getQueryParameter("targetId");
        title = intent.getData().getQueryParameter("title");
        actionbarTitle.setText(title);
        //intent.getData().getLastPathSegment();//获得当前会话类型
        mConversationType = Conversation.ConversationType.valueOf(intent.getData().getLastPathSegment().toUpperCase(Locale.getDefault()));
//        enterFragment(mConversationType, mTargetId);

    }


    /**
     * 加载会话页面 ConversationFragment
     *
     * @param mConversationType
     * @param mTargetId
     */
    private void enterFragment(Conversation.ConversationType mConversationType, String mTargetId) {

        ConversationFragment fragment = (ConversationFragment) getSupportFragmentManager().findFragmentById(R.id.conversation);

        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation").appendPath(mConversationType.getName().toLowerCase())
                .appendQueryParameter("targetId", mTargetId).build();

        fragment.setUri(uri);
    }

    /**
     * 重连
     *
     * @param token
     */
    private void reconnect(String token) {
        Log.e("", "《重连》");

        if (getApplicationInfo().packageName.equals(BaseApplication.getCurProcessName(getApplicationContext()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {
                    Log.e("", "连接失败");
                }

                @Override
                public void onSuccess(String s) {
                    enterFragment(mConversationType, mTargetId);
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.e("", "连接失败—————>" + errorCode);
                }
            });
        }
    }


    /**
     * 判断消息是否是 push 消息
     */
    private void isReconnect(Intent intent) {
        String token = LocalAppConfigUtil.getInstance().getRCToken();

        if (intent == null || intent.getData() == null)
            return;
        //push
        if (intent.getData().getScheme().equals("rong") && intent.getData().getQueryParameter("isFromPush") != null) {
            isFromPush = true;

            //通过intent.getData().getQueryParameter("push") 为true，判断是否是push消息
            if (intent.getData().getQueryParameter("isFromPush").equals("true")) {
                reconnect(token);
            } else {
                //程序切到后台，收到消息后点击进入,会执行这里
                if (RongIM.getInstance() == null || RongIM.getInstance().getRongIMClient() == null) {
                    reconnect(token);
                } else {
                    enterFragment(mConversationType, mTargetId);
                }
            }
        }
    }

    @OnClick(R.id.rl_back)
    public void onViewClicked() {
        finish();
    }
}