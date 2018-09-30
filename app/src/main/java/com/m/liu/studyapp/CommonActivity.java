package com.m.liu.studyapp;


import android.content.Context;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by luculent on 2018/9/7.
 *
 */

//标题栏设置

public class CommonActivity extends AppCompatActivity {

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isHideInput(view, ev)) {
                HideSoftInput(view.getWindowToken());
                view.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 判定是否需要隐藏
     */
    private boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 隐藏软键盘
     */
    private void HideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }



//    private static final String KC_SX = "数学";
//    private static final String KC_YW = "语文";
//    private static final String KC_YY = "英语";
//
//
//    public static List<Map<String,Object>> getData(List<String> data){
//
//        List<Map<String,Object>> list = new ArrayList<>();
//
//        for (int i=0;i<data.size();i++){
//            list.add(getMap(data.get(i)));
//        }
//
//        return list;
//    }
//
//    private static Map<String,Object> getMap(String data){
//        Map<String, Object> map = new HashMap<>();
//        switch (data){
//            case KC_SX:
//                map.put("kcLogo", R.mipmap.ic_spinnerch);
//                break;
//            case KC_YW:
//                map.put("kcLogo", R.drawable.ic_spinnermth);
//                break;
//            case KC_YY:
//                map.put("kcLogo", R.drawable.ic_spinnermth);
//                break;
//            default:
//                map.put("kcLogo",R.drawable.ic_spinnermth);
//        }
//
//        map.put("kcName", data);
//        return map;
//    }
//


}
