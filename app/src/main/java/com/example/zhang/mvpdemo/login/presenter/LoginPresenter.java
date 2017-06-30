package com.example.zhang.mvpdemo.login.presenter;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;

import com.example.zhang.mvpdemo.login.contract.LoginContract;
import com.example.zhang.mvpdemo.login.model.LoginModel;
import com.example.zhang.mvpdemo.login.view.LoginView;


/**
 * Created by Zhang
 * Time 2017/6/30.
 */

public class LoginPresenter implements LoginContract.Presenter {

    Context context;
    LoginView loginView;
    Handler handler;
    private LoginModel loginModel;

    public  LoginPresenter(LoginView loginView,Context context){
        this.loginView = loginView;
        this.context = context;
        handler = new Handler(Looper.getMainLooper());

    }

    @Override
    public void clear() {
        loginView.onClearText();
    }

    @Override
    public void doSave(String name, String passwd) {
        boolean isSaveSuccess = true;
        String  str;
        if(name.length()>10){
            str = "用户名长度不能大于10";
            isSaveSuccess = false;
        }else if(name.matches(".*[a-zA-z].*")){
            str = "用户名不能包含英文";
            isSaveSuccess = false;
        }else if(!passwd.matches(".*[a-zA-z].*")){
            str = "密码必须包含英文";
            isSaveSuccess = false;
        }else if(passwd.length()<5||passwd.length()>16){
            str = "密码长度为5--16位";
            isSaveSuccess = false;
        }else{

            //保存注册的用户名和密码
            SharedPreferences preferences  = context.getSharedPreferences("user",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=preferences.edit();
            editor.putString("name", name);
            editor.putString("age", passwd);
            editor.commit();

            str = "注册成功";
            isSaveSuccess = true;
        }
        final String finalStr = str;
        final boolean finalIsSaveSuccess = isSaveSuccess;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loginView.onSaveResult(finalIsSaveSuccess, finalStr);
            }
        }, 3000);
    }

    @Override
    public void doLogin(String name, String passwd) {

        boolean isLoginSuccess = true;
        String str = "";


        //取出保存的账户密码
        SharedPreferences preferences=context.getSharedPreferences("user", Context.MODE_PRIVATE);
        String name1=preferences.getString("name", "defaultname");
        String pass=preferences.getString("age", "0");

        if(name.equals(name1)  && passwd.equals(pass)){
            isLoginSuccess =  true;
            str = "登录成功";
        }else if(!name.equals(name1) && passwd.equals(pass)){
            isLoginSuccess = false;
            str = "账户错误";
        }else if(name.equals(name1) && !passwd.equals(pass)){
            isLoginSuccess = false;
            str = "密码错误";
        }else{
            isLoginSuccess = false;
            str = "亲，你还没注册吧！";
        }
        final boolean finalIsLoginSuccess = isLoginSuccess;
        final String finalStr = str;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loginView.onLoginResult(finalIsLoginSuccess, finalStr);
            }
        }, 3000);
    }
}
