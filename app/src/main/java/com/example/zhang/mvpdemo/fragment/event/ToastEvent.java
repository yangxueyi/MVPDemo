package com.example.zhang.mvpdemo.fragment.event;

/**
 * Created by Zhang
 * Time 2017/7/13.
 */

public class ToastEvent {

    String msg ;
    public ToastEvent( String msg){
        this.msg = msg;
    }
    public String getMessage(String msg){
        return msg;
    }
}
