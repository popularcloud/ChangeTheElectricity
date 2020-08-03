package com.younge.changetheelectricity.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.mine.adapter.RechargePackageAdapter;
import com.younge.changetheelectricity.mine.bean.MyWxBean;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.mine.bean.PayByWechatBean;
import com.younge.changetheelectricity.mine.presenter.RechargeCenterPresenter;
import com.younge.changetheelectricity.mine.view.RechargeCenterView;
import com.younge.changetheelectricity.util.PayServant;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;
import com.younge.changetheelectricity.util.ToastUtil;
import com.younge.changetheelectricity.util.callback.AliPayCallBack;
import com.younge.changetheelectricity.util.callback.OnSelectEventMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 充值中心
 */

public class RechargeCenterActivity extends MyBaseActivity<RechargeCenterPresenter> implements RechargeCenterView {

    @BindView(R.id.tv_center_title)
    TextView tvCenterTitle;
    @BindView(R.id.rv_data)
    RecyclerView rv_data;

    @BindView(R.id.ll_alipay)
    LinearLayout ll_alipay;
    @BindView(R.id.iv_alipay)
    ImageView iv_alipay;
    @BindView(R.id.ll_wechat)
    LinearLayout ll_wechat;
    @BindView(R.id.iv_wechat)
    ImageView iv_wechat;

    private Unbinder unbinder;
    private RechargePackageAdapter mAdapter;

    private List<PackageBean.ListBean> allList = new ArrayList<>();

    private int selectPosition = -1;
    private int payType = 0; //0微信 1支付宝

    @BindView(R.id.tv_submit)
    TextView tv_submit;
    private String uid;

    private String rechargeMoney;


    @Override
    protected RechargeCenterPresenter createPresenter() {
        return new RechargeCenterPresenter(this);
    }

    @Override
    protected int getContentViewId(Bundle savedInstanceState) {
        return R.layout.activity_recharge_center;
    }

    @Override
    protected void init() {
        tvCenterTitle.setText("");
        EventBus.getDefault().register(this);

        initRecycleView();
    }

    @Override
    protected void initGetData() {

    }

    @Override
    protected void widgetListener() {

    }

    private void initRecycleView() {
        mAdapter = new RechargePackageAdapter(this,allList,R.layout.item_recharge_package);
        rv_data.setLayoutManager(new GridLayoutManager(this,2));
        rv_data.setAdapter(mAdapter);

        mPresenter.getRechargePackage();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg(OnSelectEventMessage message){
        selectPosition = message.getPosition();
        //tv_total_price.setText(DateUtil.getMoney(allList.get(selectPosition).getText().getMoney()) +"元");
        rechargeMoney = allList.get(selectPosition).getText().getMoney();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.rl_fanhui_left,R.id.ll_alipay,R.id.ll_wechat,R.id.tv_submit})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.rl_fanhui_left:
                finish();
                break;
            case R.id.ll_alipay:
                payType = 1;
                iv_alipay.setImageResource(R.mipmap.ic_checked_blue);
                iv_wechat.setImageResource(R.mipmap.ic_no_checked);
                break;
            case R.id.ll_wechat:
                iv_alipay.setImageResource(R.mipmap.ic_no_checked);
                iv_wechat.setImageResource(R.mipmap.ic_checked_blue);
                payType = 0;
                break;
            case R.id.tv_submit:
                if(selectPosition == -1){
                    ToastUtil.makeText(RechargeCenterActivity.this,"请选择你需要的充值套餐");
                    return;
                }
                PackageBean.ListBean selectBean = allList.get(selectPosition);
                if(payType == 0){
                    mPresenter.rechargeByWechat(String.valueOf(selectBean.getId()), (String) SharedPreferencesUtils.getParam(RechargeCenterActivity.this,"token",""));
                }else{
                    mPresenter.rechargeByAli(String.valueOf(selectBean.getId()), (String) SharedPreferencesUtils.getParam(RechargeCenterActivity.this,"token",""));
                }
                break;
        }
    }

    @Override
    public void onGetDataSuccess(BaseModel<PackageBean> data) {
        if(data != null && data.getData() != null && data.getData().getList() != null){
            allList = data.getData().getList();
            mAdapter.replaceAll(allList);
        }
    }

    @Override
    public void onPayOrderByAliSuccess(BaseModel<Object> data) {
        if(data != null){
                        PayServant.getInstance().aliPay(String.valueOf(data.getData()), RechargeCenterActivity.this, new AliPayCallBack() {
                            @Override
                            public void OnAliPayResult(boolean success, boolean isWaiting, String msg) {
                                ToastUtil.makeText(RechargeCenterActivity.this, msg);
                                if (success) {
                                    setResult(RESULT_OK);
                                    finish();
                                }
                                finish();
                            }
                        });
        }
    }

    @Override
    public void onPayOrderByWeChatSuccess(BaseModel<PayByWechatBean> data) {
        if(data != null && data.getData() != null) {
            PayByWechatBean.PayparamsBean wx = data.getData().getPayparams();
            PayServant.getInstance().weChatPay2(
                    RechargeCenterActivity.this, wx.getAppid(),
                    wx.getPartnerid(), wx.getPrepayid(), wx.getNoncestr(),
                    wx.getTimestamp(), wx.getPackageX(), wx.getSign());
        }
    }

    @Override
    public void onGetDataFail() {

    }
}