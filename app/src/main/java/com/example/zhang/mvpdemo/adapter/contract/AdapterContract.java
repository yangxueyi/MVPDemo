package com.example.zhang.mvpdemo.adapter.contract;

/**
 * Created by Zhang
 * Time 2017/7/10.
 */

public interface AdapterContract {
    interface Model {
    }

    interface View {
    }

    interface Presenter {
        void loadDatas();
        void loadMoreData(String item);
        void showFooterView(boolean isShow);
    }
}
