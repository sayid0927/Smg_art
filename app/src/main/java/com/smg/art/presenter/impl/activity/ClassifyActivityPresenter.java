
package com.smg.art.presenter.impl.activity;

import com.smg.art.api.Api;
import com.smg.art.base.BasePresenter;
import com.smg.art.presenter.contract.activity.ClassifyContract;

import javax.inject.Inject;


public class ClassifyActivityPresenter extends BasePresenter<ClassifyContract.View> implements ClassifyContract.Presenter<ClassifyContract.View> {

    private Api api;

    @Inject
    public ClassifyActivityPresenter(Api api) {
        this.api = api;


    }

}
