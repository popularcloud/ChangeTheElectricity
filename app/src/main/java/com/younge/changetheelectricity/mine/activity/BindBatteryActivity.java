package com.younge.changetheelectricity.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;
import com.younge.changetheelectricity.changetheelectricity.adapter.BatteryDetailsAdapter;
import com.younge.changetheelectricity.main.bean.BatteryInfoBean;
import com.younge.changetheelectricity.mine.presenter.GetBetteryInfoPresenter;
import com.younge.changetheelectricity.mine.view.GetBatteryInfoView;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;
import com.younge.changetheelectricity.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BindBatteryActivity extends MyBaseActivity<GetBetteryInfoPresenter> implements GetBatteryInfoView {

    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.tv_submit)
    TextView tv_submit;

    @BindView(R.id.tv_remain)
    TextView tv_remain;
    @BindView(R.id.tv_type)
    TextView tv_type;
    @BindView(R.id.tv_sn_num)
    TextView tv_sn_num;
    @BindView(R.id.tv_num)
    TextView tv_num;

    private BatteryDetailsAdapter mAdapter;

    private List<BatteryDetailsBean> allList = new ArrayList<>();

    private String sn;

    @Override
    protected GetBetteryInfoPresenter createPresenter() {
        return new GetBetteryInfoPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewId(Bundle savedInstanceState) {
        return R.layout.activity_bind_battery;
    }

    @Override
    protected void init() {

        tv_center_title.setText("绑定电池");

        sn = getIntent().getStringExtra("sn");

        mPresenter.getBatteryInfo(sn, (String) SharedPreferencesUtils.getParam(BindBatteryActivity.this,"token",""));
    }

    @Override
    protected void initGetData() {

    }

    @Override
    protected void widgetListener() {

    }

    @OnClick({R.id.rl_fanhui_left,R.id.tv_submit})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.rl_fanhui_left:
                finish();
                break;
            case R.id.tv_submit:
                mPresenter.addBattery(sn,(String) SharedPreferencesUtils.getParam(BindBatteryActivity.this,"token",""));
                //startActivity(new Intent(BindBatteryActivity.this, MyBatteryActivity.class));
                break;
        }
    }

    @Override
    public void onGetBatteryInfoSuccess(BaseModel<BatteryInfoBean> data) {

        if(data != null && data.getData() != null){
            tv_remain.setText(String.valueOf(data.getData().getBattery()));
            tv_sn_num.setText(data.getData().getSn());
            tv_num.setText(data.getData().getSerial());
            tv_type.setText(data.getData().getVoltage());
        }else{
            ToastUtil.makeText(BindBatteryActivity.this,"电池SN码不存在");
            finish();
        }
    }

    @Override
    public void onAddBatterySuccess(BaseModel data) {
        ToastUtil.makeText(BindBatteryActivity.this,"绑定成功！");
        finish();
    }

    @Override
    public void onAddBatteryFail() {
        ToastUtil.makeText(BindBatteryActivity.this,"绑定失败！请稍后重试");
    }

    @Override
    public void onGetDataFail() {
        ToastUtil.makeText(BindBatteryActivity.this,"电池SN码不存在");
        finish();
    }


    @Override
    public void showError(String msg) {
        super.showError(msg);
        ToastUtil.makeText(BindBatteryActivity.this,msg);
    }
}
