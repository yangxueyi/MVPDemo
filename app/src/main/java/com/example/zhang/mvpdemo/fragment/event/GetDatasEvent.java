package com.example.zhang.mvpdemo.fragment.event;

import java.util.List;

/**
 * Created by Zhang
 * Time 2017/7/13.
 */

public class GetDatasEvent {
    List<String> lists;
    public GetDatasEvent(List<String> datas){
        this.lists = datas;
    }
    public List<String> getDatas(){
        return lists;
    }
}
