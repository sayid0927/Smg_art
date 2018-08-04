package com.smg.art.presenter.contract.activity;

import com.smg.art.base.BaseContract;
import com.smg.art.base.CardUrlBean;
import com.smg.art.bean.CreatWordsBean;
import com.smg.art.bean.UpLoadBean;

import java.util.List;

import okhttp3.MultipartBody;
import rx.Observable;

/**
 * Created by Mervin on 2018/8/1 0001.
 */

public interface AuthenticationContract {
    interface View extends BaseContract.BaseView {
        /**
         * 上传图片
         */
        void FetchUploadFileSuccess(CardUrlBean cardUrlBean);
        /**
         * 新增实名认证
         */
        void FetchMemberAuthSaveSuccess(CreatWordsBean cardUrlBean);



    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void FetchUploadFile(List<MultipartBody.Part> parts);
        void FetchMemberAuthSave(String ...s);

    }

}
