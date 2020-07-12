package com.younge.changetheelectricity.main.adapter;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.HashMap;

/**
 * 通用viewpager 嵌套 fragment适配器
 */
public class FragmentsPagerAdapter extends FragmentPagerAdapter {

    private HashMap<Integer, Fragment> fragmentList;
    public FragmentsPagerAdapter(FragmentManager fm, HashMap<Integer, Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList==null?0:fragmentList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}
