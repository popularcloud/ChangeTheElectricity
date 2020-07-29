package com.younge.changetheelectricity.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.changetheelectricity.adapter.MyCarListAdapter;
import com.younge.changetheelectricity.mine.bean.MyCarBean;
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


    @BindView(R.id.mBGARefreshLayout)
    BGARefreshLayout mBGARefreshLayout;

    private MyCarListAdapter mAdapter;

    private List<MyCarBean.ListBean> allList = new ArrayList<>();
    private int page = 1;

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

        tv_center_title.setText("我的车辆");
        initList();
    }

    @Override
    protected void initGetData() {

    }

    @Override
    protected void widgetListener() {

    }

    private void initList(){


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
    public void onGetCarSuccess(BaseModel<MyCarBean> data) {

        if(data != null && data.getData() != null && data.getData().getList() != null){
            if(page == 1) {
                mAdapter.replaceAll(data.getData().getList());
            }else{
                mAdapter.addAll(data.getData().getList());
            }
        }


        if(page == 1) {
           mBGARefreshLayout.endRefreshing();
        }else{
            mBGARefreshLayout.endLoadingMore();
        }
    }

    @Override
    public void onGetDataFail() {
        if(page == 1) {
            mBGARefreshLayout.endRefreshing();
        }else{
            mBGARefreshLayout.endLoadingMore();
        }
    }
}
