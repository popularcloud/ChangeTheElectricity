package com.younge.changetheelectricity.mine.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;
import com.younge.changetheelectricity.mine.bean.MyBatteryBean;
import com.younge.changetheelectricity.mine.bean.MyCarBean;

public interface MyCarView extends BaseView{


    void onGetCarSuccess(BaseModel<MyCarBean> data);

    void onGetBatterySuccess(BaseModel<MyBatteryBean> data);

    void onCarBatteryBindSuccess(BaseModel data);

    void onSetCarDefault(BaseModel data);

    void onDelSuccess(BaseModel data);

    void onGetDataFail();
}
