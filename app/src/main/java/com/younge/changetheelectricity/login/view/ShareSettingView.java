package com.younge.changetheelectricity.login.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;

import com.younge.changetheelectricity.login.bean.LoginBean;

public interface ShareSettingView extends BaseView {

    void onLoginSuccess(BaseModel<LoginBean> data);

    void ongetCodeSuccess(BaseModel<Object> data);

    void onUplaod(BaseModel<Object> data);

    void onGetDataFail();
}
