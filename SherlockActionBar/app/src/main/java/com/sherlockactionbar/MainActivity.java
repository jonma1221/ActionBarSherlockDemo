package com.sherlockactionbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockListFragment;

import fragments.FirstFragment;
import fragments.SecondFragment;

public class MainActivity extends SherlockFragmentActivity{
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupTabs();
    }

    private void setupTabs() {
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);

        ViewPager.SimpleOnPageChangeListener ViewPagerListener = new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // Find the ViewPager Position
                actionBar.setSelectedNavigationItem(position);
            }
        };

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setOnPageChangeListener(ViewPagerListener);
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            final int PAGE_COUNT = 2;
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        FirstFragment fragmenttab1 = new FirstFragment();
                        return fragmenttab1;

                    case 1:
                        SecondFragment fragmenttab2 = new SecondFragment();
                        return fragmenttab2;
                }
                return null;
            }

            @Override
            public void notifyDataSetChanged() {
                super.notifyDataSetChanged();
            }

            @Override
            public int getCount() {
                return PAGE_COUNT;
            }
        });

        ActionBar.Tab tabFirst = actionBar
                .newTab()
                .setText("First")
                .setTabListener(
                        new SherlockTabListener<FirstFragment>(R.id.flContainer, this, "First",
                                FirstFragment.class, mViewPager));

        actionBar.addTab(tabFirst);

        ActionBar.Tab tabSecond = actionBar
                .newTab()
                .setText("Second")
                .setTabListener(
                        new SherlockTabListener<SecondFragment>(R.id.flContainer, this, "Second",
                                SecondFragment.class, mViewPager));

        actionBar.addTab(tabSecond);
    }
}
