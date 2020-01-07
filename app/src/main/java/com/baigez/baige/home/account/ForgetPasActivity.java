package com.baigez.baige.home.account;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baigez.baige.R;
import com.baigez.baige.common.BaigeActivity;
import com.baigez.baige.common.utils.ConstantUtil;
import com.baigez.baige.home.impl.LoginpageImpl;
import com.baigez.baige.home.view.LoginpageView;
import com.baigez.rootlibrary.activity.utils.MobileCheckUtil;
import com.baigez.rootlibrary.activity.utils.StringUtil;

/**
 * 忘记密码
 */
public class ForgetPasActivity extends BaigeActivity<LoginpageView, LoginpageImpl> implements LoginpageView, View.OnClickListener {

    private ImageView ivForgetClosebtn;
    private Button btPorgrtCodebtn;
    private EditText etForgeteditText;
    private TextView textView2;
    private EditText et_forgeteditTexta;


    boolean isPhone, isCode, isPsd;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_forget_pas;
    }

    @Override
    protected void createPresenter() {
        super.createPresenter();
        presenter =new LoginpageImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {

    }

    private void initView() {
        ivForgetClosebtn = activity.findViewById(R.id.iv_ForgetClose_btn);
        btPorgrtCodebtn = activity.findViewById(R.id.bt_PorgrtCode_btn);
        etForgeteditText = activity.findViewById(R.id.et_ForgeteditText);

        textView2 = activity.findViewById(R.id.textView2);
        et_forgeteditTexta = activity.findViewById(R.id.et_ForgeteditTexta);


        ivForgetClosebtn.setOnClickListener(this);
        btPorgrtCodebtn.setOnClickListener(this);
        textView2.setOnClickListener(this);
        //手机号
        btPorgrtCodebtn.setEnabled(false);
        etForgeteditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s = charSequence.toString();
                if (MobileCheckUtil.isChinaPhoneLegal(s)) {
                    isPhone = true;
                    if(isCode || isPsd){
                        btPorgrtCodebtn.setEnabled(true);
                    }else{
                        btPorgrtCodebtn.setEnabled(false);
                    }
                }else{
                    btPorgrtCodebtn.setEnabled(false);
                    isPhone = false;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

        et_forgeteditTexta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s = charSequence.toString();
                if (!StringUtil.isEmpty(s)&& s.length()>=4) {
                    isCode = true;
                    if (isPhone) {
                        btPorgrtCodebtn.setEnabled(true);
                    }else{
                        btPorgrtCodebtn.setEnabled(false);
                    }
                }else{
                    btPorgrtCodebtn.setEnabled(false);
                    isCode = false;
                }
            }
            @Override
            public void afterTextChanged(Editable editable) { }
        });


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_ForgetClose_btn:
                baseFinish();
                break;
            case R.id.bt_PorgrtCode_btn://go 验证码
//                activity.startActivity(new Intent(activity,VerificationCodeActivity.class)
//                        .putExtra(ConstantUtil.KEY_CODE,etForgeteditText.getText().toString())
//                    );
                activity.startActivity(new Intent(activity,SetNewsPasswordActivity.class));
                break;
            case R.id.textView2:
                if (MobileCheckUtil.isChinaPhoneLegal(etForgeteditText.getText().toString().trim())) {
                    resetTimer();
                }else{
                    toast("手机号不正确");
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    private void resetTimer() {
        new CountDownTimer(60000,1000) {
            @SuppressLint({"ResourceAsColor", "StringFormatMatches"})
            @Override
            public void onTick(long millisUntilFinished) {
                if(textView2==null){return;}
                textView2.setEnabled(false);
                textView2.setText(getString(R.string.witimeStr, millisUntilFinished / 1000));
                textView2.setTextColor(R.color.color8E2);
            }
            @SuppressLint("ResourceAsColor")
            @Override
            public void onFinish() {
                if(textView2==null){return;}
                textView2.setEnabled(true);
                textView2.setText("重新获取");
                textView2.setTextColor(R.color.color000);

            }
        }.start();


    }
}
