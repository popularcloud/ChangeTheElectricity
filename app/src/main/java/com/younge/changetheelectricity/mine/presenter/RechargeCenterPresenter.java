package com.younge.changetheelectricity.mine.presenter;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseObserver;
import com.younge.changetheelectricity.base.BasePresenter;
import com.younge.changetheelectricity.changetheelectricity.Bean.AlipayBean;
import com.younge.changetheelectricity.mine.bean.MyWxBean;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.mine.bean.PayByWechatBean;
import com.younge.changetheelectricity.mine.bean.RechargePackageBean;
import com.younge.changetheelectricity.mine.view.RechargeCenterView;
import com.younge.changetheelectricity.net.ApiRetrofit;
import com.younge.changetheelectricity.net.ApiServer;

import java.util.List;

/**
 * File descripition:
 *
 * @author lp
 * @date 2018/6/19
 */

public class RechargeCenterPresenter extends BasePresenter<RechargeCenterView> {

    public RechargeCenterPresenter(RechargeCenterView baseView) {
        super(baseView);
    }


    /**
     *充值套餐
     */
    public void getRechargePackage() {
            addDisposable(ApiRetrofit.getInstance().getApiService().getPackage("vv/package/api/index/index","0","1","10"), new BaseObserver(baseView) {
                @Override
                public void onSuccess(BaseModel o) {
                    baseView.hideLoading();
                    baseView.onGetDataSuccess((BaseModel<PackageBean>) o);
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

    /**
     * 充值下单
     */
    public void rechargeByWechat(String packageId,String token) {

        addDisposable(ApiRetrofit.getInstance().getApiService().rechargeOrderByWechat("vv/recharge/api/recharge/submit","0",packageId,"wechat","app",token), new BaseObserver(baseView) {
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

    public void rechargeByAli(String packageId,String toke) {

        addDisposable(ApiRetrofit.getInstance().getApiService().rechargeOrderByAli("vv/recharge/api/recharge/submit","0",packageId,"alipay","app",toke), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                    baseView.onPayOrderByAliSuccess((BaseModel<AlipayBean>) o);
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
