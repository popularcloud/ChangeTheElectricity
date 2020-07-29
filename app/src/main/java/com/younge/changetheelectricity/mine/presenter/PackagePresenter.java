package com.younge.changetheelectricity.mine.presenter;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseObserver;
import com.younge.changetheelectricity.base.BasePresenter;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.mine.view.PackageListView;
import com.younge.changetheelectricity.net.ApiRetrofit;

public class PackagePresenter extends BasePresenter<PackageListView> {

    public PackagePresenter(PackageListView baseView) {
        super(baseView);
    }

    public void getPackageList(String type,String page) {
        addDisposable(ApiRetrofit.getInstance().getApiService().getPackage("vv/package/api/index/index",type,page,"10"), new BaseObserver(baseView) {
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
