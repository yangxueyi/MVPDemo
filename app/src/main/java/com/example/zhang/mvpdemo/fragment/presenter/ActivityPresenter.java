package com.example.zhang.mvpdemo.fragment.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.zhang.mvpdemo.R;
import com.example.zhang.mvpdemo.fragment.contract.ActivityContract;
import com.example.zhang.mvpdemo.fragment.event.GetDatasEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhang
 * Time 2017/7/13.
 */

public class ActivityPresenter implements ActivityContract.Presenter {

    Context context;
    public ActivityPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void loadDatas() {

        String[] strs = context.getResources().getStringArray(R.array.countries);
        final List<String> list = new ArrayList<String>();
        for(int i = 0;i < strs.length; i++){
            list.add(strs[i]);
        }

        Handler handler = new Handler(Looper.getMainLooper()) ;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                GetDatasEvent getDatasEvent = new GetDatasEvent(list);
                EventBus.getDefault().post(getDatasEvent);
            }
        },1000);

    }
}
