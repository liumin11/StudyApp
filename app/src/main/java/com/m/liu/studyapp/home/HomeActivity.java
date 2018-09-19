package com.m.liu.studyapp.home;

import com.m.liu.studyapp.CommonActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MotionEvent;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.m.liu.studyapp.R;
import com.m.liu.studyapp.haveclass.HaveClassFragment;
import com.m.liu.studyapp.myhome.MyFragment;

public class HomeActivity extends CommonActivity implements BottomNavigationBar.OnTabSelectedListener {


    private Fragment mFragment;
    private HomeFragment homeFragment;
    private HaveClassFragment haveclassFragment;
    private MyFragment myFragment;
    private long lastClickTime = 0;
//    private CommonActivity titlelayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        //标题设置
//        titlelayout=(CommonActivity)findViewById(R.id.titlelayout);
//        titlelayout.setTitle("文泰教育");

        //底部导航栏设置
        BottomNavigationBar mBottomNavigationBar= (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar
                .setActiveColor(R.color.colortheme);
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.noclasst,"待上课"))
                .addItem(new BottomNavigationItem(R.drawable.haveclass,"已上课"))
                .addItem(new BottomNavigationItem(R.drawable.my,"我的"))
                .setFirstSelectedPosition(0)//设置默认选择的按钮
                .initialise();//所有的设置需在调用该方法前完成
        mBottomNavigationBar.setTabSelectedListener(this);
        initFragment();
    }

    public void initFragment(){
        homeFragment = new HomeFragment();
        haveclassFragment = new HaveClassFragment();
        myFragment = new MyFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, homeFragment)
                .commit();
        mFragment = homeFragment;

    }

    @Override
    public void onTabSelected(int position) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                switchFragment(homeFragment);
                break;
            case 1:
                switchFragment(haveclassFragment);
                break;
            case 2:
                switchFragment(myFragment);
                break;
        }
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }
    @Override
    public void onTabReselected(int position) {

    }

    private void switchFragment(Fragment fragment) {
        //判断当前显示的Fragment是不是切换的Fragment
        if(mFragment != fragment) {
            //判断切换的Fragment是否已经添加过
            if (!fragment.isAdded()) {
                //如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
                getSupportFragmentManager().beginTransaction().hide(mFragment)
                        .add(R.id.fragment_container,fragment).commit();
            } else {
                //如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来
                getSupportFragmentManager().beginTransaction().hide(mFragment).show(fragment).commit();
            }
            mFragment = fragment;
        }
    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (isFastDoubleClick()) {
                return true;
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    public boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        lastClickTime = time;
        return timeD <= 300;
    }


}
