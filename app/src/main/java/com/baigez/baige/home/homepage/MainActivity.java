package com.baigez.baige.home.homepage;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.baigez.baige.R;
import com.baigez.baige.common.BaigeActivity;
import com.baigez.baige.home.homepage.fragemnt.OneselfFragment;
import com.baigez.baige.home.homepage.fragemnt.OpeningorderFragment;
import com.baigez.baige.home.homepage.fragemnt.WorkbenchFragment;

/**
 * 主页
 */
public class MainActivity extends BaigeActivity implements RadioGroup.OnCheckedChangeListener {

    private Fragment[] fragments1;
    private static RadioButton[] RadioButtons;
    private RadioGroup rgMain;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();



    }

    private void initView() {
        setHome(true);
        RadioButton rbWorkbenchbtn = activity.findViewById(R.id.rb_Workbench_btn);
        rbWorkbenchbtn.setButtonDrawable(new BitmapDrawable((Bitmap)null));
        RadioButton rbOpeningorderbtn = activity.findViewById(R.id.rb_Openingorder_btn);
        rbOpeningorderbtn.setButtonDrawable(new BitmapDrawable((Bitmap)null));
        RadioButton rboneselfbtn = activity.findViewById(R.id.rb_oneself_btn);
        rboneselfbtn.setButtonDrawable(new BitmapDrawable((Bitmap)null));

        rgMain=activity.findViewById(R.id.rg_main);
        rgMain.setOnCheckedChangeListener(this);
        fragments1 = new Fragment[]{
                new WorkbenchFragment(),
                new OpeningorderFragment(),
                new OneselfFragment()};
        RadioButtons = new RadioButton[fragments1.length];
        for (int i = 0; i < RadioButtons.length; i++) {
            RadioButtons[i] = (RadioButton) rgMain.getChildAt(i);
        }
        RadioButtons[0].setChecked(true);

    }

    private void initData() {


    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        for (int i = 0; i < RadioButtons.length; i++) {
            if (checkedId == RadioButtons[i].getId()) {
                switchFragment(fragments1[i]).commit();
            }
        }
    }

    private Fragment currentFragment = new Fragment();
    private FragmentTransaction switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            //第一次使用switchFragment()时currentFragment为null，所以要判断一下
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.vp_main, targetFragment, targetFragment.getClass().getName());
        } else {
            if (currentFragment != targetFragment) {
                transaction.hide(currentFragment).show(targetFragment);
            }
        }
        currentFragment = targetFragment;
        return transaction;
    }

}
