package com.example.zhang.mvpdemo.eventbus.event;


/**
 * Created by Zhang
 * Time 2017/7/12.
 */

public class ToastEvent {
    String msg;
    public ToastEvent(String msg){
        this.msg = msg;
    }

    public String getMessage(){
        return msg;
    }
}

