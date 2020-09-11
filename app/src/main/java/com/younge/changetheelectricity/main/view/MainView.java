package com.younge.changetheelectricity.main.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;
import com.younge.changetheelectricity.main.bean.ShopDetailLocationBean;
import com.younge.changetheelectricity.main.bean.UsingOrderBean;
import com.younge.changetheelectricity.mine.bean.MyBatteryBean;
import com.younge.changetheelectricity.mine.bean.MyCarBean;
import com.younge.changetheelectricity.mine.bean.PackageBean;

public interface MainView extends BaseView {

    void onGetShopLocationSuccess(BaseModel<ShopDetailLocationBean> data);

    void onGetBatterySuccess(BaseModel<MyBatteryBean> data);

    void onGetCarSuccess(BaseModel<MyCarBean> data);

    void onGetUsingOrderSuccess(BaseModel<UsingOrderBean> data);

    void onGetMyPackageSuccess(BaseModel<PackageBean> data);

    void onGetDataFail();
}
