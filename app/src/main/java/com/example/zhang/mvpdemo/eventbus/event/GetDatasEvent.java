package com.example.zhang.mvpdemo.eventbus.event;

import java.util.List;

/**
 * Created by Zhang
 * Time 2017/7/12.
 */

public class GetDatasEvent {

    List<String> datas;
    public GetDatasEvent(List<String> datas){
        this.datas = datas;
    }
    public List getDatas(){
        return datas;
    }
}
