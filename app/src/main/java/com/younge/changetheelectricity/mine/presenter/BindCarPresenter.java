package com.younge.changetheelectricity.mine.presenter;

import com.google.gson.JsonObject;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseObserver;
import com.younge.changetheelectricity.base.BasePresenter;
import com.younge.changetheelectricity.mine.bean.ReturnImgUrlBean;
import com.younge.changetheelectricity.mine.view.BindCarView;
import com.younge.changetheelectricity.net.ApiRetrofit;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;

import java.util.List;

import okhttp3.MultipartBody;

public class BindCarPresenter extends BasePresenter<BindCarView> {

    public BindCarPresenter(BindCarView baseView) {
        super(baseView);
    }

    public void uploadPicToService(List<MultipartBody.Part> partList) {
        addDisposable(ApiRetrofit.getInstance().getApiService().upLoadPic(partList), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onUploadPicSuccess((BaseModel<ReturnImgUrlBean>) o);
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

    public void addCar(String carvin, String serial,String carno,String picfront,String picback,String picleft,String picright,String token) {
        addDisposable(ApiRetrofit.getInstance().getApiService().addCar("vv/usercenter/api/car/car_edit",carvin,serial,carno,picfront,picback,picleft,picright, token), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onAddCarSuccess((BaseModel<Object>) o);
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
