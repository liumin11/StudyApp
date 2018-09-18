package com.m.liu.studyapp.myhome;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

        //从登陆接口中获得姓名
        my_xm = view.findViewById(R.id.myxm);
        Bundle bundle = getActivity().getIntent().getExtras();
        String str = bundle.getString("data");
        my_xm.setText(str);

        //从登陆接口的请求参数中获得用户名/手机号
        my_sjh = view.findViewById(R.id.mysjh);
        Bundle bundle1 = getActivity().getIntent().getExtras();
        String str1 = bundle1.getString("userName");
        my_sjh.setText(str1);

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
                exitApp();
                break;

        }
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
        Intent logoutIntent = new Intent(getActivity(), LoginActivity.class);
        logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(logoutIntent);
    }
}
