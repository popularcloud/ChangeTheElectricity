package com.younge.changetheelectricity.changetheelectricity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.younge.changetheelectricity.main.MainActivity;
import com.younge.changetheelectricity.main.bean.DeviceDetailBean;
import com.younge.changetheelectricity.main.fragment.MainFragment;
import com.younge.changetheelectricity.main.presenter.DeviceDetailPresenter;
import com.younge.changetheelectricity.main.view.DeviceDetailView;
import com.younge.changetheelectricity.mine.activity.PackageListActivity;
import com.younge.changetheelectricity.util.DateUtil;
import com.younge.changetheelectricity.util.DisplayUtil;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;
import com.younge.changetheelectricity.util.ToastUtil;
import com.younge.changetheelectricity.widget.CustomDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BatteryDetailsFragment extends MyBaseFragment<DeviceDetailPresenter> implements DeviceDetailView {

    @BindView(R.id.rv_data)
    RecyclerView rv_data;

    @BindView(R.id.fl_data)
    FrameLayout fl_data;
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

    @BindView(R.id.tv_has_bg)
    TextView tv_has_bg;
    @BindView(R.id.tv_has)
    TextView tv_has;

    private BatteryDetailsAdapter mAdapter;

    private String macno;

    private CustomDialog customDialog;


    private List<DeviceDetailBean.DeviceGoodsBean> allList = new ArrayList<>();

    private Unbinder unbinder;
    private int appointmentMinute;
    private String laterTime;

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


               // laterTime = DateUtil.getPresentTimeAddSome(appointmentMinute);

                customDialog = new CustomDialog(getActivity());
                customDialog.setTitle("");
                customDialog.setMessage("请于"+laterTime+"前到达换电柜处换电");
                customDialog.setButton1Text("确定");
                customDialog.setButton2Text("取消");
                customDialog.setCanceledOnTouchOutside(true);
                customDialog.setCancelBtn(new CustomDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomDialog dialog, int id, Object object) {
                        customDialog.dismiss();
                    }
                });
                customDialog.setEnterBtn(new CustomDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomDialog dialog, int id, Object object) {
                        DeviceDetailBean.DeviceGoodsBean deviceGoodsBean = mAdapter.getItem(position);
                        mPresenter.submitOrder(macno,String.valueOf(deviceGoodsBean.getDevice_box()),"0","1","","", (String) SharedPreferencesUtils.getParam(getContext(),"token",""));
                        customDialog.dismiss();
                    }
                });
                customDialog.show();
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
                tv_battery_account.setVisibility(View.INVISIBLE);
                ll_order_battery.setVisibility(View.VISIBLE);
                fl_data.setVisibility(View.GONE);

                List<DeviceDetailBean.DeviceGoodsBean> deviceGoodsBeans = data.getData().getDevice_goods();
                for(int i = 0;i < deviceGoodsBeans.size();i++){
                    if(appointmentBean.getMy_order().getStop_box() == deviceGoodsBeans.get(i).getDevice_box() && deviceGoodsBeans.get(i).getGoods_type() == 0){

                        float battery = deviceGoodsBeans.get(i).getBattery();

                        tv_has.setText(String.valueOf(battery)+"%");
                        //计算电量背景的长度
                        float bgLength =  80 * (deviceGoodsBeans.get(i).getBattery()/100);
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) tv_has_bg.getLayoutParams();
                        layoutParams.height = DisplayUtil.dip2px(getContext(),bgLength);
                        Log.e("背景长度:",""+bgLength);
                       // tv_has_bg.setLayoutParams(layoutParams);
                    }
                }

                ((MainFragment)getParentFragment()).showOrderTime(appointmentBean.getMy_order().getStop_box()+"号电池预约成功，换电时间为"+laterTime);

                tv_title01.setText("您已预约"+appointmentBean.getMy_order().getStop_box()+"号电池");
                tv_msg.setText("请于"+appointmentBean.getAppointment_minute()+"分钟内到此门店更换，过时将自动取消预约\n本月剩余次数："+(appointmentBean.getAppointment_count()-appointmentBean.getMy_count())+"次");

            }else{
                ll_order_battery.setVisibility(View.GONE);
                fl_data.setVisibility(View.VISIBLE);
                tv_battery_account.setVisibility(View.VISIBLE);
                tv_battery_account.setText("电池数量："+ data.getData().getActive_box()+"个");
                tv_num.setText("电柜编号："+macno);

                ((MainFragment)getParentFragment()).hiddenOrderTime();


                appointmentMinute = Integer.parseInt(data.getData().getAppointment().getAppointment_minute());
                tv_msg.setText("本月剩余次数："+(appointmentBean.getAppointment_count()-appointmentBean.getMy_count())+"次");


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
        /*ll_order_battery.setVisibility(View.VISIBLE);
        rv_data.setVisibility(View.GONE);*/

        mPresenter.getDeviceDetail("","",macno, (String) SharedPreferencesUtils.getParam(getContext(),"token",""));
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


    public void getBatteryDetailData(){
        if(getContext() != null){
            macno = (String) SharedPreferencesUtils.getParam(getContext(),"presentMacno","");
            mPresenter.getDeviceDetail("","",macno, (String) SharedPreferencesUtils.getParam(getContext(),"token",""));
        }
    }
}
