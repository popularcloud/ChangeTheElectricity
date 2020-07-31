package com.younge.changetheelectricity.main.presenter;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseObserver;
import com.younge.changetheelectricity.base.BasePresenter;
import com.younge.changetheelectricity.main.bean.ShopDetailBean;
import com.younge.changetheelectricity.main.view.ShopDetailView;
import com.younge.changetheelectricity.net.ApiRetrofit;

public class ShopDetailPresenter extends BasePresenter<ShopDetailView> {

    public ShopDetailPresenter(ShopDetailView baseView) {
        super(baseView);
    }

    public void getShopLocations(String lng,String lat,String macno,String token){
        addDisposable(ApiRetrofit.getInstance().getApiService().getShopDetail("vv/agent/api/index/seller_show",lng,lat,macno,token), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onGetShopDetailSuccess((BaseModel<ShopDetailBean>) o);
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
