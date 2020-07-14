package com.younge.changetheelectricity.changetheelectricity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseActivity;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;
import com.younge.changetheelectricity.changetheelectricity.adapter.BatteryDetailsAdapter;
import com.younge.changetheelectricity.changetheelectricity.adapter.ChargeDetailsAdapter;
import com.younge.changetheelectricity.changetheelectricity.adapter.ChargeDetailsTwoAdapter;

import org.byteam.superadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChargeDetailActivity extends BaseActivity {

    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.rv_data_two)
    RecyclerView rv_data_two;
    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    private ChargeDetailsAdapter mAdapter;
    private ChargeDetailsTwoAdapter mAdapterTwo;

    private List<BatteryDetailsBean> allList = new ArrayList<>();
    private List<BatteryDetailsBean> bllList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_detail);
        ButterKnife.bind(this);

        tv_center_title.setText("充电详情");
        initList();
    }

    private void initList(){

        allList.clear();

        BatteryDetailsBean listBean1 = new BatteryDetailsBean();
        BatteryDetailsBean listBean2 = new BatteryDetailsBean();
        BatteryDetailsBean listBean3 = new BatteryDetailsBean();
        allList.add(listBean1);
        allList.add(listBean2);
        allList.add(listBean3);

        mAdapter = new ChargeDetailsAdapter(this,allList,R.layout.item_charge_details);
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        rv_data.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {
               mAdapter.setSelPosition(position);
            }
        });

        bllList.clear();

        BatteryDetailsBean listBean4 = new BatteryDetailsBean();
        BatteryDetailsBean listBean5 = new BatteryDetailsBean();
        BatteryDetailsBean listBean6 = new BatteryDetailsBean();
        BatteryDetailsBean listBean7 = new BatteryDetailsBean();
        bllList.add(listBean4);
        bllList.add(listBean5);
        bllList.add(listBean6);
        bllList.add(listBean7);

        mAdapterTwo = new ChargeDetailsTwoAdapter(this,bllList,R.layout.item_charge_detail_two);
        rv_data_two.setLayoutManager(new GridLayoutManager(this,2));
        rv_data_two.setAdapter(mAdapterTwo);

        mAdapterTwo.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {
                mAdapterTwo.setSelPosition(position);
            }
        });
    }

    @OnClick({R.id.tv_submit,R.id.rl_fanhui_left})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.tv_submit:
                startActivity(new Intent(this, ConfirmOrderActivity.class));
                break;
            case R.id.rl_fanhui_left:
                finish();
                break;
        }
    }
}
