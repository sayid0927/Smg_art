
package com.smg.art.presenter.contract.activity;

import android.widget.TextView;

import com.smg.art.base.BaseContract;
import com.smg.art.bean.Apk_UpdateBean;

public interface MainContract {

    interface View extends BaseContract.BaseView {

        void ApkUpdateS(Apk_UpdateBean.DataBean dataBean);
        TextView tv();

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void Apk_Update();
    }

}
