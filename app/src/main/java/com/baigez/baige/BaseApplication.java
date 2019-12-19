package com.baigez.baige;

import android.app.Activity;
import android.app.Application;

import com.baigez.rootlibrary.activity.utils.LogUtils;

import java.util.Locale;

import butterknife.ButterKnife;

/**
 * Created by guolei on 2019/10/15.
 */

public class BaseApplication extends Application {
    /**
     * 是否开启调试模式
     */
    public static final boolean DEVELOP_DEBUG_MODE = true;

    private static BaseApplication instance;
    @Override
    public void onCreate() {
        instance=this;
        super.onCreate();



        initDebugModel();

    }
    /**
     * 初始化各个组件的调试模式
     */
    private void initDebugModel() {
        // 组件注入模块调试
        ButterKnife.setDebug(DEVELOP_DEBUG_MODE);
        //
    }

    public static synchronized BaseApplication getInstance() {
        return instance;
    }
}
