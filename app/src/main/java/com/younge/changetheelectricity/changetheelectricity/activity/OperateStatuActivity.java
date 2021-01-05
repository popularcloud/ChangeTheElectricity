package com.younge.changetheelectricity.changetheelectricity.activity;

import android.content.Intent;
import android.net.Uri;
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
import com.younge.changetheelectricity.mine.bean.PhoneSettingBean;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;
import com.younge.changetheelectricity.widget.CustomDialog;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 换电状态
 */

public class OperateStatuActivity extends MyBaseActivity<ChargeStatusPresenter> implements ChargeStatusView {

    @BindView(R.id.tv_center_title)
    TextView tv_center_title;

    @BindView(R.id.tv_msg)
    TextView tv_msg;
    @BindView(R.id.tv_cancel)
    TextView tv_cancel;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    @BindView(R.id.iv_header)
    ImageView iv_header;
    @BindView(R.id.tv_submit_error)
    TextView tv_submit_error;

    private String orderId;

    private Disposable mDisposable;

    private String tel;


    private int act;
    private CustomDialog customDialog;
    private int status;

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

        mPresenter.getShareSetting((String) SharedPreferencesUtils.getParam(OperateStatuActivity.this,"token",""));
    }

    @Override
    protected void initGetData() {

    }

    @Override
    protected void widgetListener() {

    }

    @OnClick({R.id.rl_fanhui_left,R.id.tv_cancel,R.id.tv_submit,R.id.tv_submit_error})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.rl_fanhui_left:
                finish();
                break;
            case R.id.tv_cancel:
                String retry = "";
                if(status == 3){
                    retry = "1";
                }

                if(act == 2){
                    mPresenter.cancel(orderId,(String) SharedPreferencesUtils.getParam(OperateStatuActivity.this,"token",""));
                }else if(act == 1){
                    mPresenter.start("3",orderId,(String) SharedPreferencesUtils.getParam(OperateStatuActivity.this,"token",""),retry);
                }

                break;
            case R.id.tv_submit:
                String retry2 = "";
                if(status == 3){
                    retry2 = "1";
                }
                mPresenter.start("",orderId,(String) SharedPreferencesUtils.getParam(OperateStatuActivity.this,"token",""),retry2);
                break;
            case R.id.tv_submit_error:
                customDialog = new CustomDialog(this);
                customDialog.setTitle("请立即联系店长");
                customDialog.setMessage(tel);
                customDialog.setButton1Text("拨打");
                customDialog.setButton2Text("取消");
                customDialog.setCanceledOnTouchOutside(true);
                customDialog.setEnterBtn(new CustomDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomDialog dialog, int id, Object object) {
                        customDialog.dismiss();

                    }
                });
                customDialog.setCancelBtn(new CustomDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomDialog dialog, int id, Object object) {
                        customDialog.dismiss();
                        Uri uri = Uri.parse("tel:" + tel);
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(uri);
                        startActivity(intent);
                    }
                });
                customDialog.show();
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
            8电池验证通过，归还成功
            */

            act = data.getData().getResult().getAct();

            tv_submit.setVisibility(View.GONE);
            tv_cancel.setVisibility(View.GONE);

            tv_msg.setText(chargeStatusBean.getResult().getMessage());
            status = chargeStatusBean.getResult().getStatus();
            switch (status){
                case 0:
                    iv_header.setImageResource(R.mipmap.ic_cdz);
                    break;
                case 1: //换电完成
                    tv_msg.setText(chargeStatusBean.getResult().getMessage()+" 三秒钟后将回到主页面");
                    iv_header.setImageResource(R.mipmap.ic_wc);
                    cancel();
                    exitDelay();
                    break;
                case 2:
                    iv_header.setImageResource(R.mipmap.ic_wc);
                    break;
                case 3:
                    iv_header.setImageResource(R.mipmap.ic_jg);
                    tv_submit.setVisibility(View.VISIBLE);
                    tv_cancel.setVisibility(View.VISIBLE);
                    if(act == 2){
                        tv_submit.setText("重新开门");
                        tv_cancel.setText("结束");
                    }else{
                        tv_submit.setText("重新开门");
                        tv_cancel.setText("放弃换电,退回旧电池");
                    }
                    break;
                case 4:
                    iv_header.setImageResource(R.mipmap.ic_cdz);
                    break;
                case 5:
                    iv_header.setImageResource(R.mipmap.ic_cdz);
                    break;
                case 6:
                    iv_header.setImageResource(R.mipmap.ic_jg);
                    tv_submit.setVisibility(View.VISIBLE);
                    tv_cancel.setVisibility(View.VISIBLE);
                    tv_submit.setText("重新打开仓门");
                    tv_cancel.setText("空箱关门（我不换电了）");
                    break;
                case 7:
                    iv_header.setImageResource(R.mipmap.ic_jg);
                    tv_submit.setVisibility(View.VISIBLE);
                    tv_submit.setText("确认");
                    break;
                case 8:
                    tv_msg.setText(chargeStatusBean.getResult().getMessage());
                    iv_header.setImageResource(R.mipmap.ic_wc);
                    break;
                case 9:
                    tv_msg.setText(chargeStatusBean.getResult().getMessage()+"三秒钟后将回到主页面");
                    iv_header.setImageResource(R.mipmap.ic_wc);
                    exitDelay();
                    cancel();
                    break;
                case 10:
                    iv_header.setImageResource(R.mipmap.ic_jg);
                    tv_submit.setVisibility(View.VISIBLE);
                    tv_submit.setText("重新打开仓门");
                    break;
                case 11:
                    iv_header.setImageResource(R.mipmap.ic_wc);
                    tv_msg.setText(chargeStatusBean.getResult().getMessage()+"三秒钟后将回到主页面");
                    break;
                case 12: //未启动
                    mPresenter.start("",orderId,(String) SharedPreferencesUtils.getParam(OperateStatuActivity.this,"token",""),"");
                    break;

            }
        }
    }

    @Override
    public void onStartSuccess(BaseModel<StartResultBean> data) {

        orderId = data.getData().getOrder_id();

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
    public void onCancelSuccess(BaseModel<StartResultBean> data) {
        //finish();
    }

    @Override
    public void onGetPhoneSuccess(BaseModel<PhoneSettingBean> data) {
        if(data != null && data.getData() != null && data.getData().getTel() != null){
            tel = data.getData().getTel();
        }
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
                    mPresenter.getOrderStatus(orderId, (String) SharedPreferencesUtils.getParam(OperateStatuActivity.this,"token",""));
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
