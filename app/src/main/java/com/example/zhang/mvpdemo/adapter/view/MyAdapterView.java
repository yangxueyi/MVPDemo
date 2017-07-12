package com.example.zhang.mvpdemo.adapter.view;

import java.util.List;

/**
 * Created by Zhang
 * Time 2017/7/10.
 */

public interface MyAdapterView {
    void onGetDataList(List<String> datas);
    void toast(String msg);
    void onLoadMoreData(String item);
    void onShowFooterView(boolean isShow);
}
