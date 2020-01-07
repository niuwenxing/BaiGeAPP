package com.baigez.baige.home.account;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baigez.baige.R;
import com.baigez.baige.common.BaigeActivity;
import com.baigez.baige.home.homepage.MainActivity;
import com.baigez.baige.home.impl.LoginpageImpl;
import com.baigez.baige.home.view.LoginpageView;
import com.baigez.rootlibrary.activity.utils.MobileCheckUtil;


/**
 * 登录页面
 */

public class LoginpageActivity extends BaigeActivity<LoginpageView, LoginpageImpl> implements LoginpageView, View.OnClickListener {
    private  EditText etPasswordstr,et_editText;
    private ImageView imgPassWord;
    boolean isPhone, isCode, isPsd;
    private Button btLoginbtn;
    private TextView tvForgetPasbtn;

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
        initView();
        initData();

    }

    private void initData() {
        imgPassWord.setSelected(false);
        etPasswordstr.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        btLoginbtn.setEnabled(false);
    }

    private void initView() {

         imgPassWord = activity.findViewById(R.id.img_passWordBtn);
         etPasswordstr = activity.findViewById(R.id.et_Passwordstr);
        tvForgetPasbtn = activity.findViewById(R.id.tv_ForgetPas_btn);
         //登录按钮
         btLoginbtn = activity.findViewById(R.id.bt_login_btn);

         imgPassWord.setOnClickListener(this);
         btLoginbtn.setOnClickListener(this);
        tvForgetPasbtn.setOnClickListener(this);



         //账号输入框
        et_editText = activity.findViewById(R.id.editText);
        et_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s = charSequence.toString();
                if (MobileCheckUtil.isChinaPhoneLegal(s)) {
//                if (s.length() == 11) {
                    isPhone = true;
                    if (isCode || isPsd) {
                        btLoginbtn.setEnabled(true);
                    } else {
                        btLoginbtn.setEnabled(false);
                    }
                } else {
                    btLoginbtn.setEnabled(false);
                    isPhone = false;
                }
            }
            @Override
            public void afterTextChanged(Editable editable) { }
        });

        //密码输入框
        etPasswordstr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s = charSequence.toString();
                if (s.length() >= 6) {
                    isCode = true;
                    if (isPhone) {
                        btLoginbtn.setEnabled(true);
                    } else {
                        btLoginbtn.setEnabled(false);
                    }
                } else {
                    btLoginbtn.setEnabled(false);
                    isCode = false;
                }
            }
            @Override
            public void afterTextChanged(Editable editable) { }
        });


    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_passWordBtn://显示隐藏/
                imgPassWord.setSelected(!imgPassWord.isSelected());
                etPasswordstr.setInputType(imgPassWord.isSelected() ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                etPasswordstr.setSelection(etPasswordstr.getText().toString().length());
                break;
            case R.id.bt_login_btn://登录
                String Account = et_editText.getText().toString();//账号
                String etpasswordstr = etPasswordstr.getText().toString();//密码
                activity.startActivity(new Intent(activity, MainActivity.class));
                finish();
                break;

            case R.id.tv_ForgetPas_btn://忘记密码
                activity.startActivity(new Intent(activity,ForgetPasActivity.class));
                break;
            default:
                throw new IllegalStateException(" Unexpected value: " + view.getId());
        }
    }
}
