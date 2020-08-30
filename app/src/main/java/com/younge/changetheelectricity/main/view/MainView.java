package com.younge.changetheelectricity.main.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;
import com.younge.changetheelectricity.main.bean.ShopDetailLocationBean;
import com.younge.changetheelectricity.mine.bean.MyCarBean;

public interface MainView extends BaseView {

    void onGetShopLocationSuccess(BaseModel<ShopDetailLocationBean> data);

    void onGetCarSuccess(BaseModel<MyCarBean> data);

    void onGetDataFail();
}
