package com.younge.changetheelectricity.main.adapter;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.younge.changetheelectricity.mine.bean.RecommendItemBean;

import java.util.ArrayList;
import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private  List<Fragment> mFragments;
    private ArrayList<RecommendItemBean> arrayList = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm, List<Fragment> mFragments,ArrayList<RecommendItemBean> arrayList) {
            super(fm);
            this.mFragments = mFragments;
            this.arrayList = arrayList;
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    }