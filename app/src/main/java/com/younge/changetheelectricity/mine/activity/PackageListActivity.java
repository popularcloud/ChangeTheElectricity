package com.younge.changetheelectricity.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.gxz.PagerSlidingTabStrip;
import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.mine.adapter.MyPagerHasTitleAdapter;
import com.younge.changetheelectricity.mine.bean.MyCarBean;
import com.younge.changetheelectricity.mine.bean.RecommendItemBean;
import com.younge.changetheelectricity.mine.fragment.ChangePackageFragment;
import com.younge.changetheelectricity.mine.fragment.RechargePackageFragment;
import com.younge.changetheelectricity.mine.presenter.MyCarPresenter;
import com.younge.changetheelectricity.mine.view.MyCarView;
import com.younge.changetheelectricity.widget.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PackageListActivity extends MyBaseActivity<MyCarPresenter> implements MyCarView {

    @BindView(R.id.cViewPager)
    CustomViewPager cViewPager;
    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;

    private List<Fragment> fragmentList = new ArrayList<>();

    private ArrayList<RecommendItemBean> arrayList = new ArrayList<>();

    @Override
    protected MyCarPresenter createPresenter() {
        return new MyCarPresenter(this);
    }

    @Override
    protected int getContentViewId(Bundle savedInstanceState) {
        return R.layout.activity_package_list;
    }

    @Override
    protected void init() {
        tv_center_title.setText("套餐");
        initViewpager();
    }

    @Override
    protected void initGetData() {

    }

    @Override
    protected void widgetListener() {

    }

    @OnClick({R.id.rl_fanhui_left})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.rl_fanhui_left:
                finish();
                break;
        }
    }

    private void initViewpager() {

        arrayList.clear();
        arrayList.add(new RecommendItemBean("骑行套餐"));
        arrayList.add(new RecommendItemBean("充电套餐"));

        fragmentList.clear();

        RechargePackageFragment batteryDetailsFragment01 = new RechargePackageFragment();
        ChangePackageFragment shopDetailFragment = new ChangePackageFragment();

        fragmentList.add(batteryDetailsFragment01);
        fragmentList.add(shopDetailFragment);


        cViewPager.setAdapter(new MyPagerHasTitleAdapter(getSupportFragmentManager(),fragmentList,arrayList));
        tabs.setViewPager(cViewPager);
        cViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

        // 设置Tab底部线的高度,传入的是dp
        tabs.setUnderlineHeight(0);
        // 设置Tab 指示器Indicator的高度,传入的是dp
        tabs.setIndicatorHeight(1);
        // 设置Tab Indicator的颜色
        tabs.setIndicatorColor(getResources().getColor(R.color.red));
        // 设置Tab标题文字的大小,传入的是sp
        tabs.setTextSize(14);
        // 设置选中Tab文字的颜色
        tabs.setSelectedTextColor(getResources().getColor(R.color.black));
        //设置正常Tab文字的颜色
        tabs.setTextColor(getResources().getColor(R.color.black_33));
    }

    @Override
    public void onGetCarSuccess(BaseModel<MyCarBean> data) {

    }

    @Override
    public void onGetDataFail() {

    }
}
