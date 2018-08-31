package com.smg.art.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.R;

/**
 * Created by Lenovo on 2018/8/23.
 */

public class NumberDialog extends Dialog implements XNumberKeyboardView.IOnKeyboardListener {
    private Activity context;


    private XNumberKeyboardView mNkvKeyboard;
    private EditText tvPwd;
    private ImageView ivDele;
    private Button btAuction;
    private String str = "";
    View localView;

    public NumberDialog(Activity context) {
        super(context, R.style.dialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater =context.getLayoutInflater();
        localView =inflater.inflate(R.layout.dialog_number, null);
        localView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.to_top));
        setContentView(localView);
        setCanceledOnTouchOutside(false);
        setCancelable(true);//弹出框不可以换返回键取消
        setCanceledOnTouchOutside(true);//失去焦点不会消失

        tvPwd = (EditText)localView.findViewById(R.id.tv_pwd);
        ivDele = (ImageView)localView. findViewById(R.id.iv_dele);
        btAuction = (Button)localView.findViewById(R.id.bt_auction);
        mNkvKeyboard = (XNumberKeyboardView)localView.findViewById(R.id.view_keyboard);
        mNkvKeyboard.setIOnKeyboardListener(this);

        btAuction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onbtAuctionClick != null) {
                    onbtAuctionClick.OnbtAuctionClick(tvPwd.getText().toString());
                }
            }
        });
        ivDele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 dismiss();
            }
        });
    }

    OnbtAuctionClick onbtAuctionClick;

    public void OnbtAuctionClick(OnbtAuctionClick onbtAuctionClick) {
        this.onbtAuctionClick = onbtAuctionClick;
    }

    @Override
    public void onInsertKeyEvent(String text) {
        if (str.length() < 7) {
            str += text;
            tvPwd.setText(str);
            tvPwd.setSelection(str.length());
        } else {
            ToastUtils.showLongToast("密码最多6位");
        }
    }

    @Override
    public void onDeleteKeyEvent() {
        if (str.length() <= 1) {
            str = "";
        } else {
            str = str.substring(0, str.length() - 1);
        }
        tvPwd.setText(str);
        tvPwd.setSelection(str.length());
    }

    public interface OnbtAuctionClick {
        void OnbtAuctionClick(String pwd);
    }

    public void setDialogAttributes(Activity context, final Dialog dialog,
                                    float widthP, float heightP, int gravity) {
        Display d = context.getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes();
        Point mPoint = new Point();
        d.getSize(mPoint);
        if (heightP != 0)
            p.height = (int) (mPoint.y * heightP);
        if (widthP != 0)
            p.width = (int) (mPoint.x * widthP);
        dialog.getWindow().setAttributes(p);
        dialog.getWindow().setGravity(gravity);
    }

    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);
    }
}
