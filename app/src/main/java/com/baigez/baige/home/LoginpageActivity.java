package com.baigez.baige.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.baigez.baige.R;
import com.baigez.baige.common.BaigeActivity;
import com.baigez.baige.home.impl.LoginpageImpl;
import com.baigez.baige.home.view.LoginpageView;

/**
 * 登录页面
 */

public class LoginpageActivity extends BaigeActivity<LoginpageView, LoginpageImpl> implements LoginpageView {

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_loginpage;
    }

    @Override
    protected void createPresenter() {
        super.createPresenter();
        presenter = new LoginpageImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}
