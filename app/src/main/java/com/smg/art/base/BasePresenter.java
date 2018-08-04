package com.smg.art.base;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.smg.art.R;
import com.smg.art.view.CustomDialog;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


public class BasePresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {

    protected T mView;
    protected CompositeSubscription mCompositeSubscription;
    private CustomDialog mDialogWaiting;
    private  Context context;

    /**
     * 显示等待提示框
     */
    public Dialog showWaitingDialog(String tip) {
        hideWaitingDialog();
        if(this.context !=null) {
            View view = View.inflate(this.context, R.layout.dialog_waiting, null);
            if (!TextUtils.isEmpty(tip))
                ((TextView) view.findViewById(R.id.tvTip)).setText(tip);
            mDialogWaiting = new CustomDialog(this.context, view, R.style.MyDialog);
            mDialogWaiting.show();
            mDialogWaiting.setCancelable(false);
            return mDialogWaiting;
        }
        return  null;
    }

    /**
     * 隐藏等待提示框
     */
    public void hideWaitingDialog() {
        if (mDialogWaiting != null) {
            mDialogWaiting.dismiss();
            mDialogWaiting = null;
        }
    }

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    protected void addSubscrebe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void  attachView(T view , Context context) {
        this.mView = view;
        this.context = context;
    }

    @Override
    public void detachView() {
        this.mView = null;
        this.context = null;
        unSubscribe();
    }
}
