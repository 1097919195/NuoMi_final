package com.example.zzn.nuomi;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.zzn.nuomi.common.http.NetWork;
import com.example.zzn.nuomi.model.MyResult;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {
    private EditText username,password;
    private RelativeLayout login;

    String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.CAMERA};
    String permission = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static int permissionCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        //请求多个权限
//        PermissionUtils.checkAndRequestMorePermissions(this,permissions,permissionCode);

        initView();
        init();
    }
    private void initView() {
        username= (EditText) findViewById(R.id.edt_login_user);
        password= (EditText) findViewById(R.id.edt_login_password);
        login= (RelativeLayout) findViewById(R.id.txt_login_btnlogin);
    }

    private void init() {
        Hawk.init(this)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setStorage(HawkBuilder.newSharedPrefStorage(this))
                .setLogLevel(LogLevel.FULL)
                .build();
        Log.e("isLogin", String.valueOf(Hawk.contains("isLogin")));
        if (Hawk.contains("isLogin")){
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().length()==0){
                    Toast.makeText(LoginActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().length()==0){
                    Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                checkAppLogin();
            }
        });
    }

    public  void checkAppLogin() {
        Call<MyResult> call;
        call = (Call<MyResult>) NetWork.getMyApi().getUserinfo(username.getText().toString(),password.getText().toString());
        call.enqueue(new Callback<MyResult>() {
            @Override
            public void onResponse(Call<MyResult> call, retrofit2.Response<MyResult> response) {
                    Log.e("success", response.body().getResults().get(0).getUserid()+response.body().getResults().get(0).getUsername()
                            +response.body().getResults().get(0).getPhone());
                        Hawk.put("userid",response.body().getResults().get(0).getUserid());
                        Hawk.put("username",response.body().getResults().get(0).getUsername());
                        Hawk.put("userphone",response.body().getResults().get(0).getPhone());
                        Hawk.put("userhead",response.body().getResults().get(0).getHead());
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        Hawk.put("isLogin",true);
                        finish();
            }
            @Override
            public void onFailure(Call<MyResult> call, Throwable t) {
                System.out.println("请求失败");
                Toast.makeText(LoginActivity.this,"用户名密码错误",Toast.LENGTH_LONG).show();
                System.out.print(t.getMessage());
            }
        });}
}
