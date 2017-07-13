package com.example.zhang.mvpdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.TabLayout;


import com.example.zhang.mvpdemo.R;
import com.example.zhang.mvpdemo.fragment.presenter.ActivityPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhang
 * Time 2017/7/13.
 */

public class FragmentActivity extends AppCompatActivity {

    private ViewPager view_pager;
    private TabLayout tab_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


        initView();

    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //设置向下兼容的toolbar
        setSupportActionBar(toolbar);

        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        view_pager = (ViewPager) findViewById(R.id.view_pager);


        /*List<Fragment> list = new ArrayList<>();
        FragmentDemo fragmentDemo = new FragmentDemo();
        FragmentDemo1 fragmentDemo1 = new FragmentDemo1();

        list.add(fragmentDemo);
        list.add(fragmentDemo1);*/


        view_pager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        view_pager.setOffscreenPageLimit(3);
        tab_layout.setupWithViewPager(view_pager);

        ActivityPresenter activityPresenter = new ActivityPresenter(this);


        activityPresenter.loadDatas();
    }

    /**
     * FragmentStatePagerAdapter:
     *   用来实现Fragment在ViewPager里面进行滑动切换的
     *   比FragmentPagerAdapter更适合用于很多界面之间的转换，而且消耗更少的内存资源
     * */
    class MyAdapter extends FragmentStatePagerAdapter{

        public String[] pagers = new String[]{"FragmentA","FragmentB+","FragmentC-"};

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentDemo.newInstance(position);
        }

        @Override
        public int getCount() {
            return pagers.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
                return pagers[position];
        }
    }

}
