package com.younge.changetheelectricity.changetheelectricity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.changetheelectricity.Bean.OrderResultBean;
import com.younge.changetheelectricity.changetheelectricity.adapter.ChargeDetailsAdapter;
import com.younge.changetheelectricity.changetheelectricity.adapter.ChargeDetailsTwoAdapter;
import com.younge.changetheelectricity.main.bean.DeviceDetailBean;
import com.younge.changetheelectricity.main.presenter.ChargeDetailPresenter;
import com.younge.changetheelectricity.main.view.ChargeDetailView;
import com.younge.changetheelectricity.mine.activity.MyPackageActivity;
import com.younge.changetheelectricity.mine.activity.PackageListActivity;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;
import com.younge.changetheelectricity.util.ToastUtil;
import com.younge.changetheelectricity.widget.CustomDialog;

import org.byteam.superadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ChargeDetailActivity extends MyBaseActivity<ChargeDetailPresenter> implements ChargeDetailView {

    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.rv_data_two)
    RecyclerView rv_data_two;
    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.tv_submit)
    TextView tv_submit;

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_address)
    TextView tv_address;

    private ChargeDetailsAdapter mAdapter;
    private ChargeDetailsTwoAdapter mAdapterTwo;

    private List<PackageBean.ListBean> allList = new ArrayList<>();
    private List<DeviceDetailBean.DeviceGoodsBean> bllList = new ArrayList<>();

    private String macno;

    private int packageType = 1; //packageType 1我的  2商城套餐

    private String selectPackageId;

    private String selectChargeBox;

    private CustomDialog customDialog;



    @Override
    protected ChargeDetailPresenter createPresenter() {
        return new ChargeDetailPresenter(this);
    }

    @Override
    protected int getContentViewId(Bundle savedInstanceState) {
        return R.layout.activity_charge_detail;
    }

    @Override
    protected void init() {
        tv_center_title.setText("充电详情");

        macno = getIntent().getStringExtra("macno");

        customDialog = new CustomDialog(this);
        customDialog.setTitle("温馨提示");
        customDialog.setMessage("您还未购买充电套餐，请先去购买充电套餐");
        customDialog.setButton1Text("是");
        customDialog.setButton2Text("否");
        customDialog.setCancelBtn(new CustomDialog.OnClickListener() {
            @Override
            public void onClick(CustomDialog dialog, int id, Object object) {
                customDialog.dismiss();
            }
        });
        customDialog.setEnterBtn(new CustomDialog.OnClickListener() {
            @Override
            public void onClick(CustomDialog dialog, int id, Object object) {
                startActivity(new Intent(ChargeDetailActivity.this, PackageListActivity.class));
            }
        });

        initList();
    }

    @Override
    protected void initGetData() {

        mPresenter.getDeviceDetail("","",macno, (String) SharedPreferencesUtils.getParam(ChargeDetailActivity.this,"token",""));

        mPresenter.getMyPackageList("2","1",(String) SharedPreferencesUtils.getParam(ChargeDetailActivity.this,"token",""));



    }

    @Override
    protected void widgetListener() {

    }

    private void initList(){

        allList.clear();
        mAdapter = new ChargeDetailsAdapter(this,allList,R.layout.item_charge_details);
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        rv_data.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {
               mAdapter.setSelPosition(position);

                selectPackageId = String.valueOf(mAdapter.getItem(position).getId());

            }
        });

        bllList.clear();
        mAdapterTwo = new ChargeDetailsTwoAdapter(this,bllList,R.layout.item_charge_detail_two);
        rv_data_two.setLayoutManager(new GridLayoutManager(this,2));
        rv_data_two.setAdapter(mAdapterTwo);

        mAdapterTwo.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {
                mAdapterTwo.setSelPosition(position);

                selectChargeBox = String.valueOf(mAdapterTwo.getItem(position).getDevice_box());

            }
        });
    }

    @OnClick({R.id.tv_submit,R.id.rl_fanhui_left})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.tv_submit:
                //startActivity(new Intent(this, ConfirmOrderActivity.class));

                if(mAdapter.getData() == null || mAdapter.getData().size() < 1){
                    if(customDialog != null){
                        customDialog.show();
                    }
                    return;
                }
                if(TextUtils.isEmpty(selectPackageId)){
                    ToastUtil.makeText(ChargeDetailActivity.this,"请选择套餐");
                    return;
                }
                if(TextUtils.isEmpty(selectChargeBox)){
                    ToastUtil.makeText(ChargeDetailActivity.this,"请选择充电口");
                    return;
                }

                Log.e("提交数据","=========macno"+macno+"======selectCargeBox"+selectChargeBox+"======selectPackageId"+selectPackageId);
                mPresenter.submitOrder(macno,selectChargeBox,"1","0","",selectPackageId, (String) SharedPreferencesUtils.getParam(ChargeDetailActivity.this,"token",""));
                break;
            case R.id.rl_fanhui_left:
                finish();
                break;
        }
    }

    @Override
    public void onGetDeviceDetailSuccess(BaseModel<DeviceDetailBean> data) {

        if(data != null && data.getData() != null && data.getData().getDevice_goods() != null){

            packageType = 2;

            tv_title.setText(data.getData().getTitle());
            tv_address.setText(data.getData().getAddress());

            List<DeviceDetailBean.DeviceGoodsBean> deviceGoodsBeans = data.getData().getDevice_goods();
            bllList.clear();
            for(int i = 0;i < deviceGoodsBeans.size(); i ++){
                if(deviceGoodsBeans.get(i).getGoods_type() == 1){     //0为电池  1为充电口
                    bllList.add(deviceGoodsBeans.get(i));
                }
            }
            mAdapterTwo.replaceAll(bllList);
        }
    }

    @Override
    public void onGetPackageSuccess(BaseModel<PackageBean> data) {
        if(data != null && data.getData() != null && data.getData().getList() != null){
            mAdapter.replaceAll(data.getData().getList());
        }
    }

    @Override
    public void onGetMyPackageSuccess(BaseModel<PackageBean> data) {
        packageType = 1;
        if(data != null && data.getData() != null && data.getData().getTotalpage() > 0){
            mAdapter.replaceAll(data.getData().getList());
        }else{
            //mPresenter.getMyPackageList("2","1",(String) SharedPreferencesUtils.getParam(ChargeDetailActivity.this,"token",""));


            if(customDialog != null){
                customDialog.show();
            }
        }
    }

    @Override
    public void onSubmitOrderSuccess(BaseModel<OrderResultBean> data) {
        if(data != null && data.getData() != null) {

            Intent intent = new Intent(ChargeDetailActivity.this, CharegeStatuActivity.class);
            intent.putExtra("orderId", data.getData().getOrder_id());
            intent.putExtra("boxId", selectChargeBox);
            startActivity(intent);
        }
    }

    @Override
    public void onGetDataFail() {

    }
}
