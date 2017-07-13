package com.example.zhang.mvpdemo.fragment.contract;

/**
 * Created by Zhang
 * Time 2017/7/13.
 */

public interface FragmentContract {
    interface Model {
    }

    interface View {
    }

    interface Presenter {
        void onItemClick(int position);
    }
}
