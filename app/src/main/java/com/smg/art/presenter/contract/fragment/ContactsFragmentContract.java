
package com.smg.art.presenter.contract.fragment;



import com.smg.art.base.BaseContract;
import com.smg.art.bean.AddFriendBean;
import com.smg.art.bean.AddressBookFriendsBean;

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
