package com.younge.changetheelectricity.changetheelectricity.presenter;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseObserver;
import com.younge.changetheelectricity.base.BasePresenter;
import com.younge.changetheelectricity.changetheelectricity.Bean.ChargeStatusBean;
import com.younge.changetheelectricity.changetheelectricity.view.ChargeStatusView;
import com.younge.changetheelectricity.net.ApiRetrofit;

public class ChargeStatusPresenter extends BasePresenter<ChargeStatusView> {

    public ChargeStatusPresenter(ChargeStatusView baseView) {
        super(baseView);
    }

    public void getOrderStatus(String orderId,String token){
        addDisposable(ApiRetrofit.getInstance().getApiService().getOrderStatus("vv/order/api/index/timer",orderId,token), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onGetStatusSuccess((BaseModel<ChargeStatusBean>) o);
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
