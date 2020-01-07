package com.baigez.baige.home.homepage.fragemnt;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.baigez.baige.R;
import com.baigez.baige.common.BaigeFragment;
import com.baigez.baige.home.homepage.activity.PatientActivity;
import com.baigez.baige.home.homepage.fragemnt.adapter.SeekmedicalAdapter;
import com.baigez.baige.utils.NiceImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: BaiGe
 * @Package: com.baigez.baige.home.homepage.fragemnt
 * @ClassName: WorkbenchFragment
 * @Description: 工作台
 * @Author: 牛文星
 * @CreateDate: 2019/12/22 21:04
 * @UpdateUser: 牛文星
 * @UpdateDate: 2019/12/22 21:04
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

public class WorkbenchFragment extends BaigeFragment {

    private View layoutFragment;

    @Override
    protected int attachLayoutRes() {
        return R.layout.workbench_layout;
    }

    @Override
    protected void init() {
        layoutFragment = getLayoutFragment();
        initView();
    }

    private void initView() {
        NiceImageView niceImageView = layoutFragment.findViewById(R.id.niceImageView);

        TextView textView =layoutFragment.findViewById(R.id.textView);

        TextView textView8 =layoutFragment.findViewById(R.id.textView8);

        TextView textView9 =layoutFragment.findViewById(R.id.textView9);

        TextView doctor_brief =layoutFragment.findViewById(R.id.doctor_brief);

        ListView seekmedical_list =layoutFragment.findViewById(R.id.seekmedical_list);

        List<String> list=new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("电竞程冠希");
        }
        SeekmedicalAdapter seekmedicalAdapter = new SeekmedicalAdapter(getContext(), list);
        seekmedical_list.setAdapter(seekmedicalAdapter);
        seekmedicalAdapter.setmOnClick(new SeekmedicalAdapter.OnClick() {
            @Override
            public void onItemClick() {
                getContext().startActivity(new Intent(getContext(), PatientActivity.class));
            }
        });
    }
}
