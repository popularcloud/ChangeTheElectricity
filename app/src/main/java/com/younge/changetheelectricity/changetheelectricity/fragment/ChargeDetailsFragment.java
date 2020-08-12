package com.younge.changetheelectricity.changetheelectricity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseFragment;
import com.younge.changetheelectricity.changetheelectricity.Bean.OrderResultBean;
import com.younge.changetheelectricity.changetheelectricity.Bean.StartResultBean;
import com.younge.changetheelectricity.changetheelectricity.adapter.MainChargeDetailsAdapter;
import com.younge.changetheelectricity.main.bean.DeviceDetailBean;
import com.younge.changetheelectricity.main.presenter.DeviceDetailPresenter;
import com.younge.changetheelectricity.main.view.DeviceDetailView;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;

import org.byteam.superadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ChargeDetailsFragment extends MyBaseFragment<DeviceDetailPresenter> implements DeviceDetailView {

    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.ll_data_title)
    LinearLayout ll_data_title;
    private MainChargeDetailsAdapter mAdapter;

    private String macno;

    private List<DeviceDetailBean.DeviceGoodsBean> allList = new ArrayList<>();

    private Unbinder unbinder;
    public ChargeDetailsFragment() {
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

        ll_data_title.setVisibility(View.GONE);
        initList();
        return v;
    }

    private void initList(){

        allList.clear();

        mAdapter = new MainChargeDetailsAdapter(getContext(),allList,R.layout.item_charge_battery_details);
        rv_data.setLayoutManager(new GridLayoutManager(getContext(),2));
        rv_data.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {

            }
        });
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser && getContext() != null){
            mPresenter.getDeviceDetail("","",macno, (String) SharedPreferencesUtils.getParam(getContext(),"token",""));
        }
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

            List<DeviceDetailBean.DeviceGoodsBean> deviceGoodsBeans = data.getData().getDevice_goods();
            allList.clear();
            for(int i = 0;i < deviceGoodsBeans.size(); i ++){
                if(deviceGoodsBeans.get(i).getGoods_type() == 1){     //0为电池  1为充电口
                    allList.add(deviceGoodsBeans.get(i));
                }
            }
            mAdapter.replaceAll(allList);
        }
    }

    @Override
    public void onOrderSuccess(BaseModel<OrderResultBean> data) {

    }

    @Override
    public void onStartSuccess(BaseModel<StartResultBean> data) {

    }

    @Override
    public void onGetDataFail() {

    }
}
