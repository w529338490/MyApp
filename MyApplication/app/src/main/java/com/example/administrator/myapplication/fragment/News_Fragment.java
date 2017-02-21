package com.example.administrator.myapplication.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.WebActivity;
import com.example.administrator.myapplication.adapter.News_fragmentAdapter;
import com.example.administrator.myapplication.entity.Result;
import com.example.administrator.myapplication.net.Api;
import com.example.administrator.myapplication.net.Service.HttpService;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/19.
 */

public class News_Fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener
{
    static News_Fragment instance;
    View view;
  //  @InjectView(R.id.recyview)
    RecyclerView recyview;
  //  @InjectView(R.id.fresh)
    SwipeRefreshLayout fresh;
    Context context;
    List<String> datas=new ArrayList<>();
    List<Result.ResultBean.DataBean> data=new ArrayList<>();
    LinearLayoutManager manager;
    News_fragmentAdapter adapter;
    HttpService service;
    String[] str_type = new String[]{"top","keji","shehui","guonei","yule"};
    int type=0;
    boolean reflash=false;

    public static News_Fragment newInstance(int type) {
        News_Fragment newsFragment = new News_Fragment();
        Bundle bundle=new Bundle();
        bundle.putInt("type",type);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.news_fragment,container,false);
      //  ButterKnife.inject(News_Fragment.this,view);
        recyview= (RecyclerView) view.findViewById(R.id.recyview);
        fresh= (SwipeRefreshLayout) view.findViewById(R.id.fresh);
        type=this.getArguments().getInt("type");
        initView( );
        initData( type,reflash);
        return  view;
    }

    private void initData(int type, final boolean reflash)
    {
        service= Api.getInstance().Api_News();
        Call<Result> call=service.Get_news(str_type[type]);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if(response.isSuccessful()&& response.body().getError_code()==0){
                    data= response.body().getResult().getData();
                    fresh.setRefreshing(false);
                    updataui(data);
                    if(reflash)
                    {
                        Toast.makeText(News_Fragment.this.getContext(),"刷新完成",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t)
            {
                Toast.makeText(News_Fragment.this.getContext(),"网络加载失败",Toast.LENGTH_SHORT).show();
                if (fresh != null) {
                    fresh.setRefreshing(false);
                }
            }
        });
    }
    //跟新UI
    private void updataui(final List<Result.ResultBean.DataBean> data)
    {
        adapter=new News_fragmentAdapter(News_Fragment.this.getContext(),data);
        recyview.setAdapter(adapter);
        adapter.setOnItemClickListener(new News_fragmentAdapter.OnItemClickListener()
        {
            @Override
            public void getData(int position)
            {
                Intent intent=new Intent(News_Fragment.this.getContext(), WebActivity.class);
                intent.putExtra("url",data.get(position).getUrl());
                News_Fragment.this.getContext().startActivity(intent);
            }
        });
    }

    private void initView()
    {
        fresh.setOnRefreshListener(this);
        fresh.setColorSchemeResources(android.R.color.holo_orange_light, android.R.color.holo_red_light, android.R.color.holo_green_light);
        manager=new LinearLayoutManager(News_Fragment.this.getContext());
        recyview.setLayoutManager(manager);
//        for(int i=0;i<20;i++) {
//            String s = new String(i+">>>>"+type);
//            datas.add(s);
//        }
//        adapter=new News_fragmentAdapter(News_Fragment.this.getContext(),datas);
//        recyview.setAdapter(adapter);

    }

    @Override
    public void onRefresh()
    {
        reflash=true;
        initData(type,reflash);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);

    }

}
