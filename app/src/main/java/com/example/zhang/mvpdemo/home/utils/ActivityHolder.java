package com.example.zhang.mvpdemo.home.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zhang
 * Time 2017/7/10.
 */

public class ActivityHolder {

    Map<String,Class<?extends Activity>> activityMap ;//将所有的activity存到map集合中
    List<String> nameList;//将所有的map集合中的key值，也就是name存到list集合中
    public ActivityHolder(){
        activityMap = new HashMap<>();
        nameList = new ArrayList<>();
    }

    /**将activity添加到集合中*/
    public  void addActivity(String name,Class<?extends Activity> activity){
        if(!activityMap.containsKey(name)){//判断map集合中是否含有了这个activity，没有就添加
            nameList.add(name);
            activityMap.put(name,activity);
        }
    }
    /**获取装有name的集合*/
    public List<String> getNameList(){
        return nameList;
    }
    /**获取activity*/
    public Class<? extends Activity> getActivity(String name){
        return activityMap.get(name);
    }
}
