package com.younge.changetheelectricity.changetheelectricity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.changetheelectricity.adapter.BatteryDetailsAdapter;
import com.younge.changetheelectricity.main.bean.DeviceDetailBean;
import com.younge.changetheelectricity.main.presenter.DeviceDetailPresenter;
import com.younge.changetheelectricity.main.view.DeviceDetailView;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;

import org.byteam.superadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BatteryDetailActivity extends MyBaseActivity<DeviceDetailPresenter> implements DeviceDetailView {

    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.tv_submit)
    TextView tv_submit;

    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.tv_battery_account)
    TextView tv_battery_account;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_address)
    TextView tv_address;

    private BatteryDetailsAdapter mAdapter;

    private String macno;

    private List<DeviceDetailBean.DeviceGoodsBean> allList = new ArrayList<>();

    @Override
    protected DeviceDetailPresenter createPresenter() {
        return new DeviceDetailPresenter(this);
    }


    @Override
    protected int getContentViewId(Bundle savedInstanceState) {
        return R.layout.activity_battery_detail;
    }

    @Override
    protected void init() {
        tv_center_title.setText("电柜详情");

        macno = getIntent().getStringExtra("macno");

        initList();
    }

    @Override
    protected void initGetData() {

    }

    @Override
    protected void widgetListener() {

    }

    private void initList(){

        allList.clear();

        mAdapter = new BatteryDetailsAdapter(this,allList,R.layout.item_battery_details);
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        rv_data.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {

            }
        });

        mPresenter.getDeviceDetail("","",macno, (String) SharedPreferencesUtils.getParam(BatteryDetailActivity.this,"token",""));
    }

    @OnClick({R.id.tv_submit,R.id.rl_fanhui_left})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.tv_submit:
                startActivity(new Intent(this, OperateStatuActivity.class));
                break;
            case R.id.rl_fanhui_left:
                finish();
                break;
        }
    }

    @Override
    public void onGetDeviceDetailSuccess(BaseModel<DeviceDetailBean> data) {

        if(data != null && data.getData() != null && data.getData().getDevice_goods() != null){
            tv_battery_account.setText("电池数量："+ data.getData().getBox());
            tv_num.setText("电柜编号："+macno);
            tv_title.setText(data.getData().getTitle());
            tv_address.setText(data.getData().getAddress());

            List<DeviceDetailBean.DeviceGoodsBean> deviceGoodsBeans = data.getData().getDevice_goods();
            allList.clear();
            for(int i = 0;i < deviceGoodsBeans.size(); i ++){
                if(deviceGoodsBeans.get(i).getGoods_type() == 0){     //0为电池  1为充电口
                    allList.add(deviceGoodsBeans.get(i));
                }
            }
            mAdapter.replaceAll(allList);
        }
    }

    @Override
    public void onGetDataFail() {

    }
}
