package com.smg.art.presenter.contract.activity;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.CardUrlBean;
import com.smg.art.bean.ComplaintBean;

import java.util.List;

import okhttp3.MultipartBody;

/**
 * Created by Mervin on 2018/8/9 0009.
 */

public interface ComplaintContract  {
    interface View extends BaseContract.BaseView {

        /**
         * 投诉
         */
        void FetchComplaintSuccess(ComplaintBean complaintBean);


        /**
         * 上传图片
         */
        void FetchUploadFileSuccess(CardUrlBean cardUrlBean);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 投诉
         */
        void FetchComplaint(String... s);


        void FetchUploadFile(List<MultipartBody.Part> parts);
    }
}
