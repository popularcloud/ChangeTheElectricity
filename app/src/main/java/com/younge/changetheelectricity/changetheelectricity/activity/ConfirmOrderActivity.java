package com.younge.changetheelectricity.changetheelectricity.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.changetheelectricity.Bean.AlipayBean;
import com.younge.changetheelectricity.changetheelectricity.adapter.ConfirmOrderAdapter;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.mine.bean.PayByWechatBean;
import com.younge.changetheelectricity.mine.presenter.PayPresenter;
import com.younge.changetheelectricity.mine.view.PayView;
import com.younge.changetheelectricity.util.JsonUtil;
import com.younge.changetheelectricity.util.PayServant;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;
import com.younge.changetheelectricity.util.ToastUtil;
import com.younge.changetheelectricity.util.callback.AliPayCallBack;
import com.younge.changetheelectricity.widget.PayTypeDialog;

import org.byteam.superadapter.OnItemClickListener;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ConfirmOrderActivity extends MyBaseActivity<PayPresenter> implements PayView {

    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    @BindView(R.id.tv_price)
    TextView tv_price;
    private ConfirmOrderAdapter mAdapter;

    private List<PackageBean.ListBean> allList = new ArrayList<>();
    private PayTypeDialog payTypeDialog;
    private Double money;
    private List<PackageBean.ListBean> packageDetailList;

    @Override
    protected PayPresenter createPresenter() {
        return new PayPresenter(this);
    }

    @Override
    protected int getContentViewId(Bundle savedInstanceState) {
        return R.layout.activity_confirm_order;
    }

    @Override
    protected void init() {
        tv_center_title.setText("确认订单");

        String packageDetails = getIntent().getStringExtra("packageDetail");
        packageDetailList  = JsonUtil.parserGsonToArray(packageDetails,new TypeToken<ArrayList<PackageBean.ListBean>>(){});

        initList();
    }

    @Override
    protected void initGetData() {

    }

    @Override
    protected void widgetListener() {

    }

    private void initList(){

        allList.clear();

        allList.addAll(packageDetailList);

        mAdapter = new ConfirmOrderAdapter(this,allList,R.layout.item_comfirm_order);
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        rv_data.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {

            }
        });

        caculateMoney();
    }

    @OnClick({R.id.tv_submit,R.id.rl_fanhui_left})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.tv_submit:

                if(allList == null || allList.size() < 1){
                    ToastUtil.makeText(ConfirmOrderActivity.this,"请选择购买的套餐");
                    return;
                }

                payTypeDialog = new PayTypeDialog(ConfirmOrderActivity.this, String.valueOf(money),new PayTypeDialog.CallBack() {
                    @Override
                    public void onSubmit(final int payType, String passWord) {

                        JsonArray jsonArray = new JsonArray();
                        for(int i = 0;i < allList.size(); i ++){
                            JsonObject jsonObject = new JsonObject();

                            jsonObject.addProperty("id",allList.get(i).getId());
                            jsonObject.addProperty("num",allList.get(i).getNum());

                            jsonArray.add(jsonObject);
                        }

                        Log.e("json数据打印","========="+JsonUtil.parserObjectToGson(jsonArray)+"支付方式："+payType);
                        switch (payType){  //1钱包 2.支付宝 3.微信
                            case 1:
                                mPresenter.rechargeByWallet(String.valueOf(allList.get(0).getType()),JsonUtil.parserObjectToGson(jsonArray), (String) SharedPreferencesUtils.getParam(ConfirmOrderActivity.this,"token",""));
                                break;
                            case 2:
                                mPresenter.rechargeByAli(String.valueOf(allList.get(0).getType()),JsonUtil.parserObjectToGson(jsonArray), (String) SharedPreferencesUtils.getParam(ConfirmOrderActivity.this,"token",""));
                                break;
                            case 3:
                                mPresenter.rechargeByWechat(String.valueOf(allList.get(0).getType()),JsonUtil.parserObjectToGson(jsonArray), (String) SharedPreferencesUtils.getParam(ConfirmOrderActivity.this,"token",""));
                                break;
                        }

                    }

                    @Override
                    public void onColse() {

                    }
                });
                payTypeDialog.show();
                break;
            case R.id.rl_fanhui_left:
                finish();
                break;
        }
    }

    public void caculateMoney(){
        money = 0d;
        if(allList != null){
            for(int i = 0;i < allList.size();i++){
                money = money + allList.get(i).getText().getMoney() * allList.get(i).getNum();
            }
        }
        tv_price.setText("￥"+money+"元");

    }

    @Override
    public void onPayOrderByWallet(BaseModel<Object> data) {
        ToastUtil.makeText(this,"购买成功！");
        finish();
    }

    @Override
    public void onPayOrderByAliSuccess(BaseModel<AlipayBean> data) {
        if(data != null){
            PayServant.getInstance().aliPay(String.valueOf(data.getData().getPayparams()), ConfirmOrderActivity.this, new AliPayCallBack() {
                @Override
                public void OnAliPayResult(boolean success, boolean isWaiting, String msg) {
                    ToastUtil.makeText(ConfirmOrderActivity.this, msg);
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
                    ConfirmOrderActivity.this, wx.getAppid(),
                    wx.getPartnerid(), wx.getPrepayid(), wx.getNoncestr(),
                    wx.getTimestamp(), wx.getPackageX(), wx.getSign());

            payTypeDialog.dismiss();
            finish();
        }
    }

    @Override
    public void onGetDataFail() {
        ToastUtil.makeText(this,"购买失败！");
    }
}
