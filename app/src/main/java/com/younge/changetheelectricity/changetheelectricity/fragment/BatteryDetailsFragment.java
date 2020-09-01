package com.younge.changetheelectricity.changetheelectricity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseFragment;
import com.younge.changetheelectricity.callback.OnItemBtnClickCallBack;
import com.younge.changetheelectricity.changetheelectricity.Bean.OrderResultBean;
import com.younge.changetheelectricity.changetheelectricity.Bean.StartResultBean;
import com.younge.changetheelectricity.changetheelectricity.adapter.BatteryDetailsAdapter;
import com.younge.changetheelectricity.main.bean.DeviceDetailBean;
import com.younge.changetheelectricity.main.presenter.DeviceDetailPresenter;
import com.younge.changetheelectricity.main.view.DeviceDetailView;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;
import com.younge.changetheelectricity.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BatteryDetailsFragment extends MyBaseFragment<DeviceDetailPresenter> implements DeviceDetailView {

    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.ll_order_battery)
    LinearLayout ll_order_battery;

    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.tv_battery_account)
    TextView tv_battery_account;

    @BindView(R.id.tv_title01)
    TextView tv_title01;
    @BindView(R.id.tv_msg)
    TextView tv_msg;

    private BatteryDetailsAdapter mAdapter;

    private String macno;


    private List<DeviceDetailBean.DeviceGoodsBean> allList = new ArrayList<>();

    private Unbinder unbinder;
    public BatteryDetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_battery_details, null);
        unbinder = ButterKnife.bind(this, v);
        mPresenter = createPresenter();
        initList();
        return v;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser && getContext() != null){
            macno = (String) SharedPreferencesUtils.getParam(getContext(),"presentMacno","");
            mPresenter.getDeviceDetail("","",macno, (String) SharedPreferencesUtils.getParam(getContext(),"token",""));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getContext() != null){
            macno = (String) SharedPreferencesUtils.getParam(getContext(),"presentMacno","");
            mPresenter.getDeviceDetail("","",macno, (String) SharedPreferencesUtils.getParam(getContext(),"token",""));
        }
    }

    private void initList(){

        allList.clear();
        mAdapter = new BatteryDetailsAdapter(getContext(), allList, R.layout.item_battery_details, new OnItemBtnClickCallBack() {
            @Override
            public void OnItemBtnclick(int position, int btn) {

                DeviceDetailBean.DeviceGoodsBean deviceGoodsBean = mAdapter.getItem(position);
                mPresenter.submitOrder(macno,String.valueOf(deviceGoodsBean.getDevice_box()),"0","1","","", (String) SharedPreferencesUtils.getParam(getContext(),"token",""));
            }
        });
        rv_data.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_data.setAdapter(mAdapter);

        /*mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {
                ToastUtil.makeText(getContext(),"预约成功");
                ll_order_battery.setVisibility(View.VISIBLE);
                rv_data.setVisibility(View.GONE);
            }
        });*/
    }

/*    public void getBatteryDetailData(String macno){
       this.macno = macno;
        mPresenter.getDeviceDetail("","",macno, (String) SharedPreferencesUtils.getParam(getContext(),"token",""));
    }*/

    @Override
    protected DeviceDetailPresenter createPresenter() {
        return new DeviceDetailPresenter(this);
    }

    @Override
    public void onGetDeviceDetailSuccess(BaseModel<DeviceDetailBean> data) {

        if(data != null && data.getData() != null && data.getData().getDevice_goods() != null){

            DeviceDetailBean.AppointmentBean appointmentBean = data.getData().getAppointment();
            if(appointmentBean != null && appointmentBean.getMy_order() != null){
                ll_order_battery.setVisibility(View.VISIBLE);
                rv_data.setVisibility(View.GONE);

                tv_title01.setText("您已预约"+appointmentBean.getMy_order().getStart_box()+"号电池");
                tv_msg.setText("请于"+appointmentBean.getAppointment_minute()+"分钟内到此门店更换，过时将自动取消预约\\n本月剩余次数："+appointmentBean.getAppointment_minute()+"次");

            }else{
                ll_order_battery.setVisibility(View.GONE);
                rv_data.setVisibility(View.VISIBLE);
                tv_battery_account.setText("电池数量："+ data.getData().getBox());
                tv_num.setText("电柜编号："+macno);

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

    }

    @Override
    public void onOrderSuccess(BaseModel<OrderResultBean> data) {
        ToastUtil.makeText(getContext(),"预约成功");
        ll_order_battery.setVisibility(View.VISIBLE);
        rv_data.setVisibility(View.GONE);
    }

    @Override
    public void onStartSuccess(BaseModel<StartResultBean> data) {

    }

    @Override
    public void onGetDataFail() {
        ToastUtil.makeText(getContext(),"连接错误，请稍后重试");
    }

    @Override
    public void showError(String msg) {
        super.showError(msg);
        ToastUtil.makeText(getContext(),msg);
    }
}
