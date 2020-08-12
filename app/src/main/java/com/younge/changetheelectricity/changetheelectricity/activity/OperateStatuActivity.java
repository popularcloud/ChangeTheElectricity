package com.younge.changetheelectricity.changetheelectricity.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.changetheelectricity.Bean.ChargeStatusBean;
import com.younge.changetheelectricity.changetheelectricity.presenter.ChargeStatusPresenter;
import com.younge.changetheelectricity.changetheelectricity.view.ChargeStatusView;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class OperateStatuActivity extends MyBaseActivity<ChargeStatusPresenter> implements ChargeStatusView {

    @BindView(R.id.tv_center_title)
    TextView tv_center_title;

    @BindView(R.id.tv_msg)
    TextView tv_msg;
    @BindView(R.id.tv_cancel)
    TextView tv_cancel;

    private String orderId;

    private Disposable mDisposable;

    @Override
    protected ChargeStatusPresenter createPresenter() {
        return new ChargeStatusPresenter(this);
    }

    @Override
    protected int getContentViewId(Bundle savedInstanceState) {
        return R.layout.activity_operate_status;
    }

    @Override
    protected void init() {
        tv_center_title.setText("换电");

        orderId = getIntent().getStringExtra("orderId");


        tv_msg.setText("仓门打开中，请耐心等待！");


        interval(2000, new CharegeStatuActivity.RxAction() {
            @Override
            public void action(long number) {
                mPresenter.getOrderStatus(orderId, (String) SharedPreferencesUtils.getParam(OperateStatuActivity.this,"token",""));
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

    public void interval(long milliSeconds, final CharegeStatuActivity.RxAction rxAction) {
        Observable.interval(milliSeconds, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        mDisposable = disposable;
                    }

                    @Override
                    public void onNext(@NonNull Long number) {
                        if (rxAction != null) {
                            rxAction.action(number);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 取消订阅
     */
    public void cancel() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public void onGetStatusSuccess(BaseModel<ChargeStatusBean> data) {
        if(data != null && data.getData() != null){
            ChargeStatusBean chargeStatusBean = data.getData();
         /*
            data.result.status状态
            0正在响应/仓门打开中，请耐心等待(定时器轮询)
            1换电完成, 3秒后转到主界面
            9换电取消
            2仓门打门成功，请放入旧电池/请拿走新电池，并关好仓门(定时器轮询)
            3仓门打开失败【act==2(重试调start)(结束调cancel)】【act==1(重试调start)(退回旧电池调start【act=3】)】
            4请将您的旧电池放入柜子里，连接好充电线，并关好仓门
            5电池正在验证中，请耐心等待(定时器轮询)
            6未检测到电池，请检查是否连接好充电线(重试调start)(结束调cancel)
            7电池验证失败，请取走您的旧电池，并关好仓门(确认调start)
            8电池验证通过，归还成功(倒数3秒调start)
            */

            switch (chargeStatusBean.getResult().getStatus()){
                case 0:
                    tv_msg.setText(chargeStatusBean.getResult().getMessage());
                    break;
                case 1: //换电完成
                    tv_msg.setText(chargeStatusBean.getResult().getMessage());
                    cancel();
                    finish();
                    break;
                case 2:
                    tv_msg.setText(chargeStatusBean.getResult().getMessage());
                    tv_cancel.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    tv_msg.setText(chargeStatusBean.getResult().getMessage());
                    cancel();
                    break;
                case 4:
                    tv_msg.setText(chargeStatusBean.getResult().getMessage());
                    tv_cancel.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    tv_msg.setText(chargeStatusBean.getResult().getMessage());
                    break;
                case 6:
                    tv_msg.setText(chargeStatusBean.getResult().getMessage());
                    break;
                case 7:
                    tv_msg.setText(chargeStatusBean.getResult().getMessage());
                    break;
                case 8:
                    tv_msg.setText(chargeStatusBean.getResult().getMessage());
                    break;
                case 9:
                    tv_msg.setText(chargeStatusBean.getResult().getMessage());
                    cancel();
                    break;

            }
        }
    }

    @Override
    public void onGetDataFail() {

    }
}
