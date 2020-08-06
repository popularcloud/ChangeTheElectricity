package com.younge.changetheelectricity.changetheelectricity.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;
import com.younge.changetheelectricity.changetheelectricity.Bean.ChargeStatusBean;
import com.younge.changetheelectricity.changetheelectricity.Bean.OrderResultBean;
import com.younge.changetheelectricity.main.bean.DeviceDetailBean;
import com.younge.changetheelectricity.mine.bean.PackageBean;

public interface ChargeStatusView extends BaseView {

    void onGetStatusSuccess(BaseModel<ChargeStatusBean> data);

    void onGetDataFail();
}
