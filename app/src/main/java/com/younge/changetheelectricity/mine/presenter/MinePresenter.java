package com.younge.changetheelectricity.mine.presenter;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseObserver;
import com.younge.changetheelectricity.base.BasePresenter;
import com.younge.changetheelectricity.mine.bean.PageInfoBean;
import com.younge.changetheelectricity.mine.bean.UserInfoBean;
import com.younge.changetheelectricity.mine.view.MineView;
import com.younge.changetheelectricity.net.ApiRetrofit;

public class MinePresenter extends BasePresenter<MineView> {

    public MinePresenter(MineView baseView) {
        super(baseView);
    }

    public void getPersonalInfo(String token){
        addDisposable(ApiRetrofit.getInstance().getApiService().getPersonalInfo("vv/usercenter/api/user/profile",token), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onGetDataSuccess((BaseModel<UserInfoBean>) o);
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

    public void aboutUs(String token){
        addDisposable(ApiRetrofit.getInstance().getApiService().aboutUs("vv/cms/api/my/page","about",token), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onGetAboutUsSuccess((BaseModel<PageInfoBean>) o);
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
