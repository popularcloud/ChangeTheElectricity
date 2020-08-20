package com.younge.changetheelectricity.changetheelectricity.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;
import com.younge.changetheelectricity.changetheelectricity.Bean.UserHistoryBean;

public interface UserHistoryView extends BaseView {

    void onGetUserHistorySuccess(BaseModel<UserHistoryBean> data);

    void onGetDataFail();
}
