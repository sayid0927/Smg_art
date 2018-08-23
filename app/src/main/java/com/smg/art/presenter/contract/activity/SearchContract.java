
package com.smg.art.presenter.contract.activity;

import com.smg.art.base.AnnouncementAuctionListBean;
import com.smg.art.base.BaseContract;
import com.smg.art.bean.CreatWordsBean;
import com.smg.art.bean.HotWordsListBean;
import com.smg.art.bean.LoginBean;

import rx.Observable;

public interface SearchContract {

    interface View extends BaseContract.BaseView {

        void  FetchHotWordsListSuccess(HotWordsListBean hotWordsListBean);

        /**
         * 首页搜索框查询
         */
        void  FetchAuctionListByNameSuccess(AnnouncementAuctionListBean announcementAuctionListBean);

        /**
         * 新增热门搜索字段
         */
        void  FetchCreatWordsBeanSuccess(CreatWordsBean creatWordsBean);
        /**
         * 搜索字段删除接口
         */
        void  FetchDeleteWordByIdSuccess(CreatWordsBean creatWordsBean);


    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        void  FetchHotWordsList();
        /**
         * 首页搜索框查询
         */
        void  FetchAuctionListByName(String ...s);
        /**
         * 新增热门搜索字段
         */
        void  FetchCreatWordsBean(String ...s);
        /**
         * 搜索字段删除接口
         */
        void  FetchDeleteWordById(String ...s);
    }
}
