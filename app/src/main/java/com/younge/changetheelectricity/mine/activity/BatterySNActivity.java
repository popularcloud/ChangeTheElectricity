package com.younge.changetheelectricity.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
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

public class BatterySNActivity extends BaseActivity {

    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.et_sn)
    EditText et_sn;

    private BatteryDetailsAdapter mAdapter;

    private List<BatteryDetailsBean> allList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_sn);
        ButterKnife.bind(this);

        tv_center_title.setText("绑定电池");
    }

    @OnClick({R.id.rl_fanhui_left,R.id.tv_submit})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.rl_fanhui_left:
                finish();
                break;
            case R.id.tv_submit:
                String sn = et_sn.getText().toString().trim();
                if(TextUtils.isEmpty(sn)){
                    ToastUtil.makeText(BatterySNActivity.this,"请输入电池sn码");
                    return;
                }

                Intent intent = new Intent(BatterySNActivity.this,BindBatteryActivity.class);
                intent.putExtra("sn",sn);
                startActivity(intent);

                break;
        }
    }
}
