package com.example.zhang.mvpdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zhang.mvpdemo.R;
import com.example.zhang.mvpdemo.fragment.adapter.MyFragmentAdapter;
import com.example.zhang.mvpdemo.fragment.event.GetDatasEvent;
import com.example.zhang.mvpdemo.fragment.presenter.FragmentPresenter;
import com.example.zhang.mvpdemo.fragment.view.FragmentView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Zhang
 * Time 2017/7/13.
 */

public class FragmentDemo extends Fragment implements FragmentView{
    private static final String BUNDLE_INDEX = "BUNDLE_INDEX";
    private int index;
    private RecyclerView recyclerView;
    private FragmentPresenter mFragmentPresenter;
    private MyFragmentAdapter mAdapter;


    public static FragmentDemo newInstance(int index){
        FragmentDemo fragmentDemo = new FragmentDemo();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_INDEX, index);
        fragmentDemo.setArguments(args);
        return fragmentDemo;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            index = getArguments().getInt(BUNDLE_INDEX);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //找到布局,并找到控件
        View view = inflater.inflate(R.layout.fragment_demo, container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        //注册eventbus
        EventBus.getDefault().register(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFragmentPresenter = new FragmentPresenter(this);
        mAdapter = new MyFragmentAdapter(mFragmentPresenter);
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getActivity(),"Tab "+index+" : "+(String)mAdapter.getItem(position),Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void onEvent(GetDatasEvent getDatasEvent){
        if(getDatasEvent!=null&&getDatasEvent.getDatas().size()>0&& getDatasEvent.getDatas()!=null){
            mAdapter.setDatas(getDatasEvent.getDatas());
        }
    }
}
