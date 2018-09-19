package com.m.liu.studyapp.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Looper;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.m.liu.studyapp.CommonActivity;
import com.m.liu.studyapp.Jgson;
import com.m.liu.studyapp.R;
import com.m.liu.studyapp.home.HomeActivity;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends CommonActivity {
    private static final String TAG ="LoginActivity";
    public String userName;
    public String password;
    private long lastClickTime = 0;

    private  String url = "http://219.159.44.166:39003/hsApp/app/login.do?operate=login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        //给登陆文字设置字体
        TextView tt = (TextView) findViewById(R.id.login_tt);
        AssetManager mgr=getAssets();//得到AssetManager
        Typeface tf=Typeface.createFromAsset(mgr, "fonts/hwhp.ttf");//根据路径得到Typeface
        tt.setTypeface(tf);//设置字体

        // 获取sharedpreferences对象
        setTitle("Login");
        SharedPreferences share = getSharedPreferences("Login",
                Context.MODE_PRIVATE);
//        userName = share.getString("userName", "");
//        password = share.getString("password", "");

        // 判断是否是之前有登录过
        if (share == null) {
            init();
        } else {
            // 判断是否刚注销
            if (share.getBoolean("LoginBool", false)) {
                // 跳转到注销页面并销毁当前activity
                Intent intent = new Intent(LoginActivity.this,
                        HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                init();
            }
        }
    }


    private void init(){
        Button login_button = (Button) findViewById(R.id.login_button);
        //登陆按钮监听
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etn=(EditText)findViewById(R.id.nametext);
                EditText etp=(EditText)findViewById(R.id.passwtext);
                userName=etn.getText().toString().trim();
                password=etp.getText().toString().trim();
                login();
            }
        });

    }

    //本地对手机号做的限制
    public boolean login_valid(){
        if (userName.equals("")){
            Toast.makeText(LoginActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if (password.equals("")){
            Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if (userName.length()<11){
            Toast.makeText(this,"请输入正确的手机号码",Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    private void login() {
        if (login_valid()) {
            post(url);
        }
    }


    //登陆请求接口
    public void post(String url){
        OkHttpClient client = new OkHttpClient();
        client.newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS).build();
        FormBody.Builder bodyBuilder = new FormBody.Builder()
                .add("userName",userName)
                .add("password",password);

        RequestBody postBody = bodyBuilder.build();

        Request postRequest = new Request.Builder()
                .url(url)
                .post(postBody)
                .build();

        Call call = client.newCall(postRequest);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(e instanceof SocketTimeoutException){

                }
                if (e instanceof ConnectException){

                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String result = response.body().string();
                    response.body().close();
                    Log.d(TAG,result);
                    try{
                        Gson gson = new Gson();
                        java.lang.reflect.Type type = new TypeToken<Jgson>() {}.getType();
                        Jgson json = gson.fromJson(result, type);
                        if (json.success.toString().equals("false")){
                            //对于当前线程的toast不加looper会崩溃
                            Looper.prepare();
                            Toast.makeText(LoginActivity.this,json.message.toString(),Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }else if (json.success.toString().equals("true")){



                            // 创建SharedPreferences对象用于储存帐号和密码,并将其私有化
                            SharedPreferences share = getSharedPreferences("Login",
                                    Context.MODE_PRIVATE);
                            // 获取编辑器来存储数据到sharedpreferences中
                            Editor editor = share.edit();
                            editor.putString("userName", userName);
                            editor.putString("password", password);
                            editor.putString("data", json.data.nickName.toString());
                            editor.putBoolean("LoginBool", true);
                            // 将数据提交到sharedpreferences中
                            editor.commit();

                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                            intent.putExtra("data", json.data.nickName.toString());
//                            intent.putExtra("userName",userName);
                            startActivity(intent);
                            finish();
                        }
                    }catch (JsonSyntaxException e){

                    }
                }
            }
        });
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (isFastDoubleClick()) {
                return true;
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    public boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        lastClickTime = time;
        return timeD <= 300;
    }



}





