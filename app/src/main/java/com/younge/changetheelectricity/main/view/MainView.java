package com.younge.changetheelectricity.main.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;
import com.younge.changetheelectricity.main.bean.ShopDetailLocationBean;

public interface MainView extends BaseView {

    void onGetShopLocationSuccess(BaseModel<ShopDetailLocationBean> data);

    void onGetDataFail();
}
