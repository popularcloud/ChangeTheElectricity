package com.younge.changetheelectricity.changetheelectricity.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.changetheelectricity.Bean.ChargeStatusBean;
import com.younge.changetheelectricity.changetheelectricity.Bean.StartResultBean;
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


/**
 * 冲电状态
 */

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

    private Disposable mDisposable;


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


        interval(2000, new RxAction() {
            @Override
            public void action(long number) {
                mPresenter.getOrderStatus(orderId, (String) SharedPreferencesUtils.getParam(CharegeStatuActivity.this,"token",""));
            }
        });

    }

    @Override
    protected void initGetData() {

    }

    @Override
    protected void widgetListener() {

    }

    public void interval(long milliSeconds, final RxAction rxAction) {
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

    public interface RxAction {
        /**
         * 让调用者指定指定动作
         *
         * @param number
         */
        void action(long number);
    }


    @OnClick({R.id.rl_fanhui_left,R.id.tv_cancel,R.id.tv_submit})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.rl_fanhui_left:
                finish();
                break;
            case R.id.tv_cancel:
                mPresenter.cancel(orderId,(String) SharedPreferencesUtils.getParam(CharegeStatuActivity.this,"token",""));
                break;
            case R.id.tv_submit:
                mPresenter.start("",orderId,(String) SharedPreferencesUtils.getParam(CharegeStatuActivity.this,"token",""));
                break;
        }
    }

    @Override
    public void onGetStatusSuccess(BaseModel<ChargeStatusBean> data) {

       // ToastUtil.makeText(this,"获取状态成功！");
        if(data != null && data.getData() != null){
            ChargeStatusBean chargeStatusBean = data.getData();
           /*
            data.result.status状态
            0正在响应(定时器轮询)
            1充电完成
            9充电取消
            2*号插座已通电，请插入充电(取消调cancel)
            3通电失败(重试调start)
            4正在充电(取消调cancel)
            5关闭失败(重试调cancel)
            */

            tv_submit.setVisibility(View.GONE);
            tv_cancel.setVisibility(View.GONE);

            tv_msg.setText(chargeStatusBean.getResult().getMessage());
            switch (chargeStatusBean.getResult().getStatus()){
                case 0:
                    iv_header.setImageResource(R.mipmap.ic_cdz);
                    break;
                case 1: //充电完成
                    iv_header.setImageResource(R.mipmap.ic_cdz);
                    tv_msg.setText(chargeStatusBean.getResult().getMessage()+" 三秒钟后将回到主页面");
                    cancel();
                    exitDelay();
                    break;
                case 2:
                    iv_header.setImageResource(R.mipmap.ic_cdz);
                    tv_msg.setText(boxId+"号插座已通电，请插入充电("+chargeStatusBean.getResult().getMessage()+")");
                    tv_cancel.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    iv_header.setImageResource(R.mipmap.ic_cdz);
                    tv_msg.setText(chargeStatusBean.getResult().getMessage());
                    tv_submit.setText("重试");
                    tv_submit.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    tv_msg.setText(chargeStatusBean.getResult().getMessage());
                    tv_cancel.setVisibility(View.VISIBLE);
                    tv_cancel.setText("取消");
                    break;
                case 5:
                    tv_msg.setText(chargeStatusBean.getResult().getMessage());
                    tv_cancel.setVisibility(View.VISIBLE);
                    tv_cancel.setText("重试");
                    break;
                case 9:
                    tv_msg.setText(chargeStatusBean.getResult().getMessage()+"三秒钟后将回到主页面");
                    iv_header.setImageResource(R.mipmap.ic_wc);
                    exitDelay();
                    cancel();
                    break;
                case 12: //未启动
                    mPresenter.start("",orderId,(String) SharedPreferencesUtils.getParam(CharegeStatuActivity.this,"token",""));
                    break;

            }
        }
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            finish();
        }
    };


    private void exitDelay(){
        mHandler.sendEmptyMessageDelayed(1, 3*1000);
    }


    @Override
    public void onStartSuccess(BaseModel<StartResultBean> data) {

    }

    @Override
    public void onCancelSuccess(BaseModel<StartResultBean> data) {

    }

    @Override
    public void onGetDataFail() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDisposable != null && mDisposable.isDisposed() && !TextUtils.isEmpty(orderId)) {
            interval(2000, new CharegeStatuActivity.RxAction() {
                @Override
                public void action(long number) {
                    mPresenter.getOrderStatus(orderId, (String) SharedPreferencesUtils.getParam(CharegeStatuActivity.this,"token",""));
                }
            });
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        cancel();
    }
}
