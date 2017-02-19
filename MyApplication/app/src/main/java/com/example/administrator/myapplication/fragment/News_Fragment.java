package com.example.administrator.myapplication.fragment;


import android.content.Context;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.util.List;

/**
 * Created by Administrator on 2017/2/19.
 */

public class News_Fragment extends Fragment
{
    static News_Fragment instance;
    View view;
    Context context;
    List<String> datas;
    String type;
    TextView tv;


    public static News_Fragment newInstance(String type) {
        News_Fragment newsFragment = new News_Fragment();
        Bundle bd = new Bundle();
        bd.putString("type", type);
        newsFragment.setArguments(bd);
        return newsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.news_fragment,container,false);
        initView();

        return  view;
    }

    private void initView()
    {
        Bundle bundle=getArguments();
        type=bundle.getString("type");
        tv= (TextView) view.findViewById(R.id.tv);
        tv.setText(type);

    }
}
