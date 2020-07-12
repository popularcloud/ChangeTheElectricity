package com.younge.changetheelectricity.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseFragment;
import com.younge.changetheelectricity.changetheelectricity.activity.BatteryDetailActivity;
import com.younge.changetheelectricity.changetheelectricity.fragment.BatteryDetailsFragment;
import com.younge.changetheelectricity.changetheelectricity.fragment.ShopDetailFragment;
import com.younge.changetheelectricity.main.adapter.MyPagerAdapter;
import com.younge.changetheelectricity.widget.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends BaseFragment {

    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.cvp_data)
    CustomViewPager cvp_data;
    @BindView(R.id.tv_scan)
    TextView tv_scan;

    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        ButterKnife.bind(this, view);
        mMapView.onCreate(savedInstanceState);

        initMapView();
        initViewpager();
        return view;
    }

    private void initMapView() {
        //初始化地图控制器对象
        AMap aMap = null;
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
    }

    private void initViewpager() {

        fragmentList.clear();

        BatteryDetailsFragment batteryDetailsFragment01 = new BatteryDetailsFragment();
        ShopDetailFragment shopDetailFragment = new ShopDetailFragment();

        fragmentList.add(batteryDetailsFragment01);
        fragmentList.add(shopDetailFragment);


        cvp_data.setAdapter(new MyPagerAdapter(getChildFragmentManager(),fragmentList));

        cvp_data.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        cvp_data.setCurrentItem(0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }


    @OnClick({R.id.tv_scan})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.tv_scan:
                getActivity().startActivity(new Intent(getActivity(), BatteryDetailActivity.class));
                break;
        }
    }

}
