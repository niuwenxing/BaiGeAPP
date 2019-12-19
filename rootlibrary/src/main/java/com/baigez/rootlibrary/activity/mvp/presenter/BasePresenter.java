package com.baigez.rootlibrary.activity.mvp.presenter;

import android.content.Context;

import com.baigez.rootlibrary.activity.mvp.presenter.biz.IBaseBiz;
import com.baigez.rootlibrary.activity.mvp.view.IBaseView;

public abstract class BasePresenter<V extends IBaseView> implements IPresenter<V>  {

    public Context context;
    public V v;


    @Override
    public void attachView(V v, Context context) {
        this.v = v;
        this.context = context;
    }

    /**
     * 批量取消业务中的网络请求
     * @param bizs 业务bean集合
     */
    public void cancelRequest(IBaseBiz... bizs){
        if (bizs != null && bizs.length > 0) {
            for (IBaseBiz biz:bizs) {
                if (biz != null) {
                    biz.cancelRequest();
                }
            }
        }
    }

    @Override
    public void detachView() {
        cancelBiz();
    }

    /**
     * 取消代理层发起的业务，不提供实现，必须被实现
     */
    public abstract void cancelBiz();
}
