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
            mPresenter.getDeviceDetail("","",macno, (String) SharedPreferencesUtils.getParam(getContext(),"token",""));
        }
    }

    private void initList(){

        allList.clear();
        mAdapter = new BatteryDetailsAdapter(getContext(),allList,R.layout.item_battery_details);
        rv_data.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_data.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {
                ToastUtil.makeText(getContext(),"预约成功");
                ll_order_battery.setVisibility(View.VISIBLE);
                rv_data.setVisibility(View.GONE);
            }
        });
    }

    public void getBatteryDetailData(String macno){
       this.macno = macno;
        mPresenter.getDeviceDetail("","",macno, (String) SharedPreferencesUtils.getParam(getContext(),"token",""));
    }

    @Override
    protected DeviceDetailPresenter createPresenter() {
        return new DeviceDetailPresenter(this);
    }

    @Override
    public void onGetDeviceDetailSuccess(BaseModel<DeviceDetailBean> data) {

        if(data != null && data.getData() != null && data.getData().getDevice_goods() != null){
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

    @Override
    public void onGetDataFail() {

    }
}
