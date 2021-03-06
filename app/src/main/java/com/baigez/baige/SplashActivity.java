package com.baigez.baige;

import androidx.annotation.Nullable;
import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.baigez.baige.common.BaigeActivity;
import com.baigez.baige.home.homepage.MainActivity;
import com.baigez.rootlibrary.activity.permission.RequestPermissionListener;

public class SplashActivity extends BaigeActivity {

    private boolean isDirectEnter = false;
    private final int SETTING_CODE = 0x1211;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusNavigationBar();
        initAuthority();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode ==SETTING_CODE){
            initAuthority();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initAuthority() {
        requestPermission(0x1208, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION
        }, new RequestPermissionListener() {
            @Override
            public void onPass(String[] strings) {

//                for (int i = 0; i < strings.length; i++) {
//                    if (strings[i].equals(Manifest.permission.READ_PHONE_STATE)) {
//                        // 初始化请求参数
//                    }
//                }
            }

            @Override
            public void onUnPass(String[] uPas) {
                if (null == uPas || uPas.length == 0) {    // 证明没有没通过的权限
                    // 设置延迟跳转
                    int delay = isDirectEnter ? 100 : 2000;
                    new Handler(new Handler.Callback() {
                        @Override
                        public boolean handleMessage(Message msg) {
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();
                            return true;
                        }
                    }).sendEmptyMessageDelayed(0, delay);
                } else {               // 有没有申请的权限
//                    dialogShowRemind(0, getString(R.string.common_prompt), getString(R.string.common_authority_warn),
//                            getString(R.string.common_open), getString(R.string.common_cancel), new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
                    startActivityForResult(getAppDetailSettingIntent(), SETTING_CODE); //直接进入手机中设置界面
//                                }
//                            }, new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                    finish();
//                                }
//                            });
                }
            }
        });
    }


    private void hideStatusNavigationBar(){
        Window window = getWindow();
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.KITKAT){
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN //hide statusBar
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION); //hide navigationBar
        }else if(Build.VERSION.SDK_INT<Build.VERSION_CODES.JELLY_BEAN){
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else if(Build.VERSION.SDK_INT<Build.VERSION_CODES.LOLLIPOP){
            //半透明
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(0);
        }
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE|
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
    }

}
