package com.baigez.rootlibrary.activity.mvp.fragment;

import android.os.Bundle;

import com.baigez.rootlibrary.activity.fragment.RootFragment;
import com.baigez.rootlibrary.activity.mvp.presenter.BasePresenter;
import com.baigez.rootlibrary.activity.mvp.view.IBaseView;

public abstract class BaseFragment<V extends IBaseView,P extends BasePresenter> extends RootFragment implements IBaseView {

    protected P presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter();
        presenter.attachView((V) this, activity);
    }

    protected abstract void createPresenter();

    protected void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    protected P getPresenter() {
        return presenter;
    }

    @Override
    public void onDestroyView() {

        presenter.detachView();
        super.onDestroyView();
    }


}
