package com.younge.changetheelectricity.main.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;
import com.younge.changetheelectricity.main.bean.DeviceDetailBean;

public interface DeviceDetailView extends BaseView {

    void onGetDeviceDetailSuccess(BaseModel<DeviceDetailBean> data);

    void onGetDataFail();
}
