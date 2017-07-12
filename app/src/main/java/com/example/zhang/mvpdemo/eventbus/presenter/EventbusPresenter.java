package com.example.zhang.mvpdemo.eventbus.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.zhang.mvpdemo.R;
import com.example.zhang.mvpdemo.eventbus.EventbusActivity;
import com.example.zhang.mvpdemo.eventbus.contract.EventbusContract;
import com.example.zhang.mvpdemo.eventbus.event.GetDatasEvent;
import com.example.zhang.mvpdemo.eventbus.event.ToastEvent;
import com.example.zhang.mvpdemo.eventbus.view.EventbusView;


import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhang
 * Time 2017/7/11.
 */

public class EventbusPresenter implements EventbusContract.Presenter {

    EventbusView mEventbusView;
    List <String> datas ;
    public EventbusPresenter(EventbusView eventbusView){
        this.mEventbusView = eventbusView;
        datas = new ArrayList<String>();
    }

    @Override
    public void loadDatas() {

        String[] strs = mEventbusView.getActivity().getResources().getStringArray(R.array.countries);
        for(int i = 0;i < strs.length; i++){
            datas.add(strs[i]);
        }

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                GetDatasEvent datasEvent = new GetDatasEvent(datas);
                EventBus.getDefault().post(datasEvent);
            }
        },2000);

    }

    @Override
    public void onItemClick(int position) {
        ToastEvent toastEvent = new ToastEvent(datas.get(position));
        EventBus.getDefault().post(toastEvent);
    }
}
