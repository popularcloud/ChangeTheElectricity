package com.younge.changetheelectricity.login.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;

import java.util.List;

import com.younge.changetheelectricity.login.bean.LoadingImgBean;

public interface LoadingView extends BaseView {

    void onGetDataSuccess(BaseModel<List<LoadingImgBean>> data);

    void onGetDataFail();
}
