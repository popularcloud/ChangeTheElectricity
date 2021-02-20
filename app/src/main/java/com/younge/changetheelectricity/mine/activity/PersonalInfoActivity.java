package com.younge.changetheelectricity.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseActivity;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.mine.bean.MyBatteryBean;
import com.younge.changetheelectricity.mine.bean.MyCarBean;
import com.younge.changetheelectricity.mine.bean.UserInfoBean;
import com.younge.changetheelectricity.mine.presenter.PersonalInfoPresenter;
import com.younge.changetheelectricity.mine.view.PersonalInfoView;
import com.younge.changetheelectricity.util.ImageLoaderUtil;
import com.younge.changetheelectricity.util.JsonUtil;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalInfoActivity extends MyBaseActivity<PersonalInfoPresenter> implements PersonalInfoView {

    @BindView(R.id.tv_center_title)
    TextView tv_center_title;

    @BindView(R.id.img_head)
    ImageView img_head;
    @BindView(R.id.txt_name)
    TextView txt_name;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_status)
    TextView tv_status;

    @BindView(R.id.ll_myCar)
    LinearLayout ll_myCar;
    @BindView(R.id.ll_my_battery)
    LinearLayout ll_my_battery;

    @BindView(R.id.tv_car_num)
    TextView tv_car_num;
    @BindView(R.id.tv_carName)
    TextView tv_carName;
    @BindView(R.id.tv_car_no)
    TextView tv_car_no;

    @BindView(R.id.tv_battery_type)
    TextView tv_battery_type;
    @BindView(R.id.tv_battery_num)
    TextView tv_battery_num;
    @BindView(R.id.tv_battery_sn)
    TextView tv_battery_sn;
    @BindView(R.id.tv_battery_has)
    TextView tv_battery_has;

    @Override
    protected PersonalInfoPresenter createPresenter() {
        return new PersonalInfoPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tv_center_title.setText("个人信息");

         String userInfoDetailStr = (String) SharedPreferencesUtils.getParam(this,"userInfoDetail","");

        UserInfoBean.UserinfoBean userInfoDetail = JsonUtil.parserGsonToObject(userInfoDetailStr, UserInfoBean.UserinfoBean.class);

        if(userInfoDetail != null){
            txt_name.setText(userInfoDetail.getUsername());
            tv_phone.setText(userInfoDetail.getMobile());
            tv_status.setText(userInfoDetail.getVerification()==1?"已认证":"未认证");
            ImageLoaderUtil.getInstance().displayFromNetDCircularT(this,userInfoDetail.getAvatar(),img_head,R.mipmap.default_portrait_100);

        }

    }

    @Override
    protected int getContentViewId(Bundle savedInstanceState) {
        return R.layout.activity_personal_info;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initGetData() {

        mPresenter.getMyCarList(String.valueOf(1), String.valueOf(SharedPreferencesUtils.getParam(PersonalInfoActivity.this,"token","")));
        mPresenter.getMyBattery("1",(String) SharedPreferencesUtils.getParam(PersonalInfoActivity.this,"token",""));
    }

    @Override
    protected void widgetListener() {

    }

    @OnClick({R.id.rl_fanhui_left,R.id.ll_myCar,R.id.ll_my_battery})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.rl_fanhui_left:
                finish();
                break;
            case R.id.ll_myCar:
                startActivity(new Intent(this, MyCarActivity.class));
                break;
            case R.id.ll_my_battery:
                startActivity(new Intent(this, MyBatteryActivity.class));
                break;
        }
    }

    @Override
    public void onGetCarSuccess(BaseModel<MyCarBean> data) {
        if(data != null && data.getData().getList().size() > 0){
            ll_myCar.setVisibility(View.VISIBLE);

            tv_car_num.setText("车架编号："+data.getData().getList().get(0).getCarvin());
            tv_carName.setText("车辆品名："+data.getData().getList().get(0).getSerial());
            tv_car_no.setText("车辆牌号："+data.getData().getList().get(0).getCarno());

        }else{
            ll_myCar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onGetBatterySuccess(BaseModel<MyBatteryBean> data) {
        if(data != null && data.getData().getList().size() > 0){
            ll_my_battery.setVisibility(View.VISIBLE);

            tv_battery_type.setText("电池型号："+data.getData().getList().get(0).getSerial());
            tv_battery_num.setText("电池编码："+data.getData().getList().get(0).getNo());
            tv_battery_sn.setText("电池SN码："+data.getData().getList().get(0).getSn());
            tv_battery_has.setText("电池电量："+data.getData().getList().get(0).getBattery()+"%");
        }else{
            ll_my_battery.setVisibility(View.GONE);
        }
    }

    @Override
    public void onGetDataFail() {

    }
}
