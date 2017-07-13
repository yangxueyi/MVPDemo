package com.example.zhang.mvpdemo.fragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhang.mvpdemo.R;
import com.example.zhang.mvpdemo.fragment.presenter.FragmentPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhang
 * Time 2017/7/13.
 */

public class MyFragmentAdapter extends RecyclerView.Adapter<MyFragmentAdapter.MyViewHolder> {

    FragmentPresenter fragmentPresenter ;
    List<String> lists;
    public MyFragmentAdapter(FragmentPresenter mFragmentPresenter) {
        this.fragmentPresenter = mFragmentPresenter;
        lists = new ArrayList<String>();
    }

    public void setDatas(List<String> datas){
        if(datas!= null&&datas.size()>0){
            lists.clear();
            lists.addAll(datas);
            notifyDataSetChanged();
        }
    }

    public String getItem(int position){
        return lists.get(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment, parent, false);
        MyViewHolder myHolder = new MyViewHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv.setText(lists.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentPresenter.onItemClick(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
