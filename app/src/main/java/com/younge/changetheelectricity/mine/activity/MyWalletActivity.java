package com.younge.changetheelectricity.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyWalletActivity extends BaseActivity {

    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.tv_money)
    TextView tv_money;

    String money;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        ButterKnife.bind(this);
        tv_center_title.setText("我的钱包");

        money = getIntent().getStringExtra("money");
        tv_money.setText(money);

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
}
