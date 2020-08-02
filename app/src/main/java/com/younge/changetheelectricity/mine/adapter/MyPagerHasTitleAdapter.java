package com.younge.changetheelectricity.mine.adapter;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.younge.changetheelectricity.mine.bean.RecommendItemBean;

import java.util.ArrayList;
import java.util.List;

public class MyPagerHasTitleAdapter extends FragmentPagerAdapter {

    private  List<Fragment> mFragments;
    private ArrayList<RecommendItemBean> arrayList;

        public MyPagerHasTitleAdapter(FragmentManager fm, List<Fragment> mFragments, ArrayList<RecommendItemBean> arrayList) {
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
    public CharSequence getPageTitle(int position) {
        return arrayList.get(position).getRecommendName();
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