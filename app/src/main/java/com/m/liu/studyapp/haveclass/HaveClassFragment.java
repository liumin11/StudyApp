package com.m.liu.studyapp.haveclass;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.m.liu.studyapp.R;

/**
 * Created by luculent on 2018/9/11.
 */

public class HaveClassFragment extends Fragment{
    private ImageView title_exit;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_get_work, container, false);
        TextView title = view.findViewById(R.id.centerTv);
        title.setText("已上课");
        title_exit = view.findViewById(R.id.title_exit);
        title_exit.setVisibility(View.GONE);
        return view;
    }
}
