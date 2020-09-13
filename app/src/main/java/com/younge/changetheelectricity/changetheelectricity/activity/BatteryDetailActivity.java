package com.younge.changetheelectricity.changetheelectricity.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.callback.OnItemBtnClickCallBack;
import com.younge.changetheelectricity.changetheelectricity.Bean.OrderResultBean;
import com.younge.changetheelectricity.changetheelectricity.Bean.StartResultBean;
import com.younge.changetheelectricity.changetheelectricity.adapter.BatteryDetailsAdapter;
import com.younge.changetheelectricity.main.bean.DeviceDetailBean;
import com.younge.changetheelectricity.main.presenter.DeviceDetailPresenter;
import com.younge.changetheelectricity.main.view.DeviceDetailView;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;
import com.younge.changetheelectricity.util.ToastUtil;

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

    @BindView(R.id.ll_batterys)
    LinearLayout ll_batterys;
    @BindView(R.id.ll_order_battery)
    LinearLayout ll_order_battery;

    @BindView(R.id.tv_title01)
    TextView tv_title01;
    @BindView(R.id.tv_msg)
    TextView tv_msg;

    //订单类型 0。其他 1。预约
    private int orderType = 0;

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

        mAdapter = new BatteryDetailsAdapter(this, allList, R.layout.item_battery_details, new OnItemBtnClickCallBack() {
            @Override
            public void OnItemBtnclick(int pisition, int btn) {

            }
        });
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        rv_data.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {

                orderType = 1;

                DeviceDetailBean.DeviceGoodsBean deviceGoodsBean = mAdapter.getItem(position);
                mPresenter.submitOrder(macno,String.valueOf(deviceGoodsBean.getDevice_box()),"0",String.valueOf(orderType),"","", (String) SharedPreferencesUtils.getParam(BatteryDetailActivity.this,"token",""));
            }
        });

        mPresenter.getDeviceDetail("","",macno, (String) SharedPreferencesUtils.getParam(BatteryDetailActivity.this,"token",""));
    }

    @OnClick({R.id.tv_submit,R.id.rl_fanhui_left})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.tv_submit:
                orderType = 0;
                mPresenter.submitOrder(macno,"","0",String.valueOf(orderType),"","", (String) SharedPreferencesUtils.getParam(BatteryDetailActivity.this,"token",""));
                break;
            case R.id.rl_fanhui_left:
                finish();
                break;
        }
    }

    @Override
    public void onGetDeviceDetailSuccess(BaseModel<DeviceDetailBean> data) {


        DeviceDetailBean.AppointmentBean appointmentBean = data.getData().getAppointment();
        if(appointmentBean != null && appointmentBean.getMy_order() != null){
            ll_order_battery.setVisibility(View.VISIBLE);
            ll_batterys.setVisibility(View.GONE);

            tv_title01.setText("您已预约"+appointmentBean.getMy_order().getStart_box()+"号电池");
            tv_title01.setText("您已预约"+appointmentBean.getMy_order().getStart_box()+"号电池");
            tv_msg.setText("请于"+appointmentBean.getAppointment_minute()+"分钟内到此门店更换，过时将自动取消预约\\n本月剩余次数："+appointmentBean.getAppointment_minute()+"次");

        }else{
            ll_order_battery.setVisibility(View.GONE);
            rv_data.setVisibility(View.VISIBLE);

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


        if(data != null && data.getData() != null && data.getData().getDevice_goods() != null){

        }
    }

    @Override
    public void onOrderSuccess(BaseModel<OrderResultBean> data) {
        if(data != null && data.getData() != null) {
            if(orderType == 0){
                Intent intent = new Intent(BatteryDetailActivity.this, OperateStatuActivity.class);
                intent.putExtra("orderId", data.getData().getOrder_id());
                startActivity(intent);
                finish();
            }else{
                ToastUtil.makeText(BatteryDetailActivity.this,"预约成功");
                ll_order_battery.setVisibility(View.VISIBLE);
                ll_batterys.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public void onStartSuccess(BaseModel<StartResultBean> data) {

    }

    @Override
    public void onGetDataFail() {

    }
}
