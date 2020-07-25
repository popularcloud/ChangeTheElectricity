package com.younge.changetheelectricity.main.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;
import com.younge.changetheelectricity.main.bean.ShopDetailBean;

public interface MainView extends BaseView {

    void onGetShopLocationSuccess(BaseModel<ShopDetailBean> data);

    void onGetDataFail();
}
