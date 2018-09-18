package com.m.liu.studyapp;

import android.util.Log;

import java.io.IOException;
import com.m.liu.studyapp.login.LoginActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by luculent on 2018/9/3.
 */

public class HttpPath {
    OkHttpClient client = new OkHttpClient();
    private static final String TAG = "LoginActivity";

    //post方式
    public void post(String url) throws IOException {
        FormBody.Builder bodyBuilder = new FormBody.Builder()
                .add("userName","userName")
                .add("password","password");

        RequestBody postBody = bodyBuilder.build();

        Request postRequest = new Request.Builder()
                .url(url)
                .post(postBody)
                .build();

        Call call = client.newCall(postRequest);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String result = response.body().string();
                    Log.d(TAG,result);
                }
            }
        });
    }

    //get方式
    public void get(String url){
        Request.Builder getbodybuilder = new Request.Builder();
        Request getRequest = getbodybuilder
                .get()
                .url(url)
                .build();
        Call call = client.newCall(getRequest);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

}
