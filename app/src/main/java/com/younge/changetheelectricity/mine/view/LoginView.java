package com.younge.changetheelectricity.mine.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;

import com.younge.changetheelectricity.login.bean.LoginBean;

public interface LoginView extends BaseView {

    void onLoginSuccess(BaseModel<LoginBean> data);

    void ongetCodeSuccess(BaseModel<Object> data);

    void onGetDataFail();
}
