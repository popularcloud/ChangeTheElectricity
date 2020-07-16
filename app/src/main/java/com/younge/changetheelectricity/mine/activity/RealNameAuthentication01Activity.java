package com.younge.changetheelectricity.mine.activity;

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

public class RealNameAuthentication01Activity extends BaseActivity {

    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    private BatteryDetailsAdapter mAdapter;

    private List<BatteryDetailsBean> allList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_name_authentication01);
        ButterKnife.bind(this);
        tv_center_title.setText("实名认证");
    }

    @OnClick({R.id.rl_fanhui_left,R.id.tv_submit})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.rl_fanhui_left:
                finish();
                break;
            case R.id.tv_submit:
                ToastUtil.makeText(this,"认证成功！");
                finish();
                break;
        }
    }
}
