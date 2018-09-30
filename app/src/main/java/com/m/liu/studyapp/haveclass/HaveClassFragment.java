package com.m.liu.studyapp.haveclass;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.m.liu.studyapp.R;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luculent on 2018/9/11.
 */

public class HaveClassFragment extends Fragment{
    private ImageView title_exit;
    private ListView listView;
    private List<HaveLessons> hmDatas;
    private HaveLessonsAdapter hmAdapter;
    private Spinner spinner;
    private TextView spinnertv,date_tv;
    private SimpleAdapter simp_adapter;
    private List<Map<String,Object>> data;
    private DatePickerDialog dateDialog;
    private int year, monthOfYear, dayOfMonth;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.getwork_activity, container, false);
        TextView title = view.findViewById(R.id.centerTv);
        title.setText("已上课");
        title_exit = view.findViewById(R.id.title_exit);
        title_exit.setVisibility(View.GONE);

        //课程下拉筛选框实现
        spinner = view.findViewById(R.id.classover_spinnerkc);
        spinnertv = view.findViewById(R.id.spinner_text);
        simp_adapter = new SimpleAdapter(getActivity(),getData(),R.layout.getwork_spinner_item,new String[]{"kcLogo","tv1"},new int[]{R.id.spinner_image,R.id.spinner_text});
        spinner.setAdapter(simp_adapter);
        //Spinner加载监听事件
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              String name =  simp_adapter.getItem(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        date_tv = view.findViewById(R.id.classover_spinrq);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(calendar.YEAR);
        monthOfYear = calendar.get(calendar.MONTH);
        dayOfMonth = calendar.get(calendar.DAY_OF_MONTH);


        date_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dateDialog.show();
            }
        });

        dateDialog = new DatePickerDialog(getActivity(),R.style.ThemeDialog,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker arg0, int year, int monthOfYear,
                                  int dayOfMonth) {
              		String text = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                date_tv.setText(text);
            }

        }, year, monthOfYear, dayOfMonth);
        dateDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                date_tv.setText("选择日期");
            }
        });



        //加载列表数据
        listView = view.findViewById(R.id.list_getwork);
        initData();
        return view;
    }

    private void initData() {
        hmDatas = new ArrayList<HaveLessons>();
        for (int i = 0;i < 20; i ++){
            hmDatas.add(new HaveLessons("教师"+i, "学生"+i, "数学", "2015-05-04", "2015-05-14"));
        }
//        Lessons lessons = new Lessons("教师1", "学生2", "数学", "2015-05-04", "2015-05-14");
//        mDatas.add(lessons);
        //为数据绑定适配器
        hmAdapter = new HaveLessonsAdapter(getContext(),hmDatas);
        listView.setAdapter(hmAdapter);
    }


    //下拉框的选择项
    public List<Map<String,Object>> getData(){
        data = new ArrayList<>();
        Map<String,Object> map =  new HashMap<>();
        map.put("kcLogo", R.drawable.ic_spinnerkc);
        map.put("tv1","选择课程");
        data.add(map);
        Map<String,Object> map1 =  new HashMap<>();
        map1.put("kcLogo", R.drawable.ic_spinnerch1);
        map1.put("tv1","语文");
        data.add(map1);
        Map<String,Object> map2 =  new HashMap<>();
        map2.put("kcLogo", R.drawable.ic_spinnermth1);
        map2.put("tv1","数学");
        data.add(map2);
        Map<String,Object> map3 =  new HashMap<>();
        map3.put("kcLogo", R.drawable.ic_spinnereg1);
        map3.put("tv1","英语");
        data.add(map3);
        return data;
    }




}
