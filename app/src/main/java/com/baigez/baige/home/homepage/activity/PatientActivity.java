package com.baigez.baige.home.homepage.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.baigez.baige.R;
import com.baigez.baige.common.BaigeActivity;

import java.util.ArrayList;
import java.util.List;

public class PatientActivity extends BaigeActivity {

    private TextView gofinsh;
    private ListView patients;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_patient;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initClick();


    }



    private void initView() {
        gofinsh = (TextView) findViewById(R.id.gofinsh);
        patients = (ListView) findViewById(R.id.patients_list);
        List sa = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            sa.add("");
        }
        PatientsAdapter patientsAdapter = new PatientsAdapter(sa,this);
        patients.setAdapter(patientsAdapter);

    }

    private void initClick() {
        gofinsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


//        patients.set

    }


    public class PatientsAdapter extends BaseAdapter {

        public List list;
        public Context context;

        public PatientsAdapter(List list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            View view;
            if (convertView == null){
                view = LayoutInflater.from(context).inflate(R.layout.patients_listitem, viewGroup, false);

            }else{
                view=convertView;
            }
            return view;
        }
    }
}
