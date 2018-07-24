
package com.smg.art.presenter.impl.fragment;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.presenter.contract.fragment.ClassifyChildContract;

import javax.inject.Inject;


public class ClassifyChildPresenter extends BasePresenter<ClassifyChildContract.View> implements ClassifyChildContract.Presenter<ClassifyChildContract.View> {

    private Api api;

    @Inject
    public ClassifyChildPresenter(Api api) {
        this.api = api;

    }
}
