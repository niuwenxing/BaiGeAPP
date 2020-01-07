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

import com.baigez.baige.home.homepage.MainActivity;
import com.baigez.baige.R;
import com.baigez.baige.common.BaigeActivity;
import com.baigez.baige.home.impl.LoginpageImpl;
import com.baigez.baige.home.view.LoginpageView;

/**
 * 设置新的密码
 */

public class SetNewsPasswordActivity extends BaigeActivity<LoginpageView, LoginpageImpl> implements LoginpageView, View.OnClickListener {

    private TextView tvHelpbtn;
    private ImageView imgPassWordBtn,imgPassWordBtntow,ivForgetClosebtn;
    private Button btSetPswdbtn;
    private EditText etPasswordstrOne,etPasswordstrTow;

    private Boolean password_oStr=false,password_tStr=false;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_set_news_password;
    }

    @Override
    protected void createPresenter() {
        super.createPresenter();
        presenter=new LoginpageImpl();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();


    }

    private void initView() {

        etPasswordstrOne = activity.findViewById(R.id.et_PasswordstrOne);//psdstr 1
        etPasswordstrTow = activity.findViewById(R.id.et_PasswordstrTow);//psdstr 2

        ivForgetClosebtn = activity.findViewById(R.id.iv_ForgetClose_btn);//返回
        ivForgetClosebtn.setOnClickListener(this);

        tvHelpbtn = activity.findViewById(R.id.tv_Helpbtn);//帮助
        tvHelpbtn.setOnClickListener(this);


        imgPassWordBtn = activity.findViewById(R.id.img_passWordBtn);//psd1 btn
        imgPassWordBtn.setOnClickListener(this);

        imgPassWordBtntow = activity.findViewById(R.id.img_passWordBtntow);//psd2 btn
        imgPassWordBtntow.setOnClickListener(this);

        btSetPswdbtn = activity.findViewById(R.id.bt_setPswdbtn);// btn
        btSetPswdbtn.setOnClickListener(this);

        imgPassWordBtn.setSelected(false);
        etPasswordstrOne.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        imgPassWordBtntow.setSelected(false);
        etPasswordstrTow.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        btSetPswdbtn.setEnabled(false);
        etPasswordstrOne.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s = charSequence.toString().trim();
                if (s.length()>=6) {
                    password_oStr=true;
                    if (password_oStr && password_tStr) {
                        btSetPswdbtn.setEnabled(true);
                    }else{
                        btSetPswdbtn.setEnabled(false);
                    }
                }else{
                    password_oStr=false;
                    btSetPswdbtn.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });
                etPasswordstrTow.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        String s = charSequence.toString().trim();
                        if (s.length()>=6) {
                            password_tStr=true;
                            if (password_oStr && password_tStr) {
                                btSetPswdbtn.setEnabled(true);
                            }else{
                                btSetPswdbtn.setEnabled(false);
                            }
                        }else{
                            password_tStr=false;
                            btSetPswdbtn.setEnabled(false);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) { }
                });


    }

    private void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_ForgetClose_btn:
                finish();
                break;
            case R.id.tv_Helpbtn:

                break;
            case R.id.bt_setPswdbtn:
                toast("修改完成");
                if (etPasswordstrOne.getText().toString().trim().equals(etPasswordstrTow.getText().toString().trim())) {
                    activity.startActivity(new Intent(activity, MainActivity.class));
                    finish();
                }else{
                    toast("两次密码不同");
                }
                break;
            case R.id.img_passWordBtn://密码1
                imgPassWordBtn.setSelected(!imgPassWordBtn.isSelected());
                etPasswordstrOne.setInputType(imgPassWordBtn.isSelected() ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                etPasswordstrOne.setSelection(etPasswordstrOne.getText().toString().length());
                break;
            case R.id.img_passWordBtntow:
                imgPassWordBtntow.setSelected(!imgPassWordBtntow.isSelected());
                etPasswordstrTow.setInputType(imgPassWordBtntow.isSelected() ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                etPasswordstrTow.setSelection(etPasswordstrTow.getText().toString().length());
                break;

        }
    }


}
