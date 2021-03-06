package com.younge.changetheelectricity.mine.presenter;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseObserver;
import com.younge.changetheelectricity.base.BasePresenter;
import com.younge.changetheelectricity.mine.bean.ShareSettingBean;
import com.younge.changetheelectricity.mine.view.ShareView;
import com.younge.changetheelectricity.net.ApiRetrofit;

import com.younge.changetheelectricity.login.view.LoginView;

public class ShareSettingPresenter extends BasePresenter<ShareView> {

    public ShareSettingPresenter(ShareView baseView) {
        super(baseView);
    }


    public void getShareSetting(String token){
        addDisposable(ApiRetrofit.getInstance().getApiService().getShareSetting("api/common/config","share",token), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onGetShareSuccess((BaseModel<ShareSettingBean>) o);
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
