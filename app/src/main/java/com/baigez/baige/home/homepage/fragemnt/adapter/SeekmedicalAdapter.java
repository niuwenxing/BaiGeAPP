package com.baigez.baige.home.homepage.fragemnt.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.baigez.baige.R;

import java.util.List;

public class SeekmedicalAdapter extends BaseAdapter {

    private Context mContext;
    private List mList;

    public SeekmedicalAdapter(Context context, List list) {
        this.mContext=context;
        this.mList=list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view;
        if (convertView == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.consultation_item, viewGroup, false);
            view.findViewById(R.id.textView13).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnClick.onItemClick();
                }
            });
        }else{
            view=convertView;
        }
        return view;
    }


    public OnClick mOnClick;
    public void setmOnClick(OnClick mOnClick){ this.mOnClick=mOnClick; }
    public interface OnClick{ void onItemClick();}

}
