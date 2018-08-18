package com.smg.art.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.utils.LocationUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.jaeger.library.StatusBarUtil;
import com.smg.art.R;
import com.smg.art.base.BaseApplication;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.utils.RongIMCUtils;
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


    /**
     * 会话类型
     */
    private String MemberId,MemberName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this,getResources().getColor(R.color.colorPrimaryDark), 10);

        if (getIntent() != null && getIntent() .getData() != null){
            MemberId = getIntent() .getData().getQueryParameter("targetId");
            MemberName = getIntent() .getData().getQueryParameter("title");
        }else {
            MemberId = getIntent().getStringExtra("MemberId");
            MemberName = getIntent().getStringExtra("MemberName");
        }
        ConversationFragment fragment = new ConversationFragment();

        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation").appendPath(io.rong.imlib.model.Conversation.ConversationType.PRIVATE.getName().toLowerCase())
                .appendQueryParameter("targetId", MemberId).build();
        fragment.setUri(uri);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.conversation, fragment);
        transaction.commit();
        actionbarTitle.setText(MemberName);

        switch (RongIMCUtils.state){
            case  RongIMCUtils.KICKED_OFFLINE_BY_OTHER_CLIENT :
                ToastUtils.showLongToast("用户账户在其他设备登录");
                break;
            case  RongIMCUtils.NETWORK_UNAVAILABLE :
                ToastUtils.showLongToast("网络不可用");
                break;
            case  RongIMCUtils.DISCONNECTED :
                ToastUtils.showLongToast("断开连接");
                break;
        }

    }
    @OnClick(R.id.rl_back)
    public void onViewClicked() {
        finish();
    }
    @Override
    public void finish() {
        super.finish();
        finishActivity();
    }
    protected void finishActivity() {
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
    }
}