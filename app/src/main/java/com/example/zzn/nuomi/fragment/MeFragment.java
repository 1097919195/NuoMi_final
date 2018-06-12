package com.example.zzn.nuomi.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zzn.nuomi.LoginActivity;
import com.example.zzn.nuomi.R;
import com.example.zzn.nuomi.activity.AboutUsActivity;
import com.example.zzn.nuomi.activity.AccountInfomationActivity;
import com.example.zzn.nuomi.activity.AccountSettingsActivity;
import com.example.zzn.nuomi.activity.MessagePromptActivity;
import com.example.zzn.nuomi.common.CacheUtil;
import com.example.zzn.nuomi.common.base.BaseFragment;
import com.example.zzn.nuomi.common.update.UpDate;
import com.example.zzn.nuomi.common.utils.CircleImageView;
import com.orhanobut.hawk.Hawk;

/**
 * Created by ZZN on 2017/8/7.
 */

public class MeFragment extends BaseFragment {
    private CardView Account_Information,Account_Settings,Message_Prompt,About_us,version_update,
                        clear_cache,back_login;
    private CircleImageView headimg;
    private TextView nickname;
    private Bitmap bitmap;
    private TextView cachesize,versionname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_me,null);
        initView(v);
        initDate();
        init();
        return v;
    }

    private void initDate() {
        try {
            cachesize.setText(CacheUtil.getTotalCacheSize(getActivity()));
            versionname.setText(getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(),0).versionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView(View v) {
        Account_Information=v.findViewById(R.id.Account_Information);
        Account_Settings=v.findViewById(R.id.Account_Settings);
        About_us=v.findViewById(R.id.About_us);
        Message_Prompt=v.findViewById(R.id.Message_Prompt);
        version_update=v.findViewById(R.id.version_update);
        clear_cache=v.findViewById(R.id.clear_cache);
        back_login=v.findViewById(R.id.back_login);
        headimg=v.findViewById(R.id.head_img);
        nickname=v.findViewById(R.id.nickname);
        versionname=v.findViewById(R.id.versionName);
        cachesize=v.findViewById(R.id.cachesize);

        Account_Information.setOnClickListener(listener);
        Account_Settings.setOnClickListener(listener);
        About_us.setOnClickListener(listener);
        Message_Prompt.setOnClickListener(listener);
        version_update.setOnClickListener(listener);
        clear_cache.setOnClickListener(listener);
        back_login.setOnClickListener(listener);
    }

    private void init() {
//        if (Hawk.contains("headImg")){
//            headimg.setImageBitmap(Hawk.get("headImg",bitmap));
//        }
        nickname.setText(Hawk.get("username").toString());
        if (Hawk.contains("nickname")){
            nickname.setText(Hawk.get("nickname").toString());
        }
    }

    private View.OnClickListener listener=new View.OnClickListener() {
        private Intent intent;
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.Account_Information:
                    intent=new Intent(getActivity(), AccountInfomationActivity.class);
                    startActivity(intent);
                    break;
                case R.id.Account_Settings:
                    intent=new Intent(getActivity(), AccountSettingsActivity.class);
                    startActivity(intent);
                    break;
                case R.id.About_us:
                    intent=new Intent(getActivity(), AboutUsActivity.class);
                    startActivity(intent);
                    break;
                case R.id.Message_Prompt:
                    intent=new Intent(getActivity(), MessagePromptActivity.class);
                    startActivity(intent);
                    break;
                case R.id.version_update:
                    new UpDate().upDate(getActivity());
                    break;
                case R.id.clear_cache:
                    CacheUtil.clearAllCache(getActivity());
                    try {
                    cachesize.setText(CacheUtil.getTotalCacheSize(getActivity()));
                    } catch (Exception e) {
                    e.printStackTrace();
                    }
                    Toast.makeText(getActivity(),"缓存已清除",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.back_login:
                    Intent i=new Intent(getActivity(), LoginActivity.class);
                    startActivity(i);
                    getActivity().finish();
                    Hawk.clear();
                    break;
            }
        }
    };
}
