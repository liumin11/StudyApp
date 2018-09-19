package com.m.liu.studyapp.haveclass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.m.liu.studyapp.R;
import com.m.liu.studyapp.home.Lessons;

import java.util.List;

/**
 * Created by luculent on 2018/9/13.
 */

public class HaveLessonsAdapter extends BaseAdapter {


        private LayoutInflater hmInflater;
        private List<HaveLessons> hmDatas;

            //MyAdapter需要一个Context，通过Context获得Layout.inflater，然后通过inflater加载item的布局
            public HaveLessonsAdapter(Context context, List<HaveLessons> datas) {
                hmInflater = LayoutInflater.from(context);
                hmDatas = datas;
            }



    @Override
    public int getCount() {
        return hmDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return hmDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = hmInflater.inflate(R.layout.getwork_listitem, parent, false); //加载布局
            holder = new ViewHolder();
            holder.jsxm_over = (TextView) convertView.findViewById(R.id.item_over_jsxm);
            holder.xsxm_over = (TextView) convertView.findViewById(R.id.item_over_xsxm);
            holder.kcxm_over = (TextView) convertView.findViewById(R.id.item_over_kcxm);
            holder.kssj_over = (TextView) convertView.findViewById(R.id.item_over_kssjd);
            holder.jssj_over = (TextView) convertView.findViewById(R.id.item_over_jssjd);

            convertView.setTag(holder);
        } else {   //else里面说明，convertView已经被复用了，说明convertView中已	经设置过tag了，即holder
            holder = (ViewHolder) convertView.getTag();
        }

        HaveLessons havelessons = hmDatas.get(position);
        holder.jsxm_over.setText(havelessons.getTeacherName());
        holder.xsxm_over.setText(havelessons.getStudentName());
        holder.kcxm_over.setText(havelessons.getClasses());
        holder.kssj_over.setText(havelessons.getStartTime());
        holder.jssj_over.setText(havelessons.getEndTime());

        return convertView;
    }

    //这个ViewHolder只能服务于当前这个特定的adapter，因为ViewHolder里会指定	item的控件，不同的ListView，item可能不同，所以ViewHolder写成一个私有的	类
    private class ViewHolder {
        TextView jsxm_over;
        TextView xsxm_over;
        TextView kcxm_over;
        TextView kssj_over;
        TextView jssj_over;
    }
}
