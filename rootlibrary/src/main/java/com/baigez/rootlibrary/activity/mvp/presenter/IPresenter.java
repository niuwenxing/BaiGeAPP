package com.baigez.rootlibrary.activity.mvp.presenter;

import android.content.Context;

/**
 * Created by gray on 2019/10/15.
 */
public interface IPresenter<V> {

    void attachView(V v, Context context);
    void detachView();
}
