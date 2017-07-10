package com.example.zhang.mvpdemo.home.contract;

/**
 * Created by Zhang
 * Time 2017/6/30.
 */

public interface HomeContract {
    interface Model {
    }

    interface View {
    }

    interface Presenter {
        /**添加数据*/
        void loadDatas();
        /**条目的点击事件*/
        void onClickItem(int position);

    }
}
