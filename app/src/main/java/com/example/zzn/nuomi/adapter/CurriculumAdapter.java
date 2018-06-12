package com.example.zzn.nuomi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zzn.nuomi.R;
import com.example.zzn.nuomi.model.AllResult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ZZN on 2017/8/10.
 */

public class CurriculumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<AllResult.Results> picData = new ArrayList<>();
    private List<AllResult.Results> textData = new ArrayList<>();
    //type
    public static final int TYPE_TYPE = 0xff01;

    public CurriculumAdapter(Context context) {
        this.context = context;
    }

    public List<AllResult.Results> getPicData() {
        return picData;
    }

    public void setPicData(List<AllResult.Results> picData) {
        this.picData = picData;
    }

    public List<AllResult.Results> getTextData() {
        return textData;
    }

    public void setTextData(List<AllResult.Results> textData) {
        this.textData = textData;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("test",getWeekDay(0));
        if (getWeekDay(0).equals("周一")){
            return new HolderType(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_curriculum6, parent, false));
        }else if (getWeekDay(0).equals("周二")){
            return new HolderType(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_curriculum5, parent, false));
        }else  if (getWeekDay(0).equals("周三")){
            return new HolderType(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_curriculum4, parent, false));
        }else if (getWeekDay(0).equals("周四")){
            return new HolderType(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_curriculum3, parent, false));
        }else if (getWeekDay(0).equals("周五")){
            return new HolderType(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_curriculum, parent, false));
        }else {
            return new HolderType(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_curriculum2, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            bindType((HolderType) holder, position);
    }

    @Override
    public int getItemCount() {
        if (picData != null)
            return picData.size();
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
            return TYPE_TYPE;
    }

    /////////////////////////////
    private void bindType(HolderType holder, int position) {
//        holder.setDataAndRefreshUI( textData.get(position));
    }
    /////////////////////////////
    public class HolderType extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public HolderType(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.course_name);
        }

        public void setDataAndRefreshUI( AllResult.Results textdata) {
            mTextView.setText(textdata.getDesc());
        }
    }
    public static String getWeekDay(int off) {
        String result = new String("");
        final Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(System.currentTimeMillis());
        int day = instance.get(Calendar.DAY_OF_WEEK);
        Log.e("test", String.valueOf(day));
        int offDay = day + off;
        switch (offDay % 7) {
            case 0:
                result = "周六";
                break;
            case 1:
                result = "周日";
                break;
            case 2:
                result = "周一";
                break;
            case 3:
                result = "周二";
                break;
            case 4:
                result = "周三";
                break;
            case 5:
                result = "周四";
                break;
            case 6:
                result = "周五";
                break;
        }
        return result;
    }
}


