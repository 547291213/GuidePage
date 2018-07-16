package com.example.xkfeng.guidepage.Viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by initializing on 2018/7/16.
 */

public class GuideAdapger extends FragmentPagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>() ;

    public GuideAdapger(FragmentManager fm , List<Fragment> fragments) {
        super(fm);

        fragmentList =  fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


}
