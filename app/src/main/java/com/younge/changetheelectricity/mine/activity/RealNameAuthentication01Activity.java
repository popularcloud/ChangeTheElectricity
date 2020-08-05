package com.younge.changetheelectricity.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;
import com.younge.changetheelectricity.changetheelectricity.adapter.BatteryDetailsAdapter;
import com.younge.changetheelectricity.mine.bean.ReturnImgUrlBean;
import com.younge.changetheelectricity.mine.presenter.RealNameAuthenticationPresenter;
import com.younge.changetheelectricity.mine.view.RealNameAuthenticationCenterView;
import com.younge.changetheelectricity.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RealNameAuthentication01Activity extends MyBaseActivity<RealNameAuthenticationPresenter> implements RealNameAuthenticationCenterView {

    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_idCard)
    EditText et_idCard;
    @BindView(R.id.iv_card_positive)
    ImageView iv_card_positive;
    @BindView(R.id.iv_card_back)
    ImageView iv_card_back;
    @BindView(R.id.iv_card_hold)
    ImageView iv_card_hold;
    private BatteryDetailsAdapter mAdapter;

    private List<BatteryDetailsBean> allList = new ArrayList<>();

    @Override
    protected RealNameAuthenticationPresenter createPresenter() {
        return new RealNameAuthenticationPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_name_authentication01);
        ButterKnife.bind(this);
        tv_center_title.setText("实名认证");
    }

    @Override
    protected int getContentViewId(Bundle savedInstanceState) {
        return R.layout.activity_real_name_authentication01;
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
                ToastUtil.makeText(this,"认证成功！");
                finish();
                break;
        }
    }

    @Override
    public void onSubmitSuccess(BaseModel<Object> data) {

    }

    @Override
    public void onUploadPicSuccess(BaseModel<ReturnImgUrlBean> data) {

    }

    @Override
    public void onGetDataFail() {

    }
}
