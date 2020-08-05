package com.younge.changetheelectricity.mine.presenter;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseObserver;
import com.younge.changetheelectricity.base.BasePresenter;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.mine.bean.PayByWechatBean;
import com.younge.changetheelectricity.mine.bean.ReturnImgUrlBean;
import com.younge.changetheelectricity.mine.view.RealNameAuthenticationCenterView;
import com.younge.changetheelectricity.mine.view.RechargeCenterView;
import com.younge.changetheelectricity.net.ApiRetrofit;

import java.util.List;

import okhttp3.MultipartBody;

/**
 * File descripition:
 *
 * @author lp
 * @date 2018/6/19
 */

public class RealNameAuthenticationPresenter extends BasePresenter<RealNameAuthenticationCenterView> {

    public RealNameAuthenticationPresenter(RealNameAuthenticationCenterView baseView) {
        super(baseView);
    }


    /**
     *实名认证
     */
    public void realNameAuthentication(String realName,String idCard,String imgFront,String imgBack,String imgHold,String token) {
            addDisposable(ApiRetrofit.getInstance().getApiService().realNameAuthentication("vv/usercenter/api/user/edit",realName,idCard,imgFront,imgBack,imgHold,token), new BaseObserver(baseView) {
                @Override
                public void onSuccess(BaseModel o) {
                    baseView.hideLoading();
                    baseView.onSubmitSuccess((BaseModel<Object>) o);
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


}
