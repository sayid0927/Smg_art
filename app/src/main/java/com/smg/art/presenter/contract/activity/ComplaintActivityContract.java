package com.smg.art.presenter.contract.activity;

import com.smg.art.base.BaseContract;
import com.smg.art.base.Constant;
import com.smg.art.bean.AuctionOrderBean;
import com.smg.art.bean.CardUrlBean;
import com.smg.art.bean.ComplaintBean;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Mervin on 2018/8/9 0009.
 */

public interface ComplaintActivityContract {
    interface View extends BaseContract.BaseView {

        /**
         * 投诉
         */
        void FetchComplaintSuccess(ComplaintBean complaintBean);






    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 投诉
         */
        void FetchComplaint(String... s);

    }
}
