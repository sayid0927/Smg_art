
package com.smg.art.presenter.contract.activity;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.ConversationBean;

import java.util.List;

import io.rong.imlib.model.Conversation;

public interface RecentMessageContract {

    interface View extends BaseContract.BaseView {

        /***
         *  获取最近会话列表成功
         * @param conversationBean
         */
        void getConversationListSuccess(List<Conversation> conversationBean);


    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /***
         *  获取最近会话列表
         */
        void getConversationList();
    }

}
