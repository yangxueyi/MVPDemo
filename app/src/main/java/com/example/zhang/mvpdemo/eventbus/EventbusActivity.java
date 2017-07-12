package com.example.zhang.mvpdemo.eventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.zhang.mvpdemo.R;
import com.example.zhang.mvpdemo.eventbus.presenter.EventbusPresenter;
import com.example.zhang.mvpdemo.eventbus.view.EventbusView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhang
 * Time 2017/7/11.
 */

public class EventbusActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView list_view;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus);
        
        initView();
        initListener();

    }

    private void initView() {

        list_view = (ListView) findViewById(R.id.list_view);

        //添加进度条
        View loadingView = LayoutInflater.from(this).inflate(R.layout.item_empty_view, null);
        ViewGroup viewGroup = (ViewGroup) this.findViewById(R.id.layout_event);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        viewGroup.addView(loadingView,layoutParams);
        list_view.setEmptyView(loadingView);

        View footerView = LayoutInflater.from(this).inflate(R.layout.item_footer, null);
        list_view.addFooterView(footerView);

    }


    private void initListener() {
        list_view.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
