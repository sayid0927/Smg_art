
package com.smg.art.presenter.contract.activity;

import com.smg.art.base.BaseContract;
import com.smg.art.base.Constant;
import com.smg.art.bean.AddFriendBean;
import com.smg.art.bean.SearchMemberBean;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface SearchContactsContract {

    interface View extends BaseContract.BaseView {
        /**
         * 搜索平台会员
         */
        void FetchSearchMemberSuccess(SearchMemberBean searchMemberBean);
        /**
         * 新增通讯录好友
         */
        void FetchAddFriendSuccess(AddFriendBean addFriendBean);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 搜索平台会员
         */
        void FetchSearchMember(String ...s);

        /**
         * 新增通讯录好友
         */
        void FetchAddFriend(String ...s);

    }
}
