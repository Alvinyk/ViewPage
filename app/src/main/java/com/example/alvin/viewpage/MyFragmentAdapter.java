package com.example.alvin.viewpage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by alvin on 2017/7/20.
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    public MyFragmentAdapter(FragmentManager fm,List<Fragment> list){
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int arg0){
        return list.get(arg0);
    }

    @Override
    public int getCount(){
        return list.size();
    }
}
