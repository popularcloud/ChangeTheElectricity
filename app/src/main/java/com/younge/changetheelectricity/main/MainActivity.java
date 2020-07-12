package com.younge.changetheelectricity.main;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseActivity;
import com.younge.changetheelectricity.main.adapter.FragmentsPagerAdapter;
import com.younge.changetheelectricity.main.fragment.MainFragment;
import com.younge.changetheelectricity.widget.CustomViewPager;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.cViewPager)
    CustomViewPager cViewPager;
    @BindView(R.id.radio_home)
    RadioButton radioHome;
    @BindView(R.id.radio_mba)
    RadioButton radioMBA;
    @BindView(R.id.radio_order)
    RadioButton radioOrder;
    @BindView(R.id.radio_news)
    RadioButton radioNews;
    @BindView(R.id.radio_mine)
    RadioButton radioMine;
    @BindView(R.id.group_tab)
    RadioGroup groupTab;


    /**
     * fragment相关
     */
    private HashMap<Integer, Fragment> fragmentHashMap;
    private HashMap rButtonHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initFragment();
    }

    private void initFragment(){

        fragmentHashMap = new HashMap<>();
        fragmentHashMap.put(0, new MainFragment());
        fragmentHashMap.put(1, new MainFragment());
        fragmentHashMap.put(2, new MainFragment());
        fragmentHashMap.put(3, new MainFragment());
        fragmentHashMap.put(4,new MainFragment());

        rButtonHashMap = new HashMap<>();
        rButtonHashMap.put(0, radioHome);
        rButtonHashMap.put(1, radioMBA);
        rButtonHashMap.put(2, radioNews);
        rButtonHashMap.put(3, radioOrder);
        rButtonHashMap.put(4, radioMine);

        //是否滑动
        cViewPager.setPagingEnabled(false);
        cViewPager.setOffscreenPageLimit(5);
        cViewPager.setAdapter(new FragmentsPagerAdapter(getSupportFragmentManager(), fragmentHashMap));
        cViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                cViewPager.setChecked(rButtonHashMap, position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @OnClick({R.id.radio_home, R.id.radio_order, R.id.radio_news, R.id.radio_mine,R.id.radio_mba})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.radio_home:
                cViewPager.setCurrentItem(0, false);
                break;
            case R.id.radio_mba:
                cViewPager.setCurrentItem(1, false);
                break;
            case R.id.radio_news:
                cViewPager.setCurrentItem(2, false);
                break;
            case R.id.radio_order:
                cViewPager.setCurrentItem(3, false);
                break;
            case R.id.radio_mine:
                cViewPager.setCurrentItem(4, false);
                break;
        }
    }
}
