package com.younge.changetheelectricity.changetheelectricity.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OperateStatuActivity extends BaseActivity {

    @BindView(R.id.tv_center_title)
    TextView tv_center_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operate_status);
        ButterKnife.bind(this);
        tv_center_title.setText("换电");
        initList();
    }

    private void initList(){

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
