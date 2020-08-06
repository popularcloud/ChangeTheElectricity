package com.younge.changetheelectricity.main.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;
import com.younge.changetheelectricity.changetheelectricity.Bean.OrderResultBean;
import com.younge.changetheelectricity.main.bean.DeviceDetailBean;
import com.younge.changetheelectricity.mine.bean.PackageBean;

public interface ChargeDetailView extends BaseView {

    void onGetDeviceDetailSuccess(BaseModel<DeviceDetailBean> data);

    void onGetPackageSuccess(BaseModel<PackageBean> data);

    void onGetMyPackageSuccess(BaseModel<PackageBean> data);

    void onSubmitOrderSuccess(BaseModel<OrderResultBean> data);

    void onGetDataFail();
}
