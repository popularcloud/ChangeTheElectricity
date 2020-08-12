package com.younge.changetheelectricity.main.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;
import com.younge.changetheelectricity.changetheelectricity.Bean.OrderResultBean;
import com.younge.changetheelectricity.changetheelectricity.Bean.StartResultBean;
import com.younge.changetheelectricity.main.bean.DeviceDetailBean;

public interface DeviceDetailView extends BaseView {

    void onGetDeviceDetailSuccess(BaseModel<DeviceDetailBean> data);

    void onOrderSuccess(BaseModel<OrderResultBean> data);

    void onStartSuccess(BaseModel<StartResultBean> data);

    void onGetDataFail();
}
