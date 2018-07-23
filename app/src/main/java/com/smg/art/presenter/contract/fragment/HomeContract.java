
package com.smg.art.presenter.contract.fragment;



import com.smg.art.base.BaseContract;
import com.smg.art.bean.Apk_UpdateBean;

public interface HomeContract {

    interface View extends BaseContract.BaseView {

        void ApkUpdateS(Apk_UpdateBean.DataBean dataBean);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

    }
}
