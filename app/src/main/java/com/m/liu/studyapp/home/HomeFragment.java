package com.m.liu.studyapp.home;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.m.liu.studyapp.R;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luculent on 2018/9/11.
 */

public class HomeFragment extends Fragment {

    private ImageView title_exit;
    private ListView listView;
    private List<Lessons> mDatas;
    private LessonsAdapter mAdapter;
    private Banner banner;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_listview, container, false);
        TextView title = view.findViewById(R.id.centerTv);
        title.setText("教育");
        title_exit = view.findViewById(R.id.title_exit);
        title_exit.setVisibility(View.GONE);
        //banner设计
        banner = view.findViewById(R.id.banner);
        List<Integer> images = new ArrayList<>();
        List<String> imagesTitle = new ArrayList<>();
        images.add(R.mipmap.bg_spinneritem);
        images.add(R.mipmap.bg_spinneritem);
        imagesTitle.add("文泰教育");
        imagesTitle.add("文泰教育2");
        //设置图片加载器
        banner.setImageLoader(new MyLoader());
        banner.isAutoPlay(true);
        banner.setBannerAnimation(Transformer.Default);
        banner.setDelayTime(5000);
        banner.setImages(images);
        banner.setBannerTitles(imagesTitle);
        banner.start();

        //加载列表数据
        listView = view.findViewById(R.id.list_home);
        initData();

        return view;
    }


    private void initData() {
        mDatas = new ArrayList<Lessons>();
        //将数据装到集合中去
        for (int i = 0; i < 20; i++) {
            mDatas.add(new Lessons("刘老师" + i, "学生" + i, "数学", "2015-05-04", "2015-05-14"));
        }
//        Lessons lessons = new Lessons("教师1", "学生2", "数学", "2015-05-04", "2015-05-14");
//        mDatas.add(lessons);
        //为数据绑定适配器
        mAdapter = new LessonsAdapter(getContext(), mDatas);
        listView.setAdapter(mAdapter);
    }


    /**
     * 图片加载类
     */
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }
}

