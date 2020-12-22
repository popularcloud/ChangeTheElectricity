package com.younge.changetheelectricity.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseActivity;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BatterySNActivity extends MyBaseActivity<GetBetteryInfoPresenter> implements GetBatteryInfoView {

    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.et_sn)
    EditText et_sn;

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

        tv_center_title.setText("绑定电池");
    }

    @Override
    protected int getContentViewId(Bundle savedInstanceState) {
        return R.layout.activity_battery_sn;
    }

    @Override
    protected void init() {

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
                sn = et_sn.getText().toString().trim();
                if(TextUtils.isEmpty(sn)){
                    ToastUtil.makeText(BatterySNActivity.this,"请输入电池sn码");
                    return;
                }
                

                mPresenter.getBatteryInfo(sn, (String) SharedPreferencesUtils.getParam(BatterySNActivity.this,"token",""));

                break;
        }
    }

    @Override
    public void onGetBatteryInfoSuccess(BaseModel<BatteryInfoBean> data) {

        Intent intent = new Intent(BatterySNActivity.this,BindBatteryActivity.class);
        intent.putExtra("sn",sn);
        startActivity(intent);
        finish();
    }

    @Override
    public void onAddBatterySuccess(BaseModel data) {

    }

    @Override
    public void onAddBatteryFail() {

    }

    @Override
    public void onGetDataFail() {

    }
}
