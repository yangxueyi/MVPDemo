package com.example.zhang.mvpdemo.login.contract;

/**
 * Created by Zhang
 * Time 2017/6/30.
 */

public interface LoginContract {
    interface Model {
        String getName();
        String getPassword();
    }
    interface View {
    }
    interface Presenter {
        void clear();
        void doSave(String name, String passwd);
        void doLogin(String name, String passwd);
    }
}
