package com.younge.changetheelectricity.main.presenter;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseObserver;
import com.younge.changetheelectricity.base.BasePresenter;
import com.younge.changetheelectricity.main.bean.DeviceDetailBean;
import com.younge.changetheelectricity.main.view.DeviceDetailView;
import com.younge.changetheelectricity.net.ApiRetrofit;

public class DeviceDetailPresenter extends BasePresenter<DeviceDetailView> {

    public DeviceDetailPresenter(DeviceDetailView baseView) {
        super(baseView);
    }

    public void getDeviceDetail(String lng,String lat,String macno,String token){
        addDisposable(ApiRetrofit.getInstance().getApiService().getDeviceDetail("vv/device/api/index/show",lng,lat,macno,token), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onGetDeviceDetailSuccess((BaseModel<DeviceDetailBean>) o);
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
