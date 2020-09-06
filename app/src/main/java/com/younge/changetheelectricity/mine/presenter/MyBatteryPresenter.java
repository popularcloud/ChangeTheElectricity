package com.younge.changetheelectricity.mine.presenter;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseObserver;
import com.younge.changetheelectricity.base.BasePresenter;
import com.younge.changetheelectricity.mine.bean.MyBatteryBean;
import com.younge.changetheelectricity.mine.bean.MyCarBean;
import com.younge.changetheelectricity.mine.view.MyBatteryView;
import com.younge.changetheelectricity.net.ApiRetrofit;

public class MyBatteryPresenter extends BasePresenter<MyBatteryView> {

    public MyBatteryPresenter(MyBatteryView baseView) {
        super(baseView);
    }

    public void getMyBattery(String page,String token) {
        addDisposable(ApiRetrofit.getInstance().getApiService().getMyBattery("vv/usercenter/api/car/battery",page,"10",token), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onGetBatterySuccess((BaseModel<MyBatteryBean>) o);
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


    public void delBattery(String batteryId,String token) {
        addDisposable(ApiRetrofit.getInstance().getApiService().delBattery("vv/usercenter/api/car/battery_del",batteryId,token), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onDelSuccess((BaseModel) o);
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

    public void getMyCarList(String page,String token) {
        addDisposable(ApiRetrofit.getInstance().getApiService().getMyCar("vv/usercenter/api/car/car",page,token), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onGetCarSuccess((BaseModel<MyCarBean>) o);
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

    public void carBindBattery(String batteryId,String carId,String token) {
        addDisposable(ApiRetrofit.getInstance().getApiService().carBindBattery("vv/usercenter/api/car/battery_car",batteryId,carId,token), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onCarBatteryBindSuccess((BaseModel) o);
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
