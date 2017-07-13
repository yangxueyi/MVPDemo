package com.example.zhang.mvpdemo.fragment.presenter;

import com.example.zhang.mvpdemo.fragment.contract.FragmentContract;
import com.example.zhang.mvpdemo.fragment.view.FragmentView;

/**
 * Created by Zhang
 * Time 2017/7/13.
 */

public class FragmentPresenter implements FragmentContract.Presenter {
    FragmentView mFragmentView;
    public FragmentPresenter(FragmentView fragmentView) {
        this.mFragmentView = fragmentView;
    }

    @Override
    public void onItemClick(int position) {
        mFragmentView.onItemClick(position);
    }
}
