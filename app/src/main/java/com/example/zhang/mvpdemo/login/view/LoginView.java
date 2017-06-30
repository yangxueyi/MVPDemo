package com.example.zhang.mvpdemo.login.view;


/**
 * Created by Zhang
 * Time 2017/6/30.
 */

public interface LoginView{
    /**清空输入的内容*/
     void onClearText();
     /**保存*/
     void onSaveResult(Boolean result, String code);
    /**登录的结果*/
     void onLoginResult(Boolean result, String code);
}
