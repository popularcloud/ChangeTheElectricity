package com.younge.changetheelectricity.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseActivity;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;
import com.younge.changetheelectricity.changetheelectricity.adapter.BatteryDetailsAdapter;
import com.younge.changetheelectricity.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyDepositActivity extends BaseActivity {

    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    @BindView(R.id.tv_right)
    TextView tv_right;

    private BatteryDetailsAdapter mAdapter;

    private List<BatteryDetailsBean> allList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_deposit);
        ButterKnife.bind(this);

        tv_center_title.setText("我的押金");
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setText("明细");
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyDepositActivity.this, DepositHistoryActivity.class));
            }
        });
    }

    @OnClick({R.id.rl_fanhui_left,R.id.tv_submit})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.rl_fanhui_left:
                finish();
                break;
            case R.id.tv_submit:
                ToastUtil.makeText(this,"敬请期待！");
                //startActivity(new Intent(MyDepositActivity.this, MyCarActivity.class));
                break;
        }
    }
}
