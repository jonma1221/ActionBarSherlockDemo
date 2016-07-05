package com.sherlockactionbar;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;

/**
 * Created by Jonathan on 7/4/2016.
 */
public class SherlockTabListener<T extends SherlockFragment> implements ActionBar.TabListener {
    private SherlockFragment mFragment;
    private final SherlockFragmentActivity mActivity;
    private final String mTag;
    private final Class<T> mClass;
    private final int mfragmentContainerId;
    private final Bundle mfragmentArgs;
    private ViewPager mPager;

    public SherlockTabListener(int fragmentContainerId, SherlockFragmentActivity activity,
                               String tag, Class<T> clz, ViewPager pager) {
        mActivity = activity;
        mTag = tag;
        mClass = clz;
        mfragmentContainerId = fragmentContainerId;
        mfragmentArgs = new Bundle();
        mPager = pager;
    }

    public SherlockTabListener(int fragmentContainerId, SherlockFragmentActivity activity,
                               String tag, Class<T> clz, Bundle args) {
        mActivity = activity;
        mTag = tag;
        mClass = clz;
        mfragmentContainerId = fragmentContainerId;
        mfragmentArgs = args;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        if (mFragment == null) {
            /*
            mFragment = (SherlockFragment) SherlockFragment
                    .instantiate(mActivity, mClass.getName(), mfragmentArgs);
            ft.add(mfragmentContainerId, mFragment, mTag);*/
            mPager.setCurrentItem(tab.getPosition());
        } else {
            ft.attach(mFragment);
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        if (mFragment != null) {
            ft.detach(mFragment);
        }
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
