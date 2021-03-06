package com.younge.changetheelectricity.mine.presenter;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseObserver;
import com.younge.changetheelectricity.base.BasePresenter;
import com.younge.changetheelectricity.main.bean.BatteryInfoBean;
import com.younge.changetheelectricity.mine.view.GetBatteryInfoView;
import com.younge.changetheelectricity.net.ApiRetrofit;

public class GetBetteryInfoPresenter extends BasePresenter<GetBatteryInfoView> {

    public GetBetteryInfoPresenter(GetBatteryInfoView baseView) {
        super(baseView);
    }

    public void getBatteryInfo(String sn,String token) {
        addDisposable(ApiRetrofit.getInstance().getApiService().getBatteryInfo("vv/usercenter/api/car/battery_search",sn,token), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onGetBatteryInfoSuccess((BaseModel<BatteryInfoBean>) o);
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

    public void addBattery(String sn,String token) {
        addDisposable(ApiRetrofit.getInstance().getApiService().addBattery("vv/usercenter/api/car/battery_edit",sn,token), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onAddBatterySuccess((BaseModel) o);
            }

            @Override
            public void onError(String msg) {
                baseView.hideLoading();
                if (baseView != null) {
                    if("连接错误".equals(msg)){
                        baseView.onAddBatteryFail();
                    }else {
                        baseView.showError(msg);
                    }
                }
            }
        });
    }


}
