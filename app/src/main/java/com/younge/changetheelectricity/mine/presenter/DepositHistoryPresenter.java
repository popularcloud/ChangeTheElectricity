package com.younge.changetheelectricity.mine.presenter;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseObserver;
import com.younge.changetheelectricity.base.BasePresenter;
import com.younge.changetheelectricity.mine.bean.DepositHistoryBean;
import com.younge.changetheelectricity.mine.view.DepositHistoryView;
import com.younge.changetheelectricity.net.ApiRetrofit;

public class DepositHistoryPresenter extends BasePresenter<DepositHistoryView> {

    public DepositHistoryPresenter(DepositHistoryView baseView) {
        super(baseView);
    }

    public void getBatteryInfo(String type,String page,String size,String token) {
        addDisposable(ApiRetrofit.getInstance().getApiService().getDepositHistory("vv/recharge/api/recharge/moneylog",type,page,size,token), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onGetDepositHistorySuccess((BaseModel<DepositHistoryBean>) o);
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
