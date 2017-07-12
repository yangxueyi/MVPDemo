package com.example.zhang.mvpdemo.adapter.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.example.zhang.mvpdemo.adapter.contract.AdapterContract;
import com.example.zhang.mvpdemo.adapter.view.MyAdapterView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Zhang
 * Time 2017/7/10.
 */

public class AdapterPresenter implements AdapterContract.Presenter {

    MyAdapterView myAdapterView;
    private List<String> datas ;

    public AdapterPresenter(MyAdapterView myAdapterView){
        this.myAdapterView = myAdapterView;
    }
    @Override
    public void loadDatas() {

        String [] strs = {"one adapter","two adapter","three adapter","four adapter","five adapter"};
        datas = new ArrayList<String>();

        for(int i = 0;i < strs.length; i++){
            datas.add(strs[i]);
        }

        Handler handler = new Handler(Looper.getMainLooper()) ;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myAdapterView.onGetDataList(datas);
            }
        },2000);
    }

    @Override
    public void loadMoreData(final String item) {

        showFooterView(true);//加载的时候显示进度条
        Handler handler = new Handler(Looper.getMainLooper()) ;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myAdapterView.onLoadMoreData(item);
            }
        },2000);
    }
    /**是否显示尾布局进度条*/
    @Override
    public void showFooterView(boolean isShow) {
        myAdapterView.onShowFooterView(isShow);
    }
}
