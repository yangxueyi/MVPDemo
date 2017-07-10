package com.example.zhang.mvpdemo.home.view;

import java.util.List;

/**
 * Created by Zhang
 * Time 2017/7/10.
 */

public interface HomeView {
    /**将数据添加集合中，并填充到adapter适配器中*/
    void onGetDataList(List<String> datas);
    void toast(String str);

}
