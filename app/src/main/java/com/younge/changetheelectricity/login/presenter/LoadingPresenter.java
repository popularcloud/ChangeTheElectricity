package com.younge.changetheelectricity.login.presenter;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseObserver;
import com.younge.changetheelectricity.base.BasePresenter;
import com.younge.changetheelectricity.net.ApiRetrofit;

import java.util.List;

import com.younge.changetheelectricity.login.bean.LoadingImgBean;
import com.younge.changetheelectricity.login.view.LoadingView;

public class LoadingPresenter extends BasePresenter<LoadingView> {

    public LoadingPresenter(LoadingView baseView) {
        super(baseView);
    }

    public void getLoadingImage(){
        addDisposable(ApiRetrofit.getInstance().getApiService().getLoadingBanner("vv/cms/api/index/banner"), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onGetDataSuccess((BaseModel<List<LoadingImgBean>>) o);
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
