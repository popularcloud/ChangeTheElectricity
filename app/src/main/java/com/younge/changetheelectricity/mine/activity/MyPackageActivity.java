package com.younge.changetheelectricity.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseActivity;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;
import com.younge.changetheelectricity.changetheelectricity.adapter.ConfirmOrderAdapter;

import org.byteam.superadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyPackageActivity extends BaseActivity {

    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
    private ConfirmOrderAdapter mAdapter;

    private List<BatteryDetailsBean> allList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_package);
        ButterKnife.bind(this);

        tv_center_title.setText("我的套餐");

        tv_right.setVisibility(View.VISIBLE);
        tv_right.setText("使用记录");
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyPackageActivity.this, UseHistoryActivity.class));
            }
        });
        initList();
    }

    private void initList(){

        allList.clear();

        BatteryDetailsBean listBean1 = new BatteryDetailsBean();
        BatteryDetailsBean listBean2 = new BatteryDetailsBean();
        allList.add(listBean1);
        allList.add(listBean2);


        mAdapter = new ConfirmOrderAdapter(this,allList,R.layout.item_my_package);
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        rv_data.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {

            }
        });
    }

    @OnClick({R.id.rl_fanhui_left})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.rl_fanhui_left:
                finish();
                break;
        }
    }
}
