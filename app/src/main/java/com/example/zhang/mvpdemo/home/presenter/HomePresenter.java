package com.example.zhang.mvpdemo.home.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import com.example.zhang.mvpdemo.adapter.AdapterActivity;
import com.example.zhang.mvpdemo.eventbus.EventbusActivity;
import com.example.zhang.mvpdemo.home.contract.HomeContract;
import com.example.zhang.mvpdemo.home.utils.ActivityHolder;
import com.example.zhang.mvpdemo.home.view.HomeView;
import com.example.zhang.mvpdemo.login.LoginActivity;

/**
 * Created by Zhang
 * Time 2017/6/30.
 */

public class HomePresenter implements HomeContract.Presenter {

    public static ActivityHolder activityHolder;
    static {
        activityHolder = new ActivityHolder();
        activityHolder.addActivity("login demo", LoginActivity.class);
        activityHolder.addActivity("adapter demo", AdapterActivity.class);
        activityHolder.addActivity("eventbus demo", EventbusActivity.class);
    }

    Context context;
    HomeView homeView;
    public HomePresenter(Context context,HomeView homeView){
        this.context = context;
        this.homeView = homeView;

    }

    @Override
    public void loadDatas() {

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //将list集合传到activity中
                homeView.onGetDataList(activityHolder.getNameList());
            }
        },200);
    }
    @Override
    public void onClickItem(int position) {
        //获取点击的activity
        Class activity = activityHolder.getActivity(activityHolder.getNameList().get(position));
        if (activity!=null){
            context.startActivity(new Intent(context, activity));
        }

    }
}
