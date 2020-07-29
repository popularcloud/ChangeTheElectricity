package com.younge.changetheelectricity.mine.presenter;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseObserver;
import com.younge.changetheelectricity.base.BasePresenter;
import com.younge.changetheelectricity.mine.bean.MyCarBean;
import com.younge.changetheelectricity.mine.view.MyCarView;
import com.younge.changetheelectricity.net.ApiRetrofit;

public class MyCarPresenter extends BasePresenter<MyCarView> {

    public MyCarPresenter(MyCarView baseView) {
        super(baseView);
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
