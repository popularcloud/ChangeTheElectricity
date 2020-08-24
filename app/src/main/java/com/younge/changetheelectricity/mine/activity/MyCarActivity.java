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
import com.younge.changetheelectricity.callback.OnItemBtnClickCallBack;
import com.younge.changetheelectricity.changetheelectricity.adapter.MyCarListAdapter;
import com.younge.changetheelectricity.mine.bean.MyCarBean;
import com.younge.changetheelectricity.mine.presenter.MyCarPresenter;
import com.younge.changetheelectricity.mine.view.MyCarView;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;
import com.younge.changetheelectricity.util.ToastUtil;

import org.byteam.superadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class MyCarActivity extends MyBaseActivity<MyCarPresenter> implements MyCarView {


    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.tv_right)
    TextView tv_right;

    @BindView(R.id.rv_data)
    RecyclerView rv_data;
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
        return R.layout.activity_my_car;
    }

    @Override
    protected void init() {

        tv_center_title.setText("我的车辆");
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setText("添加车辆");
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyCarActivity.this, BindCarActivity.class));
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

        mAdapter = new MyCarListAdapter(this, allList, R.layout.item_my_car, new OnItemBtnClickCallBack() {
            @Override
            public void OnItemBtnclick(int pisition, int btn) {

                if(btn == 1){ //删除
                    mPresenter.delCar(String.valueOf(mAdapter.getItem(pisition).getId()), (String) SharedPreferencesUtils.getParam(MyCarActivity.this,"token",""));
                }
            }
        });
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
                mPresenter.getMyCarList(String.valueOf(page), String.valueOf(SharedPreferencesUtils.getParam(MyCarActivity.this,"token","")));
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                page++;
                mPresenter.getMyCarList(String.valueOf(page), String.valueOf(SharedPreferencesUtils.getParam(MyCarActivity.this,"token","")));
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
    public void onGetCarSuccess(BaseModel<MyCarBean> data) {

        if(data != null && data.getData() != null && data.getData().getList() != null && data.getData().getList().size() > 0){
            mBGARefreshLayout.setVisibility(View.VISIBLE);
            if(page == 1) {
                mAdapter.replaceAll(data.getData().getList());
            }else{
                mAdapter.addAll(data.getData().getList());
            }
        }else{
            mBGARefreshLayout.setVisibility(View.INVISIBLE);
        }


        if(page == 1) {
           mBGARefreshLayout.endRefreshing();
        }else{
            mBGARefreshLayout.endLoadingMore();
        }
    }

    @Override
    public void onDelSuccess(BaseModel data) {
        ToastUtil.makeText(this,"删除成功！");
        mBGARefreshLayout.beginRefreshing();
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
