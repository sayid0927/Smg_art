
package com.smg.art.presenter.contract.fragment;



import com.smg.art.base.BaseContract;
import com.smg.art.base.Constant;
import com.smg.art.base.HomePageImgBean;
import com.smg.art.bean.AddFriendBean;
import com.smg.art.bean.AddressBookFriendsBean;
import com.smg.art.bean.Apk_UpdateBean;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface ContactsFragmentContract {

    interface View extends BaseContract.BaseView {
        /**
         * 查询通讯录好友列表
         */
        void  FetchAddressBookFriendsSuccess(AddressBookFriendsBean addressBookFriendsBean);
        /**
         * 删除通讯录好友
         */
        void  FetchUpdateFriendRelationSuccess(AddFriendBean addFriendBean);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 查询通讯录好友列表
         */
        void  FetchAddressBookFriends(String ...s);
        /**
         * 删除通讯录好友
         */
        void  FetchUpdateFriendRelation(String ...s);

    }
}
