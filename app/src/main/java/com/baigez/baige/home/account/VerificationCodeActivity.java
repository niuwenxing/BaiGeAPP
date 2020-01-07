package com.baigez.baige.home.account;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baigez.baige.R;
import com.baigez.baige.common.BaigeActivity;
import com.baigez.baige.common.utils.ConstantUtil;
import com.baigez.baige.customveiw.PhoneCode;
import com.baigez.baige.home.impl.LoginpageImpl;
import com.baigez.baige.home.view.LoginpageView;
import com.baigez.rootlibrary.activity.utils.MobileCheckUtil;

public class VerificationCodeActivity extends BaigeActivity<LoginpageView, LoginpageImpl> implements LoginpageView, View.OnClickListener {

    private PhoneCode pcphoneCodestr ;
    private TextView tvShowphoneStr;
    private ImageView ivForgetClosebtn;
    private TextView tvHelpbtn;
    private String phone;
    private TextView tvWeitTimeStr;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_verification_code;
    }

    @Override
    protected void createPresenter() {
        super.createPresenter();
        presenter = new LoginpageImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();



    }

    private void initView() {
        phone = getIntent().getStringExtra(ConstantUtil.KEY_CODE);

        ivForgetClosebtn = activity.findViewById(R.id.iv_ForgetClose_btn);//返回
        ivForgetClosebtn.setOnClickListener(this);


        tvHelpbtn = activity.findViewById(R.id.tv_Helpbtn);//帮助
        tvHelpbtn.setOnClickListener(this);

        pcphoneCodestr = activity.findViewById(R.id.pc_phoneCode_str);//验证码
        pcphoneCodestr.setOnInputListener(new PhoneCode.OnInputListener() {
            @Override
            public void onSucess(String code) {
                //TODO: 例如底部【下一步】按钮可点击
                toast(code);
                activity.startActivity(new Intent(activity,SetNewsPasswordActivity.class));
            }

            @Override
            public void onInput() {
                //TODO:例如底部【下一步】按钮不可点击
            }
        });
        tvShowphoneStr = activity.findViewById(R.id.tv_showphoneStr);//验证码提示

        tvWeitTimeStr = activity.findViewById(R.id.tv_weitTimeStr);//等待时长
        tvWeitTimeStr.setOnClickListener(this);

        tvShowphoneStr.setText(getString(R.string.yanzhengma, MobileCheckUtil.settingphone(phone)));

    }

    private void initData() {
        resetTimer();
    }

    private void resetTimer() {
        new CountDownTimer(60000,1000) {
            @SuppressLint({"ResourceAsColor", "StringFormatMatches"})
            @Override
            public void onTick(long millisUntilFinished) {
                if(tvWeitTimeStr==null){return;}
                tvWeitTimeStr.setEnabled(false);
                tvWeitTimeStr.setText(getString(R.string.witimeStr, millisUntilFinished / 1000));
                tvWeitTimeStr.setTextColor(R.color.color000);
            }
            @SuppressLint("ResourceAsColor")
            @Override
            public void onFinish() {
                if(tvWeitTimeStr==null){return;}
                tvWeitTimeStr.setEnabled(true);
                tvWeitTimeStr.setText("重新获取");
                tvWeitTimeStr.setTextColor(R.color.color000);

            }
        }.start();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_ForgetClose_btn:
                finish();
                break;
            case R.id.tv_Helpbtn:
                toast("帮助");
                break;
            case R.id.tv_weitTimeStr:
                resetTimer();
                break;


            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }
}
