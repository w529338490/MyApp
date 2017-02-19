package com.example.administrator.myapplication.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.security.spec.PSSParameterSpec;
import java.util.List;

/**
 * Created by Administrator on 2017/2/19.
 */

public class News_fragmentAdapter extends FragmentPagerAdapter
{
    List<Fragment> list;
    String[] mTitles;


    public News_fragmentAdapter(FragmentManager fm, List<Fragment> list, String[] mTitles)
    {
        super(fm);
        this.list=list;
        this.mTitles=mTitles;
    }

    @Override
    public Fragment getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
