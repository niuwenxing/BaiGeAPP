package com.baigez.baige.common;

import android.annotation.SuppressLint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.baigez.baige.R;
import com.baigez.baige.common.bean.EventNoticeBean;
import com.baigez.baige.common.utils.EventBusUtil;
import com.baigez.baige.common.widget.DefaultPresenterImpl;
import com.baigez.rootlibrary.activity.mvp.activity.BaseActivity;
import com.baigez.rootlibrary.activity.mvp.presenter.BasePresenter;
import com.baigez.rootlibrary.activity.mvp.view.IBaseView;
import com.baigez.rootlibrary.activity.swipeback.SwipeBackLayout;
import com.baigez.rootlibrary.activity.swipeback.Utils;
import com.baigez.rootlibrary.activity.swipeback.app.SwipeBackActivityBase;
import com.baigez.rootlibrary.activity.swipeback.app.SwipeBackActivityHelper;
import com.baigez.rootlibrary.activity.utils.StringUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class  BaigeActivity <V extends IBaseView,P extends BasePresenter<V>> extends BaseActivity<V,P> implements SwipeBackActivityBase {


    private ConnectivityManager cm;
    /**
     * 上次点击退出计时
     */
    private long firstClickTime = 0L;
    private Unbinder bind;
    /**
     * 标记是否是首页
     */
    private boolean isHome = false;
    /**
     * 是否在前台显示
     */
    private boolean isFront = false;
    /**
     * Activity滑动控制
     */
    private SwipeBackActivityHelper mHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ButterKnife.bind(this);

        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();

        checkNetWork();


    }


    //在onCreate方法彻底执行完毕的回调
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
        if (isHome) {
            setSwipeBackEnable(false);
        }
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }

    //获取向后滑动布局
    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    //设置向后滑动
    @Override
    public void setSwipeBackEnable(boolean enable) {
            getSwipeBackLayout().setEnableGesture(enable);
    }

    //滑动完成
    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }

    @SuppressLint("MissingPermission")
    public void checkNetWork() {
        NetworkInfo netIntfo = null;
        try {
            cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            netIntfo = cm.getActiveNetworkInfo();
        } catch (Exception e) {
            //异常处理
            Toast.makeText(this, "没有网络权限，请给予相关权限", Toast.LENGTH_LONG).show();
        }

        if (netIntfo == null) {
            //如果没有网络 显示不正常
            toast("暂时没有网络，请是否连接检查网络");
        } else {
            //如果没有网络 显示不正常
//            text = act.getResources().getString(R.string.netWerk_0);
//            color = act.getResources().getColor(R.color.beige);

        }
    }

   /* @Override
    public Resources getResources() {
        //需要升级到 v1.1.2 及以上版本才能使用 AutoSizeCompat
        AutoSizeCompat.autoConvertDensityOfGlobal(super.getResources());//如果没有自定义需求用这个方法
        AutoSizeCompat.autoConvertDensity(super.getResources(), 667, false);//如果有自定义需求就用这个方法
        return super.getResources();
    }*/

    @Override
    protected void createPresenter() {
        presenter = (P) new DefaultPresenterImpl();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    /**
     * 设置当前是首页
     * @param home
     */
    public void setHome(boolean home) {
        isHome = home;
    }

    /**
     * 两次点击退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && isHome ) {
            if ((System.currentTimeMillis() - firstClickTime) > 2000) {
                firstClickTime = System.currentTimeMillis();
                toast(R.string.common_exit_warn);
                return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventNotify(EventNoticeBean notice) {
        if (notice != null) {
            switch (notice.getTypeId()) {
                case EventBusUtil.EVENT_TOKEN_INVALIDATE:              // 登录过时 - 重新登录
                    if (isFront) {
                        String message = StringUtil.isEmpty(notice.getMsg()) ?
                                getString(R.string.common_token_invalidate) : notice.getMsg();
                        toastLong(message);
                        new Handler(new Handler.Callback() {
                            @Override
                            public boolean handleMessage(Message msg) {
//                                startActivity(new Intent(activity, SigninActivity.class));
                                return true;
                            }
                        }).sendEmptyMessageDelayed(0x1001, 400);
                    }
                    break;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        isFront = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isFront = false;
    }


}
