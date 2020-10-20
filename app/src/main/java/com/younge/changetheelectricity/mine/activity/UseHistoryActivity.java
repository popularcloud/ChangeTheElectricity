package com.younge.changetheelectricity.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseActivity;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;
import com.younge.changetheelectricity.changetheelectricity.Bean.UserHistoryBean;
import com.younge.changetheelectricity.changetheelectricity.adapter.ConfirmOrderAdapter;
import com.younge.changetheelectricity.changetheelectricity.adapter.UserHistoryAdapter;
import com.younge.changetheelectricity.changetheelectricity.presenter.UserHistoryPresenter;
import com.younge.changetheelectricity.changetheelectricity.view.UserHistoryView;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;

import org.byteam.superadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class UseHistoryActivity extends MyBaseActivity<UserHistoryPresenter> implements UserHistoryView {

    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.tv_right)
    TextView tv_right;

    @BindView(R.id.tv_battery_num)
    TextView tv_battery_num;
    @BindView(R.id.mBGARefreshLayout)
    BGARefreshLayout mBGARefreshLayout;

    private UserHistoryAdapter mAdapter;

    private List<UserHistoryBean.ListBean> allList = new ArrayList<>();

    private int page = 0;

    @Override
    protected UserHistoryPresenter createPresenter() {
        return new UserHistoryPresenter(this);
    }


    @Override
    protected int getContentViewId(Bundle savedInstanceState) {
        return R.layout.activity_use_history;
    }

    @Override
    protected void init() {
        tv_center_title.setText("使用记录");
        initList();


        String sn = (String) SharedPreferencesUtils.getParam(this,"presentBattery","");

        tv_battery_num.setText("当前电池（"+sn+")");
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
        allList.clear();
        mAdapter = new UserHistoryAdapter(this,allList,R.layout.item_use_history);
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
                mPresenter.getUserHistory(String.valueOf(page), String.valueOf(SharedPreferencesUtils.getParam(UseHistoryActivity.this,"token","")));
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                page++;
                mPresenter.getUserHistory(String.valueOf(page), String.valueOf(SharedPreferencesUtils.getParam(UseHistoryActivity.this,"token","")));
                return true;
            }
        });

        mBGARefreshLayout.beginRefreshing();
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
    public void onGetUserHistorySuccess(BaseModel<UserHistoryBean> data) {

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
