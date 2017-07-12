package com.example.zhang.mvpdemo.adapter.myadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zhang.mvpdemo.R;
import com.example.zhang.mvpdemo.adapter.presenter.AdapterPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhang
 * Time 2017/7/10.
 */

public class MyAdapter extends BaseAdapter {
    AdapterPresenter adapterPresenter;
    List<String> list ;
    public MyAdapter(AdapterPresenter adapterPresenter) {
        this.adapterPresenter = adapterPresenter;
        this.list = new ArrayList<String>();
    }

    /**填充adapter*/
    public void setData( List<String> datas ){
        if(list!=null||list.size()>0){
            list.clear();
            list.addAll(datas);
            notifyDataSetChanged();
        }
    }
    /**添加一个条目*/
    public void addItem(String item){
        list.add(item);
        notifyDataSetChanged();
    }
    /**删除点击的条目*/
    public void removeItem(int position){
        if(position>=0&&position<list.size()){
            list.remove(position);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        if(convertView==null){
            convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_adapter, viewGroup, false);
        }

        final String str = list.get(position);//获取条目
        TextView tv = (TextView) convertView.findViewById(R.id.tv);
        tv.setText(str);//设置文字

        TextView tv_add = (TextView) convertView.findViewById(R.id.tv_add);
        //设置点击事件
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterPresenter.loadMoreData("new "+str);//添加一个条目
            }
        });

        TextView tv_delete = (TextView) convertView.findViewById(R.id.tv_delete);
        //点击，删除条目
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(position);
            }
        });

        return convertView;
    }
}
