package com.example.administrator.myapplication.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.security.spec.PSSParameterSpec;
import java.util.List;

/**
 * Created by Administrator on 2017/2/19.
 */

public class News_fragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    Context context;
    LayoutInflater inflaters;
    List<String> list;
    final int NOFOOT = 1;
    final int YESFOOT = 2;
    public News_fragmentAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        inflaters=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType)
        {
            case NOFOOT:
                holder=new MyHolder(inflaters.inflate(R.layout.news_adapter,parent,false));
                break;

            case  YESFOOT:
                break;

        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder) {
            ((MyHolder) holder).tv.setTextColor(context.getResources().getColor(R.color.colorAccent));
            ((MyHolder) holder).tv.setText(list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        if (position == (list.size())) {
            return YESFOOT;
        } else {
            return NOFOOT;
        }
    }

    class MyHolder extends RecyclerView.ViewHolder
    {
        TextView tv;

        public MyHolder(View itemView) {
            super(itemView);
            tv= (TextView) itemView.findViewById(R.id.tv);
        }
        public void setTittle(String tittle)
        {
            if(!tittle.equals(""))
            {
                tv.setText(tittle);

            }
        }
    }
    class MyHolder_foot extends RecyclerView.ViewHolder {


        public MyHolder_foot(View itemView) {
            super(itemView);

        }
    }
}
