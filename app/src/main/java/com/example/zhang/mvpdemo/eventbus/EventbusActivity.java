package com.example.zhang.mvpdemo.eventbus;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.zhang.mvpdemo.R;
import com.example.zhang.mvpdemo.eventbus.event.GetDatasEvent;
import com.example.zhang.mvpdemo.eventbus.event.ToastEvent;
import com.example.zhang.mvpdemo.eventbus.presenter.EventbusPresenter;
import com.example.zhang.mvpdemo.eventbus.view.EventbusView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhang
 * Time 2017/7/11.
 */

public class EventbusActivity extends AppCompatActivity implements EventbusView,AdapterView.OnItemClickListener{

    private ListView list_view;
    private EventbusPresenter eventbusPresenter;

    List<String> datas = new ArrayList<String>();
    private BaseAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus);
        
        initView();
        initListener();

    }

    private void initView() {

        list_view = (ListView) findViewById(R.id.list_view);

        //注册eventbus
        EventBus.getDefault().register(this);

        //添加进度条
        View loadingView = LayoutInflater.from(this).inflate(R.layout.item_empty_view, null);
        ViewGroup viewGroup = (ViewGroup) this.findViewById(R.id.layout_event);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        viewGroup.addView(loadingView,layoutParams);
        list_view.setEmptyView(loadingView);

       /* View footerView = LayoutInflater.from(this).inflate(R.layout.item_footer, null);
        list_view.addFooterView(footerView);*/

        myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datas);
        list_view.setAdapter(myAdapter);

        eventbusPresenter = new EventbusPresenter(this);

    }


    private void initListener() {
        list_view.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        eventbusPresenter.loadDatas();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        eventbusPresenter.onItemClick(position);
    }

    //接受toastevent传过来的信息
    @Subscribe//必须添加    @Subscribe注解
    public void onToastEvent(ToastEvent toastEvent){
        if(toastEvent!=null&&toastEvent.getMessage()!=null) {
            Toast.makeText(this, toastEvent.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    //接受getdatasevent传过来的信息
    @Subscribe//必须添加    @Subscribe注解
    public void onDatasEvent(GetDatasEvent getDatasEvent){
        if(getDatasEvent!=null&&getDatasEvent.getDatas()!=null&&getDatasEvent.getDatas().size()>0){
            datas.clear();
            datas.addAll(getDatasEvent.getDatas());
            myAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
