package com.younge.changetheelectricity.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseActivity;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.base.MyBaseFragment;
import com.younge.changetheelectricity.mine.bean.PageInfoBean;
import com.younge.changetheelectricity.mine.bean.UserInfoBean;
import com.younge.changetheelectricity.mine.presenter.MinePresenter;
import com.younge.changetheelectricity.mine.view.MineView;
import com.younge.changetheelectricity.util.JsonUtil;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyWalletActivity extends MyBaseActivity<MinePresenter> implements MineView {

    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.tv_money)
    TextView tv_money;

    String money;
    private UserInfoBean.UserinfoBean userinfoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    protected int getContentViewId(Bundle savedInstanceState) {
        return R.layout.activity_my_wallet;
    }

    @Override
    protected void init() {
        tv_center_title.setText("我的钱包");
    }

    @Override
    protected void initGetData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mPresenter != null){
            mPresenter.getPersonalInfo((String) SharedPreferencesUtils.getParam(MyWalletActivity.this,"token",""));
        }
    }

    @Override
    protected void widgetListener() {

    }

    @OnClick({R.id.rl_fanhui_left,R.id.ll_wallet,R.id.ll_detail,R.id.tv_to_recharge})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.rl_fanhui_left:
                finish();
                break;
            case R.id.ll_wallet:
                startActivity(new Intent(MyWalletActivity.this, MyDepositActivity.class));
                break;
            case R.id.ll_detail:
                startActivity(new Intent(MyWalletActivity.this, DepositHistoryActivity.class));
                break;
            case R.id.tv_to_recharge:
                startActivity(new Intent(MyWalletActivity.this, RechargeCenterActivity.class));
                break;
        }
    }

    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter(this);
    }

    @Override
    public void onGetDataSuccess(BaseModel<UserInfoBean> data) {

        userinfoBean = data.getData().getUserinfo();

        String userInfoDetail = JsonUtil.parserObjectToGson(userinfoBean);
        SharedPreferencesUtils.setParam(this,"userInfoDetail",userInfoDetail);

        tv_money.setText(userinfoBean.getMoney());
    }

    @Override
    public void onGetAboutUsSuccess(BaseModel<PageInfoBean> data) {

    }

    @Override
    public void onGetDataFail() {

    }
}
