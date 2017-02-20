package com.example.administrator.myapplication.fragment;


import android.content.Context;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.News_fragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/2/19.
 */

public class News_Fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener
{
    static News_Fragment instance;
    View view;
    @InjectView(R.id.recyview)
    RecyclerView recyview;
    @InjectView(R.id.fresh)
    SwipeRefreshLayout fresh;
    Context context;
    List<String> datas=new ArrayList<>();
    String type;
    LinearLayoutManager manager;
    News_fragmentAdapter adapter;

    public static News_Fragment newInstance(String type) {
        News_Fragment newsFragment = new News_Fragment();
        return newsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.news_fragment,container,false);
        ButterKnife.inject(this,view);
        initView();
        return  view;
    }

    private void initView()
    {
        fresh.setOnRefreshListener(this);
        fresh.setColorSchemeResources(android.R.color.holo_orange_light, android.R.color.holo_red_light, android.R.color.holo_green_light);
        manager=new LinearLayoutManager(News_Fragment.this.getContext());
        recyview.setLayoutManager(manager);
        for(int i=0;i<20;i++) {
            String s = new String(i+">>>>");
            datas.add(s);
        }
        adapter=new News_fragmentAdapter(News_Fragment.this.getContext(),datas);
        recyview.setAdapter(adapter);

    }

    @Override
    public void onRefresh() {

    }
}
