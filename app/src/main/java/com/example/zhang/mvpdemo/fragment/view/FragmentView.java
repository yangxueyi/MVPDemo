package com.example.zhang.mvpdemo.fragment.view;

import android.app.Activity;

/**
 * Created by Zhang
 * Time 2017/7/13.
 */

public interface FragmentView {
     Activity getActivity();
     void onItemClick(int position);
}
