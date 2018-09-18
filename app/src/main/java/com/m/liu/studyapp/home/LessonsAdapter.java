package com.m.liu.studyapp.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.m.liu.studyapp.R;

import java.util.List;

/**
 * Created by luculent on 2018/9/13.
 */

public class LessonsAdapter extends BaseAdapter {


        private LayoutInflater mInflater;
        private List<Lessons> mDatas;

            //MyAdapter需要一个Context，通过Context获得Layout.inflater，然后通过inflater加载item的布局
            public LessonsAdapter(Context context, List<Lessons> datas) {
                mInflater = LayoutInflater.from(context);
                mDatas = datas;
            }



    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.home_listitem, parent, false); //加载布局
            holder = new ViewHolder();
            holder.jsxm = (TextView) convertView.findViewById(R.id.item_jsxm);
            holder.xsxm = (TextView) convertView.findViewById(R.id.item_xsxm);
            holder.kcxm = (TextView) convertView.findViewById(R.id.item_kcxm);
            holder.kssj = (TextView) convertView.findViewById(R.id.item_kssjd);
            holder.jssj = (TextView) convertView.findViewById(R.id.item_jssjd);

            convertView.setTag(holder);
        } else {   //else里面说明，convertView已经被复用了，说明convertView中已	经设置过tag了，即holder
            holder = (ViewHolder) convertView.getTag();
        }

        Lessons lessons = mDatas.get(position);
        holder.jsxm.setText(lessons.getTeacherName());
        holder.xsxm.setText(lessons.getStudentName());
        holder.kcxm.setText(lessons.getClasses());
        holder.kssj.setText(lessons.getStartTime());
        holder.jssj.setText(lessons.getEndTime());

        return convertView;
    }

    //这个ViewHolder只能服务于当前这个特定的adapter，因为ViewHolder里会指定	item的控件，不同的ListView，item可能不同，所以ViewHolder写成一个私有的	类
    private class ViewHolder {
        TextView jsxm;
        TextView xsxm;
        TextView kcxm;
        TextView kssj;
        TextView jssj;
    }
}
