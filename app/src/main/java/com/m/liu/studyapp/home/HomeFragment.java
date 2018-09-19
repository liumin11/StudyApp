package com.m.liu.studyapp.home;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.m.liu.studyapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luculent on 2018/9/11.
 */

public class HomeFragment extends Fragment{

    private ImageView title_exit;

    private ListView listView;
    private List<Lessons> mDatas;
    private LessonsAdapter mAdapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_listview, container, false);
        TextView title = view.findViewById(R.id.centerTv);
        title.setText("教育");
        title_exit = view.findViewById(R.id.title_exit);
        title_exit.setVisibility(View.GONE);
        listView = view.findViewById(R.id.list_home);
        initData();

        return view;
    }



    private void initData() {
        mDatas = new ArrayList<Lessons>();
        //将数据装到集合中去

        for (int i = 0;i < 20; i ++){
            mDatas.add(new Lessons("教师"+i, "学生"+i, "数学", "2015-05-04", "2015-05-14"));
        }
//        Lessons lessons = new Lessons("教师1", "学生2", "数学", "2015-05-04", "2015-05-14");
//        mDatas.add(lessons);
        //为数据绑定适配器
        mAdapter = new LessonsAdapter(getContext(),mDatas);
        listView.setAdapter(mAdapter);
    }
}

