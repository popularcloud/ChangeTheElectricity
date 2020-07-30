package com.younge.changetheelectricity.mine.presenter;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseObserver;
import com.younge.changetheelectricity.base.BasePresenter;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.mine.view.PackageListView;
import com.younge.changetheelectricity.net.ApiRetrofit;

public class MyPackagePresenter extends BasePresenter<PackageListView> {

    public MyPackagePresenter(PackageListView baseView) {
        super(baseView);
    }

    public void getMyPackageList(String page,String token) {
        addDisposable(ApiRetrofit.getInstance().getApiService().myPackageOrder("vv/package/api/index/my","","",page,"10",token), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onGetDataSuccess((BaseModel<PackageBean>) o);
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
