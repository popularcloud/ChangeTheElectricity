package com.younge.changetheelectricity.mine.presenter;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseObserver;
import com.younge.changetheelectricity.base.BasePresenter;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.mine.bean.PackageDetailBean;
import com.younge.changetheelectricity.mine.view.PackageDetailView;
import com.younge.changetheelectricity.mine.view.PackageListView;
import com.younge.changetheelectricity.net.ApiRetrofit;

public class PackageDetailPresenter extends BasePresenter<PackageDetailView> {

    public PackageDetailPresenter(PackageDetailView baseView) {
        super(baseView);
    }

    public void getPackageDtail(String packageId) {
        addDisposable(ApiRetrofit.getInstance().getApiService().getPackageDetail("vv/package/api/index/show",packageId), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onGetDataSuccess((BaseModel<PackageDetailBean>) o);
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
