package com.younge.changetheelectricity.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.changetheelectricity.adapter.MyCarListAdapter;
import com.younge.changetheelectricity.changetheelectricity.fragment.BatteryDetailsFragment;
import com.younge.changetheelectricity.changetheelectricity.fragment.ShopDetailFragment;
import com.younge.changetheelectricity.main.adapter.MyPagerAdapter;
import com.younge.changetheelectricity.mine.bean.MyCarBean;
import com.younge.changetheelectricity.mine.fragment.ChangePackageFragment;
import com.younge.changetheelectricity.mine.fragment.RechargePackageFragment;
import com.younge.changetheelectricity.mine.presenter.MyCarPresenter;
import com.younge.changetheelectricity.mine.view.MyCarView;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;
import com.younge.changetheelectricity.widget.CustomViewPager;

import org.byteam.superadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class PackageListActivity extends MyBaseActivity<MyCarPresenter> implements MyCarView {

    @BindView(R.id.cViewPager)
    CustomViewPager cViewPager;
    @BindView(R.id.tv_center_title)
    TextView tv_center_title;

    private List<Fragment> fragmentList = new ArrayList<>();

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
        tv_center_title.setText("骑行套餐");
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

        fragmentList.clear();

        RechargePackageFragment batteryDetailsFragment01 = new RechargePackageFragment();
        ChangePackageFragment shopDetailFragment = new ChangePackageFragment();

        fragmentList.add(batteryDetailsFragment01);
        fragmentList.add(shopDetailFragment);


        cViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),fragmentList));

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
        cViewPager.setCurrentItem(0);
    }

    @Override
    public void onGetCarSuccess(BaseModel<MyCarBean> data) {

    }

    @Override
    public void onGetDataFail() {

    }
}
