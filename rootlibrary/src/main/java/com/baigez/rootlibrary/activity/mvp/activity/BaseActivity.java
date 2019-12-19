package com.baigez.rootlibrary.activity.mvp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import androidx.annotation.Nullable;

import com.baigez.rootlibrary.activity.RootActivity;
import com.baigez.rootlibrary.activity.mvp.presenter.BasePresenter;
import com.baigez.rootlibrary.activity.mvp.view.IBaseView;

public abstract class BaseActivity<V extends IBaseView,P extends BasePresenter<V>> extends RootActivity {

    protected  P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
    protected void onDestroy() {
        if (presenter!=null) {
            presenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * 跳转到权限设置界面
     *
     * @return
     */
    public Intent getAppDetailSettingIntent() {
        //启动应用详情页
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        return intent;
    }
}
