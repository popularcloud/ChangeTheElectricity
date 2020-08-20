package com.younge.changetheelectricity.changetheelectricity.presenter;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseObserver;
import com.younge.changetheelectricity.base.BasePresenter;
import com.younge.changetheelectricity.changetheelectricity.Bean.UserHistoryBean;
import com.younge.changetheelectricity.changetheelectricity.view.UserHistoryView;
import com.younge.changetheelectricity.net.ApiRetrofit;

public class UserHistoryPresenter extends BasePresenter<UserHistoryView> {

    public UserHistoryPresenter(UserHistoryView baseView) {
        super(baseView);
    }

    public void getUserHistory(String page,String token){
        addDisposable(ApiRetrofit.getInstance().getApiService().getUserHistory("vv/package/api/index/log","","",page,"10",token), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onGetUserHistorySuccess((BaseModel<UserHistoryBean>) o);
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
