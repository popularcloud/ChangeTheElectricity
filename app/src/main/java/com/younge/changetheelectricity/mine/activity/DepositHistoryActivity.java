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
import com.younge.changetheelectricity.mine.bean.DepositHistoryBean;
import com.younge.changetheelectricity.mine.bean.RecommendItemBean;
import com.younge.changetheelectricity.mine.fragment.ConsumeDetailFragment;
import com.younge.changetheelectricity.mine.fragment.RechargeDetailFragment;
import com.younge.changetheelectricity.mine.presenter.DepositHistoryPresenter;
import com.younge.changetheelectricity.mine.view.DepositHistoryView;
import com.younge.changetheelectricity.widget.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DepositHistoryActivity extends MyBaseActivity<DepositHistoryPresenter> implements DepositHistoryView {


    @BindView(R.id.cViewPager)
    CustomViewPager cViewPager;
    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;

    private List<Fragment> fragmentList = new ArrayList<>();

    private ArrayList<RecommendItemBean> arrayList = new ArrayList<>();

    @Override
    protected DepositHistoryPresenter createPresenter() {
        return new DepositHistoryPresenter(this);
    }

    @Override
    protected int getContentViewId(Bundle savedInstanceState) {
        return R.layout.activity_deposit_history;
    }

    @Override
    protected void init() {
        tv_center_title.setText("明细");
        initViewpager();
    }

    @Override
    protected void initGetData() {

    }

    @Override
    protected void widgetListener() {

    }

    private void initViewpager() {

        arrayList.clear();
        arrayList.add(new RecommendItemBean("充值明细"));
        arrayList.add(new RecommendItemBean("消费明细"));

        fragmentList.clear();

        RechargeDetailFragment batteryDetailsFragment01 = new RechargeDetailFragment();
        ConsumeDetailFragment shopDetailFragment = new ConsumeDetailFragment();

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

    @OnClick({R.id.rl_fanhui_left})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.rl_fanhui_left:
                finish();
                break;
        }
    }

    @Override
    public void onGetDepositHistorySuccess(BaseModel<DepositHistoryBean> data) {

    }

    @Override
    public void onGetDataFail() {

    }
}
