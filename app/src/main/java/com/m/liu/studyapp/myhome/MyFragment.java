package com.m.liu.studyapp.myhome;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.m.liu.studyapp.R;
import com.m.liu.studyapp.login.LoginActivity;


/**
 * Created by luculent on 2018/9/11.
 */

public class MyFragment extends Fragment implements View.OnClickListener{


    private EditText editText;
    private Button fkButton;
    private ImageView title_exit;
    private TextView my_xm;
    private TextView my_sjh;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_layout, container, false);
        TextView title=view.findViewById(R.id.centerTv);
        title.setText("我的");

        my_xm = view.findViewById(R.id.myxm);
        my_sjh = view.findViewById(R.id.mysjh);

        init();

        //反馈输入框和反馈按钮添加监听
        editText =view.findViewById(R.id.myfksr) ;
        fkButton = view.findViewById(R.id.myfk);
        title_exit = view.findViewById(R.id.title_exit);
        fkButton.setOnClickListener(this);
        title_exit.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.myfk:
                feedBack();
                break;
            case R.id.title_exit:
                exitDialog();
                break;

        }
    }

    private void exitDialog(){

        AlertDialog.Builder builder = new Builder(getActivity());
        builder.setTitle("确定要退出吗？");     //设置对话框标题
//        builder.setIcon(android.R.drawable.btn_star_big_on);      //设置对话框标题前的图标
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                exitApp();
            }
        });
        builder.setCancelable(true);   //设置按钮是否可以按返回键取消,false则不可以取消
        AlertDialog dialog = builder.create();  //创建对话框
        dialog.setCanceledOnTouchOutside(true);      //设置弹出框失去焦点是否隐藏,即点击屏蔽其它地方是否隐藏
        dialog.show();
    }


    private void init(){

        //从登陆接口中获得姓名
//        Bundle bundle = getActivity().getIntent().getExtras();
//        String str = bundle.getString("data");
//        my_xm.setText(str);
//
//        //从登陆接口的请求参数中获得用户名/手机号
//        Bundle bundle1 = getActivity().getIntent().getExtras();
//        String str1 = bundle1.getString("userName");
//        my_sjh.setText(str1);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        String str = sharedPreferences.getString("userName","");
        String str1 = sharedPreferences.getString("data","");
        my_xm.setText(str1);
        my_sjh.setText(str);

    }


    //反馈内容框
    private void feedBack(){
        if (editText.getText().toString().trim().equals("")){
            Toast.makeText(getActivity(),getResources().getString(R.string.edit_fk_null),Toast.LENGTH_SHORT).show();
        }else {
            editText.setText("");
            Toast.makeText(getActivity(),"感谢您的反馈",Toast.LENGTH_LONG).show();

        }

    }

    //退出app
    private void exitApp(){

        SharedPreferences share = getActivity().getSharedPreferences("Login",
                Context.MODE_PRIVATE);
        share.edit().putBoolean("LoginBool", false).commit();

        Intent logoutIntent = new Intent(getActivity(), LoginActivity.class);
        logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(logoutIntent);
        getActivity().finish();
    }
}
