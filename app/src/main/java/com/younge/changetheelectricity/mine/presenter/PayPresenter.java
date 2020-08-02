package com.younge.changetheelectricity.mine.presenter;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseObserver;
import com.younge.changetheelectricity.base.BasePresenter;
import com.younge.changetheelectricity.mine.bean.PayByWechatBean;
import com.younge.changetheelectricity.mine.view.PayView;
import com.younge.changetheelectricity.net.ApiRetrofit;

public class PayPresenter extends BasePresenter<PayView> {

    public PayPresenter(PayView baseView) {
        super(baseView);
    }

    /**
     * 充值下单
     */
    public void rechargeByWechat(String rechargeType,String goods,String token) {

        addDisposable(ApiRetrofit.getInstance().getApiService().PackageOrderByWechat("vv/cms/api/archives/order","",rechargeType,goods,"wechat","app",token), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onPayOrderByWeChatSuccess((BaseModel<PayByWechatBean>) o);
            }

            @Override
            public void onError(String msg) {
                baseView.hideLoading();
                if (baseView != null) {
                    if("连接错误".equals(msg)){
                        baseView.onGetDataFail();
                    }else {
                        baseView.showError(msg);
                    }
                }
            }
        });
    }

    public void rechargeByAli(String rechargeType,String goods,String token) {

        addDisposable(ApiRetrofit.getInstance().getApiService().PackageOrderByAli("vv/cms/api/archives/order","",rechargeType,goods,"alipay","app",token), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onPayOrderByAliSuccess((BaseModel<Object>) o);
            }

            @Override
            public void onError(String msg) {
                baseView.hideLoading();
                if (baseView != null) {
                    if("连接错误".equals(msg)){
                        baseView.onGetDataFail();
                    }else {
                        baseView.showError(msg);
                    }
                }
            }
        });
    }

    public void rechargeByWallet(String rechargeType,String goods,String token) {

        addDisposable(ApiRetrofit.getInstance().getApiService().PackageOrderByWallet("vv/cms/api/archives/order","",rechargeType,goods,"money","",token), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onPayOrderByAliSuccess((BaseModel<Object>) o);
            }

            @Override
            public void onError(String msg) {
                baseView.hideLoading();
                if (baseView != null) {
                    if("连接错误".equals(msg)){
                        baseView.onGetDataFail();
                    }else {
                        baseView.showError(msg);
                    }
                }
            }
        });
    }

}
