package com.smg.art.presenter.contract.activity;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.ChangeNickBean;

/**
 * Created by Mervin on 2018/8/3 0003.
 */

public interface ChangeNickContract {
    interface View extends BaseContract.BaseView {
        /**
         * 修改昵称
         *
         * @param changeNickBean
         */

        void FetchChangeNickSuccess(ChangeNickBean changeNickBean);


    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        void FetchChangeNick(String... s);
    }
}
