package com.example.zhang.mvpdemo.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.zhang.mvpdemo.R;
import com.example.zhang.mvpdemo.home.presenter.HomePresenter;
import com.example.zhang.mvpdemo.home.view.HomeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhang
 * Time 2017/6/30.
 */

public class HomeActivity extends ActionBarActivity implements HomeView,AdapterView.OnItemClickListener{


    ArrayList<String> arrayList = new ArrayList<String>();
    private ListView listView;
    private BaseAdapter adapter;
    private HomePresenter homePresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
        initListener();

        homePresenter = new HomePresenter(this,this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        homePresenter.loadDatas();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.recyler_view);

        //添加进度条
        View loadingView = LayoutInflater.from(this).inflate(R.layout.item_empty_view, null);
        ViewGroup viewGroup = (ViewGroup) this.findViewById(R.id.layout_home);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        viewGroup.addView(loadingView, layoutParams);
        listView.setEmptyView(loadingView);


        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);
    }

    private void initListener() {
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        homePresenter.onClickItem(i);
    }

    @Override
    public void onGetDataList(List<String> datas) {
        if(datas!=null||datas.size()>0){
            arrayList.clear();
            arrayList.addAll(datas);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void toast(String msg) {

    }
}
