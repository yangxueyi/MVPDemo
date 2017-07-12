package com.example.zhang.mvpdemo.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.zhang.mvpdemo.R;
import com.example.zhang.mvpdemo.adapter.myadapter.MyAdapter;
import com.example.zhang.mvpdemo.adapter.presenter.AdapterPresenter;
import com.example.zhang.mvpdemo.adapter.view.MyAdapterView;

import java.util.List;

/**
 * Created by Zhang
 * Time 2017/7/10.
 */

public class AdapterActivity extends AppCompatActivity implements MyAdapterView,AdapterView.OnItemClickListener{

    private ListView list_view;
    private AdapterPresenter adapterPresenter;
    private MyAdapter myAdapter;
    private View footerViewVisible;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);

        initView();
        initListener();
    }

    private void initView() {
        list_view = (ListView) findViewById(R.id.list_view);
        //设置listview的分配器
        list_view.setDivider(null);
        //添加进度条
        View loadingView = LayoutInflater.from(this).inflate(R.layout.item_empty_view, null);
        ViewGroup viewGroup = (ViewGroup) this.findViewById(R.id.layout_adapter);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        viewGroup.addView(loadingView, layoutParams);
        list_view.setEmptyView(loadingView);


        View footerView = LayoutInflater.from(this).inflate(R.layout.item_footer, null);
        list_view.addFooterView(footerView);
        footerViewVisible = footerView.findViewById(R.id.layout_visible);

        //创建presenter对象
        adapterPresenter = new AdapterPresenter(this);
        //设置刚开始隐藏加载进度条
        adapterPresenter.showFooterView(false);

        myAdapter = new MyAdapter(adapterPresenter);
        list_view.setAdapter(myAdapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        //添加数据
        adapterPresenter.loadDatas();
    }

    private void initListener() {
        list_view.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//        adapterPresenter.onItemClick(position);
        toast( myAdapter.getItem(position));
    }
    @Override
    public void onGetDataList(List<String> datas) {
        myAdapter.setData(datas);
    }
    @Override
    public void toast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onLoadMoreData(String item) {
        myAdapter.addItem(item);
        //加载完成，隐藏进度条
        adapterPresenter.showFooterView(false);
    }
    @Override
    public void onShowFooterView(boolean isShow) {
        if(isShow){
            footerViewVisible.setVisibility(View.VISIBLE);
        }else{
            footerViewVisible.setVisibility(View.GONE);
        }
    }
}
