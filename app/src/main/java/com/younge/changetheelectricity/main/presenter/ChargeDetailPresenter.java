package com.younge.changetheelectricity.main.presenter;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseObserver;
import com.younge.changetheelectricity.base.BasePresenter;
import com.younge.changetheelectricity.main.bean.DeviceDetailBean;
import com.younge.changetheelectricity.main.view.ChargeDetailView;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.net.ApiRetrofit;

public class ChargeDetailPresenter extends BasePresenter<ChargeDetailView> {

    public ChargeDetailPresenter(ChargeDetailView baseView) {
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

    public void getPackageList(String type,String page) {
        addDisposable(ApiRetrofit.getInstance().getApiService().getPackage("vv/package/api/index/index",type,page,"10"), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onGetPackageSuccess((BaseModel<PackageBean>) o);
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


    public void getMyPackageList(String type,String page,String token) {
        addDisposable(ApiRetrofit.getInstance().getApiService().myPackageOrder("vv/package/api/index/my",type,"",page,"10",token), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onGetMyPackageSuccess((BaseModel<PackageBean>) o);
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
                baseView.onSubmitOrderSuccess((BaseModel<Object>) o);
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
