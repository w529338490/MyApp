package com.example.administrator.myapplication;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.myapplication.adapter.News_fragmentAdapter;
import com.example.administrator.myapplication.adapter.PaperAdapter;
import com.example.administrator.myapplication.fragment.News_Fragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    @InjectView(R.id.mDrawerLayout)
    DrawerLayout mDrawerLayout;
    @InjectView(R.id.bar)
    Toolbar bar;
    @InjectView(R.id.tab)
    TabLayout tab;
    @InjectView(R.id.paper)
    ViewPager pager;
    PaperAdapter adapter;

    @InjectView(R.id.nv)
            NavigationView nv;
    ArrayList<Fragment> list = new ArrayList<>();
    private final String[] mTitles = {"Android", "iOS", "前端", "拓展资源"};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initData();

    }

    private void initData()
    {
        nv.setNavigationItemSelectedListener(this);
        tab.setupWithViewPager(pager);
        bar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               mDrawerLayout.openDrawer(GravityCompat.START);

            }
        });
        //设置Toolbar和DrawerLayout实现动画和联动
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, bar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        for(int i=0;i<mTitles.length;i++)
        {
            News_Fragment newsf=News_Fragment.newInstance(mTitles[i]);
               list.add(newsf);

        }
        adapter=new PaperAdapter(getSupportFragmentManager(),list,mTitles);
        pager.setAdapter(adapter);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.nav_news:
                Toast.makeText(MainActivity.this,"nav_news",Toast.LENGTH_SHORT).show();
                break;
            case  R.id.nav_gif:
                Toast.makeText(MainActivity.this,"group_gif",Toast.LENGTH_SHORT).show();
                break;
            case  R.id.nav_video:
                Toast.makeText(MainActivity.this,"group_vedio",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}