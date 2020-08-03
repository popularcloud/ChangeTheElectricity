package com.younge.changetheelectricity.mine.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;
import com.younge.changetheelectricity.main.bean.BatteryInfoBean;
import com.younge.changetheelectricity.mine.bean.ReturnImgUrlBean;

public interface GetBatteryInfoView extends BaseView {

    void onGetBatteryInfoSuccess(BaseModel<BatteryInfoBean> data);

    void onAddBatterySuccess(BaseModel<Object> data);

    void onAddBatteryFail();

    void onGetDataFail();
}
