package com.younge.changetheelectricity.changetheelectricity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseActivity;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;
import com.younge.changetheelectricity.changetheelectricity.adapter.BatteryDetailsAdapter;

import org.byteam.superadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BatteryDetailActivity extends BaseActivity {

    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    private BatteryDetailsAdapter mAdapter;

    private List<BatteryDetailsBean> allList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_detail);
        ButterKnife.bind(this);

        tv_center_title.setText("电柜详情");
        initList();
    }

    private void initList(){

        allList.clear();

        BatteryDetailsBean listBean1 = new BatteryDetailsBean();
        BatteryDetailsBean listBean2 = new BatteryDetailsBean();
        BatteryDetailsBean listBean3 = new BatteryDetailsBean();
        BatteryDetailsBean listBean4 = new BatteryDetailsBean();
        allList.add(listBean1);
        allList.add(listBean2);
        allList.add(listBean3);
        allList.add(listBean4);

        mAdapter = new BatteryDetailsAdapter(this,allList,R.layout.item_battery_details);
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        rv_data.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {

            }
        });
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
}
