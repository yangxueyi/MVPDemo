package com.example.zhang.mvpdemo.login.model;

import com.example.zhang.mvpdemo.login.contract.LoginContract;

/**
 * Created by Zhang
 * Time 2017/6/30.
 */

public class LoginModel implements LoginContract.Model {
    String name;
    private String password;
    public  LoginModel(String name,String password){
        this.name = name;
        this.password = password;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getPassword() {
        return password;
    }
}
