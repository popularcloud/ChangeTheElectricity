package com.younge.changetheelectricity.main.presenter;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseObserver;
import com.younge.changetheelectricity.base.BasePresenter;
import com.younge.changetheelectricity.changetheelectricity.Bean.OrderResultBean;
import com.younge.changetheelectricity.changetheelectricity.Bean.StartResultBean;
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

    /**
     *
     * @param macno
     * @param device_box
     * @param goods_type 0换电 1充电
     * @param order_type 1预约 0其它
     * @param package_id 套餐id
     * @param package_user_id 已够套餐id
     * @param token
     */
    public void submitOrder(String macno,String device_box,String goods_type,String order_type,String package_id,String package_user_id,String token) {
        addDisposable(ApiRetrofit.getInstance().getApiService().submitOrder("vv/order/api/index/submit",macno,device_box,goods_type,order_type,package_id,package_user_id,token), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onOrderSuccess((BaseModel<OrderResultBean>) o);
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

    public void start(String act,String token) {
        addDisposable(ApiRetrofit.getInstance().getApiService().start("vv/order/api/index/start",act,token), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onStartSuccess((BaseModel<StartResultBean>) o);
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
