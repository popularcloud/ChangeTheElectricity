package com.younge.changetheelectricity.main.presenter;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseObserver;
import com.younge.changetheelectricity.base.BasePresenter;
import com.younge.changetheelectricity.main.bean.ShopDetailLocationBean;
import com.younge.changetheelectricity.main.view.MainView;
import com.younge.changetheelectricity.mine.bean.MyCarBean;
import com.younge.changetheelectricity.net.ApiRetrofit;

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


}
