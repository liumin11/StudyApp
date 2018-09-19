package com.m.liu.studyapp.haveclass;

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

public class HaveClassFragment extends Fragment{
    private ImageView title_exit;
    private ListView listView;
    private List<HaveLessons> hmDatas;
    private HaveLessonsAdapter hmAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_get_work, container, false);
        TextView title = view.findViewById(R.id.centerTv);
        title.setText("已上课");
        title_exit = view.findViewById(R.id.title_exit);
        title_exit.setVisibility(View.GONE);

        listView = view.findViewById(R.id.list_getwork);
        initData();

        return view;
    }

    private void initData() {
        hmDatas = new ArrayList<HaveLessons>();
        //将数据装到集合中去

        for (int i = 0;i < 20; i ++){
            hmDatas.add(new HaveLessons("教师"+i, "学生"+i, "数学", "2015-05-04", "2015-05-14"));
        }
//        Lessons lessons = new Lessons("教师1", "学生2", "数学", "2015-05-04", "2015-05-14");
//        mDatas.add(lessons);
        //为数据绑定适配器
        hmAdapter = new HaveLessonsAdapter(getContext(),hmDatas);
        listView.setAdapter(hmAdapter);
    }

}
