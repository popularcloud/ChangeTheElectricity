package com.younge.changetheelectricity.changetheelectricity.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseActivity;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.changetheelectricity.Bean.ChargeStatusBean;
import com.younge.changetheelectricity.changetheelectricity.presenter.ChargeStatusPresenter;
import com.younge.changetheelectricity.changetheelectricity.view.ChargeStatusView;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;
import com.younge.changetheelectricity.util.ToastUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CharegeStatuActivity extends MyBaseActivity<ChargeStatusPresenter> implements ChargeStatusView {

    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.iv_header)
    ImageView iv_header;
    @BindView(R.id.tv_msg)
    TextView tv_msg;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    @BindView(R.id.tv_cancel)
    TextView tv_cancel;

    private String orderId;
    private String boxId;

    @Override
    protected ChargeStatusPresenter createPresenter() {
        return new ChargeStatusPresenter(this);
    }

    @Override
    protected int getContentViewId(Bundle savedInstanceState) {
        return R.layout.activity_charge_status;
    }

    @Override
    protected void init() {
        tv_center_title.setText("充电");


        orderId = getIntent().getStringExtra("orderId");
        boxId = getIntent().getStringExtra("boxId");


        tv_msg.setText(boxId+"号插座已通电，请插入充电");




        Observable.interval(5, TimeUnit.SECONDS, Schedulers.trampoline())
                .take(100)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onSubscribe");


                    }

                    @Override
                    public void onNext(Long value) {
                        System.out.println("onNext:" + value);

                        mPresenter.getOrderStatus(orderId, (String) SharedPreferencesUtils.getParam(CharegeStatuActivity.this,"token",""));
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });

    }

    @Override
    protected void initGetData() {

    }

    @Override
    protected void widgetListener() {

    }


    @OnClick({R.id.rl_fanhui_left})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.rl_fanhui_left:
                finish();
                break;
        }
    }

    @Override
    public void onGetStatusSuccess(BaseModel<ChargeStatusBean> data) {

        ToastUtil.makeText(this,"获取状态成功！");
    }

    @Override
    public void onGetDataFail() {

    }
}
