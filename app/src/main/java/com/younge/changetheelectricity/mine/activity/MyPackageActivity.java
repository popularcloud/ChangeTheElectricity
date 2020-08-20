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
import com.younge.changetheelectricity.mine.adapter.MyPackageListAdapter;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.mine.presenter.MyPackagePresenter;
import com.younge.changetheelectricity.mine.view.PackageListView;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;

import org.byteam.superadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class MyPackageActivity extends MyBaseActivity<MyPackagePresenter> implements PackageListView {

    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.mBGARefreshLayout)
    BGARefreshLayout mBGARefreshLayout;
    private MyPackageListAdapter mAdapter;

    private int page = 1;

    private List<PackageBean.ListBean> allList = new ArrayList<>();

    @Override
    protected MyPackagePresenter createPresenter() {
        return new MyPackagePresenter(this);
    }


    @Override
    protected int getContentViewId(Bundle savedInstanceState) {
        return R.layout.activity_my_package;
    }

    @Override
    protected void init() {


        tv_center_title.setText("我的套餐");

        tv_right.setVisibility(View.VISIBLE);
        tv_right.setText("使用记录");
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyPackageActivity.this, UseHistoryActivity.class));
            }
        });
        initList();
    }

    @Override
    protected void initGetData() {

    }

    @Override
    protected void widgetListener() {

    }

    private void initList(){


        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(this, false);
        mBGARefreshLayout.setRefreshViewHolder(refreshViewHolder);

        mAdapter = new MyPackageListAdapter(this,allList,R.layout.item_my_package);
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        rv_data.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {

            }
        });

        mBGARefreshLayout.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                page = 1;
                mPresenter.getMyPackageList("",String.valueOf(page),(String) SharedPreferencesUtils.getParam(MyPackageActivity.this,"token",""));
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                page++;
                mPresenter.getMyPackageList("",String.valueOf(page),(String) SharedPreferencesUtils.getParam(MyPackageActivity.this,"token",""));
                return true;
            }
        });

        mBGARefreshLayout.beginRefreshing();

    }

    @OnClick({R.id.rl_fanhui_left,R.id.tv_submit})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.rl_fanhui_left:
                finish();
                break;
            case R.id.tv_submit:
                startActivity(new Intent(MyPackageActivity.this,PackageListActivity.class));
                break;
        }
    }

    @Override
    public void onGetDataSuccess(BaseModel<PackageBean> data) {
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

    @Override
    protected void onResume() {
        super.onResume();
        if(mBGARefreshLayout != null){
            mBGARefreshLayout.beginRefreshing();
        }
    }
}
