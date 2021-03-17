package com.younge.changetheelectricity.main.presenter;

import android.util.Log;

import com.google.gson.JsonObject;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseObserver;
import com.younge.changetheelectricity.base.BasePresenter;
import com.younge.changetheelectricity.main.bean.ShopDetailLocationBean;
import com.younge.changetheelectricity.main.bean.UsingOrderBean;
import com.younge.changetheelectricity.main.view.MainView;
import com.younge.changetheelectricity.mine.bean.MyBatteryBean;
import com.younge.changetheelectricity.mine.bean.MyCarBean;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.net.ApiRetrofit;
import com.younge.changetheelectricity.util.JsonUtil;
import com.younge.changetheelectricity.util.ToastUtil;

public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(MainView baseView) {
        super(baseView);
    }

    public void getShopLocations(String lng,String lat){
        addDisposable(ApiRetrofit.getInstance().getApiService().getShopLocations("vv/device/api/index/index",lng,lat), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onGetShopLocationSuccess((BaseModel<ShopDetailLocationBean>) o);
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

    public void getMyPackageList(String type,String page,String token) {
        addDisposable(ApiRetrofit.getInstance().getApiService().myPackageOrder("vv/package/api/index/my",type,"","",page,"10",token), new BaseObserver(baseView) {
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
     * 获取正在进行的订单
     * @param token
     */
    public void getUsingOrder(String token) {
        addDisposable(ApiRetrofit.getInstance().getApiService().getUsingOrder("vv/order/api/index/using",token), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();

                if(o.getData().toString().length() < 3){
                    baseView.onGetUsingOrderSuccess(null);
                }else{

                    try{
                        UsingOrderBean usingOrderBean = JsonUtil.parserGsonToObject(JsonUtil.parserObjectToGson(o.getData()),UsingOrderBean.class);
                        o.setDataBean(usingOrderBean);
                        baseView.onGetUsingOrderSuccess((BaseModel<UsingOrderBean>) o);
                    }catch (Exception e){
                        //ToastUtil.makeText();
                        Log.e("报错",e.getMessage());
                    }

                }


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
