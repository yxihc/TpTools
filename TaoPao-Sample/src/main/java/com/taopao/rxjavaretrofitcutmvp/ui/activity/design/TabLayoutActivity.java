package com.taopao.rxjavaretrofitcutmvp.ui.activity.design;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.ui.fragment.TabLayoutFragment;

import java.util.ArrayList;

public class TabLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        TabLayout mTabLayout = (TabLayout)findViewById(R.id.id_tab);
        ViewPager mViewPager = (ViewPager)findViewById(R.id.id_viewpager);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(TabLayoutFragment.getInstance("1"));
        fragments.add(TabLayoutFragment.getInstance("第二页"));
        fragments.add(TabLayoutFragment.getInstance("第三页"));
        fragments.add(TabLayoutFragment.getInstance("第四页"));

        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(),fragments));
        mTabLayout.setupWithViewPager(mViewPager);

    }

    class MyAdapter extends FragmentPagerAdapter{
        private String [] mTitle =new String[]{"情感","娱乐","新闻","八卦"};
        ArrayList<Fragment> fragments;
        public MyAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.fragments=fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle[position];
        }
    }
}
